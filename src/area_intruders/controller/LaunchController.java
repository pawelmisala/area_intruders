package area_intruders.controller;

import area_intruders.model.LaunchModel;
import area_intruders.view.LaunchFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LaunchController {
    private LaunchFrame launchFrame;

    public LaunchController(LaunchFrame launchFrame) {
        this.launchFrame = launchFrame;

        launchFrame.addButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (launchFrame.getNickname().isEmpty()){
                    JOptionPane.showMessageDialog(launchFrame, "Please, enter your nickname!");
                }
                else {
                    String nickname = launchFrame.getNickname(); //POBIERAMY WARTOSCI Z VIEW PO CZYM PRZYPISUJEMY JE ORAZ
                    LaunchModel model = new LaunchModel(nickname); //TWORZYMY OBIEKT MODEL KTORY PRZECHOWUJE DANE NIE MAJAC POLACZENIA Z GUI
                    launchFrame.close();
                }
            }
        });
    }

}
