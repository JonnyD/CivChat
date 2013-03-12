package civchat.model;

import java.util.HashSet;
import java.util.Set;

public class Antenna 
{	
	private int id;
	private int x;
	private int y;
	private int z;
	private String world;
	private String owner;
	private int networkId;
	private boolean damaged = false;
	
	private Set<DirtyAntennaReason> dirty = new HashSet<DirtyAntennaReason>();
	public enum DirtyAntennaReason
	{
		OWNER,
		NETWORK,
		DAMAGED
	}
	
	public Antenna(int x, int y, int z, String world, String owner, int networkId)
	{
		this.x         = x;
		this.y         = y;
		this.z         = z;
		this.world     = world;
		this.owner     = owner;
		this.networkId = networkId;
	}
	
	public Antenna(int id, int x, int y, int z, String world, String owner, boolean damaged)
	{
		this.id      = id;
		this.x       = x;
		this.y       = y;
		this.z       = z;
		this.world   = world;
		this.owner   = owner;
		this.damaged = damaged;
	}
	
	public Antenna(int id, int x, int y, int z, String world, String owner, int networkId)
	{
		this.id        = id;
		this.x         = x;
		this.y         = y;
		this.z         = z;
		this.world     = world;
		this.networkId = networkId;
	}
	
	public Antenna(int id, int x, int y, int z, String world, String owner, boolean damaged, int networkId)
	{
		this.id        = id;
		this.x         = x;
		this.y         = y;
		this.z         = z;
		this.world     = world;
		this.owner     = owner;
		this.networkId = networkId;
		this.damaged   = damaged;
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
	
	public String getWorld()
	{
		return world;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
		dirty.add(DirtyAntennaReason.OWNER);
	}
	
	public int getNetworkId()
	{
		return networkId;
	}
	
	public void setNetworkId(int networkId)
	{
		this.networkId = networkId;
		dirty.add(DirtyAntennaReason.NETWORK);
	}
	
	public boolean isDamaged()
	{
		return damaged;
	}
	
	public void setDamaged(boolean damaged)
	{
		this.damaged = damaged;
		dirty.add(DirtyAntennaReason.DAMAGED);
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
	
	@Override
	public String toString() 
	{
		return "Antenna [id=" + id + ", x=" + x + ", y=" + y + ", z=" + z
				+ ", world=" + world + ", owner=" + owner + ", networkId="
				+ networkId + ", damaged=" + damaged + ", dirty=" + dirty + "]";
	}
}
