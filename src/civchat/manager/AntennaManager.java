package civchat.manager;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import civchat.CivChat;
import civchat.model.Antenna;
import civchat.model.CivPlayer;

public class AntennaManager 
{
	private CivChat plugin;
	private StorageManager storageManager;
	
	public AntennaManager()
	{
		this.plugin = CivChat.getInstance();
		this.storageManager = plugin.getStorageManager();
	}
	
	public void recordAntennaPlace(Player player, Block block)
	{
		Antenna antenna = new Antenna(block.getX(), block.getY(), block.getZ(), player.getDisplayName());
		storageManager.insertAntenna(antenna);
	}
	
	public void recordAntennaNetwork(CivPlayer civPlayer, Antenna antenna)
	{
		antenna.setNetwork(civPlayer.getNetwork());
		storageManager.updateAntenna(antenna);
	}
}
