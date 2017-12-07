import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MonsterTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}
	
	private Monster monsterLegal, monsterAverage, monsterOverPowered;
	
	@BeforeEach
	void setUp() throws Exception {
		monsterLegal = new Monster("Grog", 5, 5, 10, 10);
		monsterAverage = new Monster("Greg", 5, 5, 10, 10);
		monsterOverPowered = new Monster("Demonlord", 5, 5, 10, 100);
	}
	
	//CONSTRUCTOR
	@Test
	public final void constructor_LegalCase() {
		Monster newMonster = new Monster("Azrael", 10, 11, 10, 20);
		assertEquals("Azrael", newMonster.getName());
		assertEquals(10, newMonster.getDamage());
		assertEquals(11, newMonster.getProtection());
		assertEquals(10, newMonster.getMaxHitpoints());
		assertEquals(20, newMonster.getStrength());
	}
	
	@Test
	public final void constructor_Throws() {
	assertThrows(IllegalArgumentException.class, () -> {
		Monster myMonster = new Monster("@zra3l", 10, 11, 10, 20);
	});
	assertThrows(IllegalArgumentException.class, () -> {
		Monster yourMonster = new Monster("Azrael", 10, 11, -44, 20);
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
	//This one also tests the a legal case for setDamage()
	
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
		monsterLegal.setHitpoints(9);
		assertEquals(9, monsterLegal.getHitpoints());
	}
	//Also tests legal case for setHitpoints()
	
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
	public final void setHitpoints_IllegalCase() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> monsterLegal.setHitpoints(-4));
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
		Assertions.assertThrows(IllegalArgumentException.class, () -> monsterLegal.setMaxHitpoints(-4));
	}
	
	@Test
	public final void hasDied_TrueCase() {
		monsterLegal.setHitpoints(0);
		assertTrue(monsterLegal.hasDied());
	}
	
	@Test
	public final void hasDied_FalseCase() {
		monsterLegal.setHitpoints(5);
		assertFalse(monsterLegal.hasDied());
	}
	
	@Test
	public final void getStrength_SingleCase() {
		assertEquals(10, monsterLegal.getStrength());
	}
	
	@Test
	public final void getAverageStrength_SingleCase() {
		assertEquals(10, Monster.getAverageStrength(monsterLegal.getStrength(), monsterAverage.getStrength()));
	}
	
	@Test
	public final void isValidAverageStrength_TrueCase() {
		float average = Monster.getAverageStrength(monsterLegal.getStrength(), monsterAverage.getStrength());
		assertTrue(Monster.isValidAverageStrength(average));
	}
	
	@Test
	public final void isValidAverageStrength_FalseCase() {
		float average = Monster.getAverageStrength(monsterLegal.getStrength(), monsterOverPowered.getStrength());
		assertFalse(Monster.isValidAverageStrength(average));
	}

}
