package civchat.command.commands;

import org.bukkit.command.CommandSender;

import civchat.command.PlayerCommand;
import civchat.manager.PlayerManager;
import civchat.model.CivPlayer;
import civchat.model.Mode;

public class CreateAntenna extends PlayerCommand
{

	public CreateAntenna() 
	{
		super("Create Antenna");
		setDescription("Creates an antenna");
		setUsage("/ccantenna");
		setArgumentRange(0,0);
		setIdentifier("ccantenna");
	}

	@Override
	public boolean execute(CommandSender sender, String[] args) {
		String playerName = sender.getName();
		
		PlayerManager playerManager = PlayerManager.getInstance();
		CivPlayer civPlayer = playerManager.getCivPlayer(playerName);
		civPlayer.setMode(Mode.CREATE_ANTENNA);
		
		return true;
	}
}
