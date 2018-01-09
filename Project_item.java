
public abstract class Item {
	
	//CONSTRUCTOR
	public Item(long ID, float weight, double value, ItemHolder holder) throws IllegalArgumentException {
        setID(ID);

        if(isValidWeight(weight))
            this.weight = weight;
        else
            throw new IllegalArgumentException();
        // assert is cannot be in final code
       // assert(isValidValue(value));
        this.value = value;
        
        if(holder == null) {
        	this.holder = holder;
        }
        else if(isValidHolder(holder)) {
            this.holder = holder;
            holder.equip(this);
        } else
            throw new IllegalArgumentException();
    }
	
	//IDENTIFICATION
	private long ID;
	
	public void setID(long ID){
        this.ID = ID;
    }

	public long getID() {
		return this.ID;
	}

	//VALUE
	private double value;

	public double getValue() {
		return this.value;
	}
	
	public abstract boolean isValidValue(double value);
	
	public void setValue(double value) throws IllegalArgumentException {
		if(isValidValue(value))
			this.value = value;
	    else
	    	throw new IllegalArgumentException();
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
	private ItemHolder holder;

    public ItemHolder getHolder() {
        if (holder != null)
            return this.holder;
        else {
            System.out.println("This item has no holder.");
            return null;
        }
    }
    
    public static Monster getIndirectHolder(Item item){
        ItemHolder holder = item.getHolder();
        if (holder instanceof Backpack){
            item = (Item) holder;
            getIndirectHolder(item);
        }
        return (Monster) holder;
    }
    
    public void setHolder(ItemHolder holder) throws IllegalArgumentException {
        if(isValidHolder(holder))
            this.holder = holder;
        else if(! holder.hasAsEquipment(this))
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
	
	public boolean isValidHolder(ItemHolder holder){
        if (holder == null)
            return true;
        else if (holder.canObtain(this))
            return true;
        else
            return false;
    }
	
}
