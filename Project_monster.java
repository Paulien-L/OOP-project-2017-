import java.util.concurrent.ThreadLocalRandom;
import static java.lang.Math.random;

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
 * @invar	The protection factor of a monster must be a valid protection factor.
 * 			| isValidProtection(getProtection())
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
 * @author Thomas and Paulien
 *
 * @version version 1
 *
 */
public class Monster {

    /**
     * Create a new monster with given name, protection factor, maximum hitpoints and strength.
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
     *
     * @pre		The given protection factor for the monster must be a valid protection factor.
     * 			| isValidProtection(protection)
     *
     * @post	The name of this new monster is equal to the given name.
     * 			| new.getName() = name
     * @post    The damage of this new monster is equal to the given damage.
     *          new.getDamage() = damage
     * @post	The protection factor of this new monster is equal to the given protection factor.
     * 			| new.getProtection() = protection
     * @post	The maximum hitpoints of this new monster is equal to the given maximum hitpoints.
     * 			| new.getMaxHitpoints() = maxHitpoints
     * @post	The strength of this new monster is equal to the given strength.
     * 			| new.getStrength() = strength
     * @post    The number of anchors of this new monster is equal to the given number of anchors.
     *          | new.getNbOfAnchors = nbOfAnchors
     *
     * @throws IllegalArgumentException
     * 			Throws exception if the given name is not a valid name.
     * 			| ! isValidName(name)
     * @throws IllegalArgumentException
     * 			Throws exception if the given maximum hitpoints isn't a valid value for maximum hitpoints.
     * 			| ! isValidMaxHitpoints(maxHitpoints)
     * @throws IllegalArgumentException
     *          Throws exception if the given number of anchors isn't a valid number of anchors.
     *          | ! isValidNbOfAnchors(nbOfAnchors)
     */
    public Monster(String name, int damage, int protection, int maxHitpoints, int strength, int nbOfAnchors) throws IllegalArgumentException {
        if(isValidName(name)) {
            this.name = name;
        } else {
            throw new IllegalArgumentException(name);
        }
        this.setDamage(damage);

        assert isValidProtection(protection);
        this.protection = protection;

        if(isValidMaxHitpoints(maxHitpoints))
            this.maxHitpoints = maxHitpoints;
        else
            throw new IllegalArgumentException();
        this.hitpoints = this.maxHitpoints;

        this.strength = strength;
        
        if(isValidNbOfAnchors(nbOfAnchors))
			this.nbOfAnchors = nbOfAnchors;
		else
			throw new IllegalArgumentException();
    }

    //NAME
    private String name;
    /**
     * Returns the name of a monster.
     * @return name
     */
  	@Basic @Immutable
    public String getName() {
        return this.name;
    }

    /**
     * Checks the validity of the given name of a monster.
     * @param name
     * @return true if the name starts with a capital letter,
     * 		   and consists of only letters, capitals, numbers, spaces and '
     * 		   | if name.matches("([A-Z][a-zA-Z0-9 ']+)")
     * 		   | 	then isValidName = true
     */
    public boolean isValidName(String name){
        return name.matches("([A-Z][a-zA-Z0-9 ']+)");
    }

    //DAMAGE
    private final int MIN_DAMAGE = 1;
    private static int MAX_DAMAGE = 20;
    private int damage;
    /**
     * Returns the damage of this monster.
     * @return this.damage
     */
    @Basic
    public int getDamage() {
        return this.damage;
    }

    /**
     * Sets damage of this monster to the given damage.
     *
     * @param damage
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
     * 			the damage is set to the minimum damage
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
  	@Basic @Immutable
    public int getMaxDamage() {
        return this.MAX_DAMAGE;
    }

    /**
     *
     * @param MAX_DAMAGE
     * @return true or false
     */
    public boolean isValidMaxDamage(int MAX_DAMAGE) {
        return (MAX_DAMAGE >= MIN_DAMAGE);
    }

    /**
     * Sets the maximum damage of a monster to the given maximum damage
     *
     * @param MAX_DAMAGE
     *
     * @post 	The maximum damage of this monster will be equal to the given maximum damage
     * 			|	new.getMaxDamage() = MAX_DAMAGE
     * @note	Can be set to private when not used/testing is done
     */
    public void setMaxDamage(int newMAX_DAMAGE) {
        if(newMAX_DAMAGE > 0)
        	MAX_DAMAGE = newMAX_DAMAGE;
    }

    //PROTECTION
    private final int protection;
    private final static int MIN_PROTECTION = 1;
    private int maxProtection = 40;

    /**
     * Returns the protection factor of a monster.
     * 	The protection factor of a monster denotes how well it can protect itself in combat.
     */
  	@Basic @Immutable
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
  	@Basic
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
    private int hitpoints;
    private int maxHitpoints;

    /**
     * Returns current hitpoints of a monster.
     * @return hitpoints
     */
  	@Basic
    public int getHitpoints() {
        return this.hitpoints;
    }

