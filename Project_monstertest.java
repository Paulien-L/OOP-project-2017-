import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Before;
import org.junit.Test;

public class MonsterTest {
	
	private Monster monsterLegal;
	private Weapon weaponLegal;
	private Purse purseLegal;
	private Backpack backpackLegal;

	@Before
	public void setUp() throws Exception {
		weaponLegal = new Weapon(10, 5, 5, null);
		purseLegal = new Purse(111, 0, 10, null, 10, 0);
		backpackLegal = new Backpack(110, 5, 5, 5, null);
		
		monsterLegal = new Monster("Grog", 5, 5, 10, 100, 4, weaponLegal, backpackLegal, purseLegal);
	}
	
	//CONSTRUCTOR
    @Test
    public final void constructor_LegalCase() {
        assertEquals("Grog", monsterLegal.getName());
        assertEquals(5, monsterLegal.getDamage());
        assertEquals(5, monsterLegal.getProtection());
        assertEquals(10, monsterLegal.getMaxHitpoints());
        assertEquals(100, monsterLegal.getStrength());
        assertEquals(4, monsterLegal.getNbAnchors());
        assertTrue(monsterLegal.hasAsEquipment(weaponLegal));
        assertTrue(monsterLegal.hasAsEquipment(backpackLegal));
    }
    
    @Test
    public final void constructor_Throws() {
        assertThrows(IllegalArgumentException.class, () -> {
            Monster myMonster = new Monster("azrael", 10, 11, 10, 20 ,3, weaponLegal,  backpackLegal);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            Monster yourMonster = new Monster("Azrael", 10, 11, -44, 20, 3, weaponLegal, backpackLegal);
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            Monster hisMonster = new Monster("Azrael", 10, 11, 10, 20, -3, weaponLegal, backpackLegal);
        });
    }
    
    //NAME
    @Test
    public final void getName_SingleCase() {
        assertEquals("Grog", monsterLegal.getName());
    }
    
    @Test
    public final void isValidName_TrueCase() {
        assertTrue(monsterLegal.isValidName("Grog"));
    }
    
    @Test
    public final void isValidName_FalseCase() {
        assertFalse(monsterLegal.isValidName("Grog2.0"));
    }
    
    //DAMAGE
    @Test
    public final void getDamage_SingleCase() {
        assertEquals(5, monsterLegal.getDamage());
    }
    
    @Test
    public final void setDamage_OverMaximum() {
        monsterLegal.setDamage(40);
        assertEquals(20, monsterLegal.getDamage());
    }

    @Test
    public final void setDamage_UnderMinimum() {
        monsterLegal.setDamage(-20);
        assertEquals(1, monsterLegal.getDamage());
    }

    @Test
    public final void getMaxDamage_SingleCase() {
        assertEquals(20, monsterLegal.getMaxDamage());
    }

    @Test
    public final void setMaxDamage_LegalCase() {
        monsterLegal.setMaxDamage(40);
        assertEquals(40, monsterLegal.getMaxDamage());
    }

    @Test
    public final void setMaxDamage_IllegalCase() {
        monsterLegal.setMaxDamage(-4);
        assertEquals(20, monsterLegal.getMaxDamage());
    }
    
  //PROTECTION
    @Test
    public final void getProtection_SingleCase() {
        assertEquals(5, monsterLegal.getProtection());
    }

    @Test
    public final void isValidProtection_TrueCase() {
        assertTrue(monsterLegal.isValidProtection(11));
    }

    @Test
    public final void isValidProtection_FalseCase() {
        assertFalse(monsterLegal.isValidProtection(-24)); //Less than minimum protection
        assertFalse(monsterLegal.isValidProtection(42)); //More than max protection
        assertFalse(monsterLegal.isValidProtection(4)); //Not a prime
    }

    @Test
    public final void getMaxProtection_SingleCase() {
        assertEquals(40, monsterLegal.getMaxProtection());
    }

    @Test
    public final void isValidMaxProtection_TrueCase() {
        assertTrue(monsterLegal.isValidMaxProtection(11));
    }

    @Test
    public final void isValidMaxProtection_FalseCase() {
        assertFalse(monsterLegal.isValidMaxProtection(-20)); //Less than minimum protection
        assertFalse(monsterLegal.isValidMaxProtection(8)); //Not a prime
    }

    @Test
    public final void setMaxProtection_LegalCase() {
        monsterLegal.setMaxProtection(11);
        assertEquals(11, monsterLegal.getMaxProtection());
    }
    
    //HITPOINTS
    @Test
    public final void getHitpoints_SingleCase() {
        assertEquals(10, monsterLegal.getHitpoints());
    }

    @Test
    public final void isValidHitpoints_TrueCase() {
        assertTrue(monsterLegal.isValidHitpoints(9));
    }

    @Test
    public final void isValidHitpoints_FalseCase() {
        assertFalse(monsterLegal.isValidHitpoints(20)); //More than max hitpoints
        assertFalse(monsterLegal.isValidHitpoints(-5)); //Less than 0
    }

    @Test
    public final void getMaxHitpoints_SingleCase() {
        assertEquals(10, monsterLegal.getMaxHitpoints());
    }

