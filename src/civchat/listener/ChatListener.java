package civchat.listener;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import civchat.CivChat;
import civchat.manager.AntennaManager;
import civchat.manager.MobilePhoneManager;
import civchat.model.Antenna;

public class ChatListener implements Listener
{
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event)
	{
		// Get the player
		Player sender = event.getPlayer();

		Set<Player> recipients = new HashSet<Player>();
		
		// Has player got a mobile phone in his inventory
		CivChat plugin = CivChat.getInstance();
		MobilePhoneManager phoneManager = plugin.getMobilePhoneManager();
		boolean hasMobilePhoneMaterial = phoneManager.hasMobilePhoneMaterial(sender);
		if(hasMobilePhoneMaterial)
		{
			AntennaManager antennaManager = plugin.getAntennaManager();
			Set<Antenna> antennasNearSender = antennaManager.getAntennasNear(sender.getLocation());
			if(antennasNearSender.size() > 0)
			{
				Player[] onlinePlayers = plugin.getServer().getOnlinePlayers();
				for(Player p : onlinePlayers)
				{
					Set<Antenna> antennasNearRecepient = antennaManager.getAntennasNear(p.getLocation());
					if(antennasNearRecepient.size() > 0)
					{
						recipients.add(p);
					}
				}
			}
		}
		
		System.out.println(recipients.toString());
        System.out.println("chat" + event.getMessage());
    }
}