    /**
     * Checks if the given value for hitpoints is valid.
     *
     * @param hitpoints
     *
     * @return	True if the value of the given hitpoints is a natural number that is
     * 			smaller than the maximum value of hitpoints.
     * 			| ((hitpoints <= getMaxHitpoints()) && (hitpoints >= 0))
     * 			| 	then result == true
     */
    public boolean isValidHitpoints(int hitpoints) {
        return (hitpoints <= getMaxHitpoints()) && (hitpoints >= 0);
    }

    /**
     * Sets the hitpoints of a monster to a new value.
     *
     * @param newHitpoints
 	 * @post 	If the given maximum hitpoins are valid, then the maximum hitpoints of a monster equal the given value.
	 * 			| if(isValidMaxHitpoints(newMaxHitpoints))
	 * 			|	then this.maxHitpoints = new.newMaxHitpoints
     * @throws IllegalArgumentException
     * 			The new value for hitpoints is not a valid value.
     * 			| isValidHitpoints(newHitpoints) == false
     * 
     * @note	Can be set to private when not used/testing is done
     * 
     */
    public void setHitpoints(int newHitpoints) throws IllegalArgumentException {
        if(isValidHitpoints(newHitpoints)) {
            this.hitpoints = newHitpoints;
        } else
            throw new IllegalArgumentException();
    }

    /**
     * Returns the value for the maximum hitpoints of the monster.
     * @return maxHitpoints
     */
  	@Basic @Immutable
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
    private final int strength;
    public static float averageStrength;

    /**
     * Returns strength of a monster.
     * @return strength
     */
  	@Basic @Immutable
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
    
