import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import DBaseConnector.*;
import java.io.*;


/**
 * Write a description of class Planner here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Planner extends JFrame
{
    // instance variables - replace the example below with your own 
    JTextField text1;
    JTextField text2;
    JPasswordField st = new JPasswordField();
    JButton button1 = new JButton("Sign up");
    JButton button = new JButton("Login");
    JCheckBox checkBox = new JCheckBox("Show login ID"); 
    DBStudent newStudent = null;
    JFrame frame = this;
    MyConnection newConnection;
    JLabel image1;
    ImageIcon background;
    
    public Planner(MyConnection newConnect) {
        super();
        
        setTitle("Student Planner Application (GUI)");
        setSize(1500,1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        background = new ImageIcon(getClass().getResource("i2.jpeg"));
        image1 =new JLabel(background);
        image1.setBounds(100,100,800,800);
        
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        JMenu file = new JMenu("File");
        menuBar.add(file);
        
        JMenu settings = new JMenu("Switch Mode");
        menuBar.add(settings);
        
        JMenuItem load = new JMenuItem("Load");
        load.addActionListener(new LoginActionListener());
        file.add(load);
        
        JMenuItem exit = new JMenuItem("Quit");
        exit.addActionListener(new LoginActionListener());
        file.add(exit);
        
        JMenuItem admin = new JMenuItem("Switch To Administrator Mode");
        admin.addActionListener(e->switchAd());
        settings.add(admin);
        
        Container contentPane = new Container();
        contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane,BoxLayout.PAGE_AXIS));
        
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setSize(500,500);
        
        
        JLabel first = new JLabel("First Name:");
        text1 = new JTextField();
        
        JLabel last = new JLabel("Last Name:");
        text2 = new JTextField();
        
        JLabel id = new JLabel("Student ID:");
        
        
        button.addActionListener(new CustomActionListener());
        button1.addActionListener(new CustomActionListener());
        checkBox.addActionListener(new LoginActionListener());
        
        first.setBounds(600,300,150,20);
        text1.setBounds(600,320,150,20);
        last.setBounds(600,360,150,20);
        text2.setBounds(600,380,150,20);
        id.setBounds(600,420,150,20);
        st.setBounds(600,440,150,20);
        button.setBounds(600,460,60,30);
        button1.setBounds(665,460,80,30);
        checkBox.setBounds(750,460,120,30);
        
        
        panel.add(first);
        panel.add(text1);
        panel.add(last);
        panel.add(text2);
        panel.add(id);
        panel.add(st);
        panel.add(button);
        panel.add(checkBox);
        panel.add(button1);
        contentPane.add(panel);
       
        
        
        
        setContentPane(contentPane);
    }
    
    public void switchAd() {
        this.setVisible(false);
        AdminMode newAdmin = new AdminMode(this); 
    } 
    
    public static void main(String[] args) {
       Planner newPlanner = new Planner(null);
       newPlanner.setVisible(true);
    }
    
    class LoginActionListener implements ActionListener {
     public void actionPerformed(ActionEvent ae) {
         String str = ae.getActionCommand();
          if(checkBox.isSelected()) {
             st.setEchoChar((char)0);
         }
         else if(!checkBox.isSelected()) {
             st.setEchoChar('•');
         }
         
         else if(str.equals("Quit")) {
             int yesOrNo = JOptionPane.showConfirmDialog(null,"Do you really want to exit?","Exit",JOptionPane.YES_NO_OPTION);
             if(yesOrNo == 0) { 
                 System.exit(0);
             }
         }
     }
    }
    
    class CustomActionListener implements ActionListener{
       public void actionPerformed(ActionEvent a) {
            String str = a.getActionCommand();
         if(text1.getText().trim().isEmpty() && text2.getText().trim().isEmpty() && new String(st.getPassword()).trim().isEmpty()) {
           JOptionPane.showMessageDialog(null,"Invalid input");
         }
         else if(text1.getText().trim().isEmpty()) {
             JOptionPane.showMessageDialog(null,"First name is empty. Please enter First name");
         }
         else if(text2.getText().trim().isEmpty()) {
             JOptionPane.showMessageDialog(null,"Last name is empty. Please enter last name");
         }
         else if(new String(st.getPassword()).trim().isEmpty()) {
             JOptionPane.showMessageDialog(null,"Please enter student ID");
         }
         else if(str.equals("Login")) {
             try{ 
                 newStudent = newConnection.loadStudent(new String(st.getPassword()), text1.getText().concat(" ").concat(text2.getText()));
                 if(newStudent.getName() == null) {
                     throw new IOException();
                 }
                 else{
                      frame.setVisible(false);
                      StudentPlanner newDeg = new StudentPlanner(frame,newStudent, newConnection);
                      newDeg.setVisible(true);
                 }
             }
             catch(IOException e){
                 JOptionPane.showMessageDialog(null,"Incorrect username or password","Error",JOptionPane.ERROR_MESSAGE);
             }
            
         } 
         else if(str.equals("Sign up")) {
             
            try{
                newConnection = new MyConnection();
                String pass = new String(st.getPassword());
                newStudent = new DBStudent(pass, text1.getText().concat(" ").concat(text2.getText()));
                newConnection.saveStudent(newStudent); 
                int n = 0;
              
                if(n == 1) {
                    throw new ArithmeticException();
                }
               
                    frame.setVisible(false);
                    SelectDegree newDeg = new SelectDegree(newStudent,newConnection);
                    newDeg.setVisible(true);
                
            }
            catch(ArithmeticException e){
                JOptionPane.showMessageDialog(null,"Woops! Something went wrong.","Error", JOptionPane.ERROR_MESSAGE);
               
            }
            
        }
      }
    
    
    
    
    
    
    }
}
