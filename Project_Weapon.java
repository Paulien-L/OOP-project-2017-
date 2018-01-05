import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Weapon extends Item {

	//CONSTRUCTOR
	public Weapon(double value, float weight, int damage, Monster holder) throws IllegalArgumentException {
		super(generateWeaponID(), weight, value, holder);

		assert(isValidDamage(damage));
		this.damage = damage;
		
		if((isValidHolder(holder) == true) && (holder.canObtain(this) == true)) {
			setHolder(holder);
			holder.obtainItem(this);
		} else {
			throw new IllegalArgumentException();
		}
	}


	//IDENTIFACATION (total)
	private static List<Long> weaponIDs =  new ArrayList<Long>();

	private static long generateWeaponID() {
		Random r = new Random();
		long range = Long.MAX_VALUE;
		long weaponID = (long) (r.nextDouble()*range);

		if(! weaponIDs.contains(weaponID)) {
			if((weaponID >= 0) && (weaponID%2 != 0)) {
				weaponIDs.add(weaponID);
				return weaponID;
			} else if((weaponID < 0) && (weaponID%2 != 0)) {
				weaponIDs.add(weaponID);
				return weaponID = Math.abs(weaponID);
			} else {
				weaponIDs.add(weaponID);
				return weaponID = Math.abs(weaponID + 1);
			}
		} else {
			return weaponID = generateWeaponID();
		}
	}
	

	//WEIGHT (defensive)
	


	//VALUE (nominal)
	public void increaseWeaponValue() {
	}

	public void decreaseWeaponValue() {
	}

	//DAMAGE (nominal)

	private int damage;
	private static int maxDamage = 20;
	private final static int minDamage = 1;

	//@Basic
	public int getDamage() {
		return this.damage;
	}

	public boolean isValidDamage(int damage) {
		if((damage >= minDamage) && (damage <= getMaxDamage()))
			return true;
		else
			return false;
	}

	public int getMaxDamage() {
		return this.maxDamage;
	}

	private boolean isValidMaxDamage(int newMaxDamage) {
		if(newMaxDamage >= minDamage)
			return true;
		else
			return false;
	}

	private void setMaxDamage(int newMaxDamage) {
		assert(isValidMaxDamage(newMaxDamage));
		this.maxDamage = newMaxDamage;
	}

	private void generateNewMaxDamage() {
		Random r = new Random();
		int newMaxDamage = r.nextInt((Integer.MAX_VALUE - minDamage) + 1) + minDamage;
		assert(isValidMaxDamage(newMaxDamage));
		this.maxDamage = newMaxDamage;
	}

	//HOLDER (defenisive)
	@Override
	public boolean isValidHolder(Monster holder) {
		if(holder == null)
			return true;
		else if((holder.getNbFreeAnchors() > 0) && (hasHolder(this) == false))
			return true;
		else if((holder.getNbFreeAnchors() > 0) && (hasHolder(this) == hasAsHolder(holder)))
			return true;
		else
			return false;
	}
}
