package area_intruders.controller;

import area_intruders.model.*;
import area_intruders.view.ControlsPanel;
import area_intruders.view.GameFrame;
import area_intruders.view.GameplayPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameController implements KeyListener {
    GameFrame gameFrame;
    GameplayPanel gameplayPanel;
    ControlsPanel controlsPanel;
    GameModel gameModel;
    Ship ship;
    EnemiesManager enemiesManager;
    BulletsManager bulletsManager;

    public GameController(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
        this.gameplayPanel = gameFrame.getGameplayPanel();
        this.controlsPanel = gameFrame.getControllsPanel();
        this.gameModel = new GameModel(this);
        this.ship = new Ship();
        this.enemiesManager = new EnemiesManager();
            enemiesManager.createEnemies();
        this.bulletsManager = new BulletsManager();
        gameplayPanel.setGameController(this);
        controlsPanel.setGameController(this);

        gameFrame.addStartButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameFrame.getCardLayout().show(gameFrame.getMainPanel(), "GAME_PANEL");
                gameplayPanel.requestFocusInWindow();
                gameModel.start();
            }
        });

        //Control buttons listeners
        controlsPanel.addMoveLeftButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (UserSettings.getInvertedMovement()){
                    ship.moveShipRight();
                }
                else {
                    ship.moveShipLeft();
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
                if (UserSettings.getInvertedMovement()){
                    ship.moveShipLeft();
                }
                else {
                    ship.moveShipRight();
                }
                gameplayPanel.requestFocusInWindow();
            }
        });
    }

    public void drawShip(Graphics g) {
        g.drawImage(ship.getShipImage(), ship.getShipX(), ship.getShipY(), ship.getShipWidth(), ship.getShipHeight(), null);
    }
    public void drawEnemies(Graphics g) {
        enemiesManager.getEnemies().stream()
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

    public void moveEnemies(){
        enemiesManager.getEnemies().stream()
                .forEach(enemy -> enemy.moveEnemy());
        enemiesManager.getEnemies().removeIf(enemy -> !enemy.isAlive());

    }
    public void moveBullets() {
        bulletsManager.getBulletsArrayList().stream()
                .forEach(bullet -> bullet.moveBullet());
        bulletsManager.getBulletsArrayList().removeIf(bullet -> bullet.wasShot());
    }
    public void checkCollision(Bullet bullet, Enemy enemy) {

    }

    //KeyListener
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT && ship.getShipX() > ship.getShipWidth()/2) {
            if (UserSettings.getInvertedMovement())
            ship.moveShipRight();
            else {
                ship.moveShipLeft();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            bulletsManager.shootBullet(ship.getShipX(), ship.getShipY(), ship.getShipWidth());
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && ship.getShipX() < GameBoardValues.getWidth() - ship.getShipWidth()/2) {
            if (UserSettings.getInvertedMovement())
                ship.moveShipLeft();
            else {
                ship.moveShipRight();
            }
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
}
