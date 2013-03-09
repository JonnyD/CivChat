package civchat.command.commands;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import civchat.command.PlayerCommand;
import civchat.manager.MobilePhoneManager;
import civchat.manager.NetworkManager;
import civchat.model.MobilePhone;
import civchat.model.Network;

public class CreatePhone extends PlayerCommand
{

	public CreatePhone() 
	{
		super("Create Phone");
		setDescription("Creates a phone");
		setUsage("/createphone <network>");
		setArgumentRange(1,1);
		setIdentifier("createphone");
	}

	@Override
	public boolean execute(CommandSender sender, String[] args) 
	{
		Player player = (Player) sender;
		
		ItemStack itemInHand    = player.getItemInHand();
		Material materialInHand = itemInHand.getData().getItemType();
		
		if(materialInHand != Material.COMPASS)
		{
			System.out.println("not a phone");
			return true;
		}
		
		String networkName            = args[0];
		NetworkManager networkManager = plugin.getNetworkManager();
		Network network               = networkManager.getNetwork(networkName);
		
		if(network == null)
		{
			System.out.println("network doesnt exist");
			return true;
		}
		
		MobilePhoneManager phoneManager = plugin.getMobilePhoneManager();
		MobilePhone mobilePhone         = phoneManager.getMobilePhone(itemInHand.getDurability());
		
		if(mobilePhone != null)
		{
			System.out.println("Already a mobile phone");
			return true;
		}
		
		int phoneId = phoneManager.recordMobilePhone(network, player);
		itemInHand.setDurability((short) phoneId);
		
		return true;
	}
	
	

}
