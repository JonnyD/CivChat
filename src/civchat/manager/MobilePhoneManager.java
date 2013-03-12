package civchat.manager;

import java.util.Map.Entry;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

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
	 * @param player
	 * @return row id integer
	 */
	public int recordMobilePhone(Player player)
	{
		String playerName = player.getDisplayName();
		MobilePhone phone = new MobilePhone(playerName);
		return storageManager.insertMobilePhone(phone);
	}
	
	public void updateMobilePhone(MobilePhone phone)
	{
		storageManager.updateMobilePhone(phone);
	}
	
	public void deleteMobilePhone(MobilePhone phone)
	{
		deleteMobilePhone(phone.getId());
	}
	
	public void deleteMobilePhone(int id)
	{
		
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
		return hasMobilePhoneMaterial(player.getInventory().getContents());
	}
	
	public boolean hasMobilePhoneMaterial(ItemStack[] inventory)
	{
		for(ItemStack item : inventory)
		{
			if(item != null)
			{
				//Todo: Material.COMPASS should be referenced from config
				if(item.getType() == Material.COMPASS)
				{
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean hasMobilePhoneMaterialInHand(Player player)
	{
		PlayerInventory inventory = player.getInventory();
		Material itemInHand       = inventory.getItemInHand().getType();
		
		if(itemInHand == Material.COMPASS)
		{
			return true;
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
	
	public void createPhone(Player player)
	{
		PlayerInventory inventory = player.getInventory();
		
		if(!hasMobilePhoneMaterial(inventory.getContents()))
		{
			player.sendMessage("You need a compass to create a phone");
			return;
		}
		
		ItemStack stack = inventory.getItemInHand();
		int stackNum = inventory.getHeldItemSlot();
		
		int phoneNum = 1;
		if(stack.getAmount() == 1)
		{
			phoneNum = stackNum;
		}
		else
		{
			phoneNum = inventory.firstEmpty();
			if(phoneNum > 0)
			{
				stack.setAmount(stack.getAmount() - 1);
				inventory.setItem(stackNum, stack);
			}
			else 
			{
				phoneNum = stackNum;
			}
		}
		
		int id = recordMobilePhone(player);
		if(id > 0)
		{
			ItemStack newStack = new ItemStack(Material.COMPASS, 1, (short) id);
			inventory.setItem(phoneNum, new ItemStack(newStack));
			player.sendMessage("Phone created");
		}
	}
}
