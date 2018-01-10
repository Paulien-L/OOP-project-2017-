/**
 * Interface of item holders to be implemented by all classes that can be the holder of an Item.
 * 
 * @author Thomas and Paulien
 * 
 * @version 1
 *
 */
public interface ItemHolder {
	
	/**
	 * Method to equip an item.
	 * @param 	item
	 * 			Item to be equipped.
	 */
	void equip(Item item);
	
	/**
	 * Checks if the object that implements this interface has the given item as equipment.
	 * @param 	item
	 * 			Item to be checked.
	 */
	boolean hasAsEquipment(Item item);
	
	/**
	 * Checks if the object that implements this interface can obtain the given item.
	 * @param 	item
	 * 			Item to be checked.
	 */
	boolean canObtain(Item item);
}
