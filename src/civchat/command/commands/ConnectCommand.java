package civchat.command.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import civchat.command.PlayerCommand;
import civchat.manager.MobilePhoneManager;
import civchat.manager.NetworkManager;
import civchat.model.MobilePhone;
import civchat.model.Network;
import civchat.utility.Utility;

public class ConnectCommand extends PlayerCommand
{

	public ConnectCommand() 
	{
		super("Connect");
		setDescription("Connects to a network");
		setUsage("/connect <network>");
		setArgumentRange(1,1);
		setIdentifier("connect");
	}

	@Override
	public boolean execute(CommandSender sender, String[] args) 
	{
		Player player = (Player) sender;
		
		MobilePhone phone = Utility.getMobilePhone(player);
		if(phone == null)
		{
			player.sendMessage("You must have a mobile phone to use this command");
			return true;
		}
		
		String networkName = args[0];
		
		Network network = Utility.getNetwork(networkName);
		if(network == null)
		{
			player.sendMessage("Network doesn't exist");
			return true;
		}
		
		phone.setNetworkId(network.getId());
		Utility.updateMobilePhone(phone);
		player.sendMessage("Connected to network");
		
		return true;
	}

}
