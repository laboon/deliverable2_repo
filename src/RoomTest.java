import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*; //method of Mock

public class RoomTest {
	
	Room r;
	/*passing in boolean hasCoffee, 
	boolean hasCream, 
	boolean hasSugar,
	boolean northExit,
	boolean southExit*/	
	@Before 
	public void setUp(){
		r = mock(Room.class);
		
	}
	
	@Test
	public void hasItemTest() {
	boolean _northExit = false;
	boolean _southExit = false;
	boolean _hasSugar = true;
	boolean _hasCream = false;
	boolean _hasCoffee = true;
	Room r = new Room(_northExit,_southExit,_hasSugar,_hasCream,_hasCoffee);
	    assertTrue(r.hasItem());

	}

	@Test
     public void testHasSugar() {
              Room r = new Room(false, false, true, false ,false);
              boolean result = r.hasSugar();  
              assertTrue(result);      
      }
	
	@Test
	public void TestHasCream() {
		 Room r = new Room(false, true, false, false ,false);
         boolean result = r.hasCream();  
         assertTrue(result);      
	}
	@Test
	public void TestHasCoffee() {
		Room r = new Room(true, false, false, false ,false);
        boolean result = r.hasCoffee();  
        assertTrue(result); 
	}
	@Test
	public void TestNorthExit() {
		 Room r = new Room(false, false, false, true ,false);
         boolean result = r.northExit();  
         assertTrue(result);      
		
	}
	@Test
	public void TestgetDescription() {
		Room r = new Room(false, false, false, false ,true);
		String result = r.getDescription();
		boolean containsSouth=result.contains("South");
		assertTrue(containsSouth);
	}
	
	@Test
	public void TestSouthExit() {
		 Room r = new Room(false, false, false, false ,true);
         boolean result = r.southExit();  
         assertTrue(result);      
		
	}
}
