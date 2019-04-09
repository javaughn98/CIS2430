package univ;

import DBaseConnector.*;
import java.util.ArrayList;
import java.util.Objects;

public class Course {

    private String courseCode;
    private String courseTitle;
    private double credit;
    private ArrayList<Course> preReqList;
    private String semesterOffered;

    /*
    constructors for the course class. this class is used to retrieve information
    about a specific course and used to set important course information by an administratior.
    */
    public Course() {
        this.courseCode = null;
        this.courseTitle = null;
        this.semesterOffered = null;
        this.credit = 0;
        this.preReqList = new ArrayList<>();
    }

    /*  Deep Copy Constructor for Course */
    public Course(Course course) {
        this.courseCode = course.getCourseCode();
        this.courseTitle = course.getCourseTitle();
        this.semesterOffered = course.getSemesterOffered();
        this.credit = course.getCourseCredit();
        this.preReqList = course.getPrerequisites();
    }
    
    public Course(String courseCode, double credit, String courseTitle, String semesterOffered, ArrayList<Course> req) {
        this.courseCode = courseCode;
        this.courseTitle = courseTitle;
        this.semesterOffered = semesterOffered;
        this.credit = credit;
        this.preReqList = req;
    }
    
    

    protected void setCourseCode(String courseCode) {
        if (courseCode != null && !courseCode.isEmpty()) {
            this.courseCode = courseCode;
        }
    }
    
    protected void setSemesterOffered(String semesterOffered) {
     if(semesterOffered != null && !semesterOffered.isEmpty()) {
         try{
            if(semesterOffered.equals("F") || semesterOffered.equals("W") || semesterOffered.equals("B")) {
                this.semesterOffered = semesterOffered;
            }
            else{
                throw new Exception();
            }
        }
        catch(Exception ex) {
            System.out.println("Semester Offered should be F, W or B.");
        }
     }
    }

    protected void setCourseTitle(String courseTitle) {
        if (courseTitle != null && !courseTitle.isEmpty()) {
            this.courseTitle = courseTitle;
        }
    }
    
    public String  getCourseStatus() {
        String status = " ";
        
        return status;
    }

    
   /** protected void setCourseGrade(String grade) {
        if (grade == null) {
            this.grade = null;
            return;
        }

        int gradeNum;
        try {
            gradeNum = Integer.parseInt(grade);
            if (gradeNum <= 100 && gradeNum >= 0) {
                this.grade = grade;
            }
            else{
             throw new Exception();   
            }
        } catch (Exception ignored) {
            System.out.println("Grades must be between 0 and 100.");
        }
    } 
    **/


    
    protected void setCourseCredit(Double credit) {
        if (credit != null && credit >= 0 && credit <= 1.0) {
            this.credit = credit;
        }
    }

    protected void setPrerequisites(ArrayList<Course> preReqList) {
        if (preReqList == null) {
            this.preReqList = null;
        } 
        else {
            this.preReqList = preReqList;
        }
    }

    public String getCourseCode() { return this.courseCode; }

    public String getCourseTitle() { return this.courseTitle; }

    public String getSemesterOffered() { return this.semesterOffered; }

    public double getCourseCredit() { return this.credit; }

    public ArrayList<Course> getPrerequisites() { return this.preReqList; }

    public String toFile() {
        String toFile = "";
        toFile += this.getCourseCode();
        toFile += ",";
        toFile += Double.toString(this.getCourseCredit());
        toFile += ",";
        toFile += this.getCourseTitle();
        toFile += ",";
        for (Course preReq : this.getPrerequisites())  {
            toFile += preReq.getCourseCode();
            toFile += ":";
        }
        if (toFile.charAt(toFile.length() - 1) == ':') {
            toFile = toFile.substring(0, toFile.length() - 1);
        }
        toFile += "?";
        return toFile;
    }

    @Override
    public String toString() {
        StringBuilder toString = new StringBuilder();
        if (this.courseCode != null) {
            toString = new StringBuilder(("Code: " + this.courseCode + System.getProperty("line.separator")));
        }
        if (this.courseTitle != null) {
            toString.append("Title: ").append(this.courseTitle).append(System.getProperty("line.separator"));
        }
        if (this.semesterOffered != null) {
            toString.append("Semester Taken: ").append(this.semesterOffered).append(System.getProperty("line.separator"));
        }
        if (this.credit > 0) {
            toString.append("Credit: ").append(this.getCourseCredit()).append(System.getProperty("line.separator"));
        }
        if (this.preReqList != null) {
            toString.append("Prerequisites: ");
            for (Course c : this.preReqList) {
                toString.append(c.getCourseCode());
            }
        }
        return toString.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
         return false;   
        }
        
        if (o == this) {
            return true;
        }

        if (!(o instanceof Course)) {
            return false;
        }

        Course course = (Course) o;
        if (course.courseCode == null || !(this.courseCode.equals(course.courseCode))) {
            return false;
        }
        
        if (course.courseTitle == null || !(this.courseTitle.equals(course.courseTitle))) {
            return false;
        }
        
        if (this.semesterOffered != null && course.semesterOffered != null) {
            if (!(this.semesterOffered.equals(course.semesterOffered))) {
                return false;
            }
        }
        
        return this.preReqList.equals(course.preReqList);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.courseCode);
        hash = 53 * hash + Objects.hashCode(this.courseTitle);
        hash = 53 * hash + Objects.hashCode(this.semesterOffered);
        hash = 53 * hash + Objects.hashCode(this.preReqList);
        return hash;
    }
}
