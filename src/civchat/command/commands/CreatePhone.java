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
		setUsage("/createphone");
		setArgumentRange(0,0);
		setIdentifier("createphone");
	}

	@Override
	public boolean execute(CommandSender sender, String[] args) 
	{
		Player player = (Player) sender;
		MobilePhoneManager phoneManager = plugin.getMobilePhoneManager();
		phoneManager.createPhone(player);
		return true;
	}
	
	

}
