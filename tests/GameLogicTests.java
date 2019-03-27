import game.*;

import static game.TileType.*;
import static game.Direction.*;

import static org.junit.Assert.*;

import org.junit.Ignore;

import game.Point;
import game.TileType;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class GameLogicTests {

    @Test
    public void testCreateGameBoard() {
        // Arrange
        GameBoard board = new GameBoard();

        // Act
        Point actual = board.getPoint(5, 15);

        // Assert
        assertEquals(FLOOR, actual.getTileType());
    }

    @Test
    public void SetTheBoardsOuterEdgesToWalls() {
        // Arrange
        GameBoard board = new GameBoard();

		// Act
		Point wallPoint = board.getPoint(0, 0);
		Point floorPoint = board.getPoint(5, 15);

		// Assert
		assertEquals(WALL, wallPoint.getTileType());
		assertEquals(FLOOR, floorPoint.getTileType());
	}

	@Test
	public void addDoorToGameBoard_PositionedRight_GetInt4ReturnedFromPoint() {
		// Arrange
		GameBoard board = new GameBoard();

		// Act
		Point doorPoint = board.getPoint(9, 18);

		// Assert
		assertEquals(DOOR, doorPoint.getTileType());
	}

	@Test
	@Parameters({ "9,2,RIGHT,CHARACTER", "8,1,UP,CHARACTER", "9,0,LEFT,WALL", "10,1,DOWN,CHARACTER" })
	public void moveCharacterOnePositionAccordingToDirection_GetIntWithResultMovement(int y, int x, Direction direction,
			TileType expected) {
		// Arrange
		GameBoard board = new GameBoard();
		board.moveCharacter(direction);

		// Act
		Point actual = board.getPoint(y, x);

		// Assert
		assertEquals(expected, actual.getTileType());
	}

	@Parameters({ "0, 1, true", // Wall
			"1, 10, false", // Character
			"5, 5, false" // Floor
	})
	@Test
	public void gettingAPointToCheckWhatsThere_GetBooleanFalseIfThereIsAWall(int y, int x, boolean expected) {
		// Arrange
		GameBoard board = new GameBoard();

        // Act
        boolean actual = false;//board.onCollision(x, y);

        // Assert
        assertTrue(actual);
        //assertEquals(expected, actual);
    }

	@Parameters({ "UP, true", "RIGHT, true", "DOWN, true", "LEFT, false" })
	@Test
	public void moveCharacterIntoWall_GettingFalseIfThereWasACollideAndMovementWasNotPossible(Direction direction,
			boolean expected) {
		// Arrange
		GameBoard board = new GameBoard();

		// Act
		boolean actual = board.moveCharacter(direction);

		// Assert
		assertEquals(expected, actual);
	}

	@Test
	public void moveCharacterTwoSteps_ShouldReturnTrueSinceTheresIsntAWall() {
		// Arrange
		GameBoard board = new GameBoard();

		// Act
		board.moveCharacter(RIGHT);
		boolean actual = board.moveCharacter(RIGHT);

		// Assert
		assertTrue(actual);
	}

	@Test
	public void moveCharacterMultipleSteps_OnLevelONE_GettingFalseSinceItIsHittingAWall() {
		// Arrange
		GameBoard board = new GameBoard();

		// Act
		board.moveCharacter(RIGHT);
		board.moveCharacter(RIGHT);
		board.moveCharacter(RIGHT);
		board.moveCharacter(RIGHT);
		board.moveCharacter(RIGHT);
		board.moveCharacter(UP);
		board.moveCharacter(UP);
		board.moveCharacter(UP);
		boolean actual = board.moveCharacter(UP);

		// Assert
		assertFalse(actual);
	}

    @Test
    public void checkIfMonsterIsAddedToTheBoard_ReturnsThe2dArrayWhichIsLoopedThroughToFindClassOfMonster() {
        // Arrange
        Levels levels = new Levels(1);
        Point[][] board = levels.getBoard();

        // Act
        Point actual = null;
        for (Point[] y:
                board) {
            for (Point x :
                    y) {
                if(x.getClass() == Monster.class){
                    actual = x;
                }
            }

        }

        // Assert
        assertEquals(Monster.class, actual.getClass());
    }

    @Ignore
    @Test
    public void characterCollisionsWithMonster_BooleanIsAliveSetsToFalseAndPlayerDead() {
        // Arrange
        Levels level = new Levels(1);
        Point[][] board = level.getBoard();

        // Act
        Point monster = null;
        for (Point[] y:
                board) {
            for (Point x :
                    y) {
                if(x.getClass() == Monster.class){
                    monster = x;
                }
            }

        }

        Player player = new Player(monster.getY()-1,monster.getX());
        board[player.getY()][player.getX()] = player;
        player.move(DOWN);

        boolean actual = PlayerScore.isAlive;

		// Assert
		assertEquals(actual, PlayerScore.getTreasures());
	}

	@Test
	public void testPlayerWalkingOntoDoorWithTreasures() {
		// Arrange
		GameBoard board = new GameBoard();

		// Act
		// Move player onto door
		for (int i = 1; i < 18; i++) {
			board.moveCharacter(Direction.RIGHT);
		}

		// Assert
		assertTrue(PlayerScore.isFinished);
	}

	@Test
	@Parameters(method = "parametersFortestPersistanceOfDoorWhenPlayerMovesOverIT")
	public void testPersistanceOfDoorWhenPlayerMovesOverIT(Direction[] direction) {
		// Arrange
		GameBoard board = new GameBoard();
		Point door = board.getPoint(board.getPlayer().getY(), board.getPlayer().getX()+1);

		door.setTileType(DOOR);
		//direction = (Direction[]) direction;
		// Act
		// Move onto the Door and past it
		for (int i = 0; i < direction.length; i++) {
			board.moveCharacter(direction[i]);
		}

		boolean isPointStillDoor = false;
		if (door.getTileType() == DOOR)
			isPointStillDoor = true;

		// Assert
		assertTrue(isPointStillDoor);

	}

	@SuppressWarnings({"unused"})
	private Object[] parametersFortestPersistanceOfDoorWhenPlayerMovesOverIT() {
		return new Object[] {
		new Direction[] {RIGHT, RIGHT},
		new Direction[] {UP,RIGHT,DOWN,DOWN},
		new Direction[] {DOWN, RIGHT, UP, UP},
		new Direction[] {DOWN, RIGHT, RIGHT, UP, LEFT, LEFT}
		};
	}

	@Test
	public void testPlayerWalkingOntoMonsterResultsInGameOver() {
		// Arrange
		GameBoard g = new GameBoard();

		// Act
		g.moveCharacter(Direction.RIGHT);
		g.moveCharacter(Direction.RIGHT);
		g.moveCharacter(Direction.DOWN);

		// Assert
		assertFalse(PlayerScore.isAlive);
	}
}