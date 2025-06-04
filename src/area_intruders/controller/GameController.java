package area_intruders.controller;

import area_intruders.model.Enemies;
import area_intruders.model.GameModel;
import area_intruders.model.Ship;
import area_intruders.model.UserSettings;
import area_intruders.view.ControllsPanel;
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
    ControllsPanel controllsPanel;
    GameModel gameModel;
    Ship ship;
    Enemies enemies;

    public GameController(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
        this.gameplayPanel = gameFrame.getGameplayPanel();
        this.controllsPanel = gameFrame.getControllsPanel();
        this.gameModel = new GameModel(this);
        this.ship = new Ship();

        this.enemies = new Enemies();
        enemies.createEnemies();

        gameplayPanel.setGameController(this);
        controllsPanel.setGameController(this);

        gameFrame.addStartButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameFrame.getCardLayout().show(gameFrame.getMainPanel(), "GAME_PANEL");
                gameplayPanel.requestFocusInWindow();
                gameModel.start();
            }
        });

        controllsPanel.addMoveLeftButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (UserSettings.getInvertedMovement()){
                    ship.moveRight();
                }
                else {
                    ship.moveLeft();
                }
                gameplayPanel.requestFocusInWindow();
            }
        });
        controllsPanel.addShootButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ship.shoot();
                gameplayPanel.requestFocusInWindow();
            }
        });
        controllsPanel.addMoveRightButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (UserSettings.getInvertedMovement()){
                    ship.moveLeft();
                }
                else {
                    ship.moveRight();
                }
                gameplayPanel.requestFocusInWindow();
            }
        });
    }

    public void drawShip(Graphics g) {
        g.drawImage(ship.getShipImage(), ship.getShipX(), ship.getShipY(), ship.getShipWidth(), ship.getShipHeight(), null);
    }
    public void drawEnemies(Graphics g) {
        enemies.getEnemies().stream()
                .filter(enemy -> enemy.isAlive())
                .forEach(enemy -> g.drawImage(enemies.getImage(), enemy.getEnemyX(), enemy.getEnemyY(), enemy.getEnemyWidth(), enemy.getEnemyHeight(), null));
    }
    public void repaintGameplayPanel() {
        gameplayPanel.repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT && ship.getShipX() > ship.getShipWidth()/2) {
            if (UserSettings.getInvertedMovement())
            ship.moveRight();
            else {
                ship.moveLeft();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            ship.shoot();
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && ship.getShipX() < gameFrame.getWIDTH() - ship.getShipWidth()/2) {
            if (UserSettings.getInvertedMovement())
                ship.moveLeft();
            else {
                ship.moveRight();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}
