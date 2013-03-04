package civchat.manager;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import civchat.CivChat;

public class ConfigManager 
{
	private CivChat plugin;
	
	private String driver;
	private String url;
	private String username;
	private String password;
	private boolean isolation;
	private boolean logging;
	private boolean rebuild;
	
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
        
        driver    = loadString("database.driver");
        url       = loadString("database.url");
        username  = loadString("database.username");
        password  = loadString("database.password");
        isolation = loadBoolean("database.isolation");
        logging   = loadBoolean("database.logging");
        rebuild   = loadBoolean("database.rebuild");
        
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

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isIsolation() {
		return isolation;
	}

	public void setIsolation(boolean isolation) {
		this.isolation = isolation;
	}

	public boolean isLogging() {
		return logging;
	}

	public void setLogging(boolean logging) {
		this.logging = logging;
	}

	public boolean isRebuild() {
		return rebuild;
	}

	public void setRebuild(boolean rebuild) {
		this.rebuild = rebuild;
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

	public void setPlugin(CivChat plugin) {
		this.plugin = plugin;
	}

	
    
}
