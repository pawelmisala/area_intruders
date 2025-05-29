package area_intruders.controller;

import area_intruders.model.Difficulty;
import area_intruders.model.LaunchModel;
import area_intruders.view.CustomRadioButton;
import area_intruders.view.LaunchFrame;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LaunchController {
    private LaunchFrame launchFrame;

    public LaunchController(LaunchFrame launchFrame) {
        this.launchFrame = launchFrame;

        launchFrame.addSubmitButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (launchFrame.getNickname().isEmpty()){
                    JOptionPane.showMessageDialog(launchFrame, "Please, enter your nickname!");
                }
                else {
                    String nickname = launchFrame.getNickname(); //POBIERAMY WARTOSCI Z VIEW PO CZYM PRZYPISUJEMY JE ORAZ
                    Difficulty difficulty = launchFrame.getDifficulty();
                    String shipIconFilePath = getShipIconFilePath();
                    int enemiesInARow = (int) launchFrame.getEnemiesInARowSpinner().getValue();
                    int enemiesFallingSpeed = launchFrame.getEnemiesFallingSpeedSlider().getValue();

                    LaunchModel model = new LaunchModel(nickname, difficulty, shipIconFilePath, enemiesInARow, enemiesFallingSpeed); //TWORZYMY OBIEKT MODEL KTORY PRZECHOWUJE DANE NIE MAJAC POLACZENIA Z GUI
                    launchFrame.close();
                }
            }
        });

        launchFrame.addEnemiesFallingSpeedSliderChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int value = launchFrame.getEnemiesFallingSpeedSlider().getValue();
                launchFrame.setEnemiesInARowSettingLabel("Enemies falling speed: (" + value + ")");
            }
        });
    }

    private String getShipIconFilePath() {
        CustomRadioButton selectedShipIconRadioButton = launchFrame.getShipRadioButtons().stream()
                .filter(e -> e.isSelected())
                .findFirst()
                .orElse(null);

        if (selectedShipIconRadioButton == null) {
            return launchFrame.getShipRadioButtons().getFirst().getShipIconFilePath();
        }

        return selectedShipIconRadioButton.getShipIconFilePath();
    }
}
