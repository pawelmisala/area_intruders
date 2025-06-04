package area_intruders.model;

public class Enemy {
    private int enemyX;
    private int enemyY;
    private int enemyWidth;
    private int enemyHeight;
    private int enemyVelocity;
    private boolean isAlive;

    protected Enemy(int i, int j){
        this.enemyX = GameBoardValues.getTileSize() + GameBoardValues.getTileSize() * i;
        this.enemyY = GameBoardValues.getTileSize() * j;
        this.enemyWidth = GameBoardValues.getTileSize() * 2;
        this.enemyHeight = GameBoardValues.getTileSize() * 2;
        this.enemyVelocity = UserSettings.getEnemiesVelocity();
        this.isAlive = true;
    }

    public void moveEnemy(){
        if (this.isAlive) {
            this.enemyX += this.enemyVelocity;

            //IF ENEMY TOUCHES THE BORDER
            if (this.enemyX + this.enemyWidth == GameBoardValues.getWidth() || this.enemyX == 0) {
                this.enemyVelocity *= -1;
                this.enemyX += this.enemyVelocity;

                this.enemyY += this.enemyHeight + (UserSettings.getEnemiesFallingSpeed() - 1) * 2;
            }
        }
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
}
