package civchat.manager;

import java.util.HashSet;
import java.util.Set;

import civchat.model.Antenna;

public class AntennaManager 
{
	private Set<Antenna> antennas = new HashSet<Antenna>();
	
	public AntennaManager()
	{
	}
	
	public void addAntenna(Antenna antenna)
	{
		this.antennas.add(antenna);
	}
	
	public void removeAntenna(Antenna antenna)
	{
		this.antennas.remove(antenna);
	}
	
	public Set<Antenna> getAntennas()
	{
		return this.antennas;
	}
}
