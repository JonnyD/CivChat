package civchat;

import java.util.logging.Logger;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import civchat.command.Command;
import civchat.command.CommandHandler;


public class CivChat extends JavaPlugin
{
	private Logger log;
	private CivChat instance;
	private CommandHandler commandHandler;
	
	public void onEnable() 
	{
		this.instance = this;
		this.commandHandler = new CommandHandler();
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
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		return commandHandler.dispatch(sender, label, args);		
	}
}
