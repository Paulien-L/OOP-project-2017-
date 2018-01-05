import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Backpack extends Item {
	
	//CONSTRUCTOR
	public Backpack(float weight, double value, float capacity, Monster holder) throws IllegalArgumentException {
		super(generateBackpackID(), weight, value, holder);
		
		if(isValidCapacity(capacity))
			this.capacity = capacity;
		else
			throw new IllegalArgumentException();
	}
	
	
	//IDENTIFICATION
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
	
	//CONTENTS (defensive)
	private static List<Item> contents = new ArrayList<Item>();
	
	public void addItem(Item item) throws IllegalArgumentException {
		if((item != null) && (item.getHolder() == null || item.getHolder() == this.getHolder()) && (this.getCapacity() - this.getContentWeight() >= item.getWeight()))
			contents.add(item);
		else
			throw new IllegalArgumentException("Cannot add item to backpack.");
	}
	
	public void removeItem(Item item) throws IllegalArgumentException {
		if(contents.contains(item))
			contents.remove(item);
		else
			throw new IllegalArgumentException("Item is not in backpack.");
	}
	
	public static List<Item> getContents() {
		return contents;
	}
	
	//WEIGHT
	private float contentWeight;
	
	public float getContentWeight() {
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
	@Override
	public boolean isValidHolder(Monster holder) {
		if(this.getHolder() == null)
			return true;
		else if(this.getHolder() == holder)
			return true;
		else
			return false;
	}
		
	
}
