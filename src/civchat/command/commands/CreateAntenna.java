package civchat.command.commands;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockPlaceEvent;

import civchat.command.PlayerCommand;

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
		
		return true;
	}
}
