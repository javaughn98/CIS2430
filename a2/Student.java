

import univ.Course;
import java.util.Objects;
import java.util.*;
import DBaseConnector.*;

public class Student {

    private String first;
    private String last;
    private int studentNum;
    public ArrayList<Course> plannedCourses;
    public ArrayList<Attempt> courseAttempts;

    public Student() {
        this.plannedCourses = new ArrayList<>();
        this.courseAttempts = new ArrayList<>();
        this.first = null;
        this.last = null;
        this.studentNum = 0;
    }
    
   /* public Student(String id, String userName) {
       DBStudent stud = MC.loadStudent(id, userName);
       String token[] = stud.getName().split(" ");
       System.out.println("Student: " + token[0] + " "  + token[1]);
    }
    */
    public void setPlannedCourses(ArrayList<Course> plannedCourses) {
        this.plannedCourses = plannedCourses;
    }
    
    public ArrayList<Course> getPlannedCourses() {
        return this.plannedCourses;   
    }
    
    public void setCourseAttempts(ArrayList<Attempt> courseAttempts) {
        this.courseAttempts = courseAttempts;
    }
    
    public ArrayList<Attempt> getCourseAttempts() {
        return this.courseAttempts;
    }

    public void setFirstName(String first) {
        if (first != null && !first.isEmpty())
            this.first = first;
    }

    public void setLastName(String last) {
        if (last != null && !last.isEmpty())
            this.last = last;
    }

    public void setStudentNumber(Integer studentNum) { this.studentNum = studentNum; }

    public String getFullName() {
        String fullName;
        if (this.first == null && this.last == null) {
            return null;
        } else if (this.first == null) {
            fullName = this.last;
        } else if (this.last == null) {
            fullName = this.first;
        } else {
            fullName = this.first + " " + this.last;
        }
        return fullName;
    }

    public String getFirstName() { return this.first; }

    public String getLastName() { return this.last; }

    public Integer getStudentNumber() { return this.studentNum; }

    /*public double totalCredits() {
        double totalCredits = 0.0;
        for (Course c : this.courses) {
            if (c.getCourseStatus() != null && c.getCourseStatus().equals("Completed")) {
                totalCredits += c.getCourseCredit();
            }
        }
        return totalCredits;
    }*/
    
    @Override
    public String toString() {
        String toString = "";
        if (this.first != null) {
            toString = ("First name: " + this.first + System.getProperty( "line.separator" ));
        }
        if (this.last != null) {
            toString += ("Last name: " + this.last + System.getProperty( "line.separator" ));
        }
        toString += ("Student number: " + this.studentNum + System.getProperty( "line.separator" ));

        return toString;
    }

    @Override
    public boolean equals(Object o) {
        if(o == null) {
         return false;   
        }
        
        if (o == this) {
            return true;
        }

        if (!(o instanceof Student)) {
            return false;
        }

        Student student = (Student) o;
        
        if (student.first == null ||!(this.first.equals(student.first))){
            return false;
        }
        
        if (student.last == null ||!(this.last.equals(student.last))){
            return false;
        }
        
        if(this.courseAttempts != null && student.courseAttempts != null) {
            if(!this.courseAttempts.equals(student.courseAttempts)) {
                return false;
            }
        }
        
        if(this.plannedCourses != null && student.plannedCourses != null) {
            if(!this.plannedCourses.equals(student.plannedCourses)) {
                return false;
            }
        }
        
        return this.studentNum == student.studentNum;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.first);
        hash = 37 * hash + Objects.hashCode(this.last);
        hash = 37 * hash + Objects.hashCode(this.studentNum);
        return hash;
    }
}
