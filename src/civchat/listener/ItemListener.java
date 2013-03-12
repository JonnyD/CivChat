package civchat.listener;

import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCombustEvent;
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
		Material material   = item.getData().getItemType();
		
		if(material != Material.COMPASS)
			return;
		
		int id = item.getDurability();
		if(id == 0)
			return;
		
		MobilePhoneManager phoneManager = plugin.getMobilePhoneManager();
		phoneManager.announcePhone(player, item);
	}
	
	@EventHandler
	public void onItemDespawnEvent(ItemDespawnEvent event)
	{
		ItemStack itemStack = event.getEntity().getItemStack();
		Material material = itemStack.getData().getItemType();
		if(material != Material.COMPASS)
			return;
		
		int id = itemStack.getDurability();
		if(id == 0)
			return;
		
		MobilePhoneManager phoneManager = plugin.getMobilePhoneManager();
		phoneManager.deleteMobilePhone(id);		
	}
	
	@EventHandler
	public void onEntityCombustEvent(EntityCombustEvent event)
	{
		if(!(event.getEntity() instanceof Item))
			return;
		
		ItemStack itemStack = ((Item) event.getEntity()).getItemStack();
		Material material = itemStack.getData().getItemType();
		if(material != Material.COMPASS)
			return;
		
		int id = itemStack.getDurability();
		if(id == 0)
			return;
		
		MobilePhoneManager phoneManager = plugin.getMobilePhoneManager();
		phoneManager.deleteMobilePhone(id);	
	}
}
