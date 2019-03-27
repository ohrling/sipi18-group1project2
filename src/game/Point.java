package game;

public abstract class Point {

    protected int y;
    protected int x;
    protected TileType tileType;

    public Point(int y, int x, TileType tileType) {
        this.y = y;
        this.x = x;
        this.tileType = tileType;
    }

    public TileType getTileType() {
        return tileType;
    }

    public Point getPoint() {
        return this;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    // Setters
    public void setTileType(TileType tileType) {
        this.tileType = tileType;
    }
}
