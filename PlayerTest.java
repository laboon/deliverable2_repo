package deliverable2_repo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

public class PlayerTest {
	@Before
	public void setUp(){
	}
	
	@After
	public void tearDown(){
	}
	
	//Test player's inventory - see what it displays
	@Test
	public void InventoryTest(){
		Player p = new Player(true, true, true);
		//Test whether hasAllItems works and whether drink will let the player win the game
		assertEquals(p.hasAllItems(), true);
		assertEquals(p.drink(), true);
		
		//Test whether other combinations will win
		Player p2 = new Player(true, false, true);
		assertEquals(p2.drink(), false);
		
		Player p3 = new Player(true, true, false);
		assertEquals(p3.drink(), false);
		
		Player p4 = new Player(false, true, true);
		assertEquals(p4.drink(), false);
		
	}

}