package area_intruders.model;

public class UserSettings {
    private static String nickname;
    private static Difficulty difficulty;
    private static String shipIconFilePath;
    private static int enemiesInARow;
    private static int enemiesFallingSpeed;
    private static boolean invertedMovement;

    //GETTERS
    public static String getNickname() {
        return nickname;
    }
    public static Difficulty getDifficulty() {
        return difficulty;
    }
    public static String getShipIconFilePath() {
        return shipIconFilePath;
    }
    public static int getEnemiesInARow() {
        return enemiesInARow;
    }
    public static int getEnemiesFallingSpeed() {
        return enemiesFallingSpeed;
    }
    public static boolean getInvertedMovement() {
        return invertedMovement;
    }

    //SETTERS
    protected static void setNickname(String nickname) {
        UserSettings.nickname = nickname;
    }
    protected static void setDifficulty(Difficulty difficulty) {
        UserSettings.difficulty = difficulty;
    }
    protected static void setShipIconFilePath(String shipIconFilePath) {
        UserSettings.shipIconFilePath = shipIconFilePath;
    }
    protected static void setEnemiesInARow(int enemiesInARow) {
        UserSettings.enemiesInARow = enemiesInARow;
    }
    protected static void setEnemiesFallingSpeed(int enemiesFallingSpeed) {
        UserSettings.enemiesFallingSpeed = enemiesFallingSpeed;
    }
    protected static void setInvertedMovement(boolean invertedMovement) {
        UserSettings.invertedMovement = invertedMovement;
    }
}


