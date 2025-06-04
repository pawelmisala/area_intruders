package area_intruders.controller;

import area_intruders.model.GameModel;
import area_intruders.model.Ship;
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
    GameModel gameModel;
    Ship ship;


    public GameController(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
        this.gameplayPanel = gameFrame.getGameplayPanel();
        this.gameModel = new GameModel(this);
        this.ship = new Ship();
        gameplayPanel.setGameController(this);

        gameFrame.addStartButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameFrame.getCardLayout().show(gameFrame.getMainPanel(), "GAME_PANEL");
                gameplayPanel.requestFocusInWindow();
                gameModel.start();
            }
        });
    }

    public void drawShip(Graphics g) {
        g.drawImage(ship.getShipImage(), ship.getShipX(), ship.getShipY(), ship.getShipWidth(), ship.getShipHeight(), null);
    }
    public void repaintGameplayPanel() {
        gameplayPanel.repaint();
    }

    //MOVEMENT

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT && ship.getShipX() > ship.getShipWidth()/2) {
            ship.moveLeft();
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && ship.getShipX() < gameFrame.getWIDTH() - ship.getShipWidth()/2) {
            ship.moveRight();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public int getShipXXX(){
        return ship.getShipX();
    }
}
