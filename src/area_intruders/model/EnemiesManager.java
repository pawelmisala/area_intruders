package area_intruders.model;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class EnemiesManager {
    private ArrayList<Enemy> enemiesArrayList;
    private int enemyRows = Player.getNumberOfRows();
    private int enemiesInARow = Player.getEnemiesInARow();
    private int enemyCount;
    private Image enemyImage;

    public EnemiesManager() {
        this.enemiesArrayList = new ArrayList<>();
        this.enemyImage = new ImageIcon("resources/enemy.png").getImage();
    }

    public void createEnemies() {
        if (enemyCount == 0) {
            for (int i = 0; i < enemiesInARow; i++) {
                for (int j = 0; j < enemyRows; j++) {
                    enemiesArrayList.add(new Enemy(i, j));
                }
            }
            this.enemyCount = enemiesArrayList.size();
        }
    }
    public void decrementEnemyCount(){
        this.enemyCount -= 1;
    }

    public ArrayList<Enemy> getEnemiesArrayList() {
        return this.enemiesArrayList;
    }
    public int getEnemyCount(){
        return this.enemyCount;
    }
    public Image getImage() {
        return this.enemyImage;
    }

    public void restartEnemies() {
        enemiesArrayList.clear();
        enemyCount = 0;
    }
}
