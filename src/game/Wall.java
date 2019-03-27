package game;

public class Wall extends StaticPoint {
    public Wall(int y, int x) {
        super(y, x, TileType.WALL);
    }
}
