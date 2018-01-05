
public abstract class Item {
	
	//CONSTRUCTOR
	public Item(long ID, float weight, double value, Monster holder) throws IllegalArgumentException {
		this.ID = ID;
		
		if(isValidWeight(weight) == true)
			this.weight = weight;
		else
			throw new IllegalArgumentException();
		
		assert(isValidValue(value));
		this.value = value;
		
		if(isValidHolder(holder) == true)
			this.holder = holder;
		else
			throw new IllegalArgumentException();
	}
	
	//IDENTIFICATION
	private final long ID;

	public long getID() {
		return this.ID;
	}

	//VALUE
	private double value;

	public double getValue() {
		return this.value;
	}

	public static boolean isValidValue(double value){
		return (value >= 0);
	}
	
	private void setValue(double value){
		if(isValidValue(value))
			this.value = value;
	    else
	    	this.value = 0;
	}
	
	//WEIGHT
	private final float weight;

	//@Basic
	public float getWeight() {
		return this.weight;
	}

	public boolean isValidWeight(float weight) {
		return (weight >= 0);
	}
	
	//HOLDER
	private Monster holder;
		
	public Monster getHolder() {
		return this.holder;
	}

	public void setHolder(Monster holder) throws IllegalArgumentException {
		if(isValidHolder(holder) == true) 
			this.holder = holder;
		if(! holder.hasAsEquipment(this))
			holder.equip(this);
		else
			throw new IllegalArgumentException();
	}

	public boolean hasHolder(Item item) {
		return (item.getHolder() != null);
	}
	
	public boolean hasAsHolder(Monster monster) {
		return (this.getHolder() == monster);
	}
	
	public abstract boolean isValidHolder(Monster holder);
	
}
