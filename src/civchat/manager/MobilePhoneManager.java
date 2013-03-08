package civchat.manager;

import org.bukkit.entity.Player;

import civchat.CivChat;
import civchat.model.MobilePhone;
import civchat.model.Network;

public class MobilePhoneManager 
{
	private CivChat plugin;
	private StorageManager storageManager;
	private ConfigManager configManager;
	
	public MobilePhoneManager()
	{
		this.plugin         = CivChat.getInstance();
		this.storageManager = plugin.getStorageManager();
		this.configManager  = plugin.getConfigManager();
	}
}
