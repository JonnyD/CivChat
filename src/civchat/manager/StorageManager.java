package civchat.manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import org.bukkit.Location;

import civchat.CivChat;
import civchat.model.Antenna;
import civchat.model.Antenna.DirtyAntennaReason;
import civchat.model.MobilePhone;
import civchat.model.MobilePhone.DirtyMobilePhoneReason;
import civchat.model.Network;
import civchat.model.Network.DirtyNetworkReason;
import civchat.storage.DB;
import civchat.storage.MySQL;
import civchat.utility.Utility;

public class StorageManager 
{
	private DB db;
	private CivChat plugin;
	private ConfigManager configManager;
	private Logger log;

	public StorageManager()
	{
		plugin        = CivChat.getInstance();
		configManager = plugin.getConfigManager();
		log           = CivChat.getLog();

		initiateDB();
	}

	private void initiateDB()
	{
		String host     = configManager.getHost();
		String database = configManager.getDatabase();
		String username = configManager.getUsername();
		String password = configManager.getPassword();
		int port        = configManager.getPort();

		db = new MySQL(host, port, database, username, password);

		if(db.checkConnection())
		{
			log.info("dbMysqlConnected");

			if(!db.existsTable("cc_network"))
			{
				log.info("Creating table: cc_network");
				String query = "CREATE TABLE IF NOT EXISTS `cc_network` (`id` integer NOT NULL auto_increment, `name` varchar(100), `owner` varchar(100), PRIMARY KEY(`id`));";
				db.execute(query);
			}

			if(!db.existsTable("cc_handset"))
			{
				log.info("Creating table: cc_handset");
				String query = "CREATE TABLE IF NOT EXISTS `cc_handset` (`id` integer NOT NULL auto_increment, `network_id` integer, `owner` varchar(100), PRIMARY KEY(`id`), FOREIGN KEY (`network_id`) REFERENCES cc_network (`id`));";
				db.execute(query);
			}

			if(!db.existsTable("cc_antenna"))
			{
				log.info("Creating table: cc_antenna");
				String query = "CREATE TABLE IF NOT EXISTS `cc_antenna` (`id` integer NOT NULL auto_increment, `x` integer NOT NULL, `y` integer NOT NULL, `z` integer NOT NULL, `world` varchar(30) NOT NULL, `owner` varchar(100) NOT NULL, `damaged` TINYINT(1) DEFAULT 0, `network_id` integer, PRIMARY KEY(`id`), FOREIGN KEY (`network_id`) REFERENCES cc_network (`id`));";
				db.execute(query);
			}
		}
	}

	public void insertAntenna(Antenna antenna)
	{
		String query  = "INSERT INTO `cc_antenna` (`x`, `y`, `z`, `world`, `owner`, `network_id`)";
		String values = "VALUES (" + antenna.getX() + ", " + antenna.getY() + ", " + antenna.getZ() + ", '" + antenna.getWorld() + "', '" + antenna.getOwner() + "', " + antenna.getNetworkId() + ")";	

		synchronized (this)
		{
			db.insert(query + values);
		}
	}

	public void updateAntenna(Antenna antenna)
	{
		String subQuery = "";

		if(antenna.isDirty(DirtyAntennaReason.OWNER))
		{
			subQuery += "owner = " + antenna.getOwner() + ", ";
		}
		if(antenna.isDirty(DirtyAntennaReason.NETWORK))
		{
			subQuery += "network_id = " + antenna.getNetworkId() + ", ";
		}
		if(antenna.isDirty(DirtyAntennaReason.DAMAGED))
		{
			subQuery += "damaged = " + antenna.isDamaged() + ", ";
		}

		if (!subQuery.isEmpty())
		{
			String query = "UPDATE `cc_antenna` SET " + Utility.stripTrailingComma(subQuery) + " WHERE x = " + antenna.getX() + " AND y = " + antenna.getY() + " AND z = " + antenna.getZ() + " AND world = '" + antenna.getWorld() + "';";
			db.execute(query);
		}

		antenna.clearDirty();
	}

	public Antenna findAntenna(Location location)
	{
		Antenna antenna = null;

		String query  = "SELECT * FROM `cc_antenna` WHERE x = " + (int) location.getX() + " AND y = " + (int) location.getY() + " AND z = " + (int) location.getZ() + " AND world = '" + location.getWorld().getName() + "';";
		ResultSet res = db.select(query);
		if(res != null)
		{
			try
			{
				while(res.next())
				{
					try {
						int id          = res.getInt("id");
						int x           = res.getInt("x");
						int y           = res.getInt("y");
						int z           = res.getInt("z");
						String world    = res.getString("world");
						String owner    = res.getString("owner");
						boolean damaged = res.getBoolean("damaged");
						int networkId   = res.getInt("network_id");

						antenna = new Antenna(id, x, y, z, world, owner, damaged, networkId);
					}
					catch (Exception ex)
					{
						log.info(ex.getMessage());
					}
				}
			}
			catch (SQLException ex)
			{
				log.severe(ex.getMessage());
			}
		}

		return antenna;
	}
	
