import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*; //method of Mock

public class PlayerTest {
	
@Test
public void noSugarTest() {
   boolean sugar = false;
   boolean cream = true;
   boolean coffee = true;
  Player p = new Player( sugar,  cream,  coffee);
  assertFalse(p.hasAllItems());
}
    
 // Testing when sugar，cream, and coffee are in the Invent, the hasAllItems return false.
@Test
public void hasAllItems() {
   boolean sugar = true;
   boolean cream = true;
   boolean coffee = true;
  Player p = new Player( sugar,  cream,  coffee);
  assertTrue(p.hasAllItems());
}   
 
   @Test
// Testing when player only has cream and coffee, he or she cannot win.
  public void drinkOnlyCoffee(){
	  boolean sugar = false;
	  boolean cream = true;
	  boolean coffee = true;
	  Player p = new Player( sugar,  cream,  coffee);
	  boolean w =  p.drink();
	  assertFalse(w);
   }
// Testing when player has cream, sugar and coffee, he or she will win.
   @Test
   public void drinkAll(){
	  boolean sugar = true;
	  boolean cream = true;
	  boolean coffee = true;
	  Player p = new Player( sugar,  cream,  coffee);
	  boolean w =  p.drink();
	  assertTrue(w);
   }
	 
}
