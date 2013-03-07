package civchat.manager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
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
	
	public void recordAntennaPlace(Player player, Location location)
	{
		int x             = (int) location.getX();
		int y             = (int) location.getY();
		int z             = (int) location.getZ();
		String playerName = player.getDisplayName();
		Antenna antenna   = new Antenna(x, y, z, playerName);
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
		if(antennaMaterials.contains(block.getType()))
		{
			Material main  = block.getType();
			Material up1   = block.getRelative(BlockFace.UP, 1).getType();
			Material up2   = block.getRelative(BlockFace.UP, 2).getType();
			Material down1 = block.getRelative(BlockFace.DOWN, 1).getType();
			Material down2 = block.getRelative(BlockFace.DOWN, 2).getType();
	
			String materials = first3Letters(up1.toString()) + firstNumber(Integer.toString(up1.getId()))
					+ first3Letters(up2.toString()) + firstNumber(Integer.toString(up2.getId()))
					+ first3Letters(main.toString()) + firstNumber(Integer.toString(main.getId()))
					+ first3Letters(down1.toString()) + firstNumber(Integer.toString(down1.getId()))
					+ first3Letters(down2.toString()) + firstNumber(Integer.toString(down2.getId()));
			
			String regex = "DIA5IRO1JUK8........|IRO1....DIA5JUK8....|........IRO1DIA5JUK8";
	
			if (materials.matches(regex)) 
			{
				return true;
			}
		}
		return false;
	}
	
	public Location findLocation(Block block)
	{
		List<Material> antennaMaterials = new ArrayList<Material>(configManager.getAntennaMaterials());
		Material material = antennaMaterials.get(0);

		Block main = block;
		Block down1 = block.getRelative(BlockFace.DOWN, 1);
		Block down2 = block.getRelative(BlockFace.DOWN, 2);
		
		Location location = null;
		if(main.getType() == material)
		{
			location = new Location(main.getWorld(), main.getX(), main.getY(), main.getZ());
		}
		else if(down1.getType() == material)
		{
			location = new Location(main.getWorld(), main.getX(), main.getY() - 1, main.getZ());
		}
		else if(down2.getType() == material)
		{
			location = new Location(main.getWorld(), main.getX(), main.getY() - 2, main.getZ());
		}

		return location;
	}
	
	public boolean isAntenna(Location location)
	{
		if(getAntenna(location) != null)
		{
			return true;
		}
		return false;
	}
	
	public Antenna getAntenna(Location location)
	{
		return null;
	}
	
	private String first3Letters(String text)
	{
		return text.substring(0, 3);
	}
	
	private String firstNumber(String text)
	{
		return text.substring(0, 1);
	}
}
