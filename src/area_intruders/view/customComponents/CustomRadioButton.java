package area_intruders.view.customComponents;

import javax.swing.*;

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