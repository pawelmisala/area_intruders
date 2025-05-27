package area_intruders.view;

import area_intruders.controller.LaunchController;
import area_intruders.model.Difficulty;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class LaunchFrame extends JFrame {
    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private final JPanel launchPanel = new LaunchPanel();
    private JLabel nicknameLabel;
    private JTextField nicknameField;
    private JButton submitButton;
    private Image frameIcon;

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
        this.add(launchPanel);
        this.setIconImage(frameIcon);
        this.setVisible(true);
        LaunchController controller = new LaunchController(this);
    }

    private class LaunchPanel extends JPanel {
        public LaunchPanel() {
            setLayout(new BorderLayout());
            this.add(new NorthPanel(), BorderLayout.NORTH);
            this.add(new CenterPanel(), BorderLayout.CENTER);
            this.add(new SouthPanel(), BorderLayout.SOUTH);
        }

        private class NorthPanel extends JPanel {
            NorthPanel() {
                this.setPreferredSize(new Dimension(WIDTH, 150));
                this.setBackground(Color.BLACK);
            }
        }
        private class CenterPanel extends JPanel {
            CenterPanel() {
                this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
                this.setPreferredSize(new Dimension(WIDTH, 450));
                JPanel nicknamePanel = new JPanel();
                    nicknameLabel = new JLabel("Nickname:");
                    nicknameField = new JTextField(15);
                    nicknamePanel.add(nicknameLabel);
                    nicknamePanel.add(nicknameField);
                    this.add(nicknamePanel);
            }
        }
        private class SouthPanel extends JPanel {
            SouthPanel() {
                this.setLayout(new FlowLayout(FlowLayout.CENTER));
                this.setPreferredSize(new Dimension(WIDTH, 50));
                this.setBackground(Color.BLACK);

                submitButton = new JButton("START");
                submitButton.setPreferredSize(new Dimension(140, 40));
                this.add(submitButton);
            }
        }
    }

    public String getNickname() {
        return nicknameField.getText();
    }

    public void addButtonListener(ActionListener listener) {
        submitButton.addActionListener(listener);
    }

    public void close(){
        this.dispose();
    }
}
