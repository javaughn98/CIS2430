import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import DBaseConnector.*;

/**
 * Write a description of class AdminMode here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class AdminMode extends JFrame
{
    // instance variables - replace the example below with your own
    JButton button1 = new JButton("Connect To The database");
    JButton button2 = new JButton("Maintain Degres in Database");
    JButton button3 = new JButton("Maintain Courses in Database");
    MyConnection newConnection;
    JTextField field1= new JTextField();
    JTextField field2= new JTextField();
    JTextField field3= new JTextField();
    JTextField field4= new JTextField();
    JTextField field5= new JTextField();
    
    public JFrame plannerFrame;
    public AdminMode(JFrame plannerFrame) {
        super();
        this.plannerFrame = plannerFrame;
        this.setVisible(true);
        setTitle("Administrator Mode");
        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setLayout(new BorderLayout());
        Container pane = new Container();
        pane = getContentPane();
        pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
        
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        JMenu file = new JMenu("File");
        menuBar.add(file);
        
        JMenu settings = new JMenu("Settings");
        menuBar.add(settings);
        
        JMenuItem save = new JMenuItem("Save");
        save.addActionListener(new AdminActionListener());
        file.add(save);
        
        JMenuItem exit = new JMenuItem("Quit");
        exit.addActionListener(new AdminActionListener());
        file.add(exit);
        
        JMenuItem toStudent = new JMenuItem("Switch To Student Mode");
        toStudent.addActionListener(e->switchPlan());
        settings.add(toStudent);
        
        
        pane.add(button1);
        pane.add(button2);
        pane.add(button3);
        
        button1.addActionListener(new AdminActionListener());
        button2.addActionListener(new AdminActionListener());
        button3.addActionListener(new AdminActionListener());
        
        setContentPane(pane);        
    }
    
    public void switchPlan() {
        this.setVisible(false);
        plannerFrame.setVisible(true);
        plannerFrame.revalidate();
        this.dispose();
    }
    
    class AdminActionListener implements ActionListener {
        
        public void actionPerformed(ActionEvent ae) {
            String string = ae.getActionCommand();
             
           if(string.equals("Save")) {
                
           }
           else if(string.equals("Quit")) {
             int yesOrNo = JOptionPane.showConfirmDialog(null,"Do you really want to exit?","Exit",JOptionPane.YES_NO_OPTION);
             if(yesOrNo == 0) { 
                 System.exit(0);
             }  
           }
           else if(string.equals("Connect To The database")) {
               newConnection = new MyConnection(DBDetails.username, DBDetails.password);
               PrepStudentScript initTables = new PrepStudentScript(true);
               //newConnection.deleteAllSavedStudent();
               //newConnection.repopulateCourses();
           }
           else if(string.equals("Maintain Degres in Database")) {
               String[] str = {"Add Degree","Remove Degree","Change Degree"};
               JComboBox<String> selection = new JComboBox<>(str);
               int num = JOptionPane.showConfirmDialog(null,selection,"Select one of the following",JOptionPane.OK_CANCEL_OPTION);
               if(num == 0 && selection.equals("Add Degree")) {
                   
               }
               else if(num == 0 && selection.equals("Remove Degree")) {
                   
               }
               else if(num == 0 && selection.equals("Change Degree")) {
                   
               }
           }
           else if(string.equals("Maintain Courses in Database")) {
               String[] str = {"Add Course", "Remove Course", "Change Course"};
               JComboBox<String> selection = new JComboBox<>(str);
               int num = JOptionPane.showConfirmDialog(null,selection,"Select one of the following:",JOptionPane.OK_CANCEL_OPTION);
               if(num == 0 && selection.getSelectedItem().equals("Add Course")) {
                   Object[] field = {
                       "Enter course code(ex CIS*2430)",field1,
                       "Enter course credit(form 0.0)", field2,
                       "Enter course name", field3,
                       "Enter semester(ex F18)", field4,
                       "Enter prerequisite(ex CIS*2520)", field5
                    };
                   num = JOptionPane.showConfirmDialog(null,field,"Add course",JOptionPane.OK_CANCEL_OPTION);
                   if(num == 0) {
                       newConnection.addCourse(field1.getText(), field2.getText(),field3.getText(),field4.getText(),field5.getText());
                       JOptionPane.showMessageDialog(null,field1.getText() + " was added to database");
                   }
               }
               else if(num == 0 && selection.getSelectedItem().equals("Remove Course")) {
                  Object[] field = {"Enter course code(ex CIS*2430)", field1};
                  num = JOptionPane.showConfirmDialog(null,field,"Remove course",JOptionPane.OK_CANCEL_OPTION);
                  if(num == 0){
                      num = JOptionPane.showConfirmDialog(null,"Do you really want to remove course?", "Delete", JOptionPane.YES_NO_OPTION);
                      if(num == 0 && newConnection.findCourse(field1.getText()) != null) {
                          newConnection.deleteCourse(field1.getText());
                          JOptionPane.showMessageDialog(null,"Course removed");
                        }
                  }
               }
               else if(num == 0 && selection.getSelectedItem().equals("Change Course")) {
                   Object[] field = {
                       "Enter course code (ex CIS*2430)",field1,
                       "Enter course credit(form 0.0)", field2,
                       "Enter course name", field3,
                       "Enter semester(ex F18)", field4,
                       "Enter prerequisite(ex CIS*2520)", field5
                    };
                   num = JOptionPane.showConfirmDialog(null, field,"Change course", JOptionPane.OK_CANCEL_OPTION);
                   if(num == 0 && newConnection.findCourse(field1.getText()) != null) {
                       newConnection.deleteCourse(field1.getText());
                       newConnection.addCourse(field1.getText(), field2.getText(),field3.getText(),field4.getText(),field5.getText());
                       JOptionPane.showMessageDialog(null,"Course was updated");
                   }
                   
               }
               
              
                
               
           }  
       }
    }
    
}
