package area_intruders.model;

public class Enemy {
    private int enemyX;
    private int enemyY;
    private int enemyWidth;
    private int enemyHeight;
    private int enemyVelocity;
    private boolean isAlive;

    protected Enemy(int i, int j){
        this.enemyWidth = GameBoardValues.getTileSize() * 2;
        this.enemyHeight = GameBoardValues.getTileSize() * 2;
        this.enemyX = (this.enemyWidth + 5) * i;
        this.enemyY = (this.enemyHeight + 5) * j;
        this.enemyVelocity = UserSettings.getEnemiesVelocity();
        this.isAlive = true;
    }

    public void moveEnemy(){
        if (this.isAlive) {
            this.enemyX += this.enemyVelocity;

            //IF ENEMY TOUCHES THE BORDER
            if (this.enemyX + this.enemyWidth >= GameBoardValues.getWidth() || this.enemyX <= 0) {
                this.enemyVelocity *= -1;
                this.enemyY += this.enemyHeight + (UserSettings.getEnemiesFallingSpeed() - 1) * GameBoardValues.getTileSize()/2;
            }
        }
    }
    public boolean checkCollisionWithShip(Ship ship){
        if (
                this.enemyX < ship.getShipX() + ship.getShipWidth() &&
                this.enemyX + this.enemyWidth > ship.getShipX() &&
                this.enemyY < ship.getShipY() + ship.getShipHeight() &&
                this.enemyY + this.enemyHeight > ship.getShipY()
        ) {
            return true;
        }
        return false;
    }

    public int getEnemyX(){
        return enemyX;
    }
    public int getEnemyY(){
        return enemyY;
    }
    public int getEnemyWidth(){
        return enemyWidth;
    }
    public int getEnemyHeight(){
        return enemyHeight;
    }

    public boolean isAlive(){
        return this.isAlive;
    }

    public void gotHit(){
        this.isAlive = false;
    }
}
