import game.Point;
import game.TileType;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class PointTest {

    @Parameters({
            "1,1,FLOOR", // Floor
            "0,0,WALL", // Wall
            "2,2,CHARACTER", // Character
            "5,5,TREASURE", // Treasure
            "10,20,DOOR", // Door
            "5,10,MONSTER" // Laser
    })
    @Test
    public void createPointGetTypeByInt(int y, int x, TileType tileType) {
        // Assert
        Point p = new Point(y, x, tileType);

        // Act
        TileType actual = p.getTileType();

        // Assert
        assertEquals(tileType,actual);
    }

    @Parameters ({
            "13, 13, TREASURE, CHARACTER, FLOOR, OPENTREASURE",
            "9, 18, DOOR, CHARACTER, FLOOR, DOOR",
            "13, 13, FLOOR, CHARACTER, FLOOR, FLOOR",
            "13, 13, FLOOR, MONSTER, FLOOR, FLOOR",
            "9, 18, DOOR, MONSTER, FLOOR, DOOR"
    })
    @Test
    public void makePointReturnToRightTileAfterCharacterMovesOutOfIt_ReturnsTileType(int y, int x, TileType groundTile, TileType newTileType, TileType moveOutTileType, TileType expectedTileType) {
        // Arrange
        Point p = new Point(y, x, groundTile);
        p.setTileType(newTileType);
        p.setTileType(moveOutTileType);

        // Act
        TileType actual = p.getTileType();

        // Assert
        assertEquals(expectedTileType, actual);
    }
}