    @Test
    public final void isValidMaxHitpoints_TrueCase() {
        assertTrue(monsterLegal.isValidMaxHitpoints(20));
    }

    @Test
    public final void isValidMaxHitpoints_FalseCase() {
        assertFalse(monsterLegal.isValidMaxHitpoints(0));
    }

    @Test
    public final void setMaxHitpoints_LegalCase() {
        monsterLegal.setMaxHitpoints(20);
        assertEquals(20, monsterLegal.getMaxHitpoints());
    }

    @Test
    public final void setMaxHitpoints_IllegalCase() {
        assertThrows(IllegalArgumentException.class, () -> monsterLegal.setMaxHitpoints(-4));
    }
    
    @Test
    public final void hasDied_FalseCase() {
        assertFalse(monsterLegal.hasDied());
    }
    
    //STRENGTH
    @Test
    public final void getStrength_SingleCase() {
        assertEquals(100, monsterLegal.getStrength());
    }
    
    //WEIGHT
    @Test
    public final void getWeight_SingleCase() {
    	assertEquals(5, weaponLegal.getWeight(), 0.25);
    	assertEquals(0, purseLegal.getWeight(), 0.25);
    	assertEquals(5, backpackLegal.getWeight(), 0.25);
    }
    
    //EQUIPMENT
    @Test
    public final void equipInAnchor_LegalCase() {
    	Weapon caladbolg = new Weapon(5,5,5, null);
    	monsterLegal.equipInAnchor(caladbolg, 3);
        assertTrue(monsterLegal.hasAsEquipment(caladbolg));
    }
    
    @Test
    public final void equipInAnchor_IllegalArgument() {
    	Weapon caladbolg = new Weapon(5,5,5, null);
    	Weapon HMSdivinity = new Weapon(5,5,5, null);
    	monsterLegal.equipInAnchor(caladbolg, 3);
    	assertThrows(IllegalArgumentException.class, () -> monsterLegal.equipInAnchor(HMSdivinity, 3));
    }
    
    @Test
    public final void equipInAnchor_IllegalIndex() {
    	Weapon caladbolg = new Weapon(5,5,5, null);
    	assertThrows(IndexOutOfBoundsException.class, () -> monsterLegal.equipInAnchor(caladbolg, -4));
    }
    
    @Test
    public final void unequip_LegalCase() {
    	Weapon caladbolg = new Weapon(5,5,5, null);
    	monsterLegal.equipInAnchor(caladbolg, 3);
    	monsterLegal.unequip(caladbolg);
    	assertFalse(monsterLegal.hasAsEquipment(caladbolg));
    }
    
    @Test
    public final void unequip_IllegalCase() {
    	Weapon caladbolg = new Weapon(5,5,5, null);
    	monsterLegal.equipInAnchor(caladbolg, 3);
    	monsterLegal.unequip(caladbolg);
    	assertFalse(monsterLegal.hasAsEquipment(caladbolg));
    }
    
    @Test
    public final void swapItem_LegalCase() {
    	Weapon caladbolg = new Weapon(5,5,5, null);
    	Weapon HMSdivinity = new Weapon(5,5,5, null);
    	monsterLegal.equipInAnchor(caladbolg, 3);
    	monsterLegal.swapItem(caladbolg, HMSdivinity);
    	assertTrue(monsterLegal.hasAsEquipment(HMSdivinity));
    	assertFalse(monsterLegal.hasAsEquipment(caladbolg));
    }
    
    @Test
    public final void calculateEquipmentLoad_SingleCase() {
    	monsterLegal.calculateEquipmentLoad();
    	assertEquals(10, monsterLegal.getEquipmentLoad(), 0.25);
    }
    
    @Test
    public final void calculateEquipmentValue_SingleCase() {
    	monsterLegal.calculateEquipmentValue();
    	assertEquals(25, monsterLegal.getEquipmentValue(), 0.25);
    }
    
    @Test
    public final void storeInBackpack_LegalCase() {
    	Weapon caladbolg = new Weapon(5,1,5, null);
    	monsterLegal.storeInBackpack(caladbolg);
    	assertEquals(caladbolg, backpackLegal.getHighestWeightItem());
    }
    
    @Test
    public final void storeInBackpack_IllegalCase() {
    	Weapon caladbolg = new Weapon(5,10000000,5, null);
    	assertThrows(NullPointerException.class, () -> monsterLegal.storeInBackpack(null)); //Null
    	assertThrows(IllegalArgumentException.class, () -> monsterLegal.storeInBackpack(caladbolg)); //Too heavy
    }
    
    @Test
    public final void destroyWeapon_LegalCase() {
    	Weapon caladbolg = new Weapon(5,1,5, null);
    	monsterLegal.equipInAnchor(caladbolg, 3);
    	monsterLegal.destroyWeapon(caladbolg);
    	assertFalse(monsterLegal.hasAsEquipment(caladbolg));
    }
    
    @Test
    public final void destroyWeapon_IllegalCase() {
    	Weapon caladbolg = new Weapon(5,1,5, null);
    	assertThrows(IllegalArgumentException.class, () -> monsterLegal.destroyWeapon(caladbolg)); //Not in equipment
    	assertThrows(NullPointerException.class, () -> monsterLegal.destroyWeapon(null)); //Null
    }
    

}
