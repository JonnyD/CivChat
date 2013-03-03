package civchat.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.entity.Player;

import civchat.model.CivPlayer;

public class PlayerManager {

	private static final PlayerManager instance = new PlayerManager();
	private Map<String, CivPlayer> civPlayers = null;
	
	private PlayerManager()
	{
		this.civPlayers = new HashMap<String, CivPlayer>();
	}	
	
	public static PlayerManager getInstance()
	{
		return instance;
	}
	
	public CivPlayer getCivPlayer(String playerName)
	{
		return this.civPlayers.get(playerName.toLowerCase());
	}
	
	public CivPlayer getCivPlayer(Player player)
	{
		String playerName = player.getDisplayName().toLowerCase();
		return this.getCivPlayer(playerName);
	}
	
	public void addCivPlayer(CivPlayer civPlayer)
	{
		Player player = civPlayer.getPlayer();
		String playerName = player.getDisplayName().toLowerCase();
		this.civPlayers.put(playerName, civPlayer);
	}
	
	public void removeCivPlayer(String playerName)
	{
		this.civPlayers.remove(playerName.toLowerCase());
	}
	
	public void removeCivPlayer(Player player)
	{
		String playerName = player.getDisplayName().toLowerCase();
		this.removeCivPlayer(playerName);
	}
}
