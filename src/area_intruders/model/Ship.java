package area_intruders.model;

import javax.swing.*;
import java.awt.*;

import static java.lang.Thread.sleep;

public class Ship {
    private int shipX;
    private int shipY;
    private int shipWidth;
    private int shipHeight;
    private Image shipImage;

    public Ship() {
        this.shipX = GameBoardValues.getTileSize() * GameBoardValues.getCols() / 2 - GameBoardValues.getTileSize(); // (width/2)-tileSize
        this.shipY = GameBoardValues.getTileSize() * GameBoardValues.getRows() - GameBoardValues.getTileSize() * 2; // height-(2*tileSize)
        this.shipWidth = GameBoardValues.getTileSize() * 2;
        this.shipHeight = GameBoardValues.getTileSize() * 2;
        this.shipImage = new ImageIcon(Player.getShipIconFilePath()).getImage();
    }

    public Image getShipImage() {
        return shipImage;
    }
    public int getShipX() {
        return shipX;
    }
    public int getShipY() {
        return shipY;
    }
    public int getShipWidth() {
        return shipWidth;
    }
    public int getShipHeight() {
        return shipHeight;
    }

    public void moveShipLeft(){
        this.shipX = this.shipX - GameBoardValues.getTileSize();
    }
    public void moveShipRight(){
        this.shipX = this.shipX + GameBoardValues.getTileSize();
    }

    public void restartShip(){
        this.shipX = GameBoardValues.getTileSize() * GameBoardValues.getCols() / 2 - GameBoardValues.getTileSize();
        this.shipY = GameBoardValues.getTileSize() * GameBoardValues.getRows() - GameBoardValues.getTileSize() * 2;
    }
}
