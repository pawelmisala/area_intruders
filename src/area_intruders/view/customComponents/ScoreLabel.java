package area_intruders.view.customComponents;

import javax.swing.*;
import java.awt.*;

public class ScoreLabel extends JLabel {
    int scoreLabelWidth;
    int scoreLabelHeight;
    int scoreLabelX;
    int scoreLabelY;

    public ScoreLabel(String text) {
        super(text);
        this.setFont(new Font("Label", Font.BOLD, 20));
        this.setForeground(Color.WHITE);
        this.scoreLabelWidth = super.getPreferredSize().width + 50;
        this.scoreLabelHeight = super.getPreferredSize().height;
        this.setPreferredSize(new Dimension(scoreLabelWidth, scoreLabelHeight));
        this.scoreLabelX = 20;
        this.scoreLabelY = 0;
        this.setBounds(scoreLabelX, scoreLabelY, scoreLabelWidth, scoreLabelHeight);
    }
}
