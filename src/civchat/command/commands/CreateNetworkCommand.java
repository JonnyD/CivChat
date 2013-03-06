package civchat.command.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import civchat.CivChat;
import civchat.command.PlayerCommand;
import civchat.manager.NetworkManager;
import civchat.manager.StorageManager;

public class CreateNetworkCommand extends PlayerCommand
{

	public CreateNetworkCommand() {
		super("Create Network");
		setDescription("Creates a network");
		setUsage("/ccnetwork <name>");
		setArgumentRange(1,1);
		setIdentifier("ccnetwork");
	}

	@Override
	public boolean execute(CommandSender sender, String[] args) 
	{
		NetworkManager networkManager = CivChat.getInstance().getNetworkManager();
		networkManager.recordNetwork(args[0], (Player) sender);
		return true;
	}

}
