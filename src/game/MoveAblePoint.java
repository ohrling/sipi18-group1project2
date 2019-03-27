package game;

import static game.Direction.*;

public abstract class MoveAblePoint extends Point {
    private Point nextPoint;
    public MoveAblePoint(int y, int x, TileType tileType) {
        super(y, x, tileType);
    }

    public Point move(Direction direction) {
        int move = direction.getValue();
        if(direction == UP || direction == DOWN) {
            nextPoint = Gameboard.getPoint(y + move,x);
            if(onCollision(nextPoint)) {
                return this;
            }else if(onTreasure(nextPoint)) {
                this.y = y + move;
                if(this.getClass() == Player.class) {
                    nextPoint = new Floor(nextPoint.getY(), nextPoint.getX());
                } else {
                    return nextPoint;
                }
            }else if(onMonster(nextPoint)) {
                this.y = y + move;
                return nextPoint;
            }
        } else {
            nextPoint = Gameboard.getPoint(y, x + move);
            if(onCollision(nextPoint)) {
                return this;
            }else if(onTreasure(nextPoint)) {
                this.x = x + move;
                nextPoint = new Floor(nextPoint.getY(), nextPoint.getX());
                return nextPoint;
            }else if(onMonster(nextPoint)) {
                this.x = x + move;
                return nextPoint;
            }else if(onDoor(nextPoint)){
                this.x = x + move;
                return this;
            }
        }
        return this;
    }

    protected abstract boolean onCollision(Point next);
    protected abstract boolean onTreasure(Point next);
    protected abstract boolean onMonster(Point next);
    protected abstract boolean onDoor(Point next);

}
