package deliverable2_repo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import org.mockito.*;

public class GameTest {

	@Before
	public void setUp(){
	}

	@After
	public void tearDown(){
	}

	@Test
	public void doSomethingMovementTest(){
		//Initialize game with mock player and mock house
		House h = Mockito.mock(House.class);
		Player p = Mockito.mock(Player.class);
		Game game = new Game(p, h);

		//Test movement here;
		int ret = game.doSomething("N");
		assertEquals(ret, 0);	
	}

	@Test
	public void doSomethingWinTest(){
		//Test winning the game here - will the game acknowledge you win if the player drinks and has coffee?
		House h = Mockito.mock(House.class);
		Player p = Mockito.mock(Player.class);
		Mockito.when(p.drink()).thenReturn(true); //Means that mock player is drinking correct items
		
		Game game = new Game(p, h);
		
		//Make sure doSomething returns 1 when we make the player drink
		assertEquals(game.doSomething("D"), 1);
		
		//Test losing the game here - will the game acknowledge you lose if the player drinks incorrectly?
		Player p2 = Mockito.mock(Player.class);
		Mockito.when(p2.drink()).thenReturn(false); //Means that mock player is drinking incorrect items
		
		Game g = new Game(p2, h);
		assertEquals(g.doSomething("D"), -1);	
	}
}