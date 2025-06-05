package area_intruders.view;

import area_intruders.view.customComponents.CustomRadioButton;

import javax.swing.*;
import java.awt.*;

public class ShipOptionPanel extends JPanel {
    private CustomRadioButton radioButton;
    private JLabel shipIconLabel;

    public ShipOptionPanel(String shipFilename) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.radioButton = new CustomRadioButton(shipFilename);
        radioButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        ImageIcon shipIcon = new ImageIcon(new ImageIcon(shipFilename).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        shipIconLabel = new JLabel(shipIcon);
        shipIconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(shipIconLabel);
        this.add(radioButton);
    }

    public CustomRadioButton getRadioButton() {
        return this.radioButton;
    }
}
