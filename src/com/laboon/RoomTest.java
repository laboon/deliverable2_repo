package com.laboon;

import static org.junit.Assert.*;
import org.junit.Test;


public class RoomTest 
{
	/*
	 * Test hasItem() with all items, should return true
	 */
	@Test
	public void testRoomHasAllItems() 
	{		
		Room r = new Room(true, true, true, true, true);
		assertTrue(r.hasItem());
	}
	
	/*
	 * Test hasItem() with 1 item, should return true
	 */
	@Test
	public void testRoomHasOneItem() 
	{		
		Room r = new Room(true, false, false, true, true);
		assertTrue(r.hasItem());
	}

	
	/*
	 * Test hasItem() with a room with no items, should return false
	 */
	@Test
	public void testRoomHasNoItems()
	{
		Room r = new Room(false, false, false, true, true);
		assertFalse(r.hasItem());
	}

	/* 
	 * Test for having sugar with a Room instantiated with sugar
	 */
	@Test
	public void testHasSugar() 
	{	
		Room r = new Room(true, true, true, true, true);
		assertTrue(r.hasSugar());
	}
	
	
	/*
	 * Test for not having sugar
	 */
	@Test
	public void testDoesNotHaveSugar()
	{
		Room r = new Room(false, false, false, false, false);
		assertFalse(r.hasSugar());		
	}
	
	/*
	 * Test for having cream
	 */
	@Test
	public void testHasCream() 
	{		
		Room r = new Room(true, true, true, true, true);
		assertTrue(r.hasCream());	
	}
	
	/*
	 * Test for not having cream
	 */
	@Test
	public void testDoesNotHaveCream()
	{
		Room r = new Room(false, false, false, false, false);
		assertFalse(r.hasCream());
	}
	
	/*
	 * Test for having coffee
	 */
	@Test
	public void testHasCoffee() 
	{
		Room r = new Room(true, true, true, true, true);
		assertTrue(r.hasCoffee());	
	}
	
	/*
	 * Test for not having coffee
	 */
	@Test
	public void testDoesNotHaveCoffee()
	{
		Room r = new Room(false, false, false, false, false);
		assertFalse(r.hasCoffee());
	}

	/*
	 * Test for a room having a north exit
	 */
	@Test
	public void testHasNorthExit() 
	{		
		Room r = new Room(true, true, true, true, true);
		assertTrue(r.northExit());	
	}

	/*
	 * Test for a room not having a north exit
	 */

	@Test
	public void testDoesNotHaveNorthExit()
	{
		Room r = new Room(false, false, false, false, false);
		assertFalse(r.northExit());
	}

	/*
	 * Test for a room having a south exit
	 */

	@Test
	public void testHasSouthExit() 
	{		
		Room r = new Room(true, true, true, true, true);
		assertTrue(r.southExit());	
	}
	
	/*
	 * Test for a room not having a south exit
	 */

	@Test
	public void testDoesNotHaveSouthExit()
	{
		Room r = new Room(false, false, false, false, false);
		assertFalse(r.southExit());
	}

	/*
	 * Test that Room constructor creates object
	 */
	@Test
	public void testRoomConstructor() {
		Room r = null;
		r = new Room(true, true, true, true, true);
		assertNotNull(r);
	}

	/*
	 * Test that get description returns a string that contains certain substrings, 
	 * which should be present in all description strings for each room
	 */
	@Test
	public void testGetDescription() 
	{
		Room r = new Room(true, true, true, true, true);
		String desc = r.getDescription();
		assertTrue(desc.contains("You see a"));
		assertTrue(desc.contains("It has a"));
	}

}
