package game;

/**
 * @author Marcus
 * @Date 2019-03-17
 * @version 0.3
 */
public class Player extends MoveAblePoint {
	public Player(int y, int x) {
		super(y, x, TileType.CHARACTER);
	}

	@Override
	protected boolean onCollision(Point next) {
		return (next.getClass() == Wall.class);
	}

	@Override
	protected boolean onTreasure(Point next) {
		if(next.getClass() == Treasure.class){
			PlayerScore.addTreasures(1);
			return true;
		}
		return false;
	}

	@Override
	protected boolean onMonster(Point next) {
		if (next.getClass() == Monster.class) {
			// Returns true to show that character really stepped onto monster
			// Game is although over
			PlayerScore.isAlive = false;
		}
		return true;
	}

	@Override
	protected boolean onDoor(Point next) {
		if (next.getClass() == Door.class) {
			if (PlayerScore.getTreasures() > 0) {
				PlayerScore.isFinished = true;
			}
			return true;
		}
		return true;
	}

    @Override
    protected boolean onFloor(Point next) {
        return false;
    }
}
