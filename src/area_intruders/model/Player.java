package area_intruders.model;

public class Player implements Comparable<Player> {
    private String nickname;
    private int score;
    private Difficulty difficulty;

    private static String shipIconFilePath;
    private static int numberOfRows;
    private static int enemiesInARow;
    private static int enemiesVelocity;
    private static int enemiesFallingSpeed;
    private static boolean invertedMovement;

    public Player(String nickname, Difficulty difficulty) {
        this.nickname = nickname;
        this.score = 0;
        this.difficulty = difficulty;
    }

    protected Player(String nickname, int score, Difficulty difficulty) {
        this.nickname = nickname;
        this.score = score;
        this.difficulty = difficulty;
    }

    public static void setPlayerSettings(String iconPath, int nOfRows, int eInARow, int eVelocity, int eFallingSpeed, boolean invMovement) {
        shipIconFilePath = iconPath;
        numberOfRows = nOfRows;
        enemiesInARow = eInARow;
        enemiesVelocity = eVelocity;
        enemiesFallingSpeed = eFallingSpeed;
        invertedMovement = invMovement;
    }

    //Getters
    public String getNickname() {
        return nickname;
    }
    public Difficulty getDifficulty() {
        return difficulty;
    }
    public int getScore() {
        return score;
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
    public static boolean isMovementInverted() {
        return invertedMovement;
    }

    public void setScore(int score) {
        this.score = score;
    }

    protected static Player readPlayerFromString(String line){
        String[] parts = line.split(":");
        return new Player(parts[0], Integer.parseInt(parts[1]), Difficulty.valueOf(parts[2]));
    }

    @Override
    public String toString() {
        return nickname + ":" + score + ":" + difficulty;
    }

    @Override
    public int compareTo(Player p) {
        int result = p.getDifficulty().compareTo(this.getDifficulty());
        if (result == 0) {
            result = p.getScore() - this.getScore();
        }
        return result;
    }
}
