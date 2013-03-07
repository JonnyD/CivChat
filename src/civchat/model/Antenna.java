package civchat.model;

import java.util.HashSet;
import java.util.Set;

public class Antenna 
{	
	private int id;
	private int x;
	private int y;
	private int z;
	private String owner;
	private String network;
	
	private Set<DirtyAntennaReason> dirty = new HashSet<DirtyAntennaReason>();
	public enum DirtyAntennaReason
	{
		OWNER,
		NETWORK
	}
	
	public Antenna(int x, int y, int z, String owner)
	{
		this.x     = x;
		this.y     = y;
		this.z     = z;
		this.owner = owner;
	}
	
	public Antenna(int id, int x, int y, int z, String owner)
	{
		this.id    = id;
		this.x     = x;
		this.y     = y;
		this.z     = z;
		this.owner = owner;
	}
	
	public Antenna(int id, int x, int y, int z, String owner, String network)
	{
		this.id      = id;
		this.x       = x;
		this.y       = y;
		this.z       = z;
		this.owner   = owner;
		this.network = network;
	}
	
	public int getId()
	{
		return this.id;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}
	
	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
		dirty.add(DirtyAntennaReason.OWNER);
	}
	
	public String getNetwork()
	{
		return network;
	}
	
	public void setNetwork(String network)
	{
		this.network = network;
		dirty.add(DirtyAntennaReason.NETWORK);
	}
	
	public boolean isDirty(DirtyAntennaReason dirtyType)
	{
		return dirty.contains(dirtyType);
	}
	
	public boolean isDirty()
	{
		return !dirty.isEmpty();
	}
	
	public void clearDirty()
	{
		dirty.clear();
	}
	
}
