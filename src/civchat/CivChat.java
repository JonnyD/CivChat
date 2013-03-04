package civchat;

import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import civchat.command.CommandHandler;
import civchat.command.commands.CreateAntenna;
import civchat.listener.BlockListener;
import civchat.listener.PlayerListener;


public class CivChat extends JavaPlugin
{
	private static CivChat instance;
	private static Logger log = Logger.getLogger("CivChat");
	private CommandHandler commandHandler;
	
	public void onEnable() 
	{
		instance = this;
		commandHandler = new CommandHandler();
		
		registerCommands();
		registerEvents();
		
		log.info("CivChat has been enabled.");
	}
	
	public void onDisable()
	{
		this.log.info("CivChat has been disabled");
	}
	
	public static CivChat getInstance() 
	{
		return instance;
	}
	
	public static Logger getLog()
	{
		return log;
	}
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		return this.commandHandler.dispatch(sender, label, args);
	}
	
	public void registerCommands()
	{
		this.commandHandler.addCommand(new CreateAntenna());
	}	
	
	public void registerEvents()
	{
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new BlockListener(), this);
		pm.registerEvents(new PlayerListener(), this);
	}
}
