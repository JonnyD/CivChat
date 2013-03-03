package civchat.command;

import java.util.LinkedHashMap;
import java.util.Map;

import org.bukkit.command.CommandSender;

public class CommandHandler 
{

	private Map<String, Command> commands = new LinkedHashMap<String, Command>();
	
	public void addCommand(Command command)
	{
		String identifier = command.getIdentifier().toLowerCase();
		this.commands.put(identifier, command);
	}
	
	public boolean dispatch(CommandSender sender, String label, String[] args)
	{
		if(commands.containsKey(label))
		{
			Command command = commands.get(label);
			if(args.length < command.getMinArguments() 
			|| args.length > command.getMaxArguments())
			{
				//displayhelp
				return true;
			}
			command.execute(sender, args);
		}
		return true;
	}
}
