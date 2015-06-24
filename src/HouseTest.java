import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*; //method of Mock

public class HouseTest {	
	House h;
	Room r;
	
	@Before 
	public void setUp(){
		h = mock(House.class);
		r = mock(Room.class);
	}
	
	@Test
	public void testGetDescription() {
		Room [] rooms = new Room [1];
		when(r.getDescription()).thenReturn("the room is dark and scary"); //stubbing - when some method is called, do this
		rooms[0]=r;
		House h = new House (rooms);
		String result = h.getCurrentRoomInfo(); //we know that the first room is at index 0
		assertEquals(result, "the room is dark and scary");
		
	}
	@Test
	public void testGetCurrentRoomInfoZero(){
		h = new House (0);
		String result = h.getCurrentRoomInfo();
		assertEquals(result, "You are in a magical land!  But you are returned to the beginning!");
	}
	@Test
	public void TestgenerateRoomsCount() {
		Room [] testRooms = new Room[3];
		House h = new House(testRooms);
		testRooms = h.generateRooms(3);
		assertEquals(testRooms.length, 3);
	}

}
