import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CoffeeMakerUT {

	private static ByteArrayOutputStream consoleOut = new ByteArrayOutputStream();
	
	//Redirects System.out calls to the consoleOut byte array before any tests run
	@BeforeClass
	public static void setUpAll() {
		System.setOut(new PrintStream(consoleOut));
	}
	
	//Restores System.out calls to their normal function	
	@AfterClass
	public static void tearDownAll() {
		System.setOut(System.out);
	}
		
	//Before each test: flush the output to the byte array, and reset it, so we are
	//starting over
	@Before
	public void setUp() {
		System.out.flush();
		consoleOut.reset();
	}
	
	/////////////////////
	// runArgs() Tests //
	/////////////////////
	
	//Tests that the method prints things and returns 0
	//Expect that the runArgs method prints information to stdOut
	@Test
	public void testRunArgs() {
		CoffeeMaker cm = new CoffeeMaker();
		int retVal = cm.runArgs("this argument is pointless");
		String output = consoleOut.toString();
		
		assertTrue(output.length() > 0);
		assertTrue(retVal == 0);
	}
	
}
