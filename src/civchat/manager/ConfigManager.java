package civchat.manager;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import civchat.CivChat;

public class ConfigManager 
{
	private CivChat plugin;
	
	private String username;
	private String host;
	private String password;
	private String database;
	private int port;
	
	private Set<Material> antennaMaterials;
	
	private File main;
	private FileConfiguration config;
	private FileConfiguration cleanConfig;
	
	public ConfigManager()
	{
		this.plugin            = CivChat.getInstance();
		this.config            = plugin.getConfig();
		this.cleanConfig       = new YamlConfiguration();
		this.main              = new File(plugin.getDataFolder() + File.separator + "config.yml");
		this.antennaMaterials  = new LinkedHashSet<Material>();
		this.load();
	}
	
	/**
	 * Load configuration
	 */
	private void load()
	{
		boolean exists = main.exists();

        if (exists)
        {
            try
            {
                config.options().copyDefaults(true);
                config.load(main);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            config.options().copyDefaults(true);
        }
        
        username = loadString("mysql.username");
        host     = loadString("mysql.host");
        password = loadString("mysql.password");
        database = loadString("mysql.database");
        port     = loadInt("mysql.port");
        
        for(String m : loadStringList("antenna.materials"))
        {
        	Material material = Material.matchMaterial(m.toString());
        	antennaMaterials.add(material);
        }
        
        save();
	}
	
	private Boolean loadBoolean(String path)
    {
        if (config.isBoolean(path))
        {
            boolean value = config.getBoolean(path);
            cleanConfig.set(path, value);
            return value;
        }
        return false;
    }

    private String loadString(String path)
    {
        if (config.isString(path))
        {
            String value = config.getString(path);
            cleanConfig.set(path, value);
            return value;
        }

        return "";
    }

    private int loadInt(String path)
    {
        if (config.isInt(path))
        {
            int value = config.getInt(path);
            cleanConfig.set(path, value);
            return value;
        }

        return 0;
    }

    private double loadDouble(String path)
    {
        if (config.isDouble(path))
        {
            double value = config.getDouble(path);
            cleanConfig.set(path, value);
            return value;
        }

        return 0;
    }
    
    private List<String> loadStringList(String path)
    {
    	if(config.isList(path))
    	{
    		List<String> value = config.getStringList(path);
    		cleanConfig.set(path, value);
    		return value;
    	}
    	
    	return null;
    }
    
    public void save()
    {
        try
        {
            cleanConfig.save(main);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

	public CivChat getPlugin() {
		return plugin;
	}

	public void setPlugin(CivChat plugin) {
		this.plugin = plugin;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public File getMain() {
		return main;
	}

	public void setMain(File main) {
		this.main = main;
	}

	public FileConfiguration getConfig() {
		return config;
	}

	public void setConfig(FileConfiguration config) {
		this.config = config;
	}

	public FileConfiguration getCleanConfig() {
		return cleanConfig;
	}

	public void setCleanConfig(FileConfiguration cleanConfig) {
		this.cleanConfig = cleanConfig;
	}

	public Set<Material> getAntennaMaterials() {
		return antennaMaterials;
	}

	public void setAntennaMaterials(Set<Material> antennaMaterials) {
		this.antennaMaterials = antennaMaterials;
	}    
	
}
