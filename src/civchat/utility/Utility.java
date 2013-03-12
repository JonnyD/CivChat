package civchat.utility;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import civchat.CivChat;
import civchat.manager.MobilePhoneManager;
import civchat.manager.NetworkManager;
import civchat.model.MobilePhone;
import civchat.model.Network;

public class Utility 
{
	public static String stripTrailingComma(String text)
	{
		int pos = text.lastIndexOf(",");
        if (pos >= 0)
        {
            return text.substring(0, pos);
        }
        return text;
	}
	
	public static boolean hasMobilePhone(Player player)
	{
		PlayerInventory inventory = player.getInventory();
		ItemStack itemInHand      = inventory.getItemInHand();
		
		if(itemInHand.getType() != Material.COMPASS)
		{
			int id = itemInHand.getDurability();
			
			if(id > 0)
			{
				CivChat plugin                  = CivChat.getInstance();
				MobilePhoneManager phoneManager = plugin.getMobilePhoneManager();		
				boolean isMobilePhone           = phoneManager.isMobilePhone(id);
				
				if(isMobilePhone)
				{
					return true;
				}
			}
		}
		
		return false;
	}
	
	public static MobilePhone getMobilePhone(Player player)
	{
		PlayerInventory inventory = player.getInventory();
		ItemStack itemInHand      = inventory.getItemInHand();
		
		MobilePhone phone = null;
		if(itemInHand.getType() == Material.COMPASS)
		{
			int id = itemInHand.getDurability();
			System.out.println(id);
			if(id > 0)
			{
				CivChat plugin                  = CivChat.getInstance();
				MobilePhoneManager phoneManager = plugin.getMobilePhoneManager();		
				phone                           = phoneManager.getMobilePhone(id);
			}
		}
		
		return phone;
	}
	
	public static void updateMobilePhone(MobilePhone phone)
	{
		CivChat plugin                  = CivChat.getInstance();
		MobilePhoneManager phoneManager = plugin.getMobilePhoneManager();
		phoneManager.updateMobilePhone(phone);
	}
	
	public static boolean doesNetworkExist(String networkName)
	{
		CivChat plugin                = CivChat.getInstance();
		NetworkManager networkManager = plugin.getNetworkManager();
		Network network               = networkManager.getNetwork(networkName);
		
		if(network != null)
		{
			return true;
		}
		
		return false;
	}
	
	public static Network getNetwork(String networkName)
	{
		CivChat plugin                = CivChat.getInstance();
		NetworkManager networkManager = plugin.getNetworkManager();
		return networkManager.getNetwork(networkName);
	}
	
}
