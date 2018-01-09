import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Backpack extends Item implements ItemHolder {

    //CONSTRUCTOR
    public Backpack(long ID, float weight, double value, float capacity, ItemHolder holder) throws IllegalArgumentException {
        super(ID, weight, value, holder);

        if(isValidCapacity(capacity))
            this.capacity = capacity;
        else
            throw new IllegalArgumentException();
    }


    //IDENTIFICATION

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

    /* I dont think we need to generate a backpack ID, it is good enough to ask for one and correct it if it is wrong.
    private static long generateBackpackID() {
        Random r = new Random();
        long range = Long.MAX_VALUE;
        long backpackID = (long) (r.nextDouble()*range);
        if((backpackID >= 0) && (backpackID%2 != 0)) {
            return backpackID;
        } else if((backpackID < 0) && (backpackID%2 != 0)) {
            return backpackID = Math.abs(backpackID);
        } else {
            return backpackID = Math.abs(backpackID + 1);
        }
    }
    */

    //CONTENTS (defensive)
    private List<Item> contents = new ArrayList<>();

    public void equip(Item item) throws IllegalArgumentException {
        if(canObtain(item)){
            contents.add(item);
            item.setHolder(this);
        } else
            throw new IllegalArgumentException("Cannot add item to backpack.");
    }

    public boolean canObtain(Item item){
        boolean valid = false;
        if ((item != null) && (item.getHolder() == null || item.getHolder() == this.getHolder()) && (this.getCapacity() - this.getContentWeight() >= item.getWeight()))
            valid = true;
        return valid;
    }

    public boolean hasAsEquipment(Item item) {
        return (this.contents.contains(item));
    }

    public void removeItem(Item item) throws IllegalArgumentException {
        if(contents.contains(item)){
            contents.remove(item);
            item.setHolder(null);
        } else
            throw new IllegalArgumentException("Item is not in backpack.");
    }

    public List<Item> getContents() {
        return contents;
    }

    //WEIGHT

    public float getContentWeight() {
        float contentWeight = 0;
        for(Item content: contents) {
            contentWeight += content.getWeight();
        }
        return contentWeight;
    }


    //CAPACITY
    private final float capacity;

    public float getCapacity() {
        return this.capacity;
    }

    public boolean isValidCapacity(float capacity) {
        return(capacity >= 0);
    }

    //HOLDER

    public boolean isValidHolder(ItemHolder holder) {
        if(this.getHolder() == null)
            return true;
        else if(this.getHolder() == holder)
            return true;
        else
            return false;
    }

    public double getValue(){
        double value = super.getValue();
        for (Item item : contents){
            value += item.getValue();
        }
        return value;
    }
    public double getOwnValue(){
        return super.getValue();
    }

    public float getWeight(){
        return getOwnWeight() + getContentWeight();
    }

    public float getOwnWeight(){
        return super.getWeight();
    }


	@Override
	public boolean isValidValue(double value) {
		// TODO Auto-generated method stub
		return false;
	}

}