	public Set<Antenna> findAntennasNear(Location location)
	{
		Set<Antenna> out = new HashSet<Antenna>();
		
		int locX        = (int) location.getX();
		int locY        = (int) location.getY();
		int locZ        = (int) location.getZ();
		String locWorld = location.getWorld().getName();
		int radius      = 20;
		
		String query = "SELECT * FROM cc_antenna WHERE ((x-(" + locX + "))*(x-(" + locX + "))+(y-(" + locY + "))*(y-(" + locY + "))+(z-(" + locZ + "))*(z-(" + locZ + ")) < " + radius*radius + ") AND world = '" + locWorld + "'";
		
		ResultSet res = db.select(query);
		if(res != null)
		{
			try
			{
				while(res.next())
				{
					try
					{
						int id          = res.getInt("id");
						int x           = res.getInt("x");
						int y           = res.getInt("y");
						int z           = res.getInt("z");
						String world    = res.getString("world");
						String owner    = res.getString("owner");
						boolean damaged = res.getBoolean("damaged");
						int networkId   = res.getInt("network_id");

						Antenna antenna = new Antenna(id, x, y, z, world, owner, damaged, networkId);
						out.add(antenna);
					}
					catch (Exception ex)
					{
						ex.printStackTrace();
					}
				}
			}
			catch (SQLException ex)
			{
				ex.printStackTrace();
			}
		}
		
		return out;
	}

	public void insertNetwork(Network network)
	{
		String query  = "INSERT INTO `cc_network` (`name`, `owner`)";
		String values = "VALUES ('" + network.getName() + "', '" + network.getOwner() + "')";

		synchronized (this)
		{
			db.insert(query + values);
		}
	}

	public void updateNetwork(Network network)
	{
		String subQuery = "";

		if(network.isDirty(DirtyNetworkReason.OWNER))
		{
			subQuery += "owner = " + network.getOwner() + ", ";
		}

		if(!subQuery.isEmpty())
		{
			String query = "UPDATE `cc_network` SET " + Utility.stripTrailingComma(subQuery) + " WHERE name = " + network.getName();
			db.execute(query);
		}

		network.clearDirty();
	}

	public Network findNetwork(String n)
	{
		Network network = null;

		String query  = "SELECT * FROM `cc_network` WHERE name = '" + n + "'";
		ResultSet res = db.select(query);
		if(res != null)
		{
			try
			{
				while(res.next())
				{
					try 
					{
						int id       = res.getInt("id");
						String name  = res.getString("name");
						String owner = res.getString("owner");

						network = new Network(id, name, owner);
					}
					catch (Exception ex)
					{
						log.info(ex.getMessage());
					}
				}
			}
			catch (SQLException ex)
			{
				log.severe(ex.getMessage());
			}
		}

		return network;
	}

	public int insertMobilePhone(MobilePhone phone)
	{
		String query  = "INSERT INTO `cc_handset` (`owner`)";
		String values = "VALUES ('" + phone.getOwner() + "')";
		
		int id = 0;
		
		synchronized(this)
		{
			id = (int) db.insert(query + values);
		}
		
		return id;
	}

	public void updateMobilePhone(MobilePhone phone)
	{
		String subQuery = "";

		if(phone.isDirty(DirtyMobilePhoneReason.NETWORK))
		{
			subQuery += "network_id = " + phone.getNetworkId() + ", ";
		}

		if(!subQuery.isEmpty())
		{
			String query = "UPDATE `cc_handset` SET " + Utility.stripTrailingComma(subQuery) + " WHERE id = " + phone.getId();
			db.execute(query);
		}
		
		phone.clearDirty();
	}

	public MobilePhone findMobilePhoneByOwner(String o)
	{
		MobilePhone mobilePhone = null;

		String query = "SELECT * FROM `cc_handset` WHERE owner = '" + o + "'";
		ResultSet res = db.select(query);
		if(res != null)
		{
			try
			{
				while(res.next())
				{
					try 
					{
						int id         = res.getInt("id");
						int networkId  = res.getInt("network_id");
						String owner   = res.getString("owner");

						mobilePhone = new MobilePhone(id, networkId, owner);
					}
					catch (Exception ex)
					{
						log.info(ex.getMessage());
					}
				}
			}
			catch (SQLException ex)
			{
				log.severe(ex.getMessage());
			}
		}

		return mobilePhone;
	}

	public MobilePhone findMobilePhoneById(int id)
	{
		MobilePhone mobilePhone = null;

		String query = "SELECT * FROM `cc_handset` WHERE id = " + id;
		ResultSet res = db.select(query);
		if(res != null)
		{
			try
			{
				while(res.next())
				{
					try 
					{
						int networkId = res.getInt("network_id");
						String owner  = res.getString("owner");

						mobilePhone = new MobilePhone(id, networkId, owner);
					}
					catch (Exception ex)
					{
						log.info(ex.getMessage());
					}
				}
			}
			catch (SQLException ex)
			{
				log.severe(ex.getMessage());
			}
		}

		return mobilePhone;
	}
	
	public void deleteMobilePhone(int id)
	{
		String query = "DELETE FROM cc_handset WHERE Id = " + id;
		
		synchronized (this)
		{
			db.delete(query);
		}
	}
}
