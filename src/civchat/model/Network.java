package civchat.model;

import java.util.HashSet;
import java.util.Set;

public class Network 
{
	private int id;
	private String name;
	private String owner;
	
	private Set<DirtyNetworkReason> dirty = new HashSet<DirtyNetworkReason>();
	public enum DirtyNetworkReason
	{
		OWNER
	}
	
	public Network(String name, String owner)
	{
		this.name  = name;
		this.owner = owner;
	}
	
	public Network(int id, String name, String owner)
	{
		this.id = id;
		this.name = name;
		this.owner = owner;
	}
	
	public int getId()
	{
		return id;
	}

	public String getName() 
	{
		return name;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
		this.dirty.add(DirtyNetworkReason.OWNER);
	}
	
	public boolean isDirty(DirtyNetworkReason dirtyType)
	{
		return this.dirty.contains(dirtyType);
	}
	
	public boolean isDirty()
	{
		return !this.dirty.isEmpty();
	}
	
	public void clearDirty()
	{
		this.dirty.clear();
	}

	@Override
	public String toString() {
		return "Network [id=" + id + ", name=" + name + ", owner=" + owner
				+ ", dirty=" + dirty + "]";
	}
	
	
}
