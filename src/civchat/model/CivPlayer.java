package civchat.model;

import org.bukkit.entity.Player;

public class CivPlayer
{
	private final Player player;
	private Mode mode;
	private Network network;
	
	public CivPlayer(Player player)
	{
		this.player = player;
		reset();
	}
	
	public void reset()
	{
		this.mode = Mode.NORMAL;
		this.network = null;
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
	
	public void setNetwork(Network network)
	{
		this.network = network;
	}
	
	public Network getNetwork()
	{
		return this.network;
	}

	@Override
	public String toString() {
		return "CivPlayer [player=" + player + ", mode=" + mode + ", network="
				+ network + "]";
	}
	
}
