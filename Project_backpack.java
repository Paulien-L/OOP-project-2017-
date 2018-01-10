import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Class of backpacks as a kind of Item.
 * In addition to an ID, weight, value, and holder a backpack has a capacity.
 * 
 * @invar	An item obtained by a backpack must be obtainable by the backpack.
 * 			| canObtain(item)
 * 
 * @author Thomas and Paulien
 * 
 * @version 4
 *
 */
public class Backpack extends Item implements ItemHolder {

    /**
     * Create a new backpack with an ID, weight, value, float, capacity and holder.
     * 
     * @param 	ID
     * 			Identification number of the backpack.
     * @param 	weight
     * 			Weight of the backpack.
     * @param 	value
     * 			Value of the backpack.
     * @param 	capacity
     * 			Capacity of the backpack.
     * @param 	holder
     * 			Holder of the backpack.
     * 
     * @post	The capacity of the backpack will be equal to the given capacity.
     * 			| new.getCapacity() == capacity
     * 
	 * @effect	The new backpack is initialised as a new item with given ID, value, weight and holder.
	 * 			super(ID, weight, value, holder)
	 * 
	 * @throws 	IllegalArgumentException
	 * 			The given weight is not a valid weight.
	 * 			| isValidWeight(weight) == false
	 * 
	 * @throws	IllegalArgumentException
	 * 			The given holder is not a valid holder.
	 * 			| isValidHolder(holder) == false
	 * 
	 * @throws	IllegalArgumentException
	 * 			The given capacity is not a valid capacity.
	 * 			| isValidCapacity(capacity) == false
	 * 
     */
    public Backpack(long ID, float weight, double value, float capacity, ItemHolder holder) throws IllegalArgumentException {
        super(ID, weight, value, holder);
        
        if(isValidCapacity(capacity))
            this.capacity = capacity;
        else
            throw new IllegalArgumentException();
    }


    //IDENTIFICATION
    
    /**
     * Method to set the ID of a backpack to a new value.
     * @param	ID
     * 			The ID of a backpack.
     * 
     * @effect	If the given ID is odd and not negative,
     * 			then the ID will be set to the given ID as in the method setID(ID) in the Item class
     * 			| if (ID%2 != 0 && ID > 0)
     * 			|	then super.setID(ID)
     * 
     * @effect	If the given ID is odd and negative,
     * 			then the ID will be set to the absolute value of the given ID as in the method setID(ID) in the Item class
     * 			| if (ID%2 != 0 && ID < 0)
     * 			|	then super.setID(Math.abs(ID))
     * 
     * @effect	If the given ID is even and negative,
     * 			then the ID will be set to the absolute value of the given ID plus one as in the method setID(ID) in the Item class
     * 			| if (ID%2 == 0 && ID < 0)
     * 			|	then super.setID(Math.abs(ID + 1))
     * 
     * @effect	If the given ID is even and not negative,
     * 			then the ID will be set to the given ID plus one as in the method setID(ID) in the Item class
     * 			| if (ID%2 == 0 && ID > 0)
     * 			|	then super.setID(ID+1)
     */
    @Override
    public void setID(long ID) {
        if (ID%2 != 0 && ID > 0)
            super.setID(ID);
        else if (ID%2 !=0 && ID < 0)
            super.setID(Math.abs(ID));
        else if (ID%2 == 0 && ID < 0)
            super.setID(Math.abs(ID + 1));
        else
            super.setID(ID+1);
    }

    //CONTENTS (defensive)
    /**
     * Variable referencing the contents of a backpack.
     */
    private List<Item> contents = new ArrayList<>();
    
    /**
     * Method to equip an item in a backpack.
     * 
     * @post	If the backpack can obtain the given item,
     * 			then the item is added to the contents of the backpack and the contents of the backpack are sorted according to weight.
     * 			| if(canObtain(item))
     * 			|	then contents.add(item)
     * 
     * @effect	If the backpack can obtain the given item,
     * 			then the holder of the item is set to this backpack using the method setHolder()
     * 			| if(canObtain(item))
     * 			|	then item.setHolder(this)
     * 
     * @throws	IllegalArgumentException
     * 			The item cannot be obtained by the backpack.
     * 			| canObtain(item) == false
     */
    public void equip(Item item) throws IllegalArgumentException {
        if(canObtain(item)){
            contents.add(item);
            item.setHolder(this);
            contents.sort(Comparator.comparing(Item::getWeight));
        } else
            throw new IllegalArgumentException("Cannot add item to backpack.");
    }
    
