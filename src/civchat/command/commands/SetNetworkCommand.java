package civchat.command.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import civchat.CivChat;
import civchat.command.PlayerCommand;
import civchat.manager.NetworkManager;
import civchat.manager.PlayerManager;
import civchat.model.CivPlayer;
import civchat.model.Mode;
import civchat.model.Network;

public class SetNetworkCommand extends PlayerCommand
{

	public SetNetworkCommand() 
	{
		super("Set Network");
		setDescription("Sets a network to an antenna");
		setUsage("/ccsetnetwork <network-name>");
		setArgumentRange(1,1);
		setIdentifier("ccsetnetwork");
	}

	@Override
	public boolean execute(CommandSender sender, String[] args) 
	{
		String playerName  = sender.getName();
		String networkName = args[0];
		
		NetworkManager networkManager = CivChat.getInstance().getNetworkManager();
		Network network               = networkManager.getNetwork(networkName);
		
		if(network == null)
		{
			System.out.println("Network doesn't exist");
			return true;
		}
		
		if(!network.getOwner().equalsIgnoreCase(playerName))
		{
			System.out.println("Not authorized");
			return true;
		}
		
		PlayerManager playerManager = PlayerManager.getInstance();
		CivPlayer civPlayer         = playerManager.getCivPlayer((Player) sender);
		
		civPlayer.setMode(Mode.SET_NETWORK);
		civPlayer.setNetwork(network);
		
		return true;
	}

}
