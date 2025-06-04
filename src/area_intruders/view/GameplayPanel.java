package area_intruders.view;

import area_intruders.controller.GameController;
import area_intruders.model.GameBoardValues;

import javax.swing.*;
import java.awt.*;

public class GameplayPanel extends JPanel {
    private final int tileSize = GameBoardValues.getTileSize();
    private final int gameBoardWidth = GameBoardValues.getWidth();
    private final int gameBoardHeight = GameBoardValues.getHeight();
    private GameController gameController;
    private JLabel scoreLabel;

    public GameplayPanel() {
        this.setPreferredSize(new Dimension(gameBoardWidth, gameBoardHeight));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.add(scoreLabel = new JLabel("SCORE: 0"));
        scoreLabel.setForeground(Color.WHITE);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        gameController.drawShip(g);
        gameController.drawEnemies(g);
        gameController.drawBullet(g);
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
        this.addKeyListener(gameController);
    }

    public JLabel getScoreLabel() {
        return scoreLabel;
    }
}

