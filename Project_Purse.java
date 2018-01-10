/**
 * Class of purses as a kind of item. In addition to an ID, weight, value and holder,
 * a purse has a capacity and number of dukats.
 * 
 * @author Thomas and Paulien
 * 
 * @version 4
 *
 */
public class Purse extends Item {
	
	/**
	 * Create a new purse with an ID, weight, value, holder, capacity and dukats.
	 * 
	 * @param 	ID
	 * 			Identification number of the purse.
	 * @param 	weight
	 * 			Weight of the purse.
	 * @param 	value
	 * 			Value of the purse.
	 * @param 	holder
	 * 			Holder of the purse.
	 * @param 	capacity
	 * 			Capacity of the purse.
	 * @param 	dukats
	 * 			Dukats of the purse.
	 * 
	 * @effect	The new purse is initialised as a new item with given ID, value, weight and holder.
	 * 			super(ID, weight, value, holder)
	 * @effect	The capacity of the purse is set as in the method setCapacity(int capacity)
	 * 			| setCapacity(capacity)
	 * @effect	The dukats of the purse is set as in the method setDukats(int dukats)
	 * 			| setDukats(dukats)
	 * 
	 * @throws 	IllegalArgumentException
	 * 			The given weight is not a valid weight.
	 * 			| isValidWeight(weight) == false
	 * 
	 * @throws	IllegalArgumentException
	 * 			The given holder is not a valid holder.
	 * 			| isValidHolder(holder) == false
	 */
	public Purse(long ID, float weight, double value, ItemHolder holder, int capacity, int dukats)throws IllegalArgumentException{
        super(ID, weight, value, holder);
        
        this.setCapacity(capacity);
        this.setDukats(dukats);
    }
	
	/**
	 * Sets ID of a purse to a new ID.
	 * 
	 * @post	If the given ID is negative, the ID of the purse is set to the absolute value of the given ID.
	 * 			| PurseId = Math.abs(PurseId)
	 * 
	 * @post	If the given ID is zero, the ID of the purse is set to one.
	 * 			| PurseId = 1
	 * 
	 * @post	If the given ID is not negative, not zero and a number of the Fibonacci series, the ID is set to the given ID.
	 * 			| if(c == PurseId)
     *          | 	then super.setID(PurseId)
     *          
     * @post	If the given ID is not negative, not zero and not a number of the Fibonacci series, 
     * 			then the ID is set to a number of the Fibonacci series.
     * 			| if(c != PurseId)
     * 			| 	then super.setID(c)
     * 
     * @effect	The ID is set using the method setID from the super class Item
     * 			| super.setID(purseID)
	 */
    @Override
    public void setID(long PurseId){
        if (PurseId <0){
            PurseId = Math.abs(PurseId);
            setID(PurseId);
        } else if (PurseId == 0) {
            PurseId = 1;
            setID(PurseId);
        } else{
            long a = 1;
            long b = 1;
            long c = 1;
            while(c < PurseId){
                c = a+b;
                a = b;
                b = c;
            }
            if(c == PurseId)
                super.setID(PurseId);
            else
                super.setID(c);
        }
    }

    /**
     * Returns base value of the purse plus the number of dukats.
     */
    public double getValue(){
        return (super.getValue()+this.dukats);
    }
    
    /**
     * Returns base value of the purse.
     */
    public double getOwnValue(){
        return super.getValue();
    }
    
    /**
     * Variable referencing the number of dukats in a purse.
     */
    private int dukats;
    
    /**
     * Returns the number of dukats in a purse.
     * @return dukats
     */
    public int getDukats(){
        return this.dukats;
    }
    
    /**
     * Sets the number of dukats of a purse to a new number of dukats.
     * @param 	newvalue
     * 
     * @post	If the new number of dukats is higher than or equal to zero
     * 			and the new number of dukats is lower than or equal to the capacity of the purse,
     * 			then the number of dukats will be equal to the given number of dukats.
     * 			| if(newvalue >= 0 && newvalue <= this.getCapacity())
     * 			| 	then new.getDukats() == newValue
     * 
     * @post	If the new number of dukats is negative,
     * 			then the new number of dukats is set to zero.
     * 			| if(newvalue < 0)
     * 			|	then new.getDukats() == 0
     * 
     * @effect	If the new number of dukats is higher than the capacity of the purse,
     * 			the purse is thorn as in the method purseTear().
     * 			| if(newvalue > this.getCapacity())
     * 			| 	then purseTear()
     * 
     */
    private void setDukats(int newvalue){
        if(newvalue >= 0 && newvalue <= this.getCapacity())
            this.dukats = newvalue;
        else if (newvalue < 0)
            this.dukats = 0;
        else
            this.purseTear();
    }
    
