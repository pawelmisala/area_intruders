package area_intruders.view;

import area_intruders.controller.GameController;
import area_intruders.model.LaunchModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class GameFrame extends JFrame {
    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private final JMenuBar menuBar;
    private final JMenu helpMenu;
    private final JMenu scoreboardMenu;
    private JButton startButton;
    private Image frameIcon;

    {
        try {
            frameIcon = ImageIO.read(new File("resources/icon.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public GameFrame(LaunchModel launchModel) {
        super("AREA INTRUDERS - " + launchModel.getNickname());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);
        this.add(new MainPanel());
        this.setResizable(false);
        this.setIconImage(frameIcon);
        this.setVisible(true);
        //MENUBAR
            this.menuBar = new JMenuBar();
            this.helpMenu = new JMenu("Help");
            this.scoreboardMenu = new JMenu("Scoreboard");
            menuBar.add(helpMenu);
            menuBar.add(scoreboardMenu);
            this.setJMenuBar(menuBar);

        GameController gameController = new GameController(this);
    }

    private class MainPanel extends JPanel {
        CardLayout cardLayout = new CardLayout();

        public MainPanel() {
            this.setLayout(cardLayout);
            this.add(new StartingScreenPanel());
            cardLayout.show(this, "STARTING_SCREEN");
        }
    }

    private class StartingScreenPanel extends JPanel {
        public StartingScreenPanel() {
            this.setLayout(new BorderLayout());
            this.setPreferredSize(new Dimension(getWIDTH(), getHEIGHT()));

            JPanel bannerPanel = new JPanel();
                ImageIcon banner = new ImageIcon("resources/banner.png");
                JLabel bannerLabel = new JLabel(banner);
                bannerPanel.setBackground(Color.BLACK);
                bannerPanel.add(bannerLabel);
                this.add(bannerPanel, BorderLayout.NORTH);

            JPanel buttonPanel = new JPanel();
                buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
                buttonPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
                startButton = new JButton("Start");
                startButton.setPreferredSize(new Dimension(200, 50));
                buttonPanel.add(startButton);
                this.add(buttonPanel, BorderLayout.CENTER);

        }


    }

    public int getWIDTH(){
        return WIDTH;
    }
    public int getHEIGHT(){
        return HEIGHT;
    }

    public void addStartButtonListener(ActionListener actionListener) {
        startButton.addActionListener(actionListener);
    }

}
