package game;

import static game.TileType.*;

public class Point {

    private int y; // On what y-axis the point is on
    private int x; // On what x-axis the point is on
    private TileType tileType; // What kind of point it is
    private TileType nextTileType;

    public Point(int y, int x, TileType tileType) {
        this.y = y;
        this.x = x;
        this.tileType = tileType;
    }

    // Getters
    public TileType getTileType() {
        return tileType;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    // Setters
    public void setTileType(TileType tileType) {
        if(this.tileType == TREASURE && tileType == CHARACTER) {
            nextTileType = OPENTREASURE;
            this.tileType = tileType;
        } if(tileType == FLOOR && nextTileType == OPENTREASURE) {
            this.tileType = nextTileType;
        } else if(this.tileType == DOOR) {
            nextTileType = DOOR;
            this.tileType = tileType;
        } else if(tileType == FLOOR && nextTileType == DOOR) {
            this.tileType = nextTileType;
        } else {
            this.tileType = tileType;
        }
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }
}
