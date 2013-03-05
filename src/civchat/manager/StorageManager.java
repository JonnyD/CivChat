package civchat.manager;

import java.util.logging.Logger;

import civchat.CivChat;
import civchat.storage.DB;
import civchat.storage.MySQL;

public class StorageManager 
{
	private DB db;
	private CivChat plugin;
	private ConfigManager configManager;
	private Logger log;
	
	public StorageManager()
	{
		plugin = CivChat.getInstance();
		configManager = plugin.getConfigManager();
		log = CivChat.getLog();
		
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
			
			if(!db.existsTable("cc_antenna"))
			{
				log.info("Creating table: cc_antenna");
				String query = "CREATE TABLE IF NOT EXISTS 'cc_antenna' ('id' integer NOT NULL auto_increment, 'x' integer NOT NULL, 'y' integer NOT NULL, 'z' integer NOT NULL, 'owner_id' integer NOT NULL);";
				db.execute(query);
			}
		}
	}
}
