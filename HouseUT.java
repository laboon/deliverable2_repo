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

	////////////////////////////////
	// getCurrentRoomInfo() Tests //
	////////////////////////////////
	
	//Tests that we get the current room's description
	@Test
	public void testGetCurrentRoomsInfo() {
		Room room = mock(Room.class);
		when(room.getDescription()).thenReturn("test");
		Room[] rooms = new Room[] { room };
		House house = new House(rooms);
		assertTrue("test".equals(house.getCurrentRoomInfo()));
	}
	
	//Tests that if we are not in a room, the "magical land" text is printed
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
	@Test
	public void testMoveSouthNoDoor() {
		Room[] rooms = new Room[] { mock(Room.class) };
		when(rooms[0].southExit()).thenReturn(false);
		when(rooms[0].getDescription()).thenReturn("room 1");
		House house = new House(rooms);
		
		assertTrue(house.getCurrentRoomInfo().equals("room 1"));
	}
	
	
	//////////////////
	// look() Tests //
	//////////////////
	
	//Tests that if there is nothing in the room, then the player gets nothing
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
	@Test
	public void testGenerateEmptyRoomArray() {
		House house = new House(0);
		Room[] rooms = house.generateRooms(0);
		
		assertTrue(rooms.length == 0);
	}
	
	//Tests that the method correctly generates a positive sized Room array
	@Test
	public void testGeneratePositiveRoomArray() {
		House house = new House(0);
		Room[] rooms = house.generateRooms(10); //arbitrary positive number
		
		assertTrue(rooms.length == 10);
	}
	
}
