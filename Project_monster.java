import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


//import be.kuleuven.cs.som.annotate.*;

/**
 * A class of monsters involving a monster's name, damage, protection factor, hitpoints, and strength.
 * Plus a method for combat between two monsters.
 *
 * @invar	The name of a monster must be a valid name.
 * 			| isvalidName(getName())
 *
 * @invar	The damage of a monster must be a valid damage.
 * 			| isValidDamage(getDamage())
 *
 * @invar	The minimum damage of a monster will at all times be 1.
 * 			| MIN_DAMAGE = 1
 *
 * @invar	The protection factor of a monster must be a valid protection factor.
 * 			| isValidProtection(getProtection())
 *
 * @invar	The minimum protection factor of a monster will at all times be 1.
 * 			| MIN_PROTECTION = 1
 *
 * @invar	The hitpoints of a monster must be valid hitpoints.
 * 			| isValidHitpoints(getHitpoints())
 *
 * @invar	The maximum hitpoints of a monster must be valid maximum hitpoints.
 * 			| isValidMaxHitpoints(getMaxHitpoints())
 *
 * @invar	The average strength of two of monsters must be 10.
 * 			| isValidAverageStrength(getAverageStrength())
 *
 * @invar	The number of anchors a monster has must be a valid number of anchors.
 * 			| isValidNbOfAnchors(getNbAnchors())
 *
 * @invar	A monster can only equip an item if that item has no current holder, if the monster has a free anchor and if the monster has enough carrying capacity.
 * 			| canEquip(Item item)
 *
 * @invar	A monster can only obtain an item if that item is not null and the monster has enough carrying capacity.
 * 			| canObtain(Item item)
 *
 * @author Thomas and Paulien
 *
 * @version version 4
 *
 */
public class Monster implements ItemHolder {

    /**
     * Create a new monster with given name, protection factor, maximum hitpoints, strength, number of anchors, weapon, purse and backpack.
     *
     * @param 	name
     * 			The name of the monster.
     * @param 	damage
     *          The damage of the monster.
     * @param 	protection
     * 			The protection factor of the monster.
     * @param 	maxHitpoints
     * 			The maximum hitpoints of the monster.
     * @param 	strength
     * 			The strength of the monster.
     * @param   nbOfAnchors
     *          The number of anchors of the monster
     * @param 	weapon
     * 			The weapon of a monster.
     * @param	backpack
     * 			The backpack of a monster.
     * @param   items
     *          Other items the monster is equipped with
     *
     * @pre		The given protection factor for the monster must be a valid protection factor.
     * 			| isValidProtection(protection)
     *
     * @post	The name of this new monster is equal to the given name.
     * 			| new.getName() = name
     * 
     * @post	The protection factor of this new monster is equal to the given protection factor.
     * 			| new.getProtection() = protection
     * 
     * @post	The maximum hitpoints of this new monster is equal to the given maximum hitpoints.
     * 			| new.getMaxHitpoints() = maxHitpoints
     * 
     * @post	The strength of this new monster is equal to the given strength.
     * 			| new.getStrength() = strength
     * 
     * @post    The number of anchors of this new monster is equal to the given number of anchors.
     *          | new.getNbOfAnchors = nbOfAnchors
     *
     * @effect	The damage of the monster is set as in the method setDamage(int damage)
     * 			| new(setDamage(damage))
     * 
     * @effect	The equipment size if of a monster is set as in the method setEquipmentSize()
     * 			| this.setEquipmentSize()
     * 
     * @effect	The given weapon is equipped in the first anchor as in the method equipInAnchor(item, anchorNb)
     * 			equipInAnchor(weapon, 0)
     * 
     * @effect	The given backpack is equipped in the third anchor as in the method equipInAnchor(item, anchorNb)
     * 			equipInAnchor(backpack, 3)
     * 
     * @effect 	The holder of the given weapon is set to this monster as in the method setHolder()
     * 			| weapon.setHolder(this)
     * 
     * @effect 	The holder of the given backpack is set to this monster as in the method setHolder()
     * 			| backpack.setHolder(this)
     *
     * @throws IllegalArgumentException
     * 			Throws exception if the given name is not a valid name.
     * 			| ! isValidName(name)
     * 
     * @throws IllegalArgumentException
     * 			Throws exception if the given maximum hitpoints isn't a valid value for maximum hitpoints.
     * 			| ! isValidMaxHitpoints(maxHitpoints)
     * 
     * @throws IllegalArgumentException
     *          Throws exception if the given number of anchors isn't a valid number of anchors.
     *          | ! isValidNbOfAnchors(nbOfAnchors)
     */
    public Monster(String name, int damage, int protection, int maxHitpoints, int strength, int nbOfAnchors, Weapon weapon, Backpack backpack, Item... items) throws IllegalArgumentException {
        if(isValidName(name)) {
            this.name = name;
        } else {
            throw new IllegalArgumentException(name);
        }

        this.setDamage(damage);

        assert isValidProtection(protection): "Protection must be higher than or equal to the minimum protection, lower than or equal to the maximum protection "
                + "and a prime number";
        this.protection = protection;

        if(isValidMaxHitpoints(maxHitpoints))
            this.maxHitpoints = maxHitpoints;
        else
            throw new IllegalArgumentException();
        this.hitpoints = this.maxHitpoints;

        this.strength = strength;

        if(isValidNbOfAnchors(nbOfAnchors)) {
            this.nbAnchors = nbOfAnchors;
            this.setEquipmentSize();
            equipInAnchor(weapon, 0);
            equipInAnchor(backpack, 2);
            weapon.setHolder(this);
            backpack.setHolder(this);
        } else
            throw new IllegalArgumentException();

        for(Item item : items){
            backpack.equip(item);
        }
    }

