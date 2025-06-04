package area_intruders.view;

import javax.swing.*;
import java.awt.*;

public class ControllsPanel extends JPanel {
    private JButton moveLeftButton, shootButton ,moveRightButton;
    public ControllsPanel() {
        this.setLayout(new GridLayout(1,3,10,10));
        moveLeftButton = new JButton("<");
        shootButton = new JButton("SHOOT");
        moveRightButton = new JButton(">");
        add(moveLeftButton);
        add(shootButton);
        add(moveRightButton);
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(512,100));
    }
}
