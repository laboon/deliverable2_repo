package deliverable2_repo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

public class CoffeeMakerTest {
	@Before
	public void setUp(){
	}
	
	@After
	public void tearDown(){
	}
	
	@Test
	public void runArgsTest(){
		CoffeeMaker cm = new CoffeeMaker();
		assertEquals(cm.runArgs("hello"), 0);
	}
}