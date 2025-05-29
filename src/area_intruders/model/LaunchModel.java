package area_intruders.model;

public class LaunchModel {
    private String nickname;
    private Difficulty difficulty;
    private String shipIconFilePath;
    private int enemiesInARow;
    private int enemiesFallingSpeed;

    public LaunchModel(String nickname, Difficulty difficulty, String shipIconFilePath, int enemiesInARow, int enemiesFallingSpeed) {
        this.nickname = nickname;
        this.difficulty = difficulty;
        this.shipIconFilePath = shipIconFilePath;
        this.enemiesInARow = enemiesInARow;
        this.enemiesFallingSpeed = enemiesFallingSpeed;
    }

    @Override
    public String toString() {
        return nickname + "\n" + difficulty + "\n" + shipIconFilePath + "\n" + enemiesInARow + "\n" + enemiesFallingSpeed;
    }
}
