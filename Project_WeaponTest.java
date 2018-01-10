import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Before;
import org.junit.Test;

public class WeaponTest {
	
	private Weapon weaponLegal;
	private Monster monsterLegal;
	private Purse purseLegal;
	private Backpack backpackLegal;

	@Before
	public void setUp() throws Exception {
		weaponLegal = new Weapon(10, 5, 5, null);
		purseLegal = new Purse(111, 5, 10, null, 10, 0);
		backpackLegal = new Backpack(110, 5, 5, 5, null);
		
		monsterLegal = new Monster("Grog", 5, 5, 10, 100, 4, weaponLegal, purseLegal, backpackLegal);
	}

	@Test
    public final void constructor_LegalCase() {
        assertEquals(10, weaponLegal.getValue(), 0.25);
        assertEquals(5, weaponLegal.getWeight(), 0.25);
        assertEquals(5, weaponLegal.getDamage());
        assertEquals(monsterLegal, weaponLegal.getHolder());
    }
	
	@Test
	public final void constructor_Throws() {
		assertThrows(IllegalArgumentException.class, () -> {
			Weapon myWeapon = new Weapon(10, -5, 5, null);
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			Weapon yourWeapon = new Weapon(10, 5, 5, monsterLegal);
		});
	}
	
	@Test
	public final void setHolder_LegalCase() {
		weaponLegal.setHolder(null);
		assertEquals(null, weaponLegal.getHolder());
		
		weaponLegal.setHolder(monsterLegal);
		assertEquals(monsterLegal, weaponLegal.getHolder());
	}

}
