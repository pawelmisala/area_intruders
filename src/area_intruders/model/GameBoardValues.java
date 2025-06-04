package area_intruders.model;

public class GameBoardValues {
    private static final int tileSize = 32;
    private static final int rows = 14;
    private static final int cols = 18;
    private static final int width = tileSize * cols;
    private static final int height = tileSize * rows;

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
        return width;
    }
    public static int getHeight(){
        return height;
    }
}

