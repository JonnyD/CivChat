package civchat.command.commands;

import org.bukkit.command.CommandSender;

import civchat.command.PlayerCommand;
import civchat.manager.NetworkManager;
import civchat.manager.PlayerManager;
import civchat.model.CivPlayer;
import civchat.model.Mode;
import civchat.model.Network;

public class CreateAntenna extends PlayerCommand
{

	public CreateAntenna() 
	{
		super("Create Antenna");
		setDescription("Creates an antenna");
		setUsage("/createantenna <network>");
		setArgumentRange(1,1);
		setIdentifier("createantenna");
	}

	@Override
	public boolean execute(CommandSender sender, String[] args) 
	{
		String playerName = sender.getName();
		String networkName = args[0];
		
		NetworkManager networkManager = plugin.getNetworkManager();
		Network network = networkManager.getNetwork(networkName);
		
		if(network == null)
		{
			sender.sendMessage("Network doesn't exist");
			return true;
		}
		
		PlayerManager playerManager = PlayerManager.getInstance();
		CivPlayer civPlayer = playerManager.getCivPlayer(playerName);
		
		civPlayer.setMode(Mode.CREATE_ANTENNA);
		civPlayer.setNetwork(network);
		
		sender.sendMessage("Antenna Creation Mode On");
		return true;
	}
}
