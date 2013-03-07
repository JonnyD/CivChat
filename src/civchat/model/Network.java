package civchat.model;

import java.util.HashSet;
import java.util.Set;

public class Network 
{
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
}
