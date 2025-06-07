package area_intruders.view;

import area_intruders.model.GameBoardValues;

import javax.swing.*;
import java.awt.*;

public class HowToPlayPanel extends JPanel {
    public HowToPlayPanel(GameFrame gameFrame) {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.BLACK);

        JLabel howToPlayLabel = new JLabel(new ImageIcon("resources/how_to_play.png"));
        this.add(howToPlayLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
            buttonPanel.setPreferredSize(new Dimension(GameBoardValues.getWidth(), 100));
            buttonPanel.setBackground(Color.BLACK);
            buttonPanel.add(gameFrame.getHowToPlayPanelMainMenuButton(), BorderLayout.SOUTH);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }
}