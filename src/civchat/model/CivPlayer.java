package civchat.model;

import org.bukkit.entity.Player;

public class CivPlayer
{

	private final Player player;
	private Mode mode;
	
	public CivPlayer(Player player)
	{
		this.player = player;
	}
	
	public Mode getMode()
	{
		return this.mode;
	}
	
	public void setMode(Mode mode)
	{
		this.mode = mode;
	}
	
	public Player getPlayer()
	{
		return this.player;
	}
}
