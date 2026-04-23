import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class Teacher extends Manager {

    private String Name;
    public int myId;
    private static int idCounter = 1000;
    private double Salary;
    private String SalaryStatus;
    private String Subjects;
    String Educationalstate;


    ArrayList<Teacher> teacher = new ArrayList<>();

    public Teacher() {
        this.Name = "";
        this.myId = ++idCounter;
        this.Salary = 20000;
        this.Subjects = "";
        this.SalaryStatus = "Unpaid";

    }

    public Teacher(int myId, String Name, String Educationalstate, String Subjects, double Salary) {
        this.myId=myId;
        this.Name = Name;
        this.Educationalstate = Educationalstate;
        this.Subjects = Subjects;
        this.Salary = Salary;
        this.SalaryStatus = "Unpaid";

    }
    public Teacher(int Id, String Name, String EducationalState, String Subjects, double Salary, String SalaryStatus) {
        this.myId = Id;
        this.Name = Name;
        this.Educationalstate = EducationalState;
        this.Subjects = Subjects;
        this.Salary = Salary;
        this.SalaryStatus = SalaryStatus;
        this.SalaryStatus = "Unpaid";

    }

    public void loadTeachersFromFile() {
        teacher.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader("teacher.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 6) {
                    int id = Integer.parseInt(parts[0]);
                    String Name = parts[1];
                    String Educationalstate = parts[2];
                    String Subjects = parts[3];
                    double Salary = Double.parseDouble(parts[4]);
                    String salaryStatus = parts[5].trim(); // reads "Paid" or "Unpaid" from file
                    teacher.add(new Teacher(id, Name, Educationalstate, Subjects, Salary, salaryStatus));

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void DealingPage() {
        loadTeachersFromFile();
        JFrame frame = new JFrame("Teacher");
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton addT = new JButton("Add Teacher");
        JButton removeT = new JButton("Remove Teacher");
        JButton feeStatusT = new JButton("Update Salary Status");
        JButton academicCalenderT = new JButton("Show Academic Calender");
        JButton editInfoT = new JButton("Edit Info");
        JButton saveDataT = new JButton("Save to File");

        JPanel panelT = new JPanel(new GridLayout(2, 2, 20, 20));
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        frame.add(panelT, BorderLayout.CENTER);
        panelT.setBorder(BorderFactory.createEmptyBorder(120, 200, 140, 200));
        panelT.setBackground(Color.LIGHT_GRAY);

        JPanel backPanelT = new JPanel();
        JButton backT = new JButton("Back to Dashboard");
        frame.add(backPanelT, BorderLayout.SOUTH);
        backPanelT.setBackground(Color.LIGHT_GRAY);
        backT.setPreferredSize(new Dimension(150, 30));
        backPanelT.add(backT);
        backT.setBackground(Color.BLACK);
        backT.setForeground(Color.LIGHT_GRAY);

        JPanel TopTitleT = new JPanel();
        frame.add(TopTitleT, BorderLayout.NORTH);
        TopTitleT.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        TopTitleT.setBackground(new Color(0x006666));

        ImageIcon img = new ImageIcon("logo.png");
        Image scaledImage = img.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel logoWithTextT = new JLabel("Teacher Manager", scaledIcon, JLabel.CENTER);
        logoWithTextT.setHorizontalTextPosition(JLabel.CENTER);
        logoWithTextT.setVerticalTextPosition(JLabel.BOTTOM);
        logoWithTextT.setFont(new Font("SansSerif", Font.BOLD, 24));
        logoWithTextT.setForeground(Color.WHITE);
        TopTitleT.add(logoWithTextT);

        panelT.add(addT);
        panelT.add(removeT);
        panelT.add(feeStatusT);
        panelT.add(academicCalenderT);
        panelT.add(editInfoT);
        panelT.add(saveDataT);

        addT.setBackground(new Color(0,102,102));
        removeT.setBackground(new Color(0,102,102));
        feeStatusT.setBackground(new Color(0,102,102));
        academicCalenderT.setBackground(new Color(0,102,102));
        editInfoT.setBackground(new Color(0,102,102));
        saveDataT.setBackground(new Color(0,102,102));
        addT.setForeground(new Color(0xF0F0F0));
        removeT.setForeground(new Color(0xF0F0F0));
        feeStatusT.setForeground(new Color(0xF0F0F0));
        academicCalenderT.setForeground(new Color(0xF0F0F0));
        editInfoT.setForeground(new Color(0xF0F0F0));
        saveDataT.setForeground(new Color(0xF0F0F0));

        ActionListener listenerT = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == addT) {
                    add();
                } else if (e.getSource() == removeT) {
                    removeTeacher();
                } else if (e.getSource() == editInfoT) {
                    editInfo();
                } else if (e.getSource() == academicCalenderT) {
                    academicCalendar();
                } else if (e.getSource() == saveDataT) {
                    saveToFile();
                } else if (e.getSource() == feeStatusT) {
                    updateSalarystatus();
                } else if (e.getSource() == backT) {
                    frame.dispose();
                    Dashboard thirdPage = new Dashboard();
                    thirdPage.DashboardPage();
                }
            }
        };

        addT.addActionListener(listenerT);
        removeT.addActionListener(listenerT);
        feeStatusT.addActionListener(listenerT);
        academicCalenderT.addActionListener(listenerT);
        editInfoT.addActionListener(listenerT);
        backT.addActionListener(listenerT);
        saveDataT.addActionListener(listenerT);

        frame.setVisible(true);
    }
    public void add() {
        JFrame addFrame = new JFrame("Add New Teacher");
        addFrame.setSize(600, 500);
        addFrame.setLayout(new BorderLayout()); // for top-center-bottom layout
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10)); // for form inputs
        // Adjusted rows
        addFrame.setLocationRelativeTo(null);

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();

        JLabel eduStateLabel = new JLabel("Educational State:");
        JComboBox<String> eduStateCombo = new JComboBox<>(new String[]{"Primary", "Secondary"});

        JLabel salaryLabel = new JLabel("Salary:");
        JTextField textSalary = new JTextField();
        textSalary.setEditable(false);

        JLabel subjectsLabel = new JLabel("Subjects:");
        JPanel subjectsPanel = new JPanel(new GridLayout(2, 3, 10, 10));

        JCheckBox subEng = new JCheckBox("English");
        JCheckBox subUrdu = new JCheckBox("Urdu");
        JCheckBox subMaths = new JCheckBox("Maths");
        JCheckBox subScience = new JCheckBox("Science");
        JCheckBox subSST = new JCheckBox("SST");

        Font subjectFont = new Font("SansSerif", Font.PLAIN, 16);

        subEng.setFont(subjectFont);
        subUrdu.setFont(subjectFont);
        subMaths.setFont(subjectFont);
        subScience.setFont(subjectFont);
        subSST.setFont(subjectFont);

        // Add to subject panel
        subjectsPanel.add(subEng);
        subjectsPanel.add(subUrdu);
        subjectsPanel.add(subMaths);
        subjectsPanel.add(subScience);
        subjectsPanel.add(subSST);

        // Initially hide secondary subjects
        subScience.setVisible(false);
        subSST.setVisible(false);

        // Educational level selection logic
        eduStateCombo.addActionListener(e -> {
            String selected = eduStateCombo.getSelectedItem().toString();
            if (selected.equals("Primary")) {
                subScience.setVisible(false);
                subSST.setVisible(false);
                textSalary.setText("30000");
            } else {
                subScience.setVisible(true);
                subSST.setVisible(true);
                textSalary.setText("40000");
            }
        });

        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
        saveButton.setPreferredSize(new Dimension(250, 50)); // width x height
        cancelButton.setPreferredSize(new Dimension(250, 50));

        saveButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        cancelButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);


        // Save Action
        saveButton.addActionListener(e -> {
            String enteredName = nameField.getText().trim();
            String enteredEduState = eduStateCombo.getSelectedItem().toString().trim();

            if (enteredName.isEmpty()) {
                JOptionPane.showMessageDialog(addFrame, "Please enter name.");
                return;
            }

            ArrayList<String> selectedSubjects = new ArrayList<>();
            if (subEng.isSelected()) selectedSubjects.add("English");
            if (subUrdu.isSelected()) selectedSubjects.add("Urdu");
            if (subMaths.isSelected()) selectedSubjects.add("Maths");
            if (subScience.isVisible() && subScience.isSelected()) selectedSubjects.add("Science");
            if (subSST.isVisible() && subSST.isSelected()) selectedSubjects.add("SST");

            if (selectedSubjects.isEmpty()) {
                JOptionPane.showMessageDialog(addFrame, "Please select at least one subject.");
                return;
            }

            String subjects = String.join("/", selectedSubjects);
            int salary = Integer.parseInt(textSalary.getText());

            int newId = ++idCounter;

            teacher.add(new Teacher(newId, enteredName, enteredEduState, subjects, salary));
            JOptionPane.showMessageDialog(addFrame, "Teacher added successfully!");
            addFrame.dispose();
        });

        cancelButton.addActionListener(e -> addFrame.dispose());

        // Add components to frame directly (like in Student)
        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(eduStateLabel);
        formPanel.add(eduStateCombo);
        formPanel.add(salaryLabel);
        formPanel.add(textSalary);
        formPanel.add(subjectsLabel);
        formPanel.add(subjectsPanel);

        // Add panels to the frame
        addFrame.add(formPanel, BorderLayout.CENTER);
        addFrame.add(buttonPanel, BorderLayout.SOUTH);


        addFrame.setVisible(true);
    }
    public void saveToFile() {
        if (teacher.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No teacher data to save.");
            return;
        }
        try {
            File file = new File("teacher.txt");

            FileWriter writer = new FileWriter(file);


            for (Teacher st : teacher) {
                writer.write(st.myId + "," + st.Name + "," + st.Educationalstate + "," +
                        st.Subjects + "," + st.Salary + "," + st.SalaryStatus + "\n");
            }

            writer.close();
            JOptionPane.showMessageDialog(null, "Teacher data saved to teacher.txt");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error saving data to file.");
        }
    }
    @Override
    void editInfo() {
        int id = promptId();
        Teacher teacher = findteacher(id);

        if (teacher == null) {
            JOptionPane.showMessageDialog(null, "Teacher not found.");
            return;
        }
        String newName = JOptionPane.showInputDialog("Enter new name:", teacher.Name);
        String newEdu = JOptionPane.showInputDialog("Enter updated educational state:", teacher.Educationalstate);

        if (newName == null || newEdu == null || newName.isEmpty() || newEdu.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Edit cancelled or invalid input.");
            return;
        }

        teacher.Name = newName;
        teacher.Educationalstate = newEdu;
        if (newEdu.equalsIgnoreCase("Primary")) {
            teacher.Salary = 30000;
        } else if (newEdu.equalsIgnoreCase("Secondary")) {
            teacher.Salary = 40000;
        }


        updateTeacherInFile(teacher);

        JOptionPane.showMessageDialog(null, "Teacher info updated successfully.");

    }
    void updateSalarystatus(){
        int id = promptId();
        Teacher teachers = findteacher(id);

        if (teachers == null) {
            JOptionPane.showMessageDialog(null, "Teacher not found.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(null, "Mark Salary as paid?", "Update Salary Status", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            teachers.SalaryStatus = "Paid"; // ✅ Correct: assigning a String

            updateTeacherInFile(teachers);
            JOptionPane.showMessageDialog(null, "Salary status updated successfully.");
        }
    }
    void removeTeacher(){

        int idToRemove = promptId();
        if (idToRemove == -1) {
            JOptionPane.showMessageDialog(null, "Invalid ID entered.");
            return;
        }

        File inputFile = new File("teacher.txt");
        File tempFile = new File("temp_teacher.txt");

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
            if (inputFile.delete() && tempFile.renameTo(inputFile)) {
                JOptionPane.showMessageDialog(null, "Teacher removed successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "Error updating the file.");
            }
        } else {
            tempFile.delete(); // deletes the extra temp file
            JOptionPane.showMessageDialog(null, "Teacher ID not found.");
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
        header.setBackground(new Color(0,102,102));
        header.setForeground(Color.WHITE);

        JFrame frame = new JFrame("Academic Calendar");
        frame.setSize(800,700);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.add(new JScrollPane(calendar));
    }
    void updateTeacherInFile(Teacher updatedTeacher) {
        File inputFile = new File("teacher.txt");
        File tempFile = new File("teacher_temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             PrintWriter writer = new PrintWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int currentId = Integer.parseInt(parts[0]);

                if (currentId == updatedTeacher.myId) {

                    writer.println(updatedTeacher.myId + "," + updatedTeacher.Name + "," +
                            updatedTeacher.Educationalstate + "," + updatedTeacher.Subjects + "," +
                            updatedTeacher.Salary + "," + updatedTeacher.SalaryStatus);

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
    public void updateIdCounterFromFile(){
        File file = new File("teacher.txt");
        if (!file.exists()) return;

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
    public Teacher findteacher(int id) {
        try (BufferedReader reader = new BufferedReader(new FileReader("teacher.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (Integer.parseInt(parts[0]) == id) {
                    return new Teacher(id, parts[1], parts[2], parts[3], Double.parseDouble(parts[4]), parts[5].trim());
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public int promptId() {
        String input = JOptionPane.showInputDialog("Enter Teacher ID:");
        if (input == null || input.trim().isEmpty()) {
            return -1;
        }
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    void viewTeacherList () {
            JFrame frame = new JFrame("Teachers List");
            frame.setSize(930, 400);
            frame.setLocationRelativeTo(null);
            frame.setBackground(Color.LIGHT_GRAY);


            String[] columnNames = {"ID", "Name", "Primary/Secondary", "Subjects", "Salary", "Salary Status"};

            DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
            try (BufferedReader br = new BufferedReader(new FileReader("teacher.txt"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(",");
                    if (data.length == 6) {
                        String[] newData = {data[0], data[1], data[2], data[3], data[4], data[5]};
                        tableModel.addRow(newData);
                    }
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(frame, "Error reading teacher file: " + e.getMessage());
            }

            JTable table = new JTable(tableModel);
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
