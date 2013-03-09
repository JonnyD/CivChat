package civchat.listener;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import civchat.CivChat;
import civchat.manager.AntennaManager;
import civchat.manager.MobilePhoneManager;
import civchat.manager.PlayerManager;
import civchat.model.Antenna;
import civchat.model.CivPlayer;
import civchat.model.Mode;

public class PlayerListener implements Listener
{
	@EventHandler
	public void login(PlayerLoginEvent event)
	{
		Player player = event.getPlayer();
		PlayerManager playerManager = PlayerManager.getInstance();
		CivPlayer civPlayer = playerManager.getOrCreateCivPlayer(player);
		playerManager.addCivPlayer(civPlayer);
	}

	@EventHandler
	public void quit(PlayerQuitEvent event)
	{
		Player player = event.getPlayer();
		PlayerManager playerManager = PlayerManager.getInstance();
		playerManager.removeCivPlayer(player);
	}

	@EventHandler
	public void interact(PlayerInteractEvent event)
	{
		if(!event.hasBlock()){return;}

		Block clickedBlock = event.getClickedBlock();
		Player player      = event.getPlayer();	

		PlayerManager playerManager = PlayerManager.getInstance();
		CivPlayer civPlayer         = playerManager.getCivPlayer(player);

		if(civPlayer != null)
		{
			Mode mode = civPlayer.getMode();

			if(mode != null && mode != Mode.NORMAL)
			{
				AntennaManager antennaManager = CivChat.getInstance().getAntennaManager();
				boolean canBeAntenna          = antennaManager.canBeAntenna(clickedBlock);

				if(canBeAntenna)
				{
					Location location = antennaManager.findLocation(clickedBlock);
					Boolean isAntenna = antennaManager.isAntenna(location);

					switch(mode)
					{
					case CREATE_ANTENNA:
						if(!isAntenna)
						{
							antennaManager.recordAntennaPlace(player, location);
						}
						break;
					case SET_NETWORK:
						if(isAntenna)
						{
							Antenna antenna = antennaManager.getAntenna(location);
							antennaManager.recordAntennaNetwork(civPlayer, antenna);
						}
						break;
					}
				}

				civPlayer.reset();
			}
		}
	}
	
	@EventHandler
	public void onItemHeldChange(PlayerItemHeldEvent event)
	{
		Player player       = event.getPlayer();
		Inventory inventory = player.getInventory();
		ItemStack item      = inventory.getItem(event.getNewSlot());
		
		MobilePhoneManager phoneManager = CivChat.getInstance().getMobilePhoneManager();
		phoneManager.announcePhone(player, item);
	}
}
