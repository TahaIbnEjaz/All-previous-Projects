import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WelcomePage extends JFrame implements ActionListener {
    JButton proceedBtn;

    public WelcomePage() {
        setTitle("Welcome To DHACSS School");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel for center positioning
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(new Color(0, 102, 102)); // Match background with mainPanel

        // Main panel with vertical layout
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(0, 102, 102));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // ===== LOGO IMAGE ON TOP =====
        try {
            ImageIcon bannerIcon = new ImageIcon(getClass().getResource("logo.png"));
            Image scaledImage = bannerIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            JLabel bannerLabel = new JLabel(new ImageIcon(scaledImage));
            bannerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));
            mainPanel.add(bannerLabel);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Image not found: " + e.getMessage());
        }

        // ===== WELCOME TEXT =====
        JLabel welcomeLabel = new JLabel("Welcome to DHACSS School System");
        welcomeLabel.setFont(new Font("Ubuntu", Font.BOLD, 24));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomeLabel.setForeground(new Color(0xF0F0F0));
        mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        mainPanel.add(welcomeLabel);

        // ===== PROCEED BUTTON =====
        proceedBtn = new JButton("Proceed to Login");
        proceedBtn.setFont(new Font("Arial", Font.BOLD, 18));
        proceedBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        proceedBtn.setMaximumSize(new Dimension(200, 50));
        proceedBtn.setPreferredSize(new Dimension(200, 50));
        proceedBtn.setBackground(new Color(0xDADADA));
        proceedBtn.setForeground(new Color(0x006666));
        proceedBtn.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY,1));
        proceedBtn.addActionListener(this);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        mainPanel.add(proceedBtn);

        // Added main panel to center panel and then to frame
        centerPanel.add(mainPanel);
        add(centerPanel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == proceedBtn) {
            dispose();
            new LoginPage();
        }
    }
}
