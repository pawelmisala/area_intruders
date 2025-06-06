package area_intruders.model;

import java.util.ArrayList;

public class BulletsManager {
    private ArrayList<Bullet> bulletsArrayList;

    public BulletsManager() {
        bulletsArrayList = new ArrayList<>();
    }

    public void shootBullet(int shipX, int shipY, int shipWidth) {
        if (bulletsArrayList.isEmpty()) { //ALLOWING TO SHOOT ONE BULLET AT A TIME
            Bullet bullet = new Bullet(shipX, shipY, shipWidth);
            bulletsArrayList.add(bullet);
        }
    }

    public ArrayList<Bullet> getBulletsArrayList() {
        return bulletsArrayList;
    }

    public void restartBullets() {
        bulletsArrayList.clear();
    }
}
