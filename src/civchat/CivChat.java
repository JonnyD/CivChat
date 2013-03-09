package civchat;

import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import civchat.command.CommandHandler;
import civchat.command.commands.CreateAntenna;
import civchat.command.commands.CreateNetworkCommand;
import civchat.command.commands.CreatePhone;
import civchat.command.commands.SetNetworkCommand;
import civchat.listener.BlockListener;
import civchat.listener.ChatListener;
import civchat.listener.PlayerListener;
import civchat.manager.AntennaManager;
import civchat.manager.ConfigManager;
import civchat.manager.MobilePhoneManager;
import civchat.manager.NetworkManager;
import civchat.manager.StorageManager;


public class CivChat extends JavaPlugin
{
	private static CivChat instance;
	private static Logger log = Logger.getLogger("CivChat");
	private CommandHandler commandHandler;
	private ConfigManager configManager;
	private StorageManager storageManager;
	private AntennaManager antennaManager;
	private NetworkManager networkManager;
	private MobilePhoneManager mobilePhoneManager;
	
	public void onEnable() 
	{
		instance = this;

		configManager  = new ConfigManager();
		storageManager = new StorageManager();
		commandHandler = new CommandHandler();
		antennaManager = new AntennaManager();
		networkManager = new NetworkManager();
		mobilePhoneManager = new MobilePhoneManager();
		
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
		commandHandler.addCommand(new CreateAntenna());
		commandHandler.addCommand(new CreateNetworkCommand());
		commandHandler.addCommand(new SetNetworkCommand());
		commandHandler.addCommand(new CreatePhone());
	}	
	
	public void registerEvents()
	{
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new BlockListener(), this);
		pm.registerEvents(new PlayerListener(), this);
		pm.registerEvents(new ChatListener(), this);
	}
	
	public ConfigManager getConfigManager()
	{
		return this.configManager;
	}
	
	public AntennaManager getAntennaManager()
	{
		return this.antennaManager;
	}
	
	public StorageManager getStorageManager()
	{
		return this.storageManager;
	}
	
	public NetworkManager getNetworkManager()
	{
		return this.networkManager;
	}
	
	public MobilePhoneManager getMobilePhoneManager()
	{
		return mobilePhoneManager;
	}
}
