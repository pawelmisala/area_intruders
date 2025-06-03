package area_intruders.view;

import area_intruders.controller.GameController;
import area_intruders.model.UserSettings;

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
    private MainPanel mainPanel;
    private GameplayPanel gameplayPanel;
    private CardLayout cardLayout;
    private JButton startButton;
    private Image frameIcon;
    private GameController gameController;

    {
        try {
            frameIcon = ImageIO.read(new File("resources/icon.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public GameFrame() {
        super("AREA INTRUDERS - " + UserSettings.getNickname());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setIconImage(frameIcon);
        this.add(this.mainPanel = new MainPanel());
        this.setVisible(true);
        this.gameController = new GameController(this, this.gameplayPanel);

        //MENUBAR
            this.menuBar = new JMenuBar();
            this.helpMenu = new JMenu("Help");
            this.scoreboardMenu = new JMenu("Scoreboard");
            menuBar.add(helpMenu);
            menuBar.add(scoreboardMenu);
            this.setJMenuBar(menuBar);
    }

    private class MainPanel extends JPanel {

        public MainPanel() {
            cardLayout = new CardLayout();
            this.setLayout(cardLayout);
            this.add(new StartingScreenPanel(), "STARTING_SCREEN");
            this.add(new GamePanel(), "GAME_PANEL");
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

    private class GamePanel extends JPanel {
        public GamePanel() {
            this.setLayout(new BorderLayout());
            this.setPreferredSize(new Dimension(getWIDTH(), getHEIGHT()));
            this.setBackground(Color.RED);
            this.add(gameplayPanel = new GameplayPanel(), BorderLayout.CENTER);
        }
    }

    public int getWIDTH(){
        return WIDTH;
    }
    public int getHEIGHT(){
        return HEIGHT;
    }
    public MainPanel getMainPanel() {
        return mainPanel;
    }
    public CardLayout getCardLayout() {
        return cardLayout;
    }
    public GameplayPanel getGameplayPanel() {
        return gameplayPanel;
    }

    public void addStartButtonListener(ActionListener actionListener) {
        startButton.addActionListener(actionListener);
    }

}
