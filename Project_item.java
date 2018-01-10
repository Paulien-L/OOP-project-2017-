/**
 * An abstract class of Items occurring in the game.
 * 
 * @invar	Each item must have a valid value.
 * 			| isValidValue(getValue())
 * 
 * @invar	Each item must have a valid weight.
 * 			| isValidWeight(getWeight())
 * 
 * @invar	Each item must have a valid holder.
 * 			| isValidHolder(getHolder())
 * 
 * @author Thomas and Paulien
 * 
 * @version 4
 *
 */
public abstract class Item {

	/**
	 * An item will have the imposed super constructor of a given ID, weight, value and holder
	 * @param	ID
	 * 			The identification number of an item.
	 * @param 	weight
	 * 			The weight of an item.
	 * @param 	value
	 * 			The value of an item.
	 * @param 	holder
	 * 			The holder of an item
	 * 
	 * @pre		The given value of an item must be a valid value.
	 * 			| isValidValue(value)
	 * 
	 * @post	The weight of this new item will be equal to the given weight.
	 * 			| new.getWeight() = weight
	 * 
	 * @post	The value of this new item will be equal to the given value.
	 * 			| new.getValue() = value
	 * 
	 * @post	The holder of this new item will be equal to the given holder.
	 * 			| new.getHolder() = holder
	 * 
	 * @effect	The ID of an item is set as in the method setID(ID)
	 * 			| new(setID(ID))	
	 * 
	 * @throws 	IllegalArgumentException
	 * 			The given weight is not a valid weight.
	 * 			| isValidWeight(weight) == false
	 * 
	 * @throws	IllegalArgumentException
	 * 			The given holder is not a valid holder.
	 * 			| isValidHolder(holder) == false
	 * 
	 */
	public Item(long ID, float weight, double value, ItemHolder holder) throws IllegalArgumentException {
		setID(ID);

		if(isValidWeight(weight))
			this.weight = weight;
		else
			throw new IllegalArgumentException();

		this.value = value;

		if(isValidHolder(holder)) {
			this.holder = holder;
			if(holder != null)
				holder.equip(this);
		} else
			throw new IllegalArgumentException();
	}
	
	//IDENTIFICATION
	/**
	 * Variable referencing the identification number of an item.
	 */
	private long ID;
	
	/**
	 * Method to set the ID to the given ID.
	 * @param ID
	 * 
	 * @post  The ID of this item will be equal to the given ID.
	 * 		  | new.ID = ID	
	 */
	public void setID(long ID){
        this.ID = ID;
    }
	
	/**
	 * Returns ID of this item.
	 * @return ID
	 */
	public long getID() {
		return this.ID;
	}

	//VALUE
	/**
	 * Variable referencing the value of an item.
	 */
	private double value;
	
	/**
	 * Returns value of this item.
	 * @return value
	 */
	public double getValue() {
		return this.value;
	}
	
	/**
	 * Checks if the given value is a valid value for this item.
	 * @param value
	 * @return 	True if the value is higher than or equal to zero.
	 * 			| value >= 0
	 */
	public boolean isValidValue(double value) {
		return (value >= 0);
	}
	
	/**
	 * Sets value of an item to a new value
	 * @param 	value
	 * 			The value of an item
	 * 
	 * @post	The value of an item will be equal to the given value.
	 * 			| new.getValue() == value
	 * 
	 * @throws 	IllegalArgumentException
	 * 			The given value is not a valid value.
	 * 			| isValidValue(value) == false
	 */
	public void setValue(double value) throws IllegalArgumentException {
		if(isValidValue(value))
			this.value = value;
	    else
	    	throw new IllegalArgumentException();
	}
	
	//WEIGHT
	/**
	 * Variable referencing the weight of an item.
	 */
	private final float weight;

	//@Basic
	/**
	 * Returns weight of this item.
	 * @return weight
	 */
	public float getWeight() {
		return this.weight;
	}
	
	/**
	 * Checks if the given weight is a valid weight for this item.
	 * @param 	weight
	 * @return	True if the given weight is higher than or equal to zero.
	 * 			| weight >= 0
	 */
	public boolean isValidWeight(float weight) {
		return (weight >= 0);
	}
	
	//HOLDER
	/**
	 * Variable referencing the holder of an item.
	 */
	private ItemHolder holder;
	
	/**
	 * Returns holder of this item
	 * @return holder
	 */
    public ItemHolder getHolder() {
        return this.holder;
    }
    
    /**
     * Returns indirect holder of an item.
     * @param item
     * @return holder
     */
    //NOT TOO SURE HOW THIS WORKS AND THEREFORE NOT TOO SURE ABOUT THE DOCUMENTATION
    public static Monster getIndirectHolder(Item item){
        ItemHolder holder = item.getHolder();
        if (holder instanceof Backpack){
            item = (Item) holder;
            getIndirectHolder(item);
        }
        return (Monster) holder;
    }
    
    /**
     * Sets holder of this item to a new holder
     * 
     * @param holder
     * 
     * @post	
     * 
     * @throws IllegalArgumentException
     */
    public void setHolder(ItemHolder holder) throws IllegalArgumentException {
        if(isValidHolder(holder))
            this.holder = holder;
        else if(! holder.hasAsEquipment(this))
            holder.equip(this);
        else
            throw new IllegalArgumentException();
    }
    
    /**
     * Checks if an item has a holder that is not null.
     * @param item
     * @return 	True if the holder of the item is not null
     * 			| item.getHolder() != null
     */
	public boolean hasHolder(Item item) {
		return (item.getHolder() != null);
	}
	
	/**
	 * Checks if an item has a monster as its holder
	 * @param 	monster
	 * @return	True if the holder of this item is the given monster
	 * 			| this.getHolder() == monster
	 */
	public boolean hasAsHolder(Monster monster) {
		return (this.getHolder() == monster);
	}
	
	/**
	 * Checks if the given holder is a valid holder for the item.
	 * @param holder
	 * @return	True if the holder is null.
	 * 			| holder == null
	 * @return	True if the holder can obtain this item.
	 * 			| holder.canObtain(this)
	 */
	public boolean isValidHolder(ItemHolder holder){
        if (holder == null)
            return true;
        else if (holder.canObtain(this))
            return true;
        else
            return false;
    }
	
}
