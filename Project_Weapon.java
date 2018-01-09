import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Weapon extends Item {

	//CONSTRUCTOR
	public Weapon(double value, float weight, int damage, ItemHolder holder) throws IllegalArgumentException {
		super(generateWeaponID(), weight, value, holder);

		assert(isValidDamage(damage));
		this.damage = damage;
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

	//VALUE (nominal)
	private static double maxValue = 20;
	
	@Override
	public boolean isValidValue(double value) {
		return ((value >= 0) && (value <= maxValue));
	}
	
	public void increaseWeaponValue() {
	}

	public void decreaseWeaponValue() {
	}
	
	private boolean isValidMaxValue(int newMaxValue) {
		return (newMaxValue >= minDamage);
	}
	
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
	private int damage;
	private final static int minDamage = 1;

	//@Basic
	public int getDamage() {
		return this.damage;
	}

	public boolean isValidDamage(int damage) {
		return((damage >= minDamage) && (damage <= this.getMaxDamage()));
	}

	public int getMaxDamage() {
		return (int) this.maxValue;
	}
}