    //NAME
    /**
     * Variable referencing the name of a monster
     */
    private final String name;

    /**
     * Returns the name of a monster.
     * @return name
     */
    // @Basic @Immutable
    public String getName() {
        return this.name;
    }

    /**
     * Checks the validity of the given name of a monster.
     * @param name
     * @return true if the name is not null, starts with a capital letter,
     * 		   and consists of only letters, capitals, numbers, spaces and '
     * 		   | if name != null && name.matches("([A-Z][a-zA-Z0-9 ']+)")
     * 		   | 	then isValidName = true
     */
    public boolean isValidName(String name){
        return ((name != null) && (name.matches("([A-Z][a-zA-Z0-9 ']+)")));
    }

    //DAMAGE
    /**
     * Variable referencing the minimum damage of a monster
     */
    private final static int MIN_DAMAGE = 1;
    /**
     * Variable referencing the maximum damage of a monster
     */
    private static int MAX_DAMAGE = 20;
    /**
     * Variable referencing the damage of a monster
     */
    private int damage;

    /**
     * Returns the damage of this monster.
     * @return this.damage
     */
    // @Basic
    public int getDamage() {
        return this.damage;
    }

    /**
     * Sets damage of this monster to the given damage.
     *
     * @param 	damage
     * 			The damage of a monster.
     *
     * @post 	The damage of this monster will be equal to the given damage
     * 			if the given damage is in the range of the minimum damage and maximum damage.
     * 			| if (damage >= MIN_DAMAGE) && (damage <= getMaxDamage())
     * 			|	then new.getDamage() = damage
     *
     * @post 	If the damage exceeds the range of the maximum damage,
     * 			the damage is set to the maximum damage.
     * 			| if (damage > getMaxDamage())
     * 			|	then new.getDamage() = MAX_DAMAGE
     *
     * @post 	If the given damage is lower than the minimum damage,
     * 			the damage is set to the minimum damage.
     * 			| if (damage < MIN_DAMAGE)
     * 			| then new.getDamage() = MIN_DAMAGE
     */
    public void setDamage(int damage) {
        if((damage >= MIN_DAMAGE) && (damage <= this.getMaxDamage())) {
            this.damage = damage;
        } else if(damage < MIN_DAMAGE) {
            this.damage = MIN_DAMAGE;
        } else {
            this.damage = getMaxDamage();
        }
    }