    /**
     * Checks whether an item can be obtained by a backpack.
     * 
     * @return	True of the item is not null, 
     * 			and if the holder of the item is null or the holder of the item is the holder of this backpack,
     * 			and if the weight of the item does not exceed the remaining capacity of the backpack.
     * 			| if (item != null) && (item.getHolder() == null || item.getHolder() == this.getHolder())
     * 			| && (this.getCapacity() - this.getContentWeight() >= item.getWeight())
     */
    public boolean canObtain(Item item) {
    	boolean valid = false;
        if ((item != null) && item.getHolder() == null && (this.getCapacity() - this.getContentWeight() >= item.getWeight())){
            if (this.getHolder().canObtain(item)) {
                valid = true;
            }
        }
        return valid;
    }
    
    /**
     * Checks if the given item is in the backpack.
     * @return	True if the contents of the backpack contain the given item.
     * 			| this.contents.contains(item)
     */
    public boolean hasAsEquipment(Item item) {
        return (this.contents.contains(item));
    }
    
    /**
     * Method to remove an item from the backpack.
     * @param 	item
     * 			The item to be removed.
     * 
     * @post	If the contents of the backpack contain the given item,
     * 			then the item is removed from the contents.
	 *			| if(contents.contains(item))
     *       	| 	then contents.remove(item)
     * 
     * @effect	If the contents of the backpack contain the given item,
     * 			then the holder of the item is set to null using the setHolder() method.
     * 			| if(contents.contains(item))
     * 			| 	then item.setHolder(null)
     * 
     * @throws 	IllegalArgumentException
     * 			The given item is not in the contents of the backpack.
     * 			| contents.contains(item) == false
     */
    public void removeItem(Item item) throws IllegalArgumentException {
        if(contents.contains(item)){
            contents.remove(item);
            item.setHolder(null);
        } else
            throw new IllegalArgumentException("Item is not in backpack.");
    }
    
    /**
     * Returns contents of the backpack.
     * @return contents
     */
    public List<Item> getContents() {
        return contents;
    }

    //WEIGHT
    
    /**
     * Returns the weight of the content of a backpack.
     * @return 	Content weight as a sum of all weights of items in the content of the backpack
     * 			| contentWeight += content.getWeight()
     */
    public float getContentWeight() {
        float contentWeight = 0;
        for(Item content: contents) {
            contentWeight += content.getWeight();
        }
        return contentWeight;
    }
    
    /**
     * Returns item with the lowest weight in this backpack
     */
    public Item getLowestWeightItem(){
        return contents.get(0);
    }
    
    /**
     * Returns item with the highest weight in this backpack
     */
    public Item getHighestWeightItem(){
        return contents.get(contents.size()-1);
    }
    
    //CAPACITY
    /**
     * Variable referencing the capacity of a backpack
     */
    private final float capacity;
    
    /**
     * Returns the capacity of this backpack.
     * @return capacity
     */
    public float getCapacity() {
        return this.capacity;
    }
    
    /**
     * Checks if the given capacity is a valid capacity for the backpack.
     * @param 	capacity
     * 			The given capacity.
     * 
     * @return	True if the given capacity is larger than or equal to zero.
     * 			| capacity >= 0
     */
    public boolean isValidCapacity(float capacity) {
        return(capacity >= 0);
    }

    //HOLDER
    /**
     * Checks if the given holder is a valid holder for the backpack.
     * @param	holder
     * 			The given holder.
     * 
     * @return	True if the current holder of this backpack is null.
     * 			| this.getHolder() == null
     * 
     *  @return	True if the current holder of this backpack is the given holder.
     *  		| this.getHolder() == holder
     *  
     */
    public boolean isValidHolder(ItemHolder holder) {
        if(this.getHolder() == null)
            return true;
        else if(this.getHolder() == holder)
            return true;
        else
            return false;
    }
    
    /**
     * Returns value of the backpack as a sum of the values of all items in its content.
     */
    public double getValue(){
        double value = super.getValue();
        for (Item item : contents){
            value += item.getValue();
        }
        return value;
    }
    
    /**
     * Returns value of the backpack itself
     * @return 	Value of the backpack.
     * 			| super.getValue()
     */
    public double getOwnValue(){
        return super.getValue();
    }
    
    /**
     * Returns total weight of the backpack as a sum of the weight of this backpack itself and the weight of the contents of this backpack.
     */
    public float getWeight(){
        return (this.getOwnWeight() + this.getContentWeight());
    }
    
    /**
     * Returns weight of this backpack itself.
     * @return 	Weight of this backpack
     * 			| super.getWeight()
     */
    public float getOwnWeight(){
        return super.getWeight();
    }

}
