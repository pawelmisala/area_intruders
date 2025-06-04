package area_intruders.model;

import java.util.ArrayList;

public class BulletsManager {
    private ArrayList<Bullet> bulletsArrayList;
    private int bulletsCount;

    public BulletsManager() {
        bulletsArrayList = new ArrayList<>();
        bulletsCount = 0;
    }

    public void shootBullet(int shipX, int shipY, int shipWidth) {
        Bullet bullet = new Bullet(shipX, shipY, shipWidth);
        bulletsArrayList.add(bullet);
    }

    public ArrayList<Bullet> getBulletsArrayList() {
        return bulletsArrayList;
    }
    public int getBulletsCount() {
        return bulletsCount;
    }
}
