package area_intruders.view;

import area_intruders.controller.LaunchController;
import area_intruders.model.Difficulty;
import area_intruders.view.customComponents.CustomRadioButton;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class LaunchFrame extends JFrame {
    private final ImageIcon banner = new ImageIcon("resources/banner.png");
    private final int WIDTH = 600;
    private final int HEIGHT = 600;
    private JTextField nicknameField;
    private JComboBox<Difficulty> difficultyComboBox;
    private static ArrayList<CustomRadioButton> shipRadioButtons = new ArrayList<>();
    private JSpinner numberOfRowsSpinner;
    private JSpinner enemiesInARowSpinner;
    private JLabel enemiesVelocityLabel;
    private JSlider enemiesVelocitySlider;
    private JLabel enemiesFallingSpeedLabel;
    private JSlider enemiesFallingSpeedSlider;
    private JCheckBox invertedMovementCheckBox;
    private JButton submitButton;
    private Image frameIcon;
    private LaunchController launchController;

    {
        try {
            frameIcon = ImageIO.read(new File("resources/icon.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public LaunchFrame() {
        super("AREA INTRUDERS");
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.add(new MainPanel());
        this.setIconImage(frameIcon);
        this.setVisible(true);
        this.launchController = new LaunchController(this);
    }

    private class MainPanel extends JPanel {
        public MainPanel() {
            setLayout(new BorderLayout());
            this.add(new NorthPanel(), BorderLayout.NORTH);
            this.add(new CenterPanel(), BorderLayout.CENTER);
            this.add(new SouthPanel(), BorderLayout.SOUTH);
        }

        private class NorthPanel extends JPanel {
            NorthPanel() {
                this.setPreferredSize(new Dimension(getWIDTH(), 150));
                this.setBackground(Color.BLACK);
                JLabel backgroundLabel = new JLabel(banner);
                this.add(backgroundLabel);
            }
        }
        private class CenterPanel extends JPanel {
            CenterPanel() {
                this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
                this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                this.setPreferredSize(new Dimension(getWIDTH(), 300));
                this.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0));
                SpinnerModel numberOfRowsSpinnerModel = new SpinnerNumberModel(1, 1, 4, 1);
                SpinnerModel enemiesInARowSpinnerModel = new SpinnerNumberModel(3, 3, 7, 1);

                SettingsPanel nicknamePanel = new SettingsPanel();
                    nicknamePanel.add(new JLabel("Nickname:"));
                    nicknameField = new JTextField(15);
                    nicknamePanel.add(nicknameField);
                    this.add(nicknamePanel);

                SettingsPanel difficultyPanel = new SettingsPanel();
                    difficultyPanel.add(new JLabel("Difficulty:"));
                    difficultyComboBox = new JComboBox<>(Difficulty.values());
                    difficultyComboBox.setPreferredSize(new Dimension(120, 20));
                    difficultyComboBox.setSelectedItem(Difficulty.CUSTOM);
                    difficultyPanel.add(difficultyComboBox);
                    this.add(difficultyPanel);

                SettingsPanel RowsSettingsPanel = new SettingsPanel();
                //NUMBER OF ROWS
                    RowsSettingsPanel.add(new JLabel("Number of Rows:"));
                    numberOfRowsSpinner = new JSpinner(numberOfRowsSpinnerModel);
                    numberOfRowsSpinner.setPreferredSize(new Dimension(40, 20));
                    RowsSettingsPanel.add(numberOfRowsSpinner);
                //NUMBER OF ENEMIES IN A ROW
                    RowsSettingsPanel.add(new JLabel("Enemies in a row:"));
                    enemiesInARowSpinner = new JSpinner(enemiesInARowSpinnerModel);
                    enemiesInARowSpinner.setPreferredSize(new Dimension(40, 20));
                    RowsSettingsPanel.add(enemiesInARowSpinner);
                    this.add(RowsSettingsPanel);

                SettingsPanel enemiesSettingPanel = new SettingsPanel();
                //ENEMIES VELOCITY
                    enemiesVelocityLabel = new JLabel("Enemies velocity: (1)");
                    enemiesSettingPanel.add(enemiesVelocityLabel);
                    enemiesVelocitySlider = new JSlider(JSlider.HORIZONTAL, 1, 5, 1);
                        enemiesVelocitySlider.setPreferredSize(new Dimension(100, 40));
                        enemiesVelocitySlider.setMajorTickSpacing(1);
                        enemiesVelocitySlider.setMinorTickSpacing(1);
                        enemiesVelocitySlider.setPaintTicks(true);
                    enemiesSettingPanel.add(enemiesVelocitySlider);
                //ENEMIES FALLING SPEED
                    enemiesFallingSpeedLabel = new JLabel("Enemies falling speed: (1)");
                    enemiesSettingPanel.add(enemiesFallingSpeedLabel);
                    enemiesFallingSpeedSlider = new JSlider(SwingConstants.HORIZONTAL, 1 , 5 , 1);
                        enemiesFallingSpeedSlider.setPreferredSize(new Dimension(100, 40));
                        enemiesFallingSpeedSlider.setMajorTickSpacing(1);
                        enemiesFallingSpeedSlider.setMinorTickSpacing(1);
                        enemiesFallingSpeedSlider.setPaintTicks(true);
                    enemiesSettingPanel.add(enemiesFallingSpeedSlider);
                    this.add(enemiesSettingPanel);

                SettingsPanel invertedMovementSettingPanel = new SettingsPanel();
                    invertedMovementSettingPanel.add(new JLabel("Inverted Movement:"));
                    invertedMovementCheckBox = new JCheckBox();
                    invertedMovementSettingPanel.add(invertedMovementCheckBox);
                    this.add(invertedMovementSettingPanel);

                SettingsPanel shipPanel = new SettingsPanel();
                shipPanel.add(new JLabel("Ship:"));
                ShipOptionPanel redShipOptionPanel = new ShipOptionPanel("resources/ship1.png");
                redShipOptionPanel.getRadioButton().setSelected(true);
                ShipOptionPanel greenShipOptionPanel = new ShipOptionPanel("resources/ship2.png");
                ShipOptionPanel blueShipOptionPanel = new ShipOptionPanel("resources/ship3.png");
                shipPanel.add(redShipOptionPanel);
                shipPanel.add(greenShipOptionPanel);
                shipPanel.add(blueShipOptionPanel);
                shipRadioButtons.add(redShipOptionPanel.getRadioButton());
                shipRadioButtons.add(greenShipOptionPanel.getRadioButton());
                shipRadioButtons.add(blueShipOptionPanel.getRadioButton());

                ButtonGroup shipButtonGroup = new ButtonGroup(); //BUTTON GROUP PREVENTS FROM CHOOSING MULTIPLE SHIP ICONS
                shipButtonGroup.add(redShipOptionPanel.getRadioButton());
                shipButtonGroup.add(greenShipOptionPanel.getRadioButton());
                shipButtonGroup.add(blueShipOptionPanel.getRadioButton());

                this.add(shipPanel);
            }
        }
        private class SouthPanel extends JPanel {
            SouthPanel() {
                this.setLayout(new FlowLayout(FlowLayout.CENTER));
                this.setPreferredSize(new Dimension(getWIDTH(), 50));
                this.setBackground(Color.BLACK);

                submitButton = new JButton("START");
                submitButton.setPreferredSize(new Dimension(140, 40));
                this.add(submitButton);
            }
        }
    }

    //GETTERS
    public int getWIDTH(){
        return WIDTH;
    }
    public int getHEIGHT(){
        return HEIGHT;
    }
    public String getNickname() {
        return nicknameField.getText();
    }
    public Difficulty getDifficulty() {
        return (Difficulty) difficultyComboBox.getSelectedItem();
    }
    public ArrayList<CustomRadioButton> getShipRadioButtons() {
        return shipRadioButtons;
    }
    public JSpinner getNumberOfRowsSpinner() {
        return numberOfRowsSpinner;
    }
    public JSpinner getEnemiesInARowSpinner(){
        return enemiesInARowSpinner;
    }
    public JSlider getEnemiesVelocitySlider(){
        return enemiesVelocitySlider;
    }
    public JSlider getEnemiesFallingSpeedSlider() {
        return enemiesFallingSpeedSlider;
    }
    public JCheckBox getInvertedMovementCheckBox() {
        return invertedMovementCheckBox;
    }

    //SETTERS
    public void setEnemiesVelocityLabel(String string){
        enemiesVelocityLabel.setText(string);
    }
    public void setEnemiesFallingSpeedLabel(String string) {
        enemiesFallingSpeedLabel.setText(string);
    }

    //LISTENER ADDERS
    public void addDifficultyComboBoxListener(ActionListener listener) {
        difficultyComboBox.addActionListener(listener);
    }
    public void addEnemiesVelocitySliderChangeListener(ChangeListener Listener) {
        enemiesVelocitySlider.addChangeListener(Listener);
    }
    public void addEnemiesFallingSpeedSliderChangeListener(ChangeListener listener){
        enemiesFallingSpeedSlider.addChangeListener(listener);
    }
    public void addSubmitButtonListener(ActionListener listener) {
        submitButton.addActionListener(listener);
    }

    public void close(){
        this.dispose();
    }
}
