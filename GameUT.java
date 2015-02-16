import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import static org.mockito.Mockito.*;
//import org.mockito.Mock;
//import org.mockito.Mockito;

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
	public void testDrinkLose() {
		String drink = "D";
		Game g = new Game(mockedPlayer, mockedHouse);
		when(mockedPlayer.drink()).thenReturn(false);
		assertEquals(-1, g.doSomething(drink));		
	    Mockito.verify(mockedPlayer).drink();
	}
	
	// Test that typing "D" to drink is a recognized command
	// Verify that the method drink() is called
	@Test
	public void testDrinkWin() {
		String drink = "D";
		Game g = new Game(mockedPlayer, mockedHouse);
		when(mockedPlayer.drink()).thenReturn(true);
		assertEquals(1, g.doSomething(drink));
	    Mockito.verify(mockedPlayer).drink();
	}
	
	// Test that typing "h" for help is a recognized command
	// Verify that the method help() is called
	@Test
	public void testHelp() {
		String help = "h";
		Game g = new Game(mockedPlayer, mockedHouse);		
		assertEquals(0, g.doSomething(help));
		assertNull(outContent.toString().trim());
	}
	
	// Test that typing "sss" is not a recognized command
	// and a message "What?" gets printed out on console
	@Test
	public void testIllegalCmdString() {
		String illegalCommand = "sss";
		Game g = new Game(mockedPlayer, mockedHouse);		
		g.doSomething(illegalCommand);
		assertEquals("What?", outContent.toString().trim());
	}
	
	// Test that typing "4" is not a recognized command
	// and a message "What?" gets printed out on console
	@Test
	public void testIllegalCmdNumber() {
		String illegalCommand = "4";
		Game g = new Game(mockedPlayer, mockedHouse);		
		g.doSomething(illegalCommand);
		assertEquals("What?", outContent.toString().trim());
	}	
	
	// Test that when there is no input entry (empty command) 
	// a message "What?" gets printed out on console
	@Test
	public void testIllegalCmdEmpty() {
		String illegalCommand = "";
		Game g = new Game(mockedPlayer, mockedHouse);		
		g.doSomething(illegalCommand);
		assertEquals("What?", outContent.toString().trim());
	}
}
