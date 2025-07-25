package area_intruders.controller;

import area_intruders.model.*;
import area_intruders.view.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameController implements KeyListener {
    private final GameFrame gameFrame;
    private final GameplayPanel gameplayPanel;
    private final ControlsPanel controlsPanel;
    private final PlayerInfoPanel playerInfoPanel;
    private GameModel gameModel;
    private final Ship ship;
    private final EnemiesManager enemiesManager;
    private final BulletsManager bulletsManager;
    private final Player player;

    public GameController(GameFrame gameFrame, Player player) {
        this.gameFrame = gameFrame;
        this.gameplayPanel = gameFrame.getGameplayPanel();
        this.controlsPanel = gameFrame.getControllsPanel();
        this.playerInfoPanel = gameFrame.getPlayerInfoPanel();
        this.gameModel = new GameModel(this, player);
        this.ship = new Ship();
        this.enemiesManager = new EnemiesManager();
        this.bulletsManager = new BulletsManager();
        this.player = player;
        gameplayPanel.setGameController(this);
        controlsPanel.setGameController(this);
        playerInfoPanel.setGameController(this);

        //Menu action listeners
        gameFrame.addTop10ScoresMenuItemListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Top10ScoresFrame();
            }
        });

        //Buttons action listeners
        gameFrame.addStartButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startNewGame();
            }
        });
        gameFrame.addTop10ScoresButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Top10ScoresFrame();
            }
        });
        gameFrame.addHowToPlayButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameFrame.changeCardLayoutPanel("HOW_TO_PLAY");
            }
        });
        gameFrame.addRestartButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startNewGame();
            }
        });
        gameFrame.addMainMenuButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameFrame.changeCardLayoutPanel("STARTING_SCREEN");
            }
        });
        gameFrame.addExitButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameFrame.dispose();
            }
        });

        //PAUSE, RESUME Buttons listeners
        gameplayPanel.addPauseButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pauseGame();
            }
        });
        gameplayPanel.addResumeButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resumeGame();
            }
        });

        //Ship movement buttons listeners
        controlsPanel.addMoveLeftButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!gameModel.isPaused()) {
                    if (Player.isMovementInverted()) {
                        ship.moveShipRight();
                    } else {
                        ship.moveShipLeft();
                    }
                }
                gameplayPanel.requestFocusInWindow();
            }
        });
        controlsPanel.addShootButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bulletsManager.shootBullet(ship.getShipX(), ship.getShipY(), ship.getShipWidth());
                gameplayPanel.requestFocusInWindow();
            }
        });
        controlsPanel.addMoveRightButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!gameModel.isPaused()) {
                    if (Player.isMovementInverted()) {
                        ship.moveShipLeft();
                    } else {
                        ship.moveShipRight();
                    }
                }
                gameplayPanel.requestFocusInWindow();
            }
        });
    }

    //Painting components
    public void drawShip(Graphics g) {
        g.drawImage(ship.getShipImage(), ship.getShipX(), ship.getShipY(), ship.getShipWidth(), ship.getShipHeight(), null);
    }
    public void drawEnemies(Graphics g) {
        enemiesManager.getEnemiesArrayList().stream()
                .filter(enemy -> enemy.isAlive())
                .forEach(enemy -> g.drawImage(enemiesManager.getImage(), enemy.getEnemyX(), enemy.getEnemyY(), enemy.getEnemyWidth(), enemy.getEnemyHeight(), null));
    }
    public void drawBullet(Graphics g) {
        g.setColor(Color.red);
        bulletsManager.getBulletsArrayList().stream()
                .filter(bullet -> !bullet.wasShot())
                .forEach(bullet -> g.fillRect(bullet.getBulletX(), bullet.getBulletY(), bullet.getBulletWidth(), bullet.getBulletHeight()));

    }
    public void repaintGameplayPanel() {
        gameplayPanel.repaint();
    }

    //Methods running in gameThread
    public void moveEnemies(){
        enemiesManager.createEnemies();
        enemiesManager.getEnemiesArrayList().stream()
                .forEach(enemy -> enemy.moveEnemy());
        enemiesManager.getEnemiesArrayList().stream()
                        .filter(enemy -> !enemy.isAlive())
                                .forEach(enemy -> enemiesManager.decrementEnemyCount());
        enemiesManager.getEnemiesArrayList().removeIf(enemy -> !enemy.isAlive());

    }
    public void moveBullets() {
        bulletsManager.getBulletsArrayList().stream()
                .forEach(bullet -> bullet.moveBullet());
        bulletsManager.getBulletsArrayList().removeIf(bullet -> bullet.wasShot());
    }
    public synchronized boolean checkBulletAndEnemyColision() {
        return bulletsManager.getBulletsArrayList().stream()
                .anyMatch(bullet -> enemiesManager.getEnemiesArrayList().stream()
                        .anyMatch(enemy -> bullet.checkColisionWithEnemy(enemy)));
    }
    public boolean checkShipAndEnemyColision(){
        return enemiesManager.getEnemiesArrayList().stream()
                .anyMatch(enemy -> enemy.checkCollisionWithShip(ship));
    }

    //Starting new game
    public void startNewGame(){
        ship.restartShip(); //SETTING DEFAULT SHIP POSITION
        enemiesManager.restartEnemies();
        bulletsManager.restartBullets();
        updateScoreLabel(0);
        gameFrame.changeCardLayoutPanel("GAME_PANEL");
        gameplayPanel.requestFocusInWindow();
        gameModel.start(); //STARTING NEW GAME THREAD
    }

    public void pauseGame(){
        gameModel.pause();
        gameplayPanel.enableGamePauseButton(false);
        gameplayPanel.enableGameResumeButton(true);
        gameplayPanel.requestFocusInWindow();
    }
    public void resumeGame(){
        gameModel.resume();
        gameplayPanel.enableGameResumeButton(false);
        gameplayPanel.enableGamePauseButton(true);
        gameplayPanel.requestFocusInWindow();
    }

    public void updateScoreLabel(int score){
        gameplayPanel.getScoreLabel().setText("SCORE: " + score + "   ");
    }
    public void gameOver(boolean isInTop10, int top10index){
        playerInfoPanel.getNicknameLabel().setText(player.getNickname());
        playerInfoPanel.getScoreLabel().setText("SCORE: " + player.getScore());
        if (isInTop10){
            playerInfoPanel.getCongratulationsPanel().setVisible(true);
            playerInfoPanel.getCongratulationsLabel().setText("CONGRATULATIONS! Your score puts you in top " + top10index);
       }
        gameFrame.changeCardLayoutPanel("GAME_OVER_PANEL");
    }

    //KeyListener
    @Override
    public void keyPressed(KeyEvent e) {
        if (!gameModel.isPaused()) { //BLOCKING MOVEMENT WHILE GAME IS PAUSED
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (player.isMovementInverted())
                        ship.moveShipRight();
                    else {
                        ship.moveShipLeft();
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (player.isMovementInverted())
                        ship.moveShipLeft();
                    else {
                        ship.moveShipRight();
                    }
                    break;
                case KeyEvent.VK_SPACE:
                    bulletsManager.shootBullet(ship.getShipX(), ship.getShipY(), ship.getShipWidth());
                    break;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            if (gameModel.isPaused()) {
                resumeGame();
            } else {
                pauseGame();
            }
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
}
