package civchat;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;


public class CivChat extends JavaPlugin
{
	private Logger log;
	private CivChat instance;
	
	public void onEnable() 
	{
		this.instance = this;
		this.log = this.getLogger();
		this.log.info("CivChat has been enabled.");
	}
	
	public void onDisable()
	{
		this.log.info("CivChat has been disabled");
	}
	
	public CivChat getInstance() 
	{
		return this.instance;
	}
}
