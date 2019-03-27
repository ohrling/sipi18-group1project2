package game;

public class PlayerScore {
    public static boolean isAlive = true;
    public static boolean isFinished = false;
    private static String name;
    private static int treasures = 0;

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        PlayerScore.name = name;
    }

    public static int getTreasures() {
        return treasures;
    }

    public static void setTreasures(int treasures) {
        PlayerScore.treasures = treasures;
    }
}
