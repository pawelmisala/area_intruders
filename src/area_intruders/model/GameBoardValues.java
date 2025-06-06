package area_intruders.model;

public class GameBoardValues {
    private static final int tileSize = 32;
    private static final int rows = 16;
    private static final int cols = 16;

    public static int getTileSize(){
        return tileSize;
    }
    public static int getRows(){
        return rows;
    }
    public static int getCols(){
        return cols;
    }
    public static int getWidth(){
        return cols * tileSize;
    }
    public static int getHeight(){
        return rows * tileSize;
    }
}

