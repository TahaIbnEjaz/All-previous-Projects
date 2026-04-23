import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashboard {
    JFrame frame;
    JButton dealTeacher;
    JButton dealStudent;
    JButton teacherInfo;
    JButton studentInfo;

    Students std = new Students();                       //object created of Student class
    Teacher obj = new Teacher ();                      // object created of Teacher class

    public void DashboardPage(){
        ImageIcon icon = new ImageIcon("icon.jpg");
        JFrame frame = new JFrame();
        frame.setTitle("Dashboard");
        frame.setVisible(true);
        frame.setSize(1000,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.LIGHT_GRAY);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        frame.setIconImage(icon.getImage());

        JPanel panel = new JPanel(new GridLayout(2, 2, 20, 20));
        panel.setBorder(BorderFactory.createEmptyBorder(100, 250, 160, 250)); // top, left, bottom, right padding
        panel.setBackground(Color.LIGHT_GRAY);

        //All buttons creation
        JButton dealTeacher = new JButton("Deal With Teacher");
        JButton dealStudent = new JButton("Deal with Student");
        JButton teacherInfo = new JButton("View Teacher Data");
        JButton studentInfo = new JButton("View Student Data");
        JButton back = new JButton("Log Out");

        // frame.add(panel);
        panel.add(dealTeacher);
        panel.add(dealStudent);
        panel.add(teacherInfo);
        panel.add(studentInfo);

        //panel at top fro title
        JPanel TopTitle = new JPanel();
        frame.add(TopTitle, BorderLayout.NORTH);
        TopTitle.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        TopTitle.setBackground(new Color(0x006666));

        ImageIcon img = new ImageIcon("logo.png");
        Image scaledImage = img.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel logoWithText = new JLabel("School Management System", scaledIcon, JLabel.CENTER);
        logoWithText.setHorizontalTextPosition(JLabel.CENTER);
        logoWithText.setVerticalTextPosition(JLabel.BOTTOM);
        logoWithText.setFont(new Font("SansSerif", Font.BOLD, 24));
        logoWithText.setForeground(Color.WHITE);
        TopTitle.add(logoWithText);

        //made a 2nd panel that has a back button in center
        JPanel backPanel = new JPanel();
        backPanel.setBackground(Color.LIGHT_GRAY);
        back.setPreferredSize(new Dimension(150, 30)); // smaller size
        backPanel.add(back);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.LIGHT_GRAY);

        dealTeacher.setBackground(new Color(0, 102, 102));
        dealStudent.setBackground(new Color(0,102,102));
        teacherInfo.setBackground(new Color(0,102,102));
        studentInfo.setBackground(new Color(0,102,102));

        dealTeacher.setForeground(new Color(0xF0F0F0));
        dealStudent.setForeground(new Color(0xF0F0F0));
        teacherInfo.setForeground(new Color(0xF0F0F0));
        studentInfo.setForeground(new Color(0xF0F0F0));

        frame.add(panel, BorderLayout.CENTER);
        frame.add(backPanel, BorderLayout.SOUTH);

        ActionListener listener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == dealTeacher) {
                    frame.dispose();
                    obj.updateIdCounterFromFile();
                    obj.DealingPage();
                } else if (e.getSource() == dealStudent) {
                    frame.dispose();
                    // to trace id saved in file
                    std.updateIdCounterFromFile();
                    std.DealingPage();

                } else if (e.getSource() == studentInfo) {
                    std.viewStudentList();
                } else if (e.getSource() == teacherInfo) {
                    obj.viewTeacherList();

                } else if (e.getSource() == back) {
                    frame.dispose();
                    new LoginPage();
                }
            }
        };

        dealTeacher.addActionListener(listener);
        dealStudent.addActionListener(listener);
        studentInfo.addActionListener(listener);
        teacherInfo.addActionListener(listener);
        back.addActionListener(listener);

    }
}