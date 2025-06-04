package area_intruders.model;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Enemies {
    private ArrayList<Enemy> enemiesArray;
    private int enemyRows = UserSettings.getNumberOfRows();
    private int enemiesInARow = UserSettings.getEnemiesInARow();
    private int enemyCount;
    private Image enemyImage;

    public Enemies() {
        this.enemiesArray = new ArrayList<>();
        this.enemyImage = new ImageIcon("resources/enemy.png").getImage();
    }

    public void createEnemies() {
        for (int i = 0; i < enemiesInARow; i++) {
            for (int j = 0; j < enemyRows; j++) {
                enemiesArray.add(new Enemy(i,j));
            }
        }
        this.enemyCount = enemiesArray.size();
    }

    public ArrayList<Enemy> getEnemies() {
        return this.enemiesArray;
    }

    public Image getImage() {
        return this.enemyImage;
    }
}
