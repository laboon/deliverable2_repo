import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PlayerUT {
	private static ByteArrayOutputStream consoleOut = new ByteArrayOutputStream();
	
	@BeforeClass
	public static void setUpAll() {
		System.setOut(new PrintStream(consoleOut));
	}
	
	@AfterClass
	public static void tearDownAll() {
		System.setOut(System.out);
	}
	
	@Before
	public void setUp() {
		System.out.flush();
		consoleOut.reset();
	}
	
	//////////////////////
	// getSugar() tests //
	//////////////////////
	
	//testing that if the user has all other items, the user will get sugar if
	// getSugar() is called
	@Test
	public void testGetSugarToComplete() {
		Player player = new Player(false, true, true); // no sugar
		player.getSugar();
		assertTrue(player.hasAllItems());
	}
	
	//testing that if the user already had sugar, they will still have sugar
	@Test
	public void testStillHasSugar() {
		Player player = new Player(true, true, true);
		player.getSugar();
		assertTrue(player.hasAllItems());
	}
	
	//////////////////////
	// getCream() tests //
	//////////////////////
	
	//testing that if the user has all other items, the user will get cream if
	// getCream() is called
	@Test
	public void testGetCreamToComplete() {
		Player player = new Player(true, false, true); // no cream
		player.getCream();
		assertTrue(player.hasAllItems());
	}
	
	//testing that if the user already had cream, they will still have cream
	@Test
	public void testStillHasCream() {
		Player player = new Player(true, true, true);
		player.getCream();
		assertTrue(player.hasAllItems());
	}


	//////////////////////
	// getCoffee() tests //
	//////////////////////
	
	//testing that if the user has all other items, the user will get coffee if
	// getCoffee() is called
	@Test
	public void testGetCoffeeToComplete() {
		Player player = new Player(true, true, false); // no coffee
		player.getCoffee();
		assertTrue(player.hasAllItems());
	}
	
	//testing that if the user already had coffee, they will still have coffee
	@Test
	public void testStillHasCoffee() {
		Player player = new Player(true, true, true);
		player.getCoffee();
		assertTrue(player.hasAllItems());
	}

	/////////////////////////
	// hasAllItems() tests //
	/////////////////////////
	
	//Tests that if the user only has one item, hasAllItems() returns false
	@Test
	public void testHasOnlyOne() {
		Player player = new Player(true, false, true);
		assertFalse(player.hasAllItems());
	}
	
	//Tests that if the user only has two items, hasAllItems() returns false
	@Test
	public void testHasTwo() {
		Player player = new Player(true, true, false);
		assertFalse(player.hasAllItems());
	}
	
	//Tests that if the user has no items, hasAllItems() returns false
	@Test
	public void testHasNone() {
		Player player = new Player(false, false, false);
		assertFalse(player.hasAllItems());
	}
	
	//Tests that hasAllItems() returns true if the user truly has all items
	@Test
	public void testHasAllItems() {
		Player player = new Player(true, true, true);
		assertTrue(player.hasAllItems());
	}
	
	///////////////////////////
	// showInventory() tests //
	///////////////////////////
	
	//Tests that if the user has no items, the game will report that each item is missing
	@Test
	public void testReportNoItems() {
		Player player = new Player(false, false, false);
		player.showInventory();
		String output = consoleOut.toString();
		System.out.flush();
		assertTrue(output.indexOf("YOU HAVE NO COFFEE!") >= 0);
		assertTrue(output.indexOf("YOU HAVE NO SUGAR!") >= 0);
		assertTrue(output.indexOf("YOU HAVE NO CREAM!") >= 0);
	}
	
	//Tests that the user is told if an item is missing
	@Test
	public void testReportAnItem() {
		Player player = new Player(true, false, false);
		player.showInventory();
		String output = consoleOut.toString();
		System.out.flush();
		assertTrue(output.indexOf("You have some tasty sugar.") >= 0);
	}
	
	//Tests that the user is not shown contradictory information about the presence of an item
	@Test
	public void testInventoryNotContradictory() {
		Player player = new Player(false, true, true);
		player.showInventory();
		String output = consoleOut.toString();
		System.out.flush();

		assertTrue(output.indexOf("You have a cup of delicious coffee.") >= 0);
		assertFalse(output.indexOf("YOU HAVE NO COFFEE!") >= 0);
	}
	
	
	///////////////////
	// drink() Tests //
	///////////////////
	
	//Tests that the user loses if they have no items
	@Test
	public void testDrinkNoItems() {
		Player player = new Player(false, false, false);
		assertFalse(player.drink());
	}
	
	//Tests that the user loses if they drink with only some items
	@Test
	public void testDrinkSomeItems() {
		Player player = new Player(true, false, true);
		assertFalse(player.drink());
	}
	
	//Tests that the user wins if they drink with all items
	@Test
	public void testDrinkAllItems() {
		Player player = new Player(true, true, true);
		assertTrue(player.drink());
	}
	
}
