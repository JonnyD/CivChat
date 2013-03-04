package civchat.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import civchat.manager.PlayerManager;
import civchat.model.CivPlayer;
import civchat.model.Mode;

public class PlayerListener implements Listener
{
	@EventHandler
	public void login(PlayerLoginEvent event)
	{
		Player player = event.getPlayer();
		PlayerManager playerManager = PlayerManager.getInstance();
		playerManager.getOrCreateCivPlayer(player);
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
		
		Player player = event.getPlayer();
		String playerName = player.getDisplayName().toLowerCase();
		
		PlayerManager playerManager = PlayerManager.getInstance();
		CivPlayer civPlayer = playerManager.getCivPlayer(playerName);
		
		if(civPlayer != null)
		{
			Mode mode = civPlayer.getMode();
			switch(mode)
			{
				case NORMAL:
					return;
				case CREATE_ANTENNA:
					System.out.println("created antenna!");
					civPlayer.reset();
			}
		}
	}
}
