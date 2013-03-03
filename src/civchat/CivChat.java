package civchat;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;


public class CivChat extends JavaPlugin
{
	private static Logger log;
	
	public void onEnable() 
	{
		log = this.getLogger();
		log.info("CivChat has been enabled.");
	}
	
	public void onDisable()
	{
		log.info("CivChat has been disabled");
	}
}
