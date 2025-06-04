package area_intruders.model;

public class Enemy {
    private int enemyX;
    private int enemyY;
    private int enemyWidth;
    private int enemyHeight;
    private boolean isAlive;

    protected Enemy(int i, int j){
        this.enemyX = GameBoardValues.getTileSize() + GameBoardValues.getTileSize() * i;
        this.enemyY = GameBoardValues.getTileSize() + GameBoardValues.getTileSize() * j;
        this.enemyWidth = GameBoardValues.getTileSize() * 2;
        this.enemyHeight = GameBoardValues.getTileSize() * 2;
        this.isAlive = true;
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
