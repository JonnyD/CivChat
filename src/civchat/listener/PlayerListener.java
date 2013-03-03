package civchat.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import civchat.manager.PlayerManager;
import civchat.model.CivPlayer;
import civchat.model.Mode;

public class PlayerListener implements Listener
{
	public void interact(PlayerInteractEvent event)
	{
		if(!event.hasBlock())
		{
			return;
		}
		
		Player player = event.getPlayer();
		String playerName = player.getDisplayName().toLowerCase();
		
		PlayerManager playerManager = PlayerManager.getInstance();
		CivPlayer civPlayer = playerManager.getCivPlayer(playerName);
		
		if(civPlayer == null)
		{
			return;
		}
		
		Mode mode = civPlayer.getMode();
		switch(mode)
		{
			case NORMAL:
				return;
			case CREATE_ANTENNA:
				System.out.println("created antenna!");
		}
	}
}
