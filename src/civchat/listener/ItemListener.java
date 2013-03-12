package civchat.listener;

import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemDespawnEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import civchat.CivChat;
import civchat.manager.MobilePhoneManager;

public class ItemListener implements Listener
{
	
	private CivChat plugin = CivChat.getInstance();

	@EventHandler
	public void onItemHeldChange(PlayerItemHeldEvent event)
	{
		Player player       = event.getPlayer();
		Inventory inventory = player.getInventory();
		ItemStack item      = inventory.getItem(event.getNewSlot());
		
		MobilePhoneManager phoneManager = plugin.getMobilePhoneManager();
		phoneManager.announcePhone(player, item);
	}
}
