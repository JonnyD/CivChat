package civchat.manager;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import civchat.CivChat;

public class ConfigManager 
{

	private CivChat plugin;
	
	private String host;
	private String database;
	private String username;
	private String password;
	private int port;
	
	private File main;
	private FileConfiguration config;
	private FileConfiguration cleanConfig;
	
	public ConfigManager()
	{
		this.plugin = CivChat.getInstance();
		this.config = plugin.getConfig();
		this.cleanConfig = new YamlConfiguration();
		this.main = new File(plugin.getDataFolder() + File.separator + "config.yml");
		this.load();
	}
	
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
        
        host = loadString("mysql.localhost");
        port = loadInt("mysql.port");
        database = loadString("mysql.database");
        username = loadString("mysql.username");
        password = loadString("mysql.password");
        
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

	public String getHost() {
		return host;
	}

	public String getDatabase() {
		return database;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public int getPort() {
		return port;
	}

	public File getMain() {
		return main;
	}

	public FileConfiguration getConfig() {
		return config;
	}

	public FileConfiguration getCleanConfig() {
		return cleanConfig;
	}
    
    
}
