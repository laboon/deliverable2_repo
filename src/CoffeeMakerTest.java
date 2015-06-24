import static org.junit.Assert.*;

import org.junit.Test;


public class CoffeeMakerTest {
	
	// this tests that runArgs always returns 0

	@Test
	public void testRunArgs() {
		CoffeeMaker cm = new CoffeeMaker();
		int returnVal = cm.runArgs("foo");
		assertEquals(returnVal, 0);
	}

}
