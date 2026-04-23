import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.IOException;

public class LoginPage extends JFrame implements ActionListener {
    JTextField usernameField;
    JPasswordField passwordField;
    JButton loginBtn, backBtn;
    BufferedImage logoImage;

    public LoginPage() {
        setTitle("Login Page");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Load logo image
        try {
            logoImage = ImageIO.read(getClass().getResource("logo.png"));
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
        }

        // Left panel (logo + title)
        JPanel leftPanel = new JPanel(null);
        leftPanel.setBackground(new Color(0, 102, 102));

        if (logoImage != null) {
            int imgWidth = 230;
            int imgHeight = 230;
            Image scaledImage = logoImage.getScaledInstance(imgWidth, imgHeight, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(scaledImage);

            JLabel imageLabel = new JLabel(icon);
            imageLabel.setBounds((450 - imgWidth) / 2, 120, imgWidth, imgHeight);
            leftPanel.add(imageLabel);

            JLabel title = new JLabel("DHA COLLEGE AND SCHOOL SYSTEM", SwingConstants.CENTER);
            title.setForeground(new Color(0xFFF0F0F0));
            title.setFont(new Font("Verdana", Font.BOLD, 20));
            title.setBounds(0, 360, 450, 40);
            leftPanel.add(title);
        }

        // Right panel
        JPanel rightPanel = new JPanel(null);
        rightPanel.setBackground(Color.lightGray);

        // Login form panel
        JPanel formPanel = new JPanel(null);
        formPanel.setBounds(140, 135, 400, 300);
        formPanel.setOpaque(false);

        JLabel titleLabel = new JLabel("LOGIN");
        titleLabel.setBounds(120, 0, 200, 40);
        titleLabel.setFont(new Font("Helvetica", Font.BOLD, 30));
        titleLabel.setForeground(new Color(0x006666));
        formPanel.add(titleLabel);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(0, 60, 100, 25);
        usernameLabel.setForeground(Color.blue);
        formPanel.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(100, 60, 150, 25);
        usernameField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        formPanel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(0, 110, 100, 25);
        passwordLabel.setForeground(Color.blue);
        formPanel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(100, 110, 150, 25);
        passwordField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        formPanel.add(passwordField);

        loginBtn = new JButton("Login");
        loginBtn.setBounds(0, 180, 100, 35);
        loginBtn.setBackground(new Color(0x006666));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.addActionListener(this);
        formPanel.add(loginBtn);

        backBtn = new JButton("Back");
        backBtn.setBounds(150, 180, 100, 35);
        backBtn.setBackground(new Color(0x006666));
        backBtn.setForeground(Color.WHITE);
        backBtn.addActionListener(this);
        formPanel.add(backBtn);

        rightPanel.add(formPanel);

        // Split Pane
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        splitPane.setResizeWeight(0.45);
        splitPane.setDividerSize(0);
        splitPane.setEnabled(false);

        add(splitPane);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == loginBtn) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (username.equals("admin") && password.equals("admin")) {
                JOptionPane.showMessageDialog(this, "Login Successful!");
                dispose();
                Dashboard thirdPage = new Dashboard();
                thirdPage.DashboardPage();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password!");
            }
        } else if (ae.getSource() == backBtn) {
            dispose();
            new WelcomePage();
        }
    }
}
