package civchat.manager;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
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
	
	/**
	 * Adds a new mobile phone to storage
	 * 
	 * @param network
	 * @param player
	 * @return row id integer
	 */
	public int recordMobilePhone(Network network, Player player)
	{
		int networkId     = network.getId();
		String playerName = player.getDisplayName();
		MobilePhone phone = new MobilePhone(networkId, playerName);
		return storageManager.insertMobilePhone(phone);
	}
	
	/**
	 * Checks if material the player is holding is the material
	 * required to be a mobile phone
	 * 
	 * @param player
	 * @return boolean
	 */
	public boolean hasMobilePhoneMaterial(Player player)
	{
		ItemStack[] inventory = player.getInventory().getContents();
		for(ItemStack item : inventory)
		{
			//Todo: Material.COMPASS should be referenced from config
			if(item.getType() == Material.COMPASS)
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Checks if mobile phone exists in storage
	 * 
	 * @param id
	 * @return boolean
	 */
	public boolean isMobilePhone(int id)
	{
		if(getMobilePhone(id) != null)
		{
			return true;
		}
		return false;
	}
	
	/**
	 * Get mobile phone by owner from storage
	 * 
	 * @param owner
	 * @return MobilePhone
	 */
	public MobilePhone getMobilePhone(String owner)
	{
		return storageManager.findMobilePhoneByOwner(owner);
	}
	
	/**
	 * Get mobile phone by id from storage
	 * 
	 * @param owner
	 * @return MobilePhone
	 */	
	public MobilePhone getMobilePhone(int id)
	{
		return storageManager.findMobilePhoneById(id);
	}
	
	/**
	 * When player selects mobile phone display status
	 * 
	 * @param player
	 * @param item
	 */
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
