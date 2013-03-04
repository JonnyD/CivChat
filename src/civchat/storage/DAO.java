package civchat.storage;

import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import civchat.CivChat;
import civchat.manager.ConfigManager;

public class DAO extends DB
{	
	private CivChat plugin;
	public DAO() {
        super(CivChat.getInstance());
        
        plugin = CivChat.getInstance();
        
        ConfigManager configManager = plugin.getConfigManager();
        String driver = configManager.getDriver();
        String url = configManager.getUrl();
        String username = configManager.getUsername();
        String password = configManager.getPassword();
        String isolation = configManager.getIsolation();
        boolean logging = configManager.isLogging();
        boolean rebuild = configManager.isRebuild();
        
        super.initializeDatabase(
        		driver, url, username, password, isolation, logging, rebuild
        );
    }

    @Override
    protected List<Class<?>> getDatabaseClasses() {
        return Arrays.asList();
    }

    public Object save(Object object) {
        getDatabase().save(object);
        return object;
    }

    public void delete(Object object) {
        getDatabase().delete(object);
    }
}
