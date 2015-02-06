package com.laboon;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlayerTest 
{

	/*
	 * Test to make sure the empty Player constructor creates an object
	 */
	@Test
	public void testPlayerContructor() 
	{
		Player p = null;
		p = new Player();
		assertNotNull(p);
	}

	/*
	 * Test that the alternate constructor creates an object 
	 */
	@Test
	public void testPlayerConstructorWithItems() 
	{
		Player p = null;
		p = new Player(true, true, true);
		assertNotNull(p);
	}

	/*
	 * Test getting sugar, see if we have all items after we get it
	 */
	@Test
	public void testGetSugar() 
	{
		Player p = new Player(false, true, true);
		p.getSugar();
		assertTrue(p.hasAllItems());
	}

	/*
	 * Test getting cream, see if we have all items after we get it
	 */
	@Test
	public void testGetCream() 
	{
		Player p = new Player(true, false, true);
		p.getCream();
		assertTrue(p.hasAllItems());
	}

	/*
	 * Test getting coffee, see if we have all items after we get it
	 */
	@Test
	public void testGetCoffee() 
	{
		Player p = new Player(true, true, false);
		p.getCoffee();
		assertTrue(p.hasAllItems());
	}

	/*
	 * Test hasAllItems() when we have all items should return true
	 */
	@Test
	public void testHaveAllItems() 
	{		
		Player p = new Player(true, true, true);
		assertTrue(p.hasAllItems());
	}

	/*
	 * Test hasAllItems() without all items, should return false
	 */
	@Test
	public void testHaveAllItemsWithoutAllItems()
	{
		Player p = new Player(false, true, true);
		assertFalse(p.hasAllItems());
		
		p = new Player(false, false, false);
		assertFalse(p.hasAllItems());
	}
	
	/*
	 * Test to see that we win if we drink with all items 
	 */
	@Test
	public void testDrinkWithAllItems() 
	{
		Player p = new Player(true, true, true);
		assertTrue(p.drink());
	}
	
	/*
	 * Test to see that we lost if we drink with no items
	 */
	@Test
	public void testDrinkWithoutAnyItems() 
	{
		Player p = new Player(false, false, false);
		assertFalse(p.drink());
	}

	/*
	 * Test to see that we lost if we drink without all items
	 */
	@Test
	public void testDrinkWithoutAllItems() 
	{
		Player p = new Player(true, false, true);
		assertFalse(p.drink());
	}
	
}
