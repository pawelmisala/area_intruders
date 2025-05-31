package area_intruders.view;

import area_intruders.controller.GameController;
import area_intruders.model.LaunchModel;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    private final int WIDTH = 1280;
    private final int HEIGHT = 960;

    public GameFrame(LaunchModel launchModel) {
        super("AREA INTRUDERS - " + launchModel.getNickname());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);
        this.add(new MainPanel());
        this.setVisible(true);
        GameController gameController = new GameController(this);
    }

    private class MainPanel extends JPanel {
        CardLayout cardLayout = new CardLayout();

        public MainPanel() {
            this.setLayout(cardLayout);
            this.add(new StartingScreenPanel(), "STARTING_SCREEN");
            cardLayout.show(this, "STARTING_SCREEN");
        }
    }

    private class StartingScreenPanel extends JPanel {
        public StartingScreenPanel() {
            this.setLayout(new BorderLayout());
            this.setBackground(Color.BLACK);
        }


    }


}
