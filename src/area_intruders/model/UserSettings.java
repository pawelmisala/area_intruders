package area_intruders.model;

public class UserSettings {
    private static String nickname;
    private static Difficulty difficulty;
    private static String shipIconFilePath;
    private static int numberOfRows;
    private static int enemiesInARow;
    private static int enemiesVelocity;
    private static int enemiesFallingSpeed;
    private static boolean invertedMovement;

    public static void initializeSettings(String n, Difficulty d, String iconPath, int nOr, int eInARow, int eVelocity ,int eFallingSpeed, boolean iMovement) {
        nickname = n;
        difficulty = d;
        shipIconFilePath = iconPath;
        numberOfRows = nOr;
        enemiesInARow = eInARow;
        enemiesVelocity = eVelocity;
        enemiesFallingSpeed = eFallingSpeed;
        invertedMovement = iMovement;
    }

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
    public static int getNumberOfRows() {
        return numberOfRows;
    }
    public static int getEnemiesInARow() {
        return enemiesInARow;
    }
    public static int getEnemiesVelocity() {
        return enemiesVelocity;
    }
    public static int getEnemiesFallingSpeed() {
        return enemiesFallingSpeed;
    }
    public static boolean getInvertedMovement() {
        return invertedMovement;
    }
}


