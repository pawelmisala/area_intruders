package area_intruders.view;

import area_intruders.controller.GameController;
import area_intruders.model.GameBoardValues;
import area_intruders.view.customComponents.GameStateActionButton;
import area_intruders.view.customComponents.ScoreLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GameplayPanel extends JPanel {
    private final int tileSize = GameBoardValues.getTileSize();
    private final int gameBoardWidth = GameBoardValues.getWidth();
    private final int gameBoardHeight = GameBoardValues.getHeight();
    private GameController gameController;
    private ScoreLabel scoreLabel;
    private GameStateActionButton gamePauseButton;
    private GameStateActionButton gameResumeButton;

    public GameplayPanel() {
        this.setPreferredSize(new Dimension(gameBoardWidth, gameBoardHeight));
        this.setBackground(Color.BLACK);
        this.setLayout(null); //ALLOWS MANUAL ALIGNMENT ON EVERY ELEMENT

        //SCORE LABEL PLACEMENT
        scoreLabel = new ScoreLabel("SCORE: 0");
        this.add(scoreLabel);

        //GAME PAUSE BUTTON PLACEMENT
        gamePauseButton = new GameStateActionButton("PAUSE");
        this.add(gamePauseButton);

        //GAME RESUME BUTTON PLACEMENT
        gameResumeButton = new GameStateActionButton("RESUME");
        this.add(gameResumeButton);

        this.setFocusable(true);
        this.requestFocusInWindow();
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

    //Getter
    public ScoreLabel getScoreLabel() {
        return scoreLabel;
    }

    public void enableGamePauseButton(boolean b) {
        gamePauseButton.setEnabled(b);
        gamePauseButton.setVisible(b);
    }
    public void enableGameResumeButton(boolean b) {
        gameResumeButton.setEnabled(b);
        gameResumeButton.setVisible(b);
    }

    public void addPauseButtonListener(ActionListener actionListener) {
        gamePauseButton.addActionListener(actionListener);
    }
    public void addResumeButtonListener(ActionListener actionListener) {
        gameResumeButton.addActionListener(actionListener);
    }
}

