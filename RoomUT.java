import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class RoomUT {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	@Before
	public void setUp() throws Exception {
		System.setOut(new PrintStream(outContent));
	}
	@After
	public void tearDown() throws Exception {
		System.setOut(null);
	}
	
	////////////////////
	// HASITEM TESTS //
	///////////////////
	
	// Tests that hasItem() returns false when no coffee, cream or sugar exists
	@Test
	public void testNoItemExists() {
		Room r = new Room(false, false, false, true, false);
		assertFalse(r.hasItem());
	}
	// Tests that hasItem() returns true when one item (coffee) exists
	@Test
	public void testItemSugarExists() {
		Room r = new Room(true, false, false, false, true);
		assertTrue(r.hasItem());
	}
	// Tests that hasItem() returns true when one item (cream) exists
	@Test
	public void testItemCreamExists() {
		Room r = new Room(false, true, false, true, true);
		assertTrue(r.hasItem());		
	}
	// Tests that hasItem() returns true when one item (sugar) exists
	@Test
	public void testItemCoffeeExists() {
		Room r = new Room(false, false, true, false, false);
		assertTrue(r.hasItem());		
	}
	// Tests that hasItem() returns true when two items (coffee and cream) exist
	@Test
	public void testCoffeeAndCreamExist() {
		Room r = new Room(true, true, false, true, false);
		assertTrue(r.hasItem());		
	}
	// Tests that hasItem() returns true when two items (coffee and sugar) exist
	@Test
	public void testCoffeeAndSugarExist() {
		Room r = new Room(true, false, true, false, true);
		assertTrue(r.hasItem());		
	}
	// Tests that hasItem() returns true when two items (cream and sugar) exist
	@Test
	public void testCreamAndSugarExist() {
		Room r = new Room(false, true, true, true, true);
		assertTrue(r.hasItem());
	}
	// Tests that hasItem() returns true when three items (coffee cream and sugar) exist
	@Test
	public void testCoffeeAndCreamAndSugarExist() {
		Room r = new Room(true, true, true, false, false);
		assertTrue(r.hasItem());
	}
	
	/////////////////////
	// HASSUGAR TESTS //
	////////////////////
	
	// Tests that hasSugar() returns false when NO sugar exists
	@Test
	public void testNoSugarExists() {
		Room r = new Room(false, false, false, true, true);
		assertFalse(r.hasSugar());
	}	
	// Tests that hasSugar() returns true when sugar exists
	@Test
	public void testSugarExists() {
		Room r = new Room(false, false, true, true, true);
		assertTrue(r.hasSugar());
	}
	
	/////////////////////
	// HASCREAM TESTS //
	////////////////////
	
	// Tests that hasCream() returns false when NO cream exists
	@Test
	public void testNoCreamExists() {
		Room r = new Room(false, false, false, true, true);
		assertFalse(r.hasCream());
	}	
	
	// Tests that hasCream returns true when cream exists
	@Test
	public void testCreamExists() {
		Room r = new Room(false, true, false, true, true);
		assertTrue(r.hasCream());
	}
	
	//////////////////////
	// HASCOFFEE TESTS //
	////////////////////

	// Tests that hasCoffee() returns false when NO coffee exists
	@Test
	public void testNoCoffeeExists() {
		Room r = new Room(false, false, false, true, true);
		assertFalse(r.hasCoffee());
	}	
	// Tests that hasCream() returns true when coffee exists
	@Test
	public void testCoffeeExists() {
		Room r = new Room(true, false, false, true, true);
		assertTrue(r.hasCoffee());
	}			

	//////////////////////
	// NORTHEXIT TESTS //
	/////////////////////
	
	// Tests that northExit() returns false when there is NO exit leading to north
	@Test
	public void testNorthExitMissing() {
		Room r = new Room(false, false, false, false, false);
		assertFalse(r.northExit());
	}	
	// Tests that northExit() returns true when there is an exit leading to north
	@Test
	public void testNorthExitExists() {
		Room r = new Room(false, false, false, true, false);
		assertTrue(r.northExit());
	}
	
	/////////////////////
	// SOUTHEXIT TESTS //
	/////////////////////
	
	// Tests that southExit() returns false when there is NO exit leading to south
	@Test
	public void testSouthExitMissing() {
		Room r = new Room(false, false, false, false, false);
		assertFalse(r.southExit());
	}	
	// Tests that southExit() returns true when there is an exit leading to south
	@Test
	public void testSouthExitExists() {
		Room r = new Room(false, false, false, false, true);
		assertTrue(r.southExit());
	}
	
	//////////////////////////
	// GETDESCRIPTION TESTS //
	//////////////////////////
	
	// Tests that getDescription() prints out a message to console when being called
	@Test
	public void testGetDescription() {
		Room r = new Room(false, false, false, true, false); 
		r.getDescription();
		assertNotNull(outContent.toString().trim());
	}	
}
