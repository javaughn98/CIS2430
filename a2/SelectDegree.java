import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import DBaseConnector.*;

/**
 * Write a description of class SelectDegree here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SelectDegree extends JFrame
{
    // instance variables - replace the example below with your own
    String major;
    String degree;
    JButton button = new JButton("Submit");
    JComboBox<String> newDegree;
    JComboBox<String> newMajor;
    MyConnection newConnection;
    
    public SelectDegree(DBStudent newStudent, MyConnection newConnect){
        super();
        newConnection = newConnect;
        setTitle("Select degree and major");
        setSize(800,600); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        Container contentPane = new Container();
        contentPane = getContentPane();
        
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setSize(500,500);
        
        JLabel label = new JLabel("Enter Degree:");
        String[] degrees = {"BCG","BCH"};
        newDegree = new JComboBox<>(degrees);
        newDegree.setToolTipText("Select a one of two degrees BCH(BComp Honours) or BCG(BComp General)"); 
        
        JLabel label1 = new JLabel("Enter Major:");
        String[] major = {"BCG"};
        newMajor = new JComboBox<>(major);
        newMajor.setToolTipText("Select a major from my degree if offered");
        
        button.addActionListener(e->switchStudPlan(newStudent));
        
        label.setBounds(300,250,90,20);
        newDegree.setBounds(300,270,90,20);
        label1.setBounds(300,300,90,20);
        newMajor.setBounds(300,320,90,20);
        button.setBounds(300,350,90,30);
        
        panel.add(label);
        panel.add(newDegree);
        panel.add(label1);
        panel.add(newMajor);
        panel.add(button);
        
        contentPane.add(panel);
    }
    
    public void switchStudPlan(DBStudent newStudent) {
        newStudent.setDegree((String)newDegree.getSelectedItem());
        this.setVisible(false);
        StudentPlanner newStudentPlanner = new StudentPlanner(this,newStudent,newConnection);
        newStudentPlanner.setVisible(true);
        this.dispose();
    }
    
    class CustomActionListener implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
            
        }
    }
}
