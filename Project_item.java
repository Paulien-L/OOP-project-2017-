
public abstract class Item {
	
	//CONSTRUCTOR
	public Item(long ID, double value, Monster holder) throws IllegalArgumentException {
		this.ID = ID;
			
		assert(isValidValue(value));
		this.value = value;
		
		if(isValidHolder(holder) == true)
			this.holder = holder;
		else
			throw new IllegalArgumentException();
	}
	
	//IDENTIFICATION
	private static long ID;

	public static long getID() {
		return ID;
	}

	public abstract boolean isValidID(long ID);

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
	
	//HOLDER
	private Monster holder;
		
	public Monster getHolder() {
		return this.holder;
	}

	public void setHolder(Monster holder) throws IllegalArgumentException {
		if(isValidHolder(holder) == true)
			this.holder = holder;
		else
			throw new IllegalArgumentException();
	}

	public boolean hasHolder(Item item) {
		if(item.getHolder() != null)
			return true;
		else
			return false;
	}

	public abstract boolean isValidHolder(Monster holder);
	
}