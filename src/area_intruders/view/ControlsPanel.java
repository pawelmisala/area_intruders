package area_intruders.view;

import area_intruders.controller.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ControlsPanel extends JPanel {
    private JButton moveLeftButton, shootButton ,moveRightButton;
    private GameController gameController;

    public ControlsPanel() {
        this.setLayout(new GridLayout(1,3,10,10));
        this.setBackground(Color.BLACK);
        moveLeftButton = new JButton("<");
        shootButton = new JButton("SHOOT");
        moveRightButton = new JButton(">");
        add(moveLeftButton);
        add(shootButton);
        add(moveRightButton);
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(512,100));
    }

    public void addMoveLeftButtonListener(ActionListener actionListener) {
        moveLeftButton.addActionListener(actionListener);
    }
    public void addShootButtonListener(ActionListener actionListener) {
        shootButton.addActionListener(actionListener);
    }
    public void addMoveRightButtonListener(ActionListener actionListener) {
        moveRightButton.addActionListener(actionListener);
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }
}
