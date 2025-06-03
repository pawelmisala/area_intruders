package area_intruders.controller;

import area_intruders.model.GameModel;
import area_intruders.model.Ship;
import area_intruders.view.GameFrame;
import area_intruders.view.GameplayPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameController {
    GameFrame gameFrame;
    GameplayPanel gameplayPanel;
    GameModel gameModel;
    Ship ship;


    public GameController(GameFrame gameFrame, GameplayPanel gameplayPanel) {
        this.gameFrame = gameFrame;
        this.gameplayPanel = gameplayPanel;
        this.gameModel = new GameModel(this);
        this.ship = new Ship();

        gameFrame.addStartButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameFrame.getCardLayout().show(gameFrame.getMainPanel(), "GAME_PANEL");
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
}
