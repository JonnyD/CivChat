package civchat.command.commands;

import java.util.Set;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import civchat.command.PlayerCommand;
import civchat.manager.AntennaManager;
import civchat.manager.MobilePhoneManager;
import civchat.model.Antenna;
import civchat.utility.Utility;

public class ScanCommand extends PlayerCommand 
{

	public ScanCommand() 
	{
		super("Scan Command");
		setDescription("Scans for networks nearby");
		setUsage("/scan");
		setArgumentRange(0,0);
		setIdentifier("scan");
	}

	@Override
	public boolean execute(CommandSender sender, String[] args) 
	{
		Player player = (Player) sender;
		
		boolean hasMobilePhone = Utility.hasMobilePhone(player);
		if(!hasMobilePhone)
		{
			player.sendMessage("You must have a mobile phone to use this command");
		}
		
		AntennaManager antennaManager = plugin.getAntennaManager();
		Set<Antenna> antennas = antennaManager.getAntennasNear(player.getLocation());
		
		for(Antenna a : antennas)
		{
			sender.sendMessage(a.toString());
		}
		
		return true;
	}

}
