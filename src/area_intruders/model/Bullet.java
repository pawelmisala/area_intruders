package area_intruders.model;

public class Bullet {
    private int bulletX;
    private int bulletY;
    private int bulletWidth;
    private int bulletHeight;
    private int bulletVellocity;
    private boolean wasShot;

    public Bullet(int shipX, int shipY, int shipWidth) {
        this.bulletX = shipX + shipWidth/2;
        this.bulletY = shipY;
        this.bulletWidth = 4;
        this.bulletHeight = 16;
        this.bulletVellocity = -16;
        this.wasShot = false;
    }

    public void moveBullet() {
        if (!this.wasShot) {
            this.bulletY += this.bulletVellocity;
            if (this.bulletY + this.bulletHeight <= 0) {
                this.wasShot = true;
            }
        }
    }

    public boolean checkColisionWithEnemy(Enemy enemy){
        if (this.bulletX >= enemy.getEnemyX() && this.bulletX <= enemy.getEnemyX() + enemy.getEnemyWidth() && this.bulletY >= enemy.getEnemyY() && this.bulletY <= enemy.getEnemyY() + enemy.getEnemyHeight() ){
            enemy.gotHit();
            this.wasShot = true;
            return true;
        }
        return false;
    }

    public int getBulletX() {
        return bulletX;
    }
    public int getBulletY() {
        return bulletY;
    }
    public boolean wasShot() {
        return wasShot;
    }
    public int getBulletWidth() {
        return bulletWidth;
    }
    public int getBulletHeight() {
        return bulletHeight;
    }
}
