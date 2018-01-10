import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * Class of weapons as a kind of item. 
 * In addition to an ID, value, weight and holder, a weapon has a damage.
 * 
 * @invar	The damage of a weapon must be a valid damage.
 * 			| isValidDamage(getDamage())
 * 
 * @invar	The maximum damage of a weapon is equal to the maximum value of all weapons.
 * 			| maxDamage = maxValue
 * 
 * @author Thomas and Paulien
 * 
 * @version 4
 *
 */
public class Weapon extends Item {

	/**
	 * Create a new weapon with a value, weight, damage and holder.
	 * 
	 * @param 	value
	 * 			Value of the weapon.
	 * @param 	weight
	 * 			Weight of the weapon.
	 * @param 	damage
	 * 			Damage of the weapon.
	 * @param 	holder
	 * 			Holder of the weapon.
	 * 
	 * @pre		The given damage of a weapon must be a valid damage for the weapon.
	 * 			| isValidDamage(damage)
	 * 
	 * @effect	The new weapon is initialised as a new item with given value, weight and holder.
	 * 			super(weight, value, holder)
	 * 
	 * @effect	The identification number of the weapon is generated as in the method generateWeaponID()
	 * 			| ID = generateWeaponID()
	 * 
	 * @throws 	IllegalArgumentException
	 * 			The given weight is not a valid weight.
	 * 			| isValidWeight(weight) == false
	 * 
	 * @throws	IllegalArgumentException
	 * 			The given holder is not a valid holder.
	 * 			| isValidHolder(holder) == false
	 */
	public Weapon(double value, float weight, int damage, ItemHolder holder) throws IllegalArgumentException {
		super(generateWeaponID(), weight, value, holder);

		assert(isValidDamage(damage));
		this.damage = damage;
	}


	//IDENTIFACATION (total)
	/**
	 * Variable referencing weapon IDs of all instances of Weapon
	 */
	private static List<Long> weaponIDs =  new ArrayList<Long>();
	
	/**
	 * Method to generate a weapon identification number.
	 * @return 	If the randomly generated ID is not an already existing ID
	 * 			and if the generated ID is larger than or equal to zero and odd,
	 * 			then the weaponID is added to the list of weapon IDs
	 * 			and the generated weaponID is returned.
	 * 			| if(! weaponIDs.contains(weaponID))
	 *			|	 if((weaponID >= 0) && (weaponID%2 != 0))
	 *			|		 then weaponIDs.add(weaponID);
	 *			|			  return weaponID;
	 *
	 * @return 	If the randomly generated ID is not an already existing ID
	 * 			and if the generated ID is smaller than zero and odd,
	 * 			then the absolute value of the weaponID is taken and added to the list of weapon IDs
	 * 			and the weaponID is returned.
	 * 			| if(! weaponIDs.contains(weaponID))
	 * 			| 	if((weaponID < 0) && (weaponID%2 != 0))
	 *			| 		then weaponID = Math.abs(weaponID);
	 *			|			 weaponIDs.add(weaponID);
	 *			|			 return weaponID;
	 *
	 * @return 	If the randomly generated ID is not an already existing ID and if the generated ID is even,
	 * 			then the absolute value of the weaponID is taken and one is added 
	 * 			then the weaponID is added to the list of weapon IDs and the weaponID is returned.
	 * 			| if(! weaponIDs.contains(weaponID))
	 * 			| 	if((weaponID%2 == 0))
	 *			| 		then weaponID = Math.abs(weaponID + 1);
	 *			|			 weaponIDs.add(weaponID);
	 *			|			 return weaponID;
	 *
	 * @return	If the randomly generated ID is an already existing ID,
	 * 			then the ID is generated again
	 * 			| if(weaponIDs.contains(weaponID))
	 * 			| 	then weaponID = generateWeaponID()
	 */
	private static long generateWeaponID() {
		Random r = new Random();
		long range = Long.MAX_VALUE;
		long weaponID = (long) (r.nextDouble()*range);

		if(! weaponIDs.contains(weaponID)) {
			if((weaponID >= 0) && (weaponID%2 != 0)) {
				weaponIDs.add(weaponID);
				return weaponID;
			} else if((weaponID < 0) && (weaponID%2 != 0)) {
				weaponID = Math.abs(weaponID);
				weaponIDs.add(weaponID);
				return weaponID;
			} else {
				weaponID = Math.abs(weaponID + 1);
				weaponIDs.add(weaponID);
				return weaponID;
			}
		} else {
			return weaponID = generateWeaponID();
		}
	}

	//VALUE (nominal)
	/**
	 * Variable referencing the maximum value of a weapon.
	 */
	private static double maxValue = 20;
	
	@Override
	public boolean isValidValue(double value) {
		return ((value >= 0) && (value <= maxValue));
	}
	
	/**
	 * Checks if the given maximum value is a valid maximum value.
	 * @param 	newMaxValue
	 * @return 	True if the given maximum value is higher than or equal to the minimum damage of a weapon.
	 * 			newMaxValue >= minDamage
	 */
	private boolean isValidMaxValue(int newMaxValue) {
		return (newMaxValue >= minDamage);
	}
	
	/**
	 * Sets maximum value of a weapon to a new maximum value.
	 * @param 	newMaxValue
	 * @pre		The new maximum value must be a valid maximum value.
	 * 			| isValidMaxValue(newMaxValue)
	 * 
	 * @post	The maximum value of all weapons will be equal to the given maximum value.
	 * 			| new.getMaxValue() = newMaxValue	
	 */
	private void setMaxValue(int newMaxValue) {
		assert(isValidMaxValue(newMaxValue));
		this.maxValue = newMaxValue;
	}

	private void generateNewMaxValue() {
		Random r = new Random();
		int newMaxValue= r.nextInt((Integer.MAX_VALUE - minDamage) + 1) + minDamage;
		assert(isValidMaxValue(newMaxValue));
		this.maxValue = newMaxValue;
	}

	//DAMAGE (nominal)
	/**
	 * Variable referencing the damage of a weapon.
	 */
	private int damage;
	/**
	 * Variable referencing the minimum damage of a weapon.
	 */
	private final static int minDamage = 1;

	//@Basic
	/**
	 * Returns damage of this weapon.
	 * @return damage
	 */
	public int getDamage() {
		return this.damage;
	}
	
	/**
	 * Checks if the given damage is a valid damage for this weapon.
	 * @param 	damage
	 * @return	True if the given damage is higher than or equal to the minimum damage
	 * 			and the given damage is lower than or equal to the maximum damage.
	 * 			| (damage >= minDamage) && (damage <= this.getMaxDamage())
	 * 
	 */
	public boolean isValidDamage(int damage) {
		return((damage >= minDamage) && (damage <= this.getMaxDamage()));
	}
	
	/**
	 * Returns maximum damage of a weapon (which is the maximum value of all weapons).
	 * @return maxValue
	 */
	public int getMaxDamage() {
		return (int) this.maxValue;
	}
}
