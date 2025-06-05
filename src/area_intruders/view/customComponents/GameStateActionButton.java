package area_intruders.view.customComponents;

import area_intruders.model.GameBoardValues;

import javax.swing.*;
import java.awt.*;

public class GameStateActionButton extends JButton {
    int buttonX;
    int buttonY;
    int buttonWidth;
    int buttonHeight;

    public GameStateActionButton(String text) {
        super(text);
        this.setForeground(Color.WHITE);
        this.setBackground(Color.BLACK);
        this.setFont(new Font("Label", Font.BOLD, 20));
        this.buttonWidth = 120;
        this.buttonHeight = 30;
        this.buttonX = GameBoardValues.getWidth() - this.buttonWidth - this.buttonWidth/3 - 5;
        this.buttonY = 0;
        this.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        this.setBounds(this.buttonX, this.buttonY, this.buttonWidth, this.buttonHeight);
    }
}
