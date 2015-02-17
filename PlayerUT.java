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
	
	//Redirects System.out calls to the consoleOut byte array before any tests run
	@BeforeClass
	public static void setUpAll() {
		System.setOut(new PrintStream(consoleOut));
	}

	//Restores System.out calls to their normal function	
	@AfterClass
	public static void tearDownAll() {
		System.setOut(System.out);
	}
	
	//Before each test: flush the output to the byte array, and reset it, so we are
	//starting over
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
	//expect that if the user has no sugar, then if they get sugar, they will
	// have all the items
	@Test
	public void testGetSugarToComplete() {
		Player player = new Player(false, true, true); // no sugar
		player.getSugar();
		assertTrue(player.hasAllItems());
	}
	
	//testing that if the user already had sugar, they will still have sugar
	//we expect that if the user already has all the items, and gets sugar, 
	// they will still have all the items
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
	//We expect that if a player has all items except for cream, then if the
	// player gets cream, then the player will have all the items.
	@Test
	public void testGetCreamToComplete() {
		Player player = new Player(true, false, true); // no cream
		player.getCream();
		assertTrue(player.hasAllItems());
	}
	
	//testing that if the user already had cream, they will still have cream
	//we expect that if the player already has all the items, and gets cream again,
	// then they will still have all the items
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
	//We expect that if the user has everything but coffee, and gets coffee,
	// the player will have all the items.
	@Test
	public void testGetCoffeeToComplete() {
		Player player = new Player(true, true, false); // no coffee
		player.getCoffee();
		assertTrue(player.hasAllItems());
	}
	
	//testing that if the user already had coffee, they will still have coffee
	//We expect that if the player already has all the items, and gets coffee
	// they will still have all the items.
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
	//We expect that a player with only sugar will return false when hasAllItems is called.
	@Test
	public void testHasOnlyOne() {
		Player player = new Player(true, false, true);
		assertFalse(player.hasAllItems());
	}
	
	//Tests that if the user only has two items, hasAllItems() returns false
	//We expect that a player with coffee and sugar will return false when
	// hasAllItems is called
	@Test
	public void testHasTwo() {
		Player player = new Player(true, true, false);
		assertFalse(player.hasAllItems());
	}
	
	//Tests that if the user has no items, hasAllItems() returns false
	//We expect that when hasAllItems is called on a player that has no items,
	// the call will return false.
	@Test
	public void testHasNone() {
		Player player = new Player(false, false, false);
		assertFalse(player.hasAllItems());
	}
	
	//Tests that hasAllItems() returns true if the user truly has all items
	//We expect that a player initialized with all the items will return true
	// when hasAllItems is called.
	@Test
	public void testHasAllItems() {
		Player player = new Player(true, true, true);
		assertTrue(player.hasAllItems());
	}
	
	///////////////////////////
	// showInventory() tests //
	///////////////////////////
	
	//Tests that if the user has no items, the game will report that each item is missing
	//We expect that if a player with no items has showInventory called, it will print out
	// all three messages reporting that it doesn't have the item.
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
	//We expect that for a player with sugar, when showInventory is called,
	// the "tasty sugar" message will be printed to stdout.
	@Test
	public void testReportAnItem() {
		Player player = new Player(true, false, false);
		player.showInventory();
		String output = consoleOut.toString();
		System.out.flush();
		assertTrue(output.indexOf("You have some tasty sugar.") >= 0);
	}
	
	//Tests that the user is not shown contradictory information about the presence of an item
	//Expect that when showInventory is called on a Player with coffee, the message confirming that
	// the player has coffee is shown, but the message alerting them they do not have coffee
	// is not shown.
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
	//Expect that if drink() is called on a Player with no items,
	// false will be returned.
	@Test
	public void testDrinkNoItems() {
		Player player = new Player(false, false, false);
		assertFalse(player.drink());
	}
	
	//Tests that the user loses if they drink with only some items
	//Expect that if drink is called on a player with just sugar and cream,
	// false will be returned.
	@Test
	public void testDrinkSomeItems() {
		Player player = new Player(true, false, true);
		assertFalse(player.drink());
	}
	
	//Tests that the user wins if they drink with all items
	//Expect that if drink is called on a player with all the items, then
	// true will be returned.
	@Test
	public void testDrinkAllItems() {
		Player player = new Player(true, true, true);
		assertTrue(player.drink());
	}
	
}
