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
	public boolean execute(CommandSender sender, String[] args) 
	{
		String playerName = sender.getName();
		
		PlayerManager playerManager = PlayerManager.getInstance();
		CivPlayer civPlayer = playerManager.getCivPlayer(playerName);
		
		Mode mode = civPlayer.getMode();
		if(mode == Mode.CREATE_ANTENNA)
		{
			civPlayer.reset();
			sender.sendMessage("Antenna Creation Mode Off");
		}
		else 
		{
			civPlayer.setMode(Mode.CREATE_ANTENNA);
			sender.sendMessage("Antenna Creation Mode On");
		}
		
		return true;
	}
}
