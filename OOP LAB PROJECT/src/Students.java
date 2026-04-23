import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class Students extends Manager {

    private String name;
    private int myId;
    private static int idCounter = 1000;
    private double fee;
    private String feeStatus;
    String regFeeStatus;
    String EducationalState;

    ArrayList<Students> students = new ArrayList<>();

    public Students() {
        this.name = "";
        this.myId = ++idCounter;
        this.fee = 15000;
        this.regFeeStatus = "Paid";
        this.feeStatus = "Unpaid";
    }

    public Students(String name, String EducationalState) {
        this.name = name;
        this.myId = ++idCounter;
        this.EducationalState = EducationalState;
        this.fee = EducationalState.equals("Primary") ? 10000 : 15000;
        this.regFeeStatus = "Paid";
        this.feeStatus = "Unpaid";
    }

    public Students(int id, String name, String EducationalState, String regFeeStatus, String feeStatus) {
        this.myId = id;
        this.name = name;
        this.EducationalState = EducationalState;
        this.regFeeStatus = regFeeStatus;
        this.feeStatus = feeStatus;
        this.fee = EducationalState.equals("Primary") ? 10000 : 15000;
    }

    public void DealingPage() {
        JFrame frame = new JFrame("Student");
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton add = new JButton("Add Student");
        JButton remove = new JButton("Remove Student");
        JButton feeStatus = new JButton("Update FeeStatus");
        JButton academicCalender = new JButton("Show Academic Calender");
        JButton editInfo = new JButton("Edit Info");
        JButton saveData = new JButton("Save to File");

        //A panel in center that contain all buttons
        JPanel panel = new JPanel(new GridLayout(2, 2, 20, 20));
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.CENTER);
        panel.setBorder(BorderFactory.createEmptyBorder(120, 200, 140, 200));
        panel.setBackground(Color.LIGHT_GRAY);

        //back to dashboard button setup
        JPanel backPanel = new JPanel();
        JButton back = new JButton("Back to Dashboard");
        frame.add(backPanel, BorderLayout.SOUTH);
        backPanel.setBackground(Color.LIGHT_GRAY);
        back.setPreferredSize(new Dimension(150, 30));
        backPanel.add(back);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.LIGHT_GRAY);

        // panel at top for title
        JPanel TopTitle = new JPanel();
        frame.add(TopTitle, BorderLayout.NORTH);
        TopTitle.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        TopTitle.setBackground(new Color(0x006666));

        ImageIcon img = new ImageIcon("logo.png");
        Image scaledImage = img.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel logoWithText = new JLabel("Student Manager", scaledIcon, JLabel.CENTER);
        logoWithText.setHorizontalTextPosition(JLabel.CENTER);
        logoWithText.setVerticalTextPosition(JLabel.BOTTOM);
        logoWithText.setFont(new Font("SansSerif", Font.BOLD, 24));
        logoWithText.setForeground(Color.WHITE);
        TopTitle.add(logoWithText);

        panel.add(add);
        panel.add(remove);
        panel.add(feeStatus);
        panel.add(academicCalender);
        panel.add(editInfo);
        panel.add(saveData);

        //Buttons Design
        add.setBackground(new Color(0,102,102));
        remove.setBackground(new Color(0,102,102));
        feeStatus.setBackground(new Color(0,102,102));
        academicCalender.setBackground(new Color(0,102,102));
        editInfo.setBackground(new Color(0,102,102));
        saveData.setBackground(new Color(0,102,102));
        add.setForeground(new Color(0xF0F0F0));
        remove.setForeground(new Color(0xF0F0F0));
        feeStatus.setForeground(new Color(0xF0F0F0));
        academicCalender.setForeground(new Color(0xF0F0F0));
        editInfo.setForeground(new Color(0xF0F0F0));
        saveData.setForeground(new Color(0xF0F0F0));

        ActionListener listener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == add) {
                    add();
                } else if (e.getSource() == remove) {
                    removeStudent();
                } else if (e.getSource() == editInfo) {
                    editInfo();
                } else if (e.getSource() == academicCalender) {
                    academicCalendar();
                } else if (e.getSource() == saveData) {
                    saveToFile();
                } else if (e.getSource() == feeStatus) {
                    updatefeestatus();
                } else if (e.getSource() == back) {
                    frame.dispose();
                    Dashboard thirdPage = new Dashboard();
                    thirdPage.DashboardPage();
                }
            }
        };

        add.addActionListener(listener);
        remove.addActionListener(listener);
        feeStatus.addActionListener(listener);
        academicCalender.addActionListener(listener);
        editInfo.addActionListener(listener);
        back.addActionListener(listener);
        saveData.addActionListener(listener);

        frame.setVisible(true);
    }

    // Add multiple students in array list
    public void add() {
        JFrame addFrame = new JFrame("Add New Student");
        addFrame.setSize(500, 300);
        addFrame.setLayout(new GridLayout(4, 2, 10, 10));
        addFrame.setLocationRelativeTo(null);

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();

        JLabel eduLabel = new JLabel("Educational State:");
        JComboBox<String> eduBox = new JComboBox<>(new String[]{"Primary", "Secondary"});

        JLabel feeLabel = new JLabel("Fees:");
        JTextField feeField = new JTextField("10000");
        feeField.setEditable(false);

        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");

        eduBox.addActionListener(e -> {
            String selected = eduBox.getSelectedItem().toString();
            feeField.setText(selected.equals("Primary") ? "10000" : "15000");
        });

        addFrame.add(nameLabel);    addFrame.add(nameField);
        addFrame.add(eduLabel);     addFrame.add(eduBox);
        addFrame.add(feeLabel);     addFrame.add(feeField);
        addFrame.add(saveButton);   addFrame.add(cancelButton);

        saveButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String state = eduBox.getSelectedItem().toString().trim();
            if (!name.isEmpty()) {
                students.add(new Students(name, state));
                JOptionPane.showMessageDialog(addFrame, "Student added!");
                addFrame.dispose();
            } else {
                JOptionPane.showMessageDialog(addFrame, "Please enter all fields.");
            }
        });

        cancelButton.addActionListener(e -> addFrame.dispose());

        addFrame.setVisible(true);
    }


