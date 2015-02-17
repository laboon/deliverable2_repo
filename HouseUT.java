import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class HouseUT {

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

	////////////////////////////////
	// getCurrentRoomInfo() Tests //
	////////////////////////////////
	
	//Tests that we get the current room's description
	//Mocking a room, and stubbing out its getDescription method so that it just
	// returns the string "test".  We expect that the house will describe the room
	// as "test"
	@Test
	public void testGetCurrentRoomsInfo() {
		Room room = mock(Room.class);
		when(room.getDescription()).thenReturn("test");
		Room[] rooms = new Room[] { room };
		House house = new House(rooms);
		assertTrue("test".equals(house.getCurrentRoomInfo()));
	}
	
	//Tests that if we are not in a room, the "magical land" text is printed
	//In a house with 0 rooms, we expect that the house will respond with the 
	// "magical land" text on a getCurrentRoomInfo call.
	@Test
	public void testNotInARoomInfo() {
		Room[] rooms = new Room[0];
		House house = new House(rooms);
		assertTrue(house.getCurrentRoomInfo().indexOf("magical land") >= 0);
	}

	
	///////////////////////
	// moveNorth() Tests //
	///////////////////////
	
	//Tests that we can move north if there is a door to the north
	//In a house with two rooms, where the starting room has a door to the north,
	// we expect that before switching rooms, we will see the first room's
	// description, and after moving north, we will see the second room's.
	@Test
	public void testMoveThroughNorthDoor() {
		Room[] rooms = new Room[] { mock(Room.class), mock(Room.class) };
		when(rooms[0].northExit()).thenReturn(true);
		when(rooms[0].getDescription()).thenReturn("room 1");
		when(rooms[1].getDescription()).thenReturn("room 2");
		House house = new House(rooms);
		
		String before = house.getCurrentRoomInfo();
		house.moveNorth();
		String after = house.getCurrentRoomInfo();
		
		assertTrue(before.equals("room 1"));
		assertTrue(after.equals("room 2"));
	}
	
	//Tests that we can't move north if there is no door to the north
	//We expect that if the user attempts to move north in a room with no
	// door to the north, then they will still be in the same room after they
	// attempt to move north
	@Test
	public void testMoveNorthNoDoor() {
		Room[] rooms = new Room[] { mock(Room.class), mock(Room.class) };
		when(rooms[0].northExit()).thenReturn(true);
		when(rooms[0].northExit()).thenReturn(false);
		when(rooms[0].getDescription()).thenReturn("room 1");
		when(rooms[1].getDescription()).thenReturn("room 2");
		House house = new House(rooms);
		
		house.moveNorth(); //into room with no north door
		String before = house.getCurrentRoomInfo();
		house.moveNorth();
		String after = house.getCurrentRoomInfo();
		
		assertTrue(before.equals("room 2"));
		assertTrue(after.equals("room 2"));
	}
	
	
	///////////////////////
	// moveSouth() Tests //
	///////////////////////
	
	//Tests that we can move south if there is a room to the south
	//We expect that if the user attempts to move south in a room that has 
	// a door to the south, the description of the room they are in will change
	@Test
	public void testMoveThroughSouthDoor() {
		Room[] rooms = new Room[] { mock(Room.class), mock(Room.class) };
		when(rooms[0].southExit()).thenReturn(false);
		when(rooms[0].getDescription()).thenReturn("room 1");
		when(rooms[1].getDescription()).thenReturn("room 2");
		House house = new House(rooms);
	
		house.moveNorth();
		assertTrue(house.getCurrentRoomInfo().equals("room 2"));
		house.moveSouth();
		assertTrue(house.getCurrentRoomInfo().equals("room 1"));
	}
	
	//Tests that we can't move south if there is no door to the south
	//We expect that if the user tries to move south in a room with no door to the
	// south, the room description will not change
	@Test
	public void testMoveSouthNoDoor() {
		Room[] rooms = new Room[] { mock(Room.class) };
		when(rooms[0].southExit()).thenReturn(false);
		when(rooms[0].getDescription()).thenReturn("room 1");
		House house = new House(rooms);
		house.moveSouth();
		
		assertTrue(house.getCurrentRoomInfo().equals("room 1"));
	}
	
	
	//////////////////
	// look() Tests //
	//////////////////
	
	//Tests that if there is nothing in the room, then the player gets nothing
	//Expect that if the user is in a room that says it does not have an item,
	// when the user looks, they will not get any items
	@Test
	public void testLookEmptyRoom() {
		Room room = mock(Room.class);
		when(room.hasItem()).thenReturn(false);
		House house = new House(1);
		Player player = mock(Player.class);
		house.look(player, room);
		
		verify(player, never()).getCoffee();
		verify(player, never()).getCream();
		verify(player, never()).getSugar();
	}
	
	//Tests that if there is an item in the room, then the player gets it
	//Expect that if the room has coffee, then when look is called, getcoffee
	// will be called
	@Test
	public void testLookRoomWithItem() {
		Room room = mock(Room.class);
		when(room.hasItem()).thenReturn(true);
		when(room.hasCoffee()).thenReturn(true);
		when(room.hasCream()).thenReturn(false);
		when(room.hasSugar()).thenReturn(false);
		Player player = mock(Player.class);
		House house = new House(1);
		house.look(player, room);
		
		verify(player, never()).getCream();
		verify(player, never()).getSugar();
		verify(player, times(1)).getCoffee();
	}
	
	
	///////////////////////////
	// generateRooms() Tests //
	///////////////////////////
	
	//Tests that the method correctly generates an empty Room array
	//Expect that, if requested, generateRooms can generate an array of
	// rooms of size zero (as an edge case)
	@Test
	public void testGenerateEmptyRoomArray() {
		House house = new House(0);
		Room[] rooms = house.generateRooms(0);
		
		assertTrue(rooms.length == 0);
	}
	
	//Tests that the method correctly generates a positive sized Room array
	//We expect that generateRooms can generate a positively sized array of rooms, here,
	// ten (an arbitrary positive number)
	@Test
	public void testGeneratePositiveRoomArray() {
		House house = new House(0);
		Room[] rooms = house.generateRooms(10); //arbitrary positive number
		
		assertTrue(rooms.length == 10);
	}
	
}
