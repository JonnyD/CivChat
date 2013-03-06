package civchat.model;

public class Network 
{
	private String name;
	private String owner;
	
	public Network(String name, String owner)
	{
		this.name = name;
		this.owner = owner;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	
}
