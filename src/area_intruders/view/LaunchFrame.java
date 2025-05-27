package area_intruders.view;

import javax.swing.*;
import java.awt.*;

public class LaunchFrame extends JFrame {
    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private final JPanel launchPanel = new LaunchPanel();

    public LaunchFrame() {
        super("AREA INTRUDERS");
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.add(launchPanel);
        this.setVisible(true);
    }

    private class LaunchPanel extends JPanel {
        public LaunchPanel() {
            setLayout(new BorderLayout());
            this.add(new NorthPanel(), BorderLayout.NORTH);
            this.add(new CenterPanel(), BorderLayout.CENTER);
        }

        private class NorthPanel extends JPanel {
            NorthPanel() {
                this.setPreferredSize(new Dimension(super.getWidth(), 150));
                this.setBackground(Color.BLACK);
            }
        }
        private class CenterPanel extends JPanel {
            JTextField nicknameField = new JTextField(20);

            CenterPanel() {
                this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
                this.setPreferredSize(new Dimension(super.getWidth(), 450));
            }
        }
    }
}
