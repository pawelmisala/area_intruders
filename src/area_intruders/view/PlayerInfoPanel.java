package area_intruders.view;

import area_intruders.controller.GameController;

import javax.swing.*;
import java.awt.*;

public class PlayerInfoPanel extends JPanel {
    private GameController gameController;
    private JLabel nicknameLabel;
    private JLabel scoreLabel;
    private JPanel congratulationsPanel;
    private JLabel congratulationsLabel;

    public PlayerInfoPanel() {
        this.setBackground(Color.black);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel nicknamePanel = new JPanel();
        nicknamePanel.setBackground(Color.black);
        nicknameLabel = new JLabel("NICKNAME");
        nicknameLabel.setForeground(Color.WHITE);
        nicknameLabel.setFont(new Font("LABEL", Font.BOLD, 80));
        nicknamePanel.add(nicknameLabel);
        this.add(nicknamePanel);

        JPanel scorePanel = new JPanel();
        scorePanel.setBackground(Color.black);
        scoreLabel = new JLabel("SCORE");
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setFont(new Font("LABEL", Font.BOLD, 30));
        scorePanel.add(scoreLabel);
        this.add(scorePanel);

        congratulationsPanel = new JPanel();
        congratulationsPanel.setBackground(Color.black);
        congratulationsLabel = new JLabel("CONGRATULATIONS");
        congratulationsLabel.setForeground(Color.WHITE);
        congratulationsLabel.setFont(new Font("LABEL", Font.BOLD, 15));
        congratulationsPanel.add(congratulationsLabel);
        congratulationsPanel.setVisible(false);
        this.add(congratulationsPanel);

    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public JLabel getNicknameLabel() {
        return nicknameLabel;
    }
    public JLabel getScoreLabel() {
        return scoreLabel;
    }
    public JPanel getCongratulationsPanel() {
        return congratulationsPanel;
    }
    public JLabel getCongratulationsLabel() {
        return congratulationsLabel;
    }
}
