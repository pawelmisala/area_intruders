package area_intruders.model;

public class LaunchModel {
    private String nickname;
    private Difficulty difficulty;
    private String shipIconFilePath;
    private int enemiesInARow;
    private int enemiesFallingSpeed;
    private boolean invertedMovement;

    public LaunchModel(String nickname, Difficulty difficulty, String shipIconFilePath, int enemiesInARow, int enemiesFallingSpeed, boolean invertedMovement) {
        this.nickname = nickname;
        this.difficulty = difficulty;
        this.shipIconFilePath = shipIconFilePath;
        this.enemiesInARow = enemiesInARow;
        this.enemiesFallingSpeed = enemiesFallingSpeed;
        this.invertedMovement = invertedMovement;
    }

    public String getNickname() {
        return nickname;
    }
}
