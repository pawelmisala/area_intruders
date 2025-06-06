package area_intruders.controller;

import area_intruders.model.Difficulty;
import area_intruders.model.Player;
import area_intruders.view.customComponents.CustomRadioButton;
import area_intruders.view.GameFrame;
import area_intruders.view.LaunchFrame;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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
                    Player player = new Player(launchFrame.getNickname(), launchFrame.getDifficulty());
                    Player.setPlayerSettings(
                            getShipIconFilePathFromLaunchFrame(),
                            (Integer) launchFrame.getNumberOfRowsSpinner().getValue(),
                            (Integer) launchFrame.getEnemiesInARowSpinner().getValue(),
                            launchFrame.getEnemiesVelocitySlider().getValue(),
                            launchFrame.getEnemiesFallingSpeedSlider().getValue(),
                            launchFrame.getInvertedMovementCheckBox().isSelected()
                    );

                    launchFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    launchFrame.close();
                    GameFrame gameFrame = new GameFrame(player);
                }
            }
        });

        launchFrame.addDifficultyComboBoxListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Difficulty currentDifficulty = launchFrame.getDifficulty();
                switch (currentDifficulty) {
                    case EASY:
                        //NUMBER OF ROWS
                        launchFrame.getNumberOfRowsSpinner().setValue(3);
                        launchFrame.getNumberOfRowsSpinner().setEnabled(false);
                        //ENEMIES IN A ROW
                        launchFrame.getEnemiesInARowSpinner().setValue(5);
                        launchFrame.getEnemiesInARowSpinner().setEnabled(false);
                        //ENEMIES VELOCITY
                        launchFrame.getEnemiesVelocitySlider().setValue(2);
                        launchFrame.getEnemiesVelocitySlider().setEnabled(false);
                        //ENEMIES FALLING SPEED
                        launchFrame.setEnemiesFallingSpeedLabel("Enemies falling speed: (1)");
                        launchFrame.getEnemiesFallingSpeedSlider().setValue(1);
                        launchFrame.getEnemiesFallingSpeedSlider().setEnabled(false);
                        //INVERTED MOVEMENT
                        launchFrame.getInvertedMovementCheckBox().setSelected(false);
                        launchFrame.getInvertedMovementCheckBox().setEnabled(false);
                        break;
                    case MEDIUM:
                        //NUMBER OF ROWS
                        launchFrame.getNumberOfRowsSpinner().setValue(4);
                        launchFrame.getNumberOfRowsSpinner().setEnabled(false);
                        //ENEMIES IN A ROW
                        launchFrame.getEnemiesInARowSpinner().setValue(5);
                        launchFrame.getEnemiesInARowSpinner().setEnabled(false);
                        //ENEMIES VELOCITY
                        launchFrame.getEnemiesVelocitySlider().setValue(3);
                        launchFrame.getEnemiesVelocitySlider().setEnabled(false);
                        //ENEMIES FALLING SPEED
                        launchFrame.setEnemiesFallingSpeedLabel("Enemies falling speed: (2)");
                        launchFrame.getEnemiesFallingSpeedSlider().setValue(2);
                        launchFrame.getEnemiesFallingSpeedSlider().setEnabled(false);
                        //INVERTED MOVEMENT
                        launchFrame.getInvertedMovementCheckBox().setSelected(false);
                        launchFrame.getInvertedMovementCheckBox().setEnabled(false);
                        break;
                    case HARD:
                        //NUMBER OF ROWS
                        launchFrame.getNumberOfRowsSpinner().setValue(4);
                        launchFrame.getNumberOfRowsSpinner().setEnabled(false);
                        //ENEMIES IN A ROW
                        launchFrame.getEnemiesInARowSpinner().setValue(5);
                        launchFrame.getEnemiesInARowSpinner().setEnabled(false);
                        //ENEMIES VELOCITY
                        launchFrame.getEnemiesVelocitySlider().setValue(4);
                        launchFrame.getEnemiesVelocitySlider().setEnabled(false);
                        //ENEMIES FALLING SPEED
                        launchFrame.setEnemiesFallingSpeedLabel("Enemies falling speed: (4)");
                        launchFrame.getEnemiesFallingSpeedSlider().setValue(3);
                        launchFrame.getEnemiesFallingSpeedSlider().setEnabled(false);
                        //INVERTED MOVEMENT
                        launchFrame.getInvertedMovementCheckBox().setSelected(true);
                        launchFrame.getInvertedMovementCheckBox().setEnabled(false);
                        break;
                    case CUSTOM:
                        launchFrame.getNumberOfRowsSpinner().setEnabled(true);
                        launchFrame.getEnemiesInARowSpinner().setEnabled(true);
                        launchFrame.getEnemiesVelocitySlider().setEnabled(true);
                        launchFrame.getEnemiesFallingSpeedSlider().setEnabled(true);
                        launchFrame.getInvertedMovementCheckBox().setEnabled(true);

                    default:
                        break;
                };
            }
        });

        launchFrame.addEnemiesVelocitySliderChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int value = launchFrame.getEnemiesVelocitySlider().getValue();
                launchFrame.setEnemiesVelocityLabel("Enemies velocity: (" + value + ")");
            }
        });

        launchFrame.addEnemiesFallingSpeedSliderChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int value = launchFrame.getEnemiesFallingSpeedSlider().getValue();
                launchFrame.setEnemiesFallingSpeedLabel("Enemies falling speed: (" + value + ")");
            }
        });
    }

    private String getShipIconFilePathFromLaunchFrame() {
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
