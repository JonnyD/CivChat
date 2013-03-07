package civchat.listener;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import civchat.CivChat;
import civchat.manager.AntennaManager;
import civchat.model.Antenna;

public class BlockListener implements Listener 
{

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event)
	{
		Block block = event.getBlock();
		AntennaManager antennaManager = CivChat.getInstance().getAntennaManager();
		boolean canBeAntenna = antennaManager.canBeAntenna(block);
		if(canBeAntenna)
		{
			Location location = antennaManager.findLocation(block);
			Antenna antenna = antennaManager.getAntenna(location);
			if(antenna != null)
			{
				antennaManager.recordAntennaDamage(antenna);
			}
		}
	}
}
