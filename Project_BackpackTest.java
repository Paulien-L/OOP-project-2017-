import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Before;
import org.junit.Test;

public class BackpackTest {
	
	private Backpack backpackLegal;
	private Weapon weaponBackpack;
	private Weapon weaponHeavy;

	
	@Before
	public void setUp() throws Exception {
		backpackLegal = new Backpack(110, 5, 5, 5, null);
		weaponBackpack = new Weapon(10, 5, 5, null);
		weaponHeavy = new Weapon(10, 50000000, 5, null);
	}

	@Test
	public final void equip_LegalCase() {
		backpackLegal.equip(weaponBackpack);
		assertTrue(backpackLegal.getContents().contains(weaponBackpack));
	}
	
	@Test
	public final void equip_throws_1() {
		assertThrows(IllegalArgumentException.class, () -> {
			backpackLegal.equip(null);
		});
		
		backpackLegal.equip(weaponBackpack);
		assertThrows(IllegalArgumentException.class, () -> {
			backpackLegal.equip(weaponBackpack);
		});
	}
	
	@Test
	public final void equip_throws_2() {
		assertThrows(IllegalArgumentException.class, () -> {
			backpackLegal.equip(weaponHeavy);
		});
	}
	
	@Test
	public final void removeItem_LegalCase() {
		backpackLegal.equip(weaponBackpack);
		backpackLegal.removeItem(weaponBackpack);
		assertFalse(backpackLegal.getContents().contains(weaponBackpack));
	}
	
	@Test
	public final void removeItem_throws() {
		assertThrows(IllegalArgumentException.class, () -> {
			backpackLegal.removeItem(null);
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			backpackLegal.removeItem(weaponHeavy);
		});
	}

}
