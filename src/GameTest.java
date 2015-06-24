import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;


public class GameTest {
	Player p;
	House h;
	Game g;
	@Before 
	public void setUp() {
		p = mock(Player.class);
		h = mock(House.class);
		g = new Game (p, h);
	
	}
	
	@After
	public void tearDown() {
	}
	
	@Test
	public void testMoveNorth() {
		//execution
		int result = g.doSomething("N");
		//assertions
		verify(h).moveNorth();
		assertEquals(result, 0);
	}
	@Test 
	public void testMoveSouth(){
		//execution
		int result = g.doSomething("S");
		//assertions
		verify(h).moveSouth();
		assertEquals(result, 0);
	}
	@Test 
	public void testLook(){
		//execution
		int result = g.doSomething("L");
		//assertions
		verify(h).look(p, null);
		assertEquals(result, 0);
	}
	@Test 
	public void testShowInventory(){
		//execution
		int result = g.doSomething("I");
		//assertions
		verify(p).showInventory();
		assertEquals(result, 0);
	}
	@Test 
	public void testDrinkTrueResult(){
		//execution
		when(p.drink()).thenReturn(true);
		int result = g.doSomething("D");
		//assertions
		verify(p).drink();
		assertEquals(result, 1);
	}
	@Test
	public void testDrinkFalseResult(){
		//execution
		when(p.drink()).thenReturn(false);
		int result = g.doSomething("D");
		//assertions
		verify(p).drink();
		assertEquals(result, -1);
	}
	@Test 
	public void testDoNothing(){
		//execution
		int result = g.doSomething("");
		//assertions
		assertEquals(result, 0);
	}
}
