package Items;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Weapon {
	
	//CONSTRUCTOR
	public Weapon(double weight) throws IllegalArgumentException {
		if(isValidWeight(weight) == true )
			this.weight = weight;
		else
			throw new IllegalArgumentException();
	}
	
	//IDENTIFACATION (total)
	private long weaponID;
	private List<Long> weapons =  new ArrayList<Long>();
	
	public long getWeaponID() {
		return this.weaponID;
	}
	
	public void setWeaponID() {
		Random r = new Random();
		long range = Long.MAX_VALUE;
		final long weaponID = (long) (r.nextDouble()*range);
		if(isUniqueWeaponId(weaponID) == true)
			if((weaponID >= 0) && (weaponID%2 != 0)) {
				this.weaponID = weaponID;
				weapons.add(this.weaponID);
			} else if(weaponID < 0) {
				this.weaponID = Math.abs(weaponID);
				weapons.add(this.weaponID);
			} else {
				this.weaponID = Math.abs(weaponID + 1);
				weapons.add(this.weaponID);
			}
		else
			setWeaponID();
	}
	
	public boolean isUniqueWeaponId(long weaponID) {
		if(! weapons.contains(weaponID))
			return true;
		else
			return false;				
	}
	
	//WEIGHT (defensive)
	private final double weight;
	
	public double getWeight() {
		return this.weight; //Do some formatting here?
	}
	
	//https://stackoverflow.com/questions/2808535/round-a-double-to-2-decimal-places
	public boolean isValidWeight(double weight) {
		if(BigDecimal.valueOf(weight).scale() < 2)
			if(weight > 0)
				return true;
			else 
				return false;
		else
			return false;
	}
	
	public static void main(String[] args) {
		Weapon balrog = new Weapon(200);
		balrog.setWeaponID();
		System.out.println(balrog.getWeaponID());
	}
}