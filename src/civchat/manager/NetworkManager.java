package civchat.manager;

import org.bukkit.entity.Player;

import civchat.CivChat;
import civchat.model.Network;

public class NetworkManager 
{
	private CivChat plugin;
	private StorageManager storageManager;
	
	public NetworkManager()
	{
		this.plugin = CivChat.getInstance();
		this.storageManager = plugin.getStorageManager();
	}
	
	public void recordNetwork(String name, Player player)
	{
		Network network = new Network(name, player.getDisplayName());
		storageManager.insertNetwork(network);
	}
	
	public Network getNetwork(String name)
	{
		return storageManager.findNetwork(name);
	}
}