// this saves data in file
    int lastSavedIndex = 0;
public void saveToFile() {
    if (students.isEmpty() || lastSavedIndex >= students.size()) {
        JOptionPane.showMessageDialog(null, "No new student data to save.");
        return;
    }
    try (FileWriter writer = new FileWriter("students.txt", true)) {
        for (int i = lastSavedIndex; i < students.size(); i++) {
            Students s = students.get(i);
            writer.write(s.myId + "," + s.name + "," + s.EducationalState + "," + s.regFeeStatus + "," + s.feeStatus + "\n");
        }
        lastSavedIndex = students.size();
        JOptionPane.showMessageDialog(null, "Data saved to file.");
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    @Override
    public void editInfo() {
        int id = promptId();
        Students student = findStudent(id);

        if (student == null) {
            JOptionPane.showMessageDialog(null, "Student not found.");
            return;
        }

        JTextField nameField = new JTextField(student.name);
        JComboBox<String> eduBox = new JComboBox<>(new String[]{"Primary", "Secondary"});
        eduBox.setSelectedItem(student.EducationalState);
        JCheckBox regFeeCheck = new JCheckBox("Registration Fee Paid", student.regFeeStatus.equals("Paid"));
        JComboBox<String> feeStatusBox = new JComboBox<>(new String[]{"Paid", "Unpaid"});
        feeStatusBox.setSelectedItem(student.feeStatus);

        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(new JLabel("Name:")); panel.add(nameField);
        panel.add(new JLabel("Education Level:")); panel.add(eduBox);
        panel.add(new JLabel("Reg Fee Status:")); panel.add(regFeeCheck);
        panel.add(new JLabel("Fee Status:")); panel.add(feeStatusBox);

        int result = JOptionPane.showConfirmDialog(null, panel, "Edit Student", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            student.name = nameField.getText();
            student.EducationalState = (String) eduBox.getSelectedItem();
            student.regFeeStatus = regFeeCheck.isSelected() ? "Paid" : "Unpaid";
            student.feeStatus = (String) feeStatusBox.getSelectedItem();

            updateStudentInFile(student);
            JOptionPane.showMessageDialog(null, "Student info updated successfully.");
        }
    }

    void updatefeestatus(){
        int id = promptId();
        Students student = findStudent(id);

        if (student == null) {
            JOptionPane.showMessageDialog(null, "Student not found.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(null, "Mark fee as paid?", "Update Fee Status", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            student.feeStatus = "Paid";
            updateStudentInFile(student);
            JOptionPane.showMessageDialog(null, "Fee status updated successfully.");
        }
    }

    void removeStudent(){
        int idToRemove = promptId();
        if (idToRemove == -1) {
            JOptionPane.showMessageDialog(null, "Invalid ID entered.");
            return;
        }

        File inputFile = new File("students.txt");
        File tempFile = new File("temp_students.txt");

        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                String[] parts = currentLine.split(",");
                int currentId = Integer.parseInt(parts[0]);

                if (currentId == idToRemove) {
                    found = true; // Skip this student (i.e., remove)
                    continue;
                }
                writer.write(currentLine + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error reading file.");
            return;
        }

        if (found) {
            // Replace old file with new file
            if (inputFile.delete() && tempFile.renameTo(inputFile)) {
                JOptionPane.showMessageDialog(null, "Student removed successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "Error updating the file.");
            }
        } else {
            tempFile.delete(); // deletes the extra temp file
            JOptionPane.showMessageDialog(null, "Student ID not found.");
        }
    }

    @Override
    void academicCalendar() {
        String[][] data ={
                {"Start of academic session","15th May"},
                {"Youm e Takbeer","28th July"},
                {"Summer break","1st June - 31st July"},
                {"Independence Day","14th August"},
                {"First","16th August - 1st September"},
                {"Second assessment","15th September - 1st October"},
                {"Iqbal Day","9th November"},
                {"Mid term examination","20th October - 10th November"},
                {"PTM","15th November"},
                {"Third Assessment","1st Dec"},
                {"Winter break","21-31dec"},
                {"Fourth assessment","1st Feb"},
                {"Kashmir Day","5th February"},
                {"End of Session","20th March"},
                {"Final exam","27th march - 15th April"},
                {"Annual result declaration","25th April"},
        };
        String[] heading = {"Events" , "Dates"};

        JTable calendar = new JTable(data,heading);
        calendar.setFont(new Font("SansSerif", Font.BOLD, 14));
        calendar.setRowHeight(40);
        calendar.setBackground(new Color(0xDADADA));

        JTableHeader header = calendar.getTableHeader();
        header.setFont(new Font("SansSerif", Font.BOLD, 16));
        header.setBackground(new Color(0x006666));
        header.setForeground(Color.WHITE);

        JFrame frame = new JFrame("Academic Calendar");
        frame.setSize(800,700);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.add(new JScrollPane(calendar));
    }

    //this method is used to update data in file, shared by 2 other methods
    void updateStudentInFile(Students updatedStudent) {
        File inputFile = new File("students.txt");
        File tempFile = new File("students_temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             PrintWriter writer = new PrintWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int currentId = Integer.parseInt(parts[0]);

                if (currentId == updatedStudent.myId) {
                    writer.println(updatedStudent.myId + "," + updatedStudent.name + "," +
                            updatedStudent.EducationalState + "," + updatedStudent.regFeeStatus + "," +
                            updatedStudent.feeStatus);
                } else {
                    writer.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (inputFile.delete()) {
            tempFile.renameTo(inputFile);
        }
    }

    //this method is reading the highest ID from Student.txt file so it can append when program restarts.  Got it?

    public void updateIdCounterFromFile(){
        File file = new File("students.txt");
        if (!file.exists()) return; //if file does not exist

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int maxId = 1000;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 0) {
                    int id = Integer.parseInt(parts[0]);
                    if (id > maxId) maxId = id;
                }
            }
            idCounter = maxId;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //this finds id from the file, split into string type array then convert id's string into interger
    Students findStudent(int id) {
        try (BufferedReader reader = new BufferedReader(new FileReader("students.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (Integer.parseInt(parts[0]) == id) {
                    return new Students(id, parts[1], parts[2], parts[3].trim(), parts[4].trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //it takes id as input
    public int promptId() {
        String input = JOptionPane.showInputDialog("Enter Student ID:");
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    //method to view student list
    void viewStudentList() {
        JFrame frame = new JFrame("Students List");
        frame.setSize(930, 400);
        frame.setLocationRelativeTo(null);

        String[] columnNames = {"ID", "Name", "Level", "Reg. Fee Status", "Fee", "Fee Status"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        try (BufferedReader reader = new BufferedReader(new FileReader("students.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    double fee = parts[2].equals("Primary") ? 10000 : 15000;
                    model.addRow(new String[]{parts[0], parts[1], parts[2], parts[3], String.valueOf((int) fee), parts[4]});
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error reading file.");
        }

        JTable table = new JTable(model);
        table.setRowHeight(25);
        table.setFont(new Font("SansSerif", Font.PLAIN, 14));
        table.setSelectionBackground(Color.LIGHT_GRAY);
        table.setGridColor(Color.GRAY);

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("SansSerif", Font.BOLD, 16));
        header.setBackground(Color.DARK_GRAY);
        header.setForeground(Color.WHITE);

        frame.add(new JScrollPane(table));
        frame.setVisible(true);
    }
}