package area_intruders.view;

import area_intruders.controller.GameController;
import area_intruders.model.GameBoardValues;
import area_intruders.model.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class GameFrame extends JFrame {
    private final int WIDTH = GameBoardValues.getWidth();
    private final int HEIGHT = (GameBoardValues.getHeight() + 180);
    private final JMenuBar menuBar;
    private JMenuItem top10scoresMenuItem;
    private JMenuItem howToPlayMenuItem;
    private MainPanel mainPanel;
    private GameplayPanel gameplayPanel;
    private ControlsPanel controlsPanel;
    private PlayerInfoPanel playerInfoPanel;
    private CardLayout cardLayout;
    private JButton startButton;
    private JButton top10scoresButton;
    private JButton howToPlayButton;
    private JButton exitButton;
    private JButton restartButton;
    private JButton mainMenuButton;
    private Image frameIcon;
    private GameController gameController;

    {
        try {
            frameIcon = ImageIO.read(new File("resources/icon.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public GameFrame(Player player) {
        super("AREA INTRUDERS | " + player.getNickname().toUpperCase());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setIconImage(frameIcon);
        this.add(this.mainPanel = new MainPanel());
        this.setVisible(true);
        //MENUBAR
        JMenu gameMenu = new JMenu("Game");
        this.menuBar = new JMenuBar();
        this.menuBar.add(gameMenu);
        this.top10scoresMenuItem = new JMenuItem("TOP 10 scores");;
        this.howToPlayMenuItem = new JMenuItem("How to play");
            gameMenu.add(top10scoresMenuItem);
            gameMenu.add(howToPlayMenuItem);
        this.setJMenuBar(menuBar);

        //GAME CONTROLLER
        this.gameController = new GameController(this, player);
    }

    private class MainPanel extends JPanel {

        public MainPanel() {
            cardLayout = new CardLayout();
            this.setLayout(cardLayout);
            this.add(new StartingScreenPanel(), "STARTING_SCREEN");
            this.add(new GamePanel(), "GAME_PANEL");
            this.add(new GameOverPanel(), "GAME_OVER_PANEL");
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

            StartingScreenButtonsPanel buttonPanel = new StartingScreenButtonsPanel();
                JPanel startButtonPanel = new JPanel();
                    startButtonPanel.setBackground(Color.BLACK);
                    startButton = new JButton("START");
                    startButton.setPreferredSize(new Dimension(200, 50));
                    startButtonPanel.add(startButton);

                JPanel top10scoresPanel = new JPanel();
                    top10scoresPanel.setBackground(Color.BLACK);
                    top10scoresButton = new JButton("TOP 10 SCORES");
                    top10scoresButton.setPreferredSize(new Dimension(200, 50));
                    top10scoresPanel.add(top10scoresButton);

                JPanel howToPlayPanel = new JPanel();
                    howToPlayPanel.setBackground(Color.BLACK);
                    howToPlayButton = new JButton("HOW TO PLAY");
                    howToPlayButton.setPreferredSize(new Dimension(200, 50));
                    howToPlayPanel.add(howToPlayButton);

                JPanel exitButtonPanel = new JPanel();
                    exitButtonPanel.setBackground(Color.BLACK);
                    exitButton = new JButton("EXIT");
                    exitButton.setPreferredSize(new Dimension(200, 50));
                    exitButtonPanel.add(exitButton);

                buttonPanel.add(startButtonPanel);
                buttonPanel.add(top10scoresPanel);
                buttonPanel.add(howToPlayPanel);
                buttonPanel.add(exitButtonPanel);
                this.add(buttonPanel, BorderLayout.CENTER);
        }
    }

    private class GamePanel extends JPanel {
        public GamePanel() {
            this.setPreferredSize(new Dimension(getWIDTH(), getHEIGHT()));
            this.setBackground(Color.BLACK);
            this.add(gameplayPanel = new GameplayPanel(), BorderLayout.CENTER);
            this.add(controlsPanel = new ControlsPanel(), BorderLayout.SOUTH);
        }
    }

    private class GameOverPanel extends JPanel {
        private GameController gameController;

        public GameOverPanel() {
            this.setLayout(new BorderLayout());
            this.setBackground(Color.BLACK);

            //North
            JLabel gameOverLabel = new JLabel(new ImageIcon("resources/game_over.png"));
            this.add(gameOverLabel, BorderLayout.NORTH);

            //Center
            this.add(playerInfoPanel = new PlayerInfoPanel(), BorderLayout.CENTER);

             //South
            JPanel buttonPanel = new JPanel();
                buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
                buttonPanel.setBackground(Color.BLACK);
                    restartButton = new JButton("Restart");
                    mainMenuButton = new JButton("Main Menu");
                    restartButton.setPreferredSize(new Dimension(200, 50));
                    mainMenuButton.setPreferredSize(new Dimension(200, 50));
                buttonPanel.add(restartButton);
                buttonPanel.add(mainMenuButton);
                this.add(buttonPanel, BorderLayout.SOUTH);
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
    public ControlsPanel getControllsPanel() {
        return controlsPanel;
    }
    public PlayerInfoPanel getPlayerInfoPanel() {
        return playerInfoPanel;
    }

    public void addTop10ScoresMenuItemListener(ActionListener actionListener) {
        top10scoresMenuItem.addActionListener(actionListener);
    }
    public void addHowToPlayMenuItemListener(ActionListener actionListener) {
        howToPlayMenuItem.addActionListener(actionListener);
    }

    public void addStartButtonListener(ActionListener actionListener) {
        startButton.addActionListener(actionListener);
    }
    public void addTop10ScoresButtonListener(ActionListener actionListener) {
        top10scoresButton.addActionListener(actionListener);
    }
    public void addHowToPlayButtonListener(ActionListener actionListener) {
        howToPlayMenuItem.addActionListener(actionListener);
    }
    public void addExitButtonListener(ActionListener actionListener) {
        exitButton.addActionListener(actionListener);
    }
    public void addRestartButtonListener(ActionListener actionListener) {
        restartButton.addActionListener(actionListener);
    }
    public void addMainMenuButtonListener(ActionListener actionListener) {
        mainMenuButton.addActionListener(actionListener);
    }

}