   private float carryingCapacity;
	/**
	 * Method that calculates the carrying capacity of a monster based on its strength
	 * @return The carrying capacity of a monster expressed as 12kg times the strength of the monster.
	 * 			| carryingCapacity = 12000*monster.getStrength()
	 */
    public float getCarryingCapacity() {
	    	return this.carryingCapacity = 12000*this.getStrength();
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
                DefendingMonster.setHitpoints(DefendingMonster.getHitpoints() - (this.getDamage() + (this.getStrength() - 5) / 3));
            } catch (IllegalArgumentException e){
                DefendingMonster.setHitpoints(0);
            }
        }
    }
    
    //ANCHOR
    /**
	 * Variable that represents the number of anchors a monster has.
	 */
	private int nbOfAnchors;
	/**
	 * Variable that represents the maximum number of anchors.
	 */
	private static int maxNbOfAnchors = 2;
    
	/**
	 * Method that returns the number of anchors a monster possesses.
	 * @return Number of anchors of a monster
	 * 			|nbOfAnchors
	 */	
    public int getNbOfAnchors() {
        return this.nbOfAnchors;
    }
	/**
	 * Method to check if a given number of anchors is valid.
	 * @param 	nbOfAnchors
	 * @return 	True if the given number of anchors is lower than the maximum number of anchors.
	 * 			| nbOfAnchors <= getMaxNbOfAnchors()
	 */
    public boolean isValidNbOfAnchors(int nbOfAnchors) {
        return (nbOfAnchors <= getMaxNbOfAnchors());
    }
	
	/**
	 * Returns the maximum number of anchors monsters can have.
	 * @return	Maximum number of anchors
	 * 			|maxNbOfAnchors
	 */
    public int getMaxNbOfAnchors() {
        return this.maxNbOfAnchors;
    }
	
	/**
	 * Sets the maximum number of anchors to a new value.
	 * @param 	maxNbOfAnchors
	 * @post	The maximum number of anchors will be equal to the new value
	 * 			| maxNbOfAnchors = new.maxNbOfAnchors
	 * @throws 	IllegalArgumentException
	 * 			The new maximumNbOfAnchors is not a valid number of anchors.
	 * 			isValidNbOfMaxAnchors(maxNbOfAnchors) == false
	 * @note	Not used in the current game.
	 */
	private void setMaxNbOFAnchors(int maxNbOfAnchors) throws IllegalArgumentException {
        if(isValidNbOfMaxAnchors(maxNbOfAnchors) == true)
            this.maxNbOfAnchors = maxNbOfAnchors;
        else
            throw new IllegalArgumentException();
    }
	
	/**
	 * Checks if the given number of maximum anchors is valid.
	 * @param maxNbOfAnchors
	 * @return	True if the maximum number of anchors is greater or equal to zero.
	 * 			|(maxNbOfAnchors >= 0)
	 * @note	Not used in the current game.
	 */
    private boolean isValidNbOfMaxAnchors(int maxNbOfAnchors) {
        return (maxNbOfAnchors >= 0)
    }
		
    //WEAPONS
    /**
	 * List of weapons a monster is carrying.
	 */
    private List<Weapon> weapons = new ArrayList<Weapon>();
	
	/**
	* Total weight of the weapons a monster is carrying.
	*/
	private float totalWeight;
	
	/**
	 * Method to get the number of weapons a monster is carrying
	 * @return 	The number of weapons a monster is carrying
	 * 			| weapons.size()
	 */
    public int getNbWeapons() {
        return weapons.size();
    }
	/**
	 * Method to calculate the total weight of the weapons a monster is carrying
	 * @return 	The total weight of the weapons a monster is carrying
	 * 			| totalWeight
	 */
	public float getTotalWeight() {
		for (Weapon weapon: weapons) {
			totalWeight += weapon.getWeight();
		}
		return totalWeight;
	}
	
    /**
	 * Method to check if a monster can obtain a certain weapon
	 * @param weapon
	 * @return 	True if the weapon exists, the monster is a valid holder of the weapon 
	 * 			and the monster has enough carrying capacity to carry the weapon.
	 * 			| if((weapon != null) && (weapon.isValidHolder(this) == true) 
	 * 			| && ((this.getCarryingCapacity() - this.getTotalWeight()) >= weapon.getWeight()))
	 * 			|	then result == true
	 */
	public boolean canHaveAsWeapon(Weapon weapon) {
		return ((weapon != null) && (weapon.isValidHolder(this) == true) && ((this.getCarryingCapacity() - this.getTotalWeight()) >= weapon.getWeight()));
	}
	
	/**
	 * Method for a monster to obtain a weapon.
  	 * @param 	weapon
	 * @post	If the monster can have the given weapon as a weapon, the weapon will be added to the list of weapons a monster is carrying
	 * 			and the holder of the weapon will be set to this monster.
	 * 			| if(canHaveAsWeapon(weapon) == true)
	 * 			|	then weapons.add(weapon)
	 * 			|	and weapon.setHolder(this)
	 * @throws 	IllegalArgumentException
	 * 			The monster cannot have the given weapon as a weapon.
	 * 			canHaveAsWeapon(weapon) == false
	 */
    public void obtainWeapon(Weapon weapon) throws IllegalArgumentException{
        if(canHaveAsWeapon(weapon) == true) {
            weapons.add(weapon);
            weapon.setHolder(this);
        } else
            throw new IllegalArgumentException();
    }
	
	/**
	 * Method to destroy a weapon the monster is carrying.
	 * @param 	weapon
	 * @post	If the weapon is carried by the monster, it will be removed from the list of weapons the monster is carrying
	 * 			and the holder of the weapon will be set to null.
	 * 			| if weapons.contains(weapon)
	 * 			| 	then weapons.remove(weapon)
	 * 			|	and weapon.setHolder(null)
	 * @throws 	IllegalArgumentException
	 */
    public void destroyWeapon(Weapon weapon) throws IllegalArgumentException {
        if(weapons.contains(weapon) == true) {
            weapons.remove(weapon);
            weapon.setHolder(null);
        } else {
            throw new IllegalArgumentException();
        }
    }
	    
    //PURSE
    private Item purse;
    
    /**
     * @param args
     * @throws IllegalArgumentException
     *         If the average strength is not equal to 10
     *         | ! isValidAverageStrength(averageStrength)
     */
    public static void main(String[] args) throws IllegalArgumentException{
        Monster Monster1, Monster2;
        Monster1 = new Monster("Jinx", 5, 5, 20, 10);
        Monster2 = new Monster("Kadabra", 7, 3, 20, 10);

        getAverageStrength(Monster1.strength, Monster2.strength);
        if (!isValidAverageStrength(averageStrength)){
            throw new IllegalArgumentException();
        }
        System.out.println("Monster 1");
        System.out.println("name: " + Monster1.getName());
        System.out.println("damage: " + Monster1.getDamage());
        System.out.println("protection: " + Monster1.getProtection());
        System.out.println("hitpoints: " + Monster1.getMaxHitpoints());
        System.out.println("strength: " + Monster1.getStrength() + "\n");

        System.out.println("Monster 2");
        System.out.println("name: " + Monster2.getName());
        System.out.println("damage: " + Monster2.getDamage());
        System.out.println("protection: " + Monster2.getProtection());
        System.out.println("hitpoints: " + Monster2.getMaxHitpoints());
        System.out.println("strength: " + Monster2.getStrength() + "\n");

        while(!Monster1.hasDied() && !Monster2.hasDied()){
            double start = random();
            if (start < 0.50){
                Monster1.hitOtherMonster(Monster2);
                if (!Monster2.hasDied()){
                    Monster2.hitOtherMonster(Monster1);
                }
            } else {
                Monster2.hitOtherMonster(Monster1);
                if (!Monster1.hasDied()){
                    Monster1.hitOtherMonster(Monster2);
                }
            }
        }
        
        if (!Monster1.hasDied() && Monster2.hasDied()){
            System.out.println("The winner is Monster 1!");
            System.out.println("name: " + Monster1.getName());
            System.out.println("damage: " + Monster1.getDamage());
            System.out.println("protection: " + Monster1.getProtection());
            System.out.println("hitpoints: " + Monster1.getHitpoints());
            System.out.println("strength: " + Monster1.getStrength() + "\n");
        }else if (Monster1.hasDied() && !Monster2.hasDied()) {
            System.out.println("The winner is Monster 2!");
            System.out.println("name: " + Monster2.getName());
            System.out.println("damage: " + Monster2.getDamage());
            System.out.println("protection: " + Monster2.getProtection());
            System.out.println("hitpoints: " + Monster2.getHitpoints());
            System.out.println("strength: " + Monster2.getStrength() + "\n");
        }
    }
}
