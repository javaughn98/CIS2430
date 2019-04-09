import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import DBaseConnector.*;
import java.util.*;
import univ.*;
/**
 * Write a description of class StudentPlanner here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class StudentPlanner extends JFrame
{
    // instance variables - replace the example below with your own

    JButton button1 = new JButton("1 -Maintain Courses In My Plan Of Study");
    JButton button2 = new JButton("2 - Maintain Courses In My Transcript");
    JButton button3 = new JButton("3 - Save Program");
    JButton button4 = new JButton("4 - View Required Courses Not In Transcript Nor Plan Of Study");
    JButton button5 = new JButton("5 - View Required Courses Not In Transcript");
    JButton button6 = new JButton("6 - Number Of Credits To Add To Complete Degree");
    JButton button7 = new JButton("7 - View Prerequisites For Degree and Major");
    JButton button8 = new JButton("8 - View Lists of Prerequisites I Must Tate to Take Courses In Plan Of Study");
    JButton button9 = new JButton("9 - View Completed Credits");
    JButton button10 = new JButton("10 - View Remaining Credits To Complete Program");
    JButton button11 = new JButton("11 - Detemine If You Have Met The Requirements");
    JButton button12 = new JButton("12 - View Course Plan");
    JButton button13 = new JButton("13 - View GPA");
    JButton button14 = new JButton("14 - View GPA(CIS)");
    JButton button15 = new JButton("15 - View GPA For 10 Most Recent Credits");
    MyConnection newConnection;
    DBStudent newDBStudent;
    JComboBox<String> selection;
    Student student = new Student();
    
    
    public StudentPlanner(JFrame degreeFrame,DBStudent newStudent, MyConnection newConnect) {
        super();
        newConnection = newConnect;
        newDBStudent = newStudent;
        setTitle("Student Planner Application");
        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        JMenu file = new JMenu("file");
        menuBar.add(file);
        
        JMenu settings = new JMenu("Settings");
        menuBar.add(settings);
        
        JMenuItem quit = new JMenuItem("Quit");
        quit.addActionListener(new CustomActionListener());
        file.add(quit);
        
        
        
        JMenuItem save = new JMenuItem("Save");
        save.addActionListener(new CustomActionListener());
        file.add(save);
        
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JLabel text = new JLabel();
        text.setPreferredSize(new Dimension(20,20));
        text.setText("Click to select one of the following options below:");
        
        
        
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        panel2.setLayout(new GridLayout(17,1));
        
        JTextField textField = new JTextField("my name is javaughn");
        
        panel1.add(textField);
        contentPane.add(text,BorderLayout.NORTH);
        contentPane.add(panel1,BorderLayout.CENTER);
        
        panel2.add(button1);
        panel2.add(button2);
        panel2.add(button3);
        panel2.add(button4);
        panel2.add(button5);
        panel2.add(button6);
        panel2.add(button7);
        panel2.add(button8);
        panel2.add(button9);
        panel2.add(button10);
        panel2.add(button11);
        panel2.add(button12);
        panel2.add(button13);
        panel2.add(button14);
        panel2.add(button15);

        

        button1.setToolTipText("Add or remove courses in my plan of study");
        button2.setToolTipText("Add,change or remove courses in my transcript or list of courses I have taken");
        button3.setToolTipText("Save my program (the plan and my transcript");
        button4.setToolTipText("View a list of required courses for my degree and majorthat are not represented in my plan of study nor in my transcript");
        button5.setToolTipText("View a list of required courses not represented in my transcript");
        button6.setToolTipText("View the number of credits I must addto my plan of study in order to have a plan that allows me to complete my degree");
        button7.setToolTipText("View a list of prerequisite courses for any required course for my degree and major");
        button8.setToolTipText("View a list of prerequisite courses that I must takein order to take the courses curently in my plan of study");
        button9.setToolTipText("View the number of credits I have completed in my program");
        button10.setToolTipText("View the number of credits I have remaining to complete my program");
        button11.setToolTipText("Determine if I have met the completion requirements of my chosen degree");
        button12.setToolTipText("View my course plan, organized by the semester I have, or plan to, take the courses");
        button14.setToolTipText("View my GPA for CIS courses");
        button15.setToolTipText("View my GPA for my most recent 10 credits");
        
        button1.addActionListener(new CustomActionListener());
        button2.addActionListener(new CustomActionListener());
        button3.addActionListener(new CustomActionListener());
        button4.addActionListener(new CustomActionListener());
        button5.addActionListener(new CustomActionListener());
        button6.addActionListener(new CustomActionListener());
        button7.addActionListener(new CustomActionListener());
        button8.addActionListener(new CustomActionListener());
        button9.addActionListener(new CustomActionListener());
        button10.addActionListener(new CustomActionListener());
        button11.addActionListener(new CustomActionListener());
        button12.addActionListener(new CustomActionListener());
        button13.addActionListener(new CustomActionListener());
        button14.addActionListener(new CustomActionListener());
        button15.addActionListener(new CustomActionListener());

        
        contentPane.add(panel2,BorderLayout.WEST);
        
        setContentPane(contentPane);    
    }
    
    public void splitUserName(DBStudent dbs, Student st) {
        String token[] = dbs.getName().split(" ");
        String first;
        String last;
        
        first = token[0];
        last = token[1];
        
        st.setFirstName(first);
        st.setLastName(last);
        
    }
    
    public ArrayList<Course> parseCourses(DBStudent dbs, Student st) {
        ArrayList<String> list = dbs.getCourses();
        ArrayList<Course> c = new ArrayList<>();
        String token[];
        String str;
        Course course;
        for(String string : list) {
            token = string.split(",");
            if(token[2] == "''") {
                
                str = token[1].substring(token[1].length() - 1);
               // course = new Course(token[0], Double.parseDouble(str.substring(1,str.length())), token[3], token[4], token[5]);
                
                
            }
            else {
            
            }
        }
        return c;
    }
    
    public Student DBStudentToStudent(DBStudent s) {
        Student convertS = new Student();
        Course c = new Course();
        splitUserName(s, convertS);
        convertS.setStudentNumber(Integer.parseInt(s.getId()));
        
        return convertS;
    }
    
    public DBStudent StudentToDBStudent(Student s) {
        DBStudent convertS = new DBStudent();
        return convertS;
    }
    
    
    class CustomActionListener implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
            String choice = ae.getActionCommand();
            if(choice.equals("Quit")) {
                int yesOrNo = JOptionPane.showConfirmDialog(null,"Do you really want to exit?","Exit",JOptionPane.YES_NO_OPTION);
                if(yesOrNo == 0) { 
                    System.exit(0);
                }
            }
            else if(choice.equals("1 - Maintain Courses In My Plan Of Study")) {
                String[] str = {"Add Course", "Remove Course"};
                selection = new JComboBox<>(str); 
                JOptionPane.showConfirmDialog(null, selection,"Maintain Courses in POS", JOptionPane.OK_CANCEL_OPTION);
                
            }
            else if(choice.equals("2 - Maintain Courses In My Transcript")) {
                String[] str = {"Add Course", "Change grade", "Remove Course"};
                selection = new JComboBox<>(str);
                JOptionPane.showConfirmDialog(null, selection, "Maintain courses in transcript", JOptionPane.OK_CANCEL_OPTION);
            }
            else if(choice.equals("3 - Save Program")) {
                
            }
            else if(choice.equals("4 - View Required Courses Not In Transcript Nor Plan Of Study")) {
                
            }
            else if(choice.equals("5 - View Required Courses Not In Transcript")) {
                
            }
            else if(choice.equals("6 - Number Of Credits To Add To Complete Degree")) {
                
            }
            else if(choice.equals("7 - View Prerequisites For Degree and Major")) {
                
            }
            else if(choice.equals("8 - View Lists of Prerequisites I Must Tate to Take Courses In Plan Of Study")) {
                
            }
            else if(choice.equals("9 - View Completed Credits")) {
                
            }
            else if(choice.equals("10 - View Remaining Credits To Complete Program")) {
                
            }
            else if(choice.equals("11 - Detemine If You Have Met The Requirements")) {
                
            }
            else if(choice.equals("12 - View Course Plan")) {
                
            }
            else if(choice.equals("13 - View GPA")) {
                
            }
            else if(choice.equals("14 - View GPA(CIS)")) {
                
            }
            else if(choice.equals("15 - View GPA For 10 Most Recent Credits")) {
                
            }
        }
    }
}
