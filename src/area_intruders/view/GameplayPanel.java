package area_intruders.view;

import area_intruders.controller.GameController;
import area_intruders.model.GameBoardValues;

import javax.swing.*;
import java.awt.*;

public class GameplayPanel extends JPanel {
    private final int tileSize = GameBoardValues.getTileSize();
    private final int columns = GameBoardValues.getCols();
    private final int rows = GameBoardValues.getRows();
    private final int gameBoardWidth = columns * tileSize;
    private final int gameBoardHeight = rows * tileSize;
    private GameController gameController;

    public GameplayPanel() {
        this.setPreferredSize(new Dimension(gameBoardWidth, gameBoardHeight));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        gameController.drawShip(g);
        gameController.drawEnemies(g);
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
        this.addKeyListener(gameController);
    }
}