    /**
     * Method to add dukats to a purse.
     * 
     * @param   numberToAdd
     * 			Number of dukats to add.			
     * 
     * @effect	Sets number of dukats to the current number of dukats plus the number of dukats to add
     * 			as in the method setDukats().
     * 			| setDukats(this.getDukats() + numberToAdd)
     */
    private void addDukats(int numberToAdd){
        setDukats(this.getDukats() + numberToAdd);
    }
    
    /**
     * Method to remove dukats from a purse
     * 
     * @param 	numberToDistract
     * 			Number of dukats to remove.
     * 
     * @effect	If the number of dukats to remove is smaller than or equal to the current number of dukats in the purse,
     * 			then the dukats are removed as in the method setDukats().
     * 			| setDukats(this.getDukats() - numberToDistract)
     */
    private void removeDukats(int numberToDistract){
        if (numberToDistract <= this.dukats)
            setDukats(this.getDukats() - numberToDistract);
        else
            System.out.println("Insufficient dukats to remove");
    }
    
    /**
     * Method to transfer dukats from one purse to another purse.
     * 
     * @param 	otherpurse
     * 			Purse the dukats will be transferred to.
     * 
     * @param 	numberToTransfer
     * 			Number of dukats to transfer.
     * 
     * @effect	If the number of dukats to transfer is smaller than the current number of dukats in the purse,
     * 			then the dukats are removed from one purse as in the method removeDukats(dukats)
     * 			and added to the other purse as in the method addDukats(dukats).
     * 			| this.removeDukats(numberToTransfer)
     * 			| otherpurse.addDukats(numberToTransfer)
     * 
     */
    public void transferDukats(Purse otherpurse, int numberToTransfer){
        if (numberToTransfer <= this.dukats) {
            this.removeDukats(numberToTransfer);
            otherpurse.addDukats(numberToTransfer);
        } else
            System.out.println("Insufficient dukats to transfer, transfer cancelled");
    }
    
    /**
     * Variable referencing the capacity of a purse.
     */
    private int capacity;
    
    /**
     * Returns capacity of this purse.
     * @return capacity
     */
    public int getCapacity(){
        return this.capacity;
    }
    
    /**
     * Method to set the capacity of a purse to a new capacity.
     * 
     * @param capacity
     * 
     * @post	If the given capacity is larger than zero,
     * 			then the capacity of this purse will be equal to the given capacity.
     * 			| if (capacity > 0)
     * 			|	then new.getCapacity() = capacity
     * 
     * @post	If the given capacity is not larger than zero,
     * 			then the capacity of this purse will be equal to one.
     * 			| if(!capacity > 0)
     * 			|	then new.getCapacity() = 1
     * 
     */
    void setCapacity(int capacity){
        if (capacity>0)
            this.capacity = capacity;
        else
            this.capacity = 1;
    }
    
    /**
     * Method to tear a purse dropping all dukats in the purse.
     * 
     * @effect	The value of the purse is set to zero as in the method setValue(value).
     * 			| setValue(0)
     * 
     * @effect	The number of dukats in the purse is set to zero as in the method setDukats(dukats).
     * 			| setDukats(0)
     */
    private void purseTear(){
        this.setValue(0);
        this.setDukats(0);
    }
    
    /**
     * Returns the total weight of a purse as the weight (weight of the purse itself plus weight of the contents).
     * @return	The weight of the purse plus the number of dukats times 50.
     * 			| super.getWeight() + (this.getDukats()*50)
     */
    public float getWeight(){
        return super.getWeight() + (this.getDukats()*50);
    }
    
    /**
     * Returns the weight of the purse itself.
     */
    public float purseWeight(){
        return super.getWeight();
    }

}
