package civchat.manager;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import civchat.CivChat;
import civchat.model.MobilePhone;
import civchat.model.Network;

public class MobilePhoneManager 
{
	private CivChat plugin;
	private StorageManager storageManager;
	private ConfigManager configManager;
	
	public MobilePhoneManager()
	{
		this.plugin         = CivChat.getInstance();
		this.storageManager = plugin.getStorageManager();
		this.configManager  = plugin.getConfigManager();
	}
	
	public int recordMobilePhone(Network network, Player player)
	{
		int networkId     = network.getId();
		String playerName = player.getDisplayName();
		MobilePhone phone = new MobilePhone(networkId, playerName);
		return storageManager.insertMobilePhone(phone);
	}
	
	public boolean isMobilePhone(int id)
	{
		if(getMobilePhone(id) != null)
		{
			return true;
		}
		return false;
	}
	
	public MobilePhone getMobilePhone(String owner)
	{
		return storageManager.findMobilePhoneByOwner(owner);
	}
	
	public MobilePhone getMobilePhone(int id)
	{
		return storageManager.findMobilePhoneById(id);
	}
	
	public void announcePhone(Player player, ItemStack item)
	{
		if(item == null)
			return;
		
		Material material = item.getData().getItemType();
		int id = item.getDurability();
		if(material == Material.COMPASS && id > 0)
		{
			MobilePhone phone = getMobilePhone(item.getDurability());
			if(phone != null)
			{
				player.sendMessage(ChatColor.GREEN+"Phone - " + phone.toString());
			}
		}
	}
}