    /**
     * Returns the maximum damage of a monster.
     * @return MAX_DAMAGE
     */
//  @Basic @Immutable
    public int getMaxDamage() {
        return this.MAX_DAMAGE;
    }

    /**
     * Checks if the given max damage is a valid value for the max damage of a monster
     * @param MAX_DAMAGE
     * @return 	True if the given maximum damage is higher or equal to the minimum damage.
     * 			| MAX_DAMAGE >= MIN_DAMAGE
     */
    public boolean isValidMaxDamage(int MAX_DAMAGE) {
        return (MAX_DAMAGE >= MIN_DAMAGE);
    }

    /**
     * Sets the maximum damage of a monster to the given maximum damage
     *
     * @param newMAX_DAMAGE
     *
     * @post 	The maximum damage of this monster will be equal to the given maximum damage
     * 			if the given maximum damage is a valid maximum damage.
     * 			| if(isValidMaxDamage(newMAX_DAMAGE))
     * 			| 	then new.getMaxDamage() = MAX_DAMAGE
     *
     * @post	If the given maximum damage is not a valid maximum damage, the maximum damage will not be changed.
     * 			| if(isValidMaxDamage(newMAX_DAMAGE) == false)
     * 			| 	then MAX_DAMAGE = MAX_DAMAGE
     *
     * @note	Can be set to private when not used/testing is done
     */
    public void setMaxDamage(int newMAX_DAMAGE) {
        if(isValidMaxDamage(newMAX_DAMAGE))
            MAX_DAMAGE = newMAX_DAMAGE;
    }

    //PROTECTION
    /**
     * Variable referencing the protection factor of a monster.
     */
    private final int protection;
    /**
     * Variable referencing the minimal protection factor of a monster
     */
    private final static int MIN_PROTECTION = 1;
    /**
     * Variable referencing the maximum protection factor of a monster
     */
    private int maxProtection = 40;

    /**
     * Returns the protection factor of a monster.
     * 	The protection factor of a monster denotes how well it can protect itself in combat.
     */
//  @Basic @Immutable
    public int getProtection() {
        return this.protection;
    }

