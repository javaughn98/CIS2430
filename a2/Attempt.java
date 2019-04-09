import univ.*;   
import java.util.*;
/**
 * Write a description of class Attempt here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

 /*
    Records the students attempt at a course that was taken
 */
public class Attempt
{
    private String attemptGrade;
    private String semesterTaken;
    private Course courseAttempted; 
    // instance variables - replace the example below with your own
    
   public Attempt() {
       this.attemptGrade = null;
       this.semesterTaken = null;
       this.courseAttempted = new Course();
   }
   
   
   public Attempt(String attemptGrade, String semesterTaken, Course courseAttempted) {
       this.attemptGrade = attemptGrade;
       this.semesterTaken = semesterTaken;
       this.courseAttempted = courseAttempted;
   }
   
   
   
   public void setAttemptGrade(String attemptGrade) {
       if(this.attemptGrade != null && !attemptGrade.isEmpty()) {
           this.attemptGrade = attemptGrade;
       }
   }
   
   
   public String getAttemptGrade() {
       return this.attemptGrade;
   }
   
   
   
   public void setSemesterTaken(String semesterTaken) {
    if(semesterTaken != null && !semesterTaken.isEmpty()){
     try{
         if(semesterTaken.equals("F") || semesterTaken.equals("W") || semesterTaken.equals("B")) {
             this.semesterTaken = semesterTaken;
         }
         else{
             throw new Exception();
         }
     }
     catch(Exception ex) {
         System.out.println("Semester Taken should be F, W or B.");
     }
    }
   }
    
    
   public String getSemesterTaken() {
        return this.semesterTaken;
   }
   
   
   public void setCourseAttempted(Course courseAttempted) {
       this.courseAttempted = courseAttempted;
   }
   
   
   public Course getCourseAttempted() {
       return this.courseAttempted;
   }
   
   @Override
   public String toString() {
      StringBuilder toString = new StringBuilder();
      
      if(this.attemptGrade != null) {
          toString.append("Grade: ").append(this.attemptGrade).append(System.getProperty("line.separator")); 
        }
      if(this.semesterTaken != null) {
          toString.append("Semester: ").append(this.semesterTaken).append(System.getProperty("line.separator"));
      }
      if(this.courseAttempted != null) {
          toString.append("Course: ").append(this.courseAttempted.getCourseCode()).append(System.getProperty("line.separator"));
      }
      return toString.toString();
   }
   
   @Override
   public boolean equals(Object o) {
    if(o == null) {
           return false;
    }
        
    if(o == this) {
           return true;
    }
        
    if(!(o instanceof Attempt)) {
            return false;
    }
       
    Attempt attempt = (Attempt) o;
       
    if(this.attemptGrade != null && attempt.attemptGrade != null) {
       if(!(this.attemptGrade.equals(attempt.attemptGrade))) {
           return false;
        }
    }
        
    if(this.semesterTaken != null && attempt.semesterTaken != null) { 
       if(!(this.semesterTaken.equals(attempt.semesterTaken))) {
           return false;
       }
    }
    
    return this.courseAttempted == attempt.courseAttempted;
   }
}
