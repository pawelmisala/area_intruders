package area_intruders.controller;

import area_intruders.model.GameModel;
import area_intruders.view.GameFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameController {
    GameFrame gameFrame;
    GameModel gameModel;


    public GameController(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
        this.gameModel = new GameModel();

        gameFrame.addStartButtonListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("TEST");
            }
        });
    }
}