    /**
     * A method to determine if the given protection is a valid protection.
     * @param protection
     *
     * @return 	True if the given protection is larger than the minimum protection value,
     * 			smaller than the maximum protection value, and a prime number
     * 			| result == (protection >= MIN_PROTECTION) && (protection <= getMaxProtection())
     * 			| && (protection%i != 0)
     *
     */
    public boolean isValidProtection(int protection) {
        if((protection >= MIN_PROTECTION) && (protection <= getMaxProtection())) {
            for(int i=2;i*i<=protection;i+=1) {
                if(protection%i == 0)
                    return false;
                else
                    return true;
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns the maximum protection factor of a monster.
     *
     */
//  @Basic
    public int getMaxProtection() {
        return this.maxProtection;
    }

    /**
     * A method to determine if the given maximum protection is a valid maximum protection.
     * @param maxProtection
     *
     * @return 	True if the given maximum protection is larger than the minimum protection value and a prime number
     * 			| result == (protection >= MIN_PROTECTION) && (protection%i != 0)
     *
     */
    public boolean isValidMaxProtection(int maxProtection) {
        if(maxProtection >= MIN_PROTECTION) {
            for(int i=2;i*i<=maxProtection;i++) {
                return maxProtection % i != 0;
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Sets the maximum protection to the given maximum protection.
     *
     * @param newMaxProtection
     *
     * @pre		The given maximum protection is a valid protection.
     * 			| isValidProtection(newMaxProtection)
     *
     * @post	The maximum protection will be equal to the new maximum protection.
     * 			| new.getMaxProtection() = newMaxProtection
     *
     * @note	Can be set to private when not used/testing is done
     *
     */
    public void setMaxProtection(int newMaxProtection) {
        assert isValidMaxProtection(newMaxProtection);
        this.maxProtection = newMaxProtection;
    }

    //HITPOINTS
    /**
     * Variable referencing the current hitpoints of a monster.
     */
    private int hitpoints;
    /**
     * Variable referencing the maximum hitpoints of a monster.
     */
    private int maxHitpoints;

    /**
     * Returns current hitpoints of a monster.
     * @return hitpoints
     */
//  @Basic
    public int getHitpoints() {
        return this.hitpoints;
    }

    /**
     * Checks if the given value for the current hitpoints is valid.
     *
     * @param hitpoints
     *
     * @return	True if the value of the given current hitpoints is a natural number that is
     * 			smaller than the maximum value of current hitpoints.
     * 			| ((hitpoints <= getMaxHitpoints()) && (hitpoints >= 0))
     * 			| 	then result == true
     */
    public boolean isValidHitpoints(int hitpoints) {
        return (hitpoints <= getMaxHitpoints()) && (hitpoints >= 0);
    }

    /**
     * Sets the current hitpoints of a monster to a new value.
     *
     * @param newHitpoints
     * @post 	If the given current hitpoints are valid, then the current hitpoints of a monster equal the given value.
     * 			| if(isValidHitpoints(newHitpoints))
     * 			|	then this.hitpoints = new.newhitpoints
     * @throws IllegalArgumentException
     * 			The new value for the current hitpoints is not a valid value.
     * 			| isValidHitpoints(newHitpoints) == false
     *
     * @note	Can be set to private when not used/testing is done
     *
     */
    private void setHitpoints(int newHitpoints) throws IllegalArgumentException {
        if(isValidHitpoints(newHitpoints)) {
            this.hitpoints = newHitpoints;
        } else
            throw new IllegalArgumentException();
    }

    /**
     * Returns the value for the maximum hitpoints of the monster.
     * @return maxHitpoints
     */
//  @Basic @Immutable
    public int getMaxHitpoints() {
        return this.maxHitpoints;
    }

    /**
     * Checks if the given value for the maximum hitpoints is valid.
     * @param maxHitpoints
     * @return True if the value for the maximum hitpoints is a natural number.
     * 			| (maxHitpoints > 0)
     */
    public boolean isValidMaxHitpoints(int maxHitpoints) {
        return maxHitpoints > 0;
    }

    /**
     * Sets the maximum hitpoints of a monster to a new value.
     *
     * @param newMaxHitpoints
     *
     * @post	The maximum hitpoints of a monster will be equal to the given maximum hitpoints.
     * 			| new.getMaxHitpoints() = maxHitpoints
     *
     * @throws IllegalArgumentException
     * 			The new value for the maximum hitpoints is not a valid value.
     * 			| isValidMaxHitpoints(newMaxHitpoints) == false
     *
     * @note	Can be set to private when not used/testing is done
     *
     */
    public void setMaxHitpoints(int newMaxHitpoints) throws IllegalArgumentException {
        if(isValidMaxHitpoints(newMaxHitpoints))
            this.maxHitpoints = newMaxHitpoints;
        else
            throw new IllegalArgumentException();
    }

    /**
     * Checks if a monster has died.
     * @return 	True if the hitpoints of a monster reach zero.
     * 			| (hitpoints == 0)
     */
    public boolean hasDied() {
        return this.hitpoints == 0;
    }

    //STRENGTH
    /**
     * Variable referencing a monster's strength.
     */
    private final int strength;
    /**
     * Variable referencing the average strength of two monsters.
     */
    public static float averageStrength;

    /**
     * Returns strength of a monster.
     * @return strength
     */
//  @Basic
    public int getStrength(){
        return strength;
    }

    /**
     * Returns the average strength of two monsters.
     * @param s1 The strength of the first monster.
     * @param s2 The strength of the second monster.
     * @return averageStrength The average strength.
     * 		   | (s1+s2)/n
     */
    public static float getAverageStrength(int s1, int s2) {
        return averageStrength = ((float)s1+s2)/2;
    }
    /**
     * Checks if the average strength of two monsters is valid.
     * @param average
     * @return 	True if the average strength of two monsters is 10
     * 			|(average == 10)
     */
    public static boolean isValidAverageStrength(float average){
        return average == 10;
    }

    /**
     * Method that calculates the carrying capacity of a monster based on its strength
     * @return The carrying capacity of a monster expressed as 12kg times the strength of the monster.
     * 			| carryingCapacity = 12000*monster.getStrength()
     */
    public float getCarryingCapacity() {
        float carryingCapacity = 12000*this.getStrength();
        return carryingCapacity;
    }

    //HITTING
    /**
     * A method to hit another monster
     * @param DefendingMonster
     *        The monster that gets hit
     *
     */
    public void hitOtherMonster(Monster DefendingMonster){
        int randomNum = ThreadLocalRandom.current().nextInt(0, 31);
        int battlevalue;
        if (randomNum < this.getHitpoints()){
            battlevalue = randomNum;
        } else {
            battlevalue = this.getHitpoints();
        }
        if (battlevalue > DefendingMonster.getProtection()) {
            try {
                if (this.getEquipment()[1] instanceof Weapon)
                    DefendingMonster.setHitpoints(DefendingMonster.getHitpoints() - (this.getDamage() + ((Weapon)this.getEquipment()[1]).getDamage() +(this.getStrength() - 5) / 3));
                else
                    DefendingMonster.setHitpoints(DefendingMonster.getHitpoints() - (this.getDamage() + (this.getStrength() - 5) / 3));
            } catch (IllegalArgumentException e){
                DefendingMonster.setHitpoints(0);
            }
        }
    }

    //ANCHOR
    /**
     * Variable referencing the number of anchors a monster has.
     */
    private final int nbAnchors;
    /**
     * Variable referencing the minimum number of anchors a monster has.
     */
    private final static int minNbOfAnchors = 3;
    /**
     * Variable referencing the equipment a monster has.
     */
    private Item[] equipment;

    /**
     * Returns equipment of a monster.
     * @return equipment
     */
    public Item[] getEquipment() {
        return this.equipment;
    }



    /**
     * Method to set the size of the equipment for a monster based on its number of anchors.
     * @post 	The equipment size of a monster will be equal to the number of anchors of a monster.
     * 			| new.getEquipment().length() = equipment.length()
     */
    //NOT TOO SURE ABOUT THIS DOCUMENTATION
    public void setEquipmentSize(){
        this.equipment = new Item[this.nbAnchors];
    }

    /**
     * Method that returns the number of anchors a monster possesses.
     * @return nbAnchors
     */
//  @Basic @Immutable
    public int getNbAnchors() {
        return this.nbAnchors;
    }

    /**
     * Returns number of items a monster has equipped.
     * @return Number of equipped items
     * 			| equippedItems
     */
    public int getNbEquippedItems() {
        int equippedItems = 0;
        for (Item equippedItem : equipment){
            if (equippedItem != null)
                equippedItems++;
        }
        return equippedItems;
    }

    /**
     * Calculates the number of anchors of a monster that are not occupied by equipment.
     * @return 	Number of free anchors
     * 			| this.getNbAnchors() - this.getNbEquippedItems()
     */
    public int getNbFreeAnchors() {
        return (this.getNbAnchors() - this.getNbEquippedItems());
    }

    /**
     * Method to check if a given number of anchors is valid.
     * @param 	nbOfAnchors
     * @return 	True if the given number of anchors is lower than the maximum number of anchors.
     * 			| nbOfAnchors <= getMaxNbOfAnchors()
     */
    public boolean isValidNbOfAnchors(int nbOfAnchors) {
        return (nbOfAnchors >= minNbOfAnchors);
    }


    //EQUIPMENT
    /**
     * Checks if a monster has a certain item in their equipment.
     * @return 	True if the equipment of a monster contains the given item.
     * 			| this.equipment.contains(item)
     */
    public boolean hasAsEquipment(Item item) {
        return (Arrays.asList(this.equipment).contains(item));
    }

    /**
     * Checks if the monster can obtain an item in its equipment.
     * @param 	item
     * @return 	True if the item does not have a current holder,
     * 			the monster has at least one free anchor
     * 			and the weight of the item does not exceed the remaining carrying capacity of the monster.
     * 			| (item.getHolder() == null) 
     * 			| && (this.getNbFreeAnchors() > 0)
     * 			| && (((this.getCarryingCapacity() - this.getEquipmentLoad()) >= item.getWeight()))
     * 
     * @note	"I can hold this item right now."
     */
    public boolean canObtain(Item item) {
        return ((item.getHolder() == null) && (this.getNbFreeAnchors() > 0)) && (((this.getCarryingCapacity() - this.getEquipmentLoad()) >= item.getWeight()));
    }
    
    /**
     * Checks if the monster can add an item to its backpack
     * @param 	item
     * @return 	True if the item does not have a current holder and if the monster has at least one free anchor.
     * 			| (item.getHolder() == null) && (this.getNbFreeAnchors() > 0)
     * 
     * @note	"I have enough carrying capacity to add this Item to my backpack"
     */
	public boolean canEquip(Item item) {
        return ((item != null) && ((this.getCarryingCapacity() - this.getEquipmentLoad()) >= item.getWeight()));
	}

    //no method to equip in a specific spot
    /**
     * Method to add an item to a monsters equipment.
     * @post	If the monster can equip the item, the item will be added to an empty spot in the equipment
     * 			and the holder of the item will be set to this Monster.
     * 			| if(canEquip(item) == true)
     * 			| 	then equippedItem = item
     *          |    	 item.setHolder(this)
     *
     * @effect	The equipment load of the monster will be calculated as in the method calculateEquipmentLoad()
     * 			| calculateEquipmentLoad()
     *
     * @throws IllegalArgumentException
     * 			The given item cannot be equipped.
     * 			| canEquip(item) == false
     */
    public void equip(Item item) throws IllegalArgumentException {
        this.calculateEquipmentLoad();
        if(canObtain(item) == true) {
            for (Item equippedItem : equipment){
                if (equippedItem == null) {
                    equippedItem = item;
                    item.setHolder(this);
                    break;
                }
            }
        }
        else
            throw new IllegalArgumentException();
    }

    /**
     * Method to equip an item in an anchor.
     * @param item
     * @param anchorNb
     *
     * @post	If the item can be equipped by the Monster, the item will be added to equipment at the given anchor number
     * 			and the holder of the item is set to this Monster.
     * 			| if(canEquip(item))
     * 			|	then equipment[anchorNb] = item
     *         			 item.setHolder(this)
     *
     * @effect	The equipment load of the monster will be calculated as in the method calculateEquipmentLoad()
     * 			| calculateEquipmentLoad()
     * 
     * @throws	IndexOutOfBoundsException
     * 			The given anchorNb is larger than the size of the array or negative.
     * 			| anchorNb > this.nbAnchors - 1 || anchorNb < 0
     * 
     * @throws	IllegalArgumentException
     * 			The given anchor number already has equipment at that slot.
     * 			| equipment[anchorNb] != null
     *
     * @throws IllegalArgumentException
     * 			The given item cannot be equipped.
     * 			| canEquip(item) == false
     * 
     */
    public void equipInAnchor(Item item, int anchorNb) throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
        this.calculateEquipmentLoad();
        if((anchorNb > this.nbAnchors - 1) || (anchorNb < 0)) {
            throw new ArrayIndexOutOfBoundsException();
        }
        else if(canObtain(item)) {
            if(equipment[anchorNb] == null){
                equipment[anchorNb] = item;
                item.setHolder(this);
            } else
            	throw new IllegalArgumentException("Cannot equip in achor that is occupied.");
        } else
            throw new IllegalArgumentException();
    }

    /**
     * Method to unequip an item.
     * @param item
     *
     * @post	If the equipment of a Monster contains the given item, then the item is removed from the equipment
     * 			and the holder of the item is set to null.
     * 			| if this.equipment.contains(item)
     * 			|	then equipment[i] = null
     *      	|		 item.setHolder(null)
     *
     * @effect	The equipment load of the monster will be calculated as in the method calculateEquipmentLoad()
     * 			| calculateEquipmentLoad()
     *
     * @throws IllegalArgumentException
     * 			The given item is not equipped by the monster.
     * 			this.equipment.contains(item) == false
     *
     * @throws NullPointerException
     * 			The given item is null
     * 			item == null
     */
    public void unequip(Item item) throws IllegalArgumentException, NullPointerException {
        if(item == null)
            throw new NullPointerException();
        else if(Arrays.asList(this.equipment).contains(item) == true) {
            int i = Arrays.asList(this.equipment).indexOf(item);
            equipment[i] = null;
            item.setHolder(null);
            this.calculateEquipmentLoad();
        } else
            throw new IllegalArgumentException();
    }

    /**
     * Method to swap an equipped item with another item.
     * @param itemToUnequip
     * @param itemToEquip
     *
     * @effect	The item to unequip is unequipped as in the method unequip.
     * 			| unequip(itemToUnequip)
     *
     * @effect	The item to equip is equipped as in the method equipInAnchor.
     * 			| equipInAnchor(itemToEquip, i)
     *
     * @throws	IllegalArgumentException
     *			The given item is not equipped by the monster.
     * 			this.equipment.contains(item) == false
     *
     * @throws IllegalArgumentException
     * 			The given item cannot be equipped.
     * 			| canEquip(item) == false
     *
     * @throws 	NullPointerException
     * 			The given item is null
     * 			item == null
     */
    public void swapItem(Item itemToUnequip, Item itemToEquip) throws IllegalArgumentException, NullPointerException {
        int i = Arrays.asList(this.equipment).indexOf(itemToUnequip);
        unequip(itemToUnequip);
        equipInAnchor(itemToEquip, i);
    }
    
    /**
     * Returns all IDs of items a monster has in its equipment or backpacks
     * @return  allItems
     * 			
     */
    public List<Long> getAllItemIDs(){
        List<Long> allItems = new ArrayList<>();
        for (Item item : equipment){
            if (item != null)
                allItems.add(item.getID());
        }
        for (Backpack bp : this.getEquippedBackpacks()){
            for (Item item : bp.getContents()){
                allItems.add(item.getID());
            }
        }
        return allItems;
    }
    
    /**
     * Total weight of the items a monster has in its equipment.
     */
    private float equipmentLoad;

    /**
     * Returns current equipment load of a monster.
     * @return equipmentLoad
     */
    public float getEquipmentLoad(){
        return this.equipmentLoad;
    }

    /**
     * Method to calculate the total weight of the items a monster has in its equipment
     * @post	The equipment load of a Monster will be equal to the sum of the weights of each item in a Monster's equipment,
     * 			if the item is not null
     * 			| for(Item item: this.equipment)
     *			|	if(item != null)
     *			|		then equipmentLoad += item.getWeight();
     */
    public void calculateEquipmentLoad() {
        float equipmentLoad = 0;
        for(Item item: this.equipment) {
            if(item != null)
                equipmentLoad += item.getWeight();
        }
        this.equipmentLoad = equipmentLoad;
    }

    /**
     * Total value of the items a monster has in its equipment.
     */
    private double equipmentValue;

    /**
     * Returns value of a monster's equipment
     * @return equipmentValue
     */
    public double getEquipmentValue() {
        return this.equipmentValue;
    }

    /**
     * Method to calculate the total value of the items a monster has in its equipment
     * @post	The equipment value of a Monster will be equal to the sum of the values of each item in a Monster's equipment,
     * 			if the item is not null
     * 			| for(Item item: this.equipment)
     * 			|	if(item != null)
     * 			|		then value += item.getValue()
     */
    public void calculateEquipmentValue() {
        double value = 0;
        for (Item item : this.equipment){
            if(item != null)
                value += item.getValue();
        }
        this.equipmentValue = value;
    }

    /**
     * Method to destroy a weapon the monster is carrying.
     * @param 	weapon
     *
     * @post	If the weapon is carried by the monster, it will be removed from the list of weapons the monster is carrying
     * 			and the holder of the weapon will be set to null.
     * 			| if this.equipment.contains(weapon)
     * 			| 	then equipment[i] = null
     * 			|	and weapon.setHolder(null)
     *
     * @throws 	IllegalArgumentException
     * 			The weapon is not in the equipment of the monster.
     * 			this.equipment.contains(weapon) == false
     *
     * @throws 	NullPointerException
     * 			The given weapon is null
     * 			weapon == null
     *
     */
    public void destroyWeapon(Weapon weapon) throws IllegalArgumentException, NullPointerException {
        if(weapon == null)
            throw new NullPointerException();
        else if(Arrays.asList(this.equipment).contains(weapon) == true) {
            weapon.setHolder(null);
            int i = Arrays.asList(this.equipment).indexOf(weapon);
            equipment[i] = null;
            this.calculateEquipmentLoad();
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Method to add backpacks in the equipment to a list of equipped backpacks.
     * @return A list of equipped backpacks
     * 			equippedBackpacks
     *
     * @post	If an item in the equipment of a Monster is a backpack
     * 			then the backpack is added to the list of equipped backpacks.
     * 			| for (Item item : equipment)
     *      	|	if (item instanceof Backpack)
     *          |		then equippedBackpacks.add((Backpack)item)
     *
     */
    public ArrayList<Backpack> getEquippedBackpacks(){
        ArrayList<Backpack> equippedBackpacks = new ArrayList<>();
        for (Item item : equipment){
            if (item instanceof Backpack)
                equippedBackpacks.add((Backpack)item);
        }
        return equippedBackpacks;
    }

    /**
     * Method to store an item in a backpack equipped by the monster.
     * @param item
     *
     * @post	If the item can be obtained by the monster, and one of the backpacks of a Monster has enough capacity to carry the weight of the item.
     * 			then the item is added to the backpack and the holder of the item is set to this Monster.
     * 			| if(canObtain(item))
     * 			| 	if (bp.canObtain(item)
     * 			|		then bp.equip(item)
     * 			|		item.setHolder(this)
     *
     * @effect Adding the backpack uses the method equip in Backpack
     * 			| bp.equip(item)
     *
     * @effect	The equipment load of the monster will be calculated as in the method calculateEquipmentLoad()
     * 			| calculateEquipmentLoad()
     *
     * @throws IllegalArgumentException
     * 			The item cannot be obtained by the monster
     * 			| canObtain(item) == false
     * 
     * @throws	NullPointerException
     * 			The item to be stored cannot be null
     * 			| item != null
     *
     */
    public void storeInBackpack(Item item) throws IllegalArgumentException, NullPointerException {
        if(item == null)
        	throw new NullPointerException();
        else if(this.canObtain(item)) {
            for(Backpack bp : this.getEquippedBackpacks()){
                if(bp.canObtain(item)) {
                    bp.equip(item);
                    item.setHolder(bp);
                    break;
                }
            }
        } else
            throw new IllegalArgumentException();
    calculateEquipmentLoad();
    }
  }
