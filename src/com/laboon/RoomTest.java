package com.laboon;


import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.Test;


public class RoomTest 
{

	@Test
	public void testHasItem() 
	{
		assertTrue(true);
		//fail("Not yet implemented");
	}

	@Test
	/* Check for not having sugar with a mocked Room
	 * Check for having sugar with a Room instantiated with sugar
	 */
	public void testHasSugar() 
	{
		Room r = mock(Room.class);
		assertFalse(r.hasSugar());
		
		r = new Room(true, true, true, true, true);
		assertTrue(r.hasSugar());		
	}

	@Test
	public void testHasCream() 
	{
		Room r = mock(Room.class);
		assertFalse(r.hasCream());
		
		r = new Room(true, true, true, true, true);
		assertTrue(r.hasCream());	
	}

	@Test
	public void testHasCoffee() {
		Room r = mock(Room.class);
		assertFalse(r.hasCoffee());
		
		r = new Room(true, true, true, true, true);
		assertTrue(r.hasCoffee());	
	}

	@Test
	public void testNorthExit() 
	{
		Room r = mock(Room.class);
		assertFalse(r.northExit());
		
		r = new Room(true, true, true, true, true);
		assertTrue(r.northExit());	
		
	}

	@Test
	public void testSouthExit() {
		Room r = mock(Room.class);
		assertFalse(r.southExit());
		
		r = new Room(true, true, true, true, true);
		assertTrue(r.southExit());	
	}

	@Test
	public void testRoom() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDescription() {
		fail("Not yet implemented");
	}

}
