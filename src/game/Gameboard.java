package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static game.TileType.*;

public class Gameboard {

    private static Point[][] boardGrid;
    private List<MoveAblePoint> movingObjects = new ArrayList<>();
    private int level = 3;

    private Door door = new Door(9, 18);

    private Levels levels;
    private Player player;

    public Gameboard() {
        levels = new Levels(level);
        player = new Player(9, 1);
        PlayerScore.setName("Kalle");
        boardGrid = levels.getBoard();
        movingObjects.addAll(levels.getMonsters());
        movingObjects.add(player);
        boardGrid[door.getY()][door.getX()] = door;
        boardGrid[9][14].setTileType(TREASURE);
        updateBoard();
    }

    private void updateBoard() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                placeMovingObjects();
            }
        };

        Timer timer = new Timer("boardTimer");

        timer.scheduleAtFixedRate(task, 0, 10);
    }

    private void placeMovingObjects() {
        for (Monster m :
                levels.getMonsters()) {
            movingObjects.set(movingObjects.indexOf(m), m);
        }
        for (MoveAblePoint mP :
                movingObjects) {
            boardGrid[mP.getY()][mP.getX()] = mP;
        }
    }

    // Returning the Point of requested position
    public static Point getPoint(int y, int x) {
        return boardGrid[y][x];
    }

    // Moving character in desired direction
    public boolean moveCharacter(Direction direction) {
        boardGrid[player.getY()][player.getX()] = player.move(direction);
        return true;
    }

    public static Point[][] getBoardGrid() {
        return boardGrid;
    }

    public Point getPlayer() {
        return player;
    }
}
