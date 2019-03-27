package game;

public class Monster extends MoveAblePoint {

    public Monster(int y, int x) {
        super(y, x, TileType.MONSTER);
    }

    @Override
    protected boolean onCollision(Point next) {
        return next.getClass() == Wall.class;
    }

    @Override
    protected boolean onTreasure(Point next) {
        return !(next.getClass() == Treasure.class);
    }

    @Override
    protected boolean onMonster(Point next) {
        return !(next.getClass() == Monster.class);
    }

    @Override
    protected boolean onDoor(Point next) {
        return !(next.getClass() == Door.class);
    }
}
