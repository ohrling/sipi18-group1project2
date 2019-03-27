
/**
 * @author Marcus Laitala
 * @Date 2019-03-17 
 * @version 0.8
 */

import game.*;
import junitparams.JUnitParamsRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import game.Player;
import game.Point;

import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class PlayerTest {
	Player player;

	@Before
	public void setup() {
		player = new Player(9, 1);
	}

	@Test
	public void testPlayerPickupTreasureIncreasesTreasure() {
		// Arrange
        PlayerScore.addTreasures(1);

		// Act
		int actual = PlayerScore.getTreasures();

		// Assert
		assertEquals(actual, 1);
	}

	@Test
	public void testSetPlayerPosition(){
		// Arrange
		player = new Player(1, 1);
		
		//Act
		Point actual = (Point)player;
		
		//Assert
		assertTrue(actual.equals(player));

	}
	
	@Test
	public void testSetPlayerAlive() {
		//Arrange
		
		//Act
		PlayerScore.isAlive = false;
		
		//Assert
		assertFalse(PlayerScore.isAlive);
	}
}
