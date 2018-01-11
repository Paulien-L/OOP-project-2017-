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
     * @effect	The ID of the backpack is set to a new ID as in the method setID(long ID)
     * 			| setID(ID)
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
    public final void setID(long ID) {
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
     * 			then the item is added to the contents of the backpack.
     * 			| if(canObtain(item))
     * 			|	then contents.add(item)
     *
     * @effect	If the backpack can obtain the given item,
     * 			then the holder of the item is set to this backpack using the method setHolder()
     * 			| if(canObtain(item))
     * 			|	then item.setHolder(this)
     * @effect  The Items in the backpack will get sorted by weight after addition of the new item.
     *          |contents.sort(Comparator.comparing(Item::getWeight))
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
     * A method to equip many items at once.
     * @post    Each item will get added to the backpack, if the preconditions of the equip method are fulfilled
     *          |for (item in items)
     *              equip(item)
     */
    public void equipMany(Item... items){
        for (Item item : items)
            equip(item);
    }

    /**
     * Checks whether an item can be obtained by a backpack.
     * 
     * @param	item
     * 			Item to be checked
     *
     * @return	True if the item is not null, the holder of the item is null, 
     * 			the weight of the item does not exceed the remaining capacity of the backpack
     * 			and if the holder of this backpack is an instance of Backpack, if the holder of the backpack can obtain the item.
     * 			| (item != null) && (item.getHolder() == null)  && (this.getCapacity() - this.getContentWeight()) >= item.getWeight()) == true
     * 			| if (this.getHolder() instanceof Backpack) == true
     * 			| 	(this.getHolder().canObtain(item)) == true
     * 
     * @return	True if the item is not null, the holder of the item is null, 
     * 			the weight of the item does not exceed the remaining capacity of the backpack
     * 			and if the holder of this backpack is an instance of Monster, if the holder can obtain this item.
     *			| (item != null) && (item.getHolder() == null)  && (this.getCapacity() - this.getContentWeight()) >= item.getWeight()) == true
     * 			| if (this.getHolder() instanceof Backpack) == true
     * 			| 	(this.getHolder().canObtain(item)) == true
     * 
     * @return	True if the item is not null, the holder of the item is null, 
     * 			the weight of the item does not exceed the remaining capacity of the backpack
     * 			and the holder is not an instance of Monster or Backpack.
     * 			| (item != null) && (item.getHolder() == null)  && (this.getCapacity() - this.getContentWeight()) >= item.getWeight()) == true
     * 			| (this.getHolder() instanceof Backpack) == false
     * 			| (this.getHolder() instanceof Monster) == false
     * 
     */
    public boolean canObtain(Item item){
        boolean valid = false;
        if ((item != null) && item.getHolder() == null && (this.getCapacity() - this.getContentWeight()) >= item.getWeight()){
            if (this.getHolder() instanceof Backpack){
                if (this.getHolder().canObtain(item))
                    valid = true;
            } else if (this.getHolder() instanceof Monster){
                if (((Monster)this.getHolder()).canEquip(item))
                    valid = true;
            } else {
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
        if(getContents().contains(item)){
            contents.remove(item);
            item.setHolder(null);
        } else
            throw new IllegalArgumentException("Item is not in backpack.");
    }

    /**
     * Returns contents of this backpack.
     * @return contents
     */
    public List<Item> getContents() {
        return this.contents;
    }

    //WEIGHT

    /**
     * Returns the weight of the content of a backpack.
     * @return 	Content weight as a sum of all weights of items in the content of the backpack
     * 			| contentWeight += content.getWeight()
     */
    public float getContentWeight() {
        float contentWeight = 0;
        if(this.contents == null) {
        	contentWeight = 0;
        }
        else if(this.contents != null) {
        	for(Item content: contents) {
            contentWeight += content.getWeight();
        	}
        }
        return contentWeight;
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

    /**
     * A method to get the lowest weight item in the backpack.
     * @return the first item of the backpack, which is sorted on weight, so the lowest weight item.
     *         |contents.get(0)
     */
    public Item getLowestWeightItem(){
        return this.contents.get(0);
    }

    /**
     * A method to get the highest weight item in the backpack.
     * @return the last item of the backpack, which is sorted on weight, so the highest weight item.
     *         |contents.get(contents.size-1)
     */
    public Item getHighestWeightItem(){
        return this.contents.get(this.contents.size()-1);
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
}
