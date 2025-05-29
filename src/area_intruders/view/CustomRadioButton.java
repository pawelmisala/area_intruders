package area_intruders.view;

import javax.swing.*;
import java.awt.*;

public class CustomRadioButton extends JRadioButton {
    private String shipIconFilePath;

    public CustomRadioButton(String shipIconFilePath) {
        super();
        this.shipIconFilePath = shipIconFilePath;
    }

    public String getShipIconFilePath() {
        return shipIconFilePath;
    }
}