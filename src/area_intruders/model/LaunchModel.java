package area_intruders.model;

import javax.swing.*;

public class LaunchModel {
    private String nickname;
    private Difficulty difficulty;
    private Icon shipIcon;

    public LaunchModel(String nickname, Difficulty difficulty, Icon shipIcon) {
        this.nickname = nickname;
        this.difficulty = difficulty;
        this.shipIcon = shipIcon;
    }
}
