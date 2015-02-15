import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class GameUT {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	@Mock
	Player mockedPlayer = Mockito.mock(Player.class);
	House mockedHouse = Mockito.mock(House.class); 
	@Before
	public void setUp() throws Exception {
		System.setOut(new PrintStream(outContent));
	}
	@After
	public void tearDown() throws Exception {
		System.setOut(null);
	}
	
	// --------------------------------------------------------------
	// DOSOMETHING TESTS
	// --------------------------------------------------------------
	
	// Test that typing "N" to go North is a recognized command
	// Verify that the method moveNorth() is called
	@Test
	public void testMoveNorthCommand() {
		// If change it to "n", the test fails
		String goNorth = "N";
		Game g = new Game(mockedPlayer, mockedHouse);
		assertEquals(0, g.doSomething(goNorth));
	    Mockito.verify(mockedHouse).moveNorth();
	}
	
	// Test that typing "S" to go South is a recognized command
	// Verify that the method moveSouth() is called
	@Test
	public void testMoveSouthCommand() {
		String goSouth = "S";
		Game g = new Game(mockedPlayer, mockedHouse);
		assertEquals(0, g.doSomething(goSouth));
	    Mockito.verify(mockedHouse).moveSouth();
	}
	
	// Test that typing "l" to look around is a recognized command
	// Verify that the method look(Player, Room) is called
	@Test
	public void testLookCommand() {
		String look = "l";
		Game g = new Game(mockedPlayer, mockedHouse);
		assertEquals(0, g.doSomething(look));
	    Mockito.verify(mockedHouse).look(mockedPlayer, null);
	}
	
	// Test that typing "I" to show inventory is a recognized command
	// Verify that the method showInventory() is called
	@Test
	public void testShowInventory() {
		String showInventory = "I";
		Game g = new Game(mockedPlayer, mockedHouse);
		assertEquals(0, g.doSomething(showInventory));
	    Mockito.verify(mockedPlayer).showInventory();
	}
	
	// Test that typing "D" to drink is a recognized command
	// Verify that the method drink() is called
	@Test
	public void testDrink() {
		String drink = "D";
		Game g = new Game(mockedPlayer, mockedHouse);
		assertEquals(-1, g.doSomething(drink));
	    Mockito.verify(mockedPlayer).drink();
	}
	
	// Test that typing "s!" is not a recognized command
	// and a message "What?" gets printed out on console
	@Test
	public void testIllegalCommand() {
		String illegalCommand = "s!";
		Game g = new Game(mockedPlayer, mockedHouse);		
		g.doSomething(illegalCommand);
		assertEquals("What?", outContent.toString().trim());
	}	
}
