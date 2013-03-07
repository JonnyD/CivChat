package civchat.manager;

import java.util.Iterator;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

import civchat.CivChat;
import civchat.model.Antenna;
import civchat.model.CivPlayer;

public class AntennaManager 
{
	private CivChat plugin;
	private StorageManager storageManager;
	private ConfigManager configManager;
	
	public AntennaManager()
	{
		this.plugin         = CivChat.getInstance();
		this.storageManager = plugin.getStorageManager();
		this.configManager  = plugin.getConfigManager();
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
	
	public boolean canBeAntenna(Block block) 
	{
		Set<Material> antennaMaterials = configManager.getAntennaMaterials();		
		String antennaMaterialString = "";
		for(Material m : antennaMaterials)
		{
			antennaMaterialString += m.toString();
		}
		
		String incomingMaterialString = block.getType().toString();
		for(int i = 1; i <= antennaMaterials.size(); i++)
		{
			Material above = block.getRelative(BlockFace.UP, i).getType();
			incomingMaterialString += above.toString();
		}

		if (antennaMaterialString.matches(incomingMaterialString)) {
			return true;
		}

		return false;
	}
}
