import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import univ.Course;
import univ.CourseCatalog; 


/*
    This is a degree class for Bachelor of computing General
    degree course which is used to check the requirements for the program
    and to check is the student has met such requirements 
*/
public class BCG extends GeneralDegree {

    private static final double maxOneSubjectCredits = 11.00;
    private static final double max1000LvlCredits = 6.00;
    private static final double rqrd3000orHigherCredits = 4.00;
    private static final double rqrdCisStat2000orHigherCredits = 0.5;
    private static final double rqrdScienceCredits = 2.00;
    private static final double rqrdArtsSocialScienceCredits = 2.00;

    public BCG() {
        super();
    }

    public boolean isDigit(String string) {
        if (string.matches("[a-zA-Z]+")) {
            return false; 
        }
        
        return true;
    }


    public boolean meetsRequirements(ArrayList<Course> allTheCoursesPlannedAndTaken) {
        double totalCredits = 0.0, credits3000 = 0.0, credits1000 = 0.0, creditsSubject = 0.0, creditsCisStat2000 = 0.0;
        String[] courseCodeParts;
   
     /*   
        for(Course c : all.getPlannedCourses()) {
            courseCode = c.getCourseCode();
            for(Attempt att : thePlan.getCourseAttempts()) {
                courseCode1 = att.getCourseAttempted().getCourseCode();
                if(courseCode.equals(courseCode1)) {
                    if(att.getAttemptGrade() == "P") {
                        courseCodeParts = c.getCourseCode().split("\\*", 2);
                         if (courseCodeParts[0].equals("CIS")) {
                         creditsSubject += c.getCourseCredit();
                        }
                        if (Double.parseDouble(courseCodeParts[1]) < 2000) {
                            credits1000 += c.getCourseCredit();
                        }   
                        if (Double.parseDouble(courseCodeParts[1]) >= 3000) {
                            credits3000 += c.getCourseCredit();
                        }
                        if ((courseCodeParts[0].equals("CIS") || courseCodeParts[0].equals("STAT")) && Double.parseDouble(courseCodeParts[1]) >= 2000) {
                            creditsCisStat2000 += c.getCourseCredit();
                        }
                        if (creditsSubject < maxOneSubjectCredits && credits1000 < max1000LvlCredits) {
                            totalCredits += c.getCourseCredit();
                        }
                    }
                    else if(isDigit(att.getAttemptGrade()) == true) {
                        if(Integer.parseInt(att.getAttemptGrade()) >= 50) {
                            courseCodeParts = c.getCourseCode().split("\\*", 2);
                            if (courseCodeParts[0].equals("CIS")) {
                                creditsSubject += c.getCourseCredit();
                            }
                            if (Double.parseDouble(courseCodeParts[1]) < 2000) {
                                credits1000 += c.getCourseCredit();
                            }
                            if (Double.parseDouble(courseCodeParts[1]) >= 3000) {
                                credits3000 += c.getCourseCredit();
                            }
                            if ((courseCodeParts[0].equals("CIS") || courseCodeParts[0].equals("STAT")) && Double.parseDouble(courseCodeParts[1]) >= 2000) {
                                creditsCisStat2000 += c.getCourseCredit();
                            }
                            if (creditsSubject < maxOneSubjectCredits && credits1000 < max1000LvlCredits) {
                                 totalCredits += c.getCourseCredit();
                            }
                        }
                    }
                }
            }
        }
        
        return totalCredits >= rqrdNumberOfCredits && credits3000 >= rqrd3000orHigherCredits && creditsCisStat2000 >= rqrdCisStat2000orHigherCredits;
        */
        for (Course c : allTheCoursesPlannedAndTaken) {
                courseCodeParts = c.getCourseCode().split("\\*", 2);
                if (courseCodeParts[0].equals("CIS")) {
                    creditsSubject += c.getCourseCredit();
                }
                if (Double.parseDouble(courseCodeParts[1]) < 2000) {
                    credits1000 += c.getCourseCredit();
                }
                if (Double.parseDouble(courseCodeParts[1]) >= 3000) {
                    credits3000 += c.getCourseCredit();
                }
                if ((courseCodeParts[0].equals("CIS") || courseCodeParts[0].equals("STAT")) && Double.parseDouble(courseCodeParts[1]) >= 2000) {
                    creditsCisStat2000 += c.getCourseCredit();
                }
                if (creditsSubject < maxOneSubjectCredits && credits1000 < max1000LvlCredits) {
                    totalCredits += c.getCourseCredit();
                }
        }
        return totalCredits >= rqrdNumberOfCredits && credits3000 >= rqrd3000orHigherCredits && creditsCisStat2000 >= rqrdCisStat2000orHigherCredits;
    }






    public double numberOfCreditsRemaining(ArrayList<Course> allTheCoursesPlannedAndTaken) {
        double remainingCredits = 15, credits3000 = 0.0, credits1000 = 0.0, creditsSubject = 0.0, creditsCisStat2000 = 0.0, totalCredits = 0.0;
        String[] courseCodeParts;
        
        for(Course courses : allTheCoursesPlannedAndTaken) {
            courseCodeParts = courses.getCourseCode().split("\\*", 2);
            if (courseCodeParts[0].equals("CIS")) {
                 creditsSubject += courses.getCourseCredit();
            }
            if (Double.parseDouble(courseCodeParts[1]) < 2000) {
                if(credits1000 < 6.00) {
                    credits1000 += courses.getCourseCredit();
                }
            }
            if (Double.parseDouble(courseCodeParts[1]) >= 3000) {
                if(credits3000 < 4.00) {
                 credits3000 += courses.getCourseCredit();
                }
            }
            if ((courseCodeParts[0].equals("CIS") || courseCodeParts[0].equals("STAT")) && Double.parseDouble(courseCodeParts[1]) >= 2000) {
                if(creditsCisStat2000 < 0.5) { 
                    creditsCisStat2000 += courses.getCourseCredit();
                }
            }
            if (creditsSubject < maxOneSubjectCredits && credits1000 < max1000LvlCredits) {
                 totalCredits += courses.getCourseCredit();
            }
         }
         
         
        
        /*CourseCatalog catalog = thePlan.getCatalog();
        ArrayList<Course> courses = thePlan.getCourses();
        for (Course c : courses) {
                if (! c.getCourseStatus().equals("Completed")){
                    if (!completed) {
                    remainingCredits += c.getCourseCredit();
                }
            }

        }*/
        return remainingCredits;
    }

    public ArrayList<Course> remainingRequiredCourses(ArrayList<Course> allTheCoursesPlannedAndTaken) {
        ArrayList<Course> remainingRequiredCourses = new ArrayList<>();
        ArrayList<Course> requiredCourses = getRequiredCourses();
        String courseCode = " ";
        boolean completed;
        boolean required;
        for(Course courses : requiredCourses) {
            completed = false;
            courseCode = courses.getCourseCode();
            for(Course plannedCourses : allTheCoursesPlannedAndTaken) {
                if(courseCode.equals(plannedCourses.getCourseCode())) {
                    completed = true;
                    break;
                }
            }
            if(completed == false) {
                remainingRequiredCourses.add(courses);
            }
        }
      /*  for(Course planned : allTheCoursesPlannedAndTaken) {
            if(planned.getCourseGrade() == null || Integer.parseInt(planned.getCourseGrade()) < 50) {
                remainingRequiredCourses.add(planned);
            }
        }
        /*CourseCatalog catalog = thePlan.getCatalog();
        ArrayList<Course> remainingRequiredCourses = new ArrayList<>();
        ArrayList<Course> courses = thePlan.getCourses();
        for (String needed : this.listOfRequiredCourseCodes) {
            for (Course c : courses) {
                if ((c.getCourseCode() != null && c.getCourseCode().equals(needed)) && (c.getCourseStatus() != null && c.getCourseStatus().equals("Completed"))) {
                    completed = true;
                    break;
                }
            }
            if (!completed) {
                if (catalog.findCourse(needed) != null) {
                    remainingRequiredCourses.add(catalog.findCourse(needed));
                } else {
                    System.out.println("Course not in catalog: " + needed);
                }
            }
            completed = false;
        }*/
        return remainingRequiredCourses;
    }

    @Override
    public String toString() {
        StringBuilder toString = new StringBuilder();
        if (this.title != null) {
            toString = new StringBuilder(("Code: " + this.title + System.getProperty("line.separator")));
        }
       /* if (this.listOfRequiredCourseCodes != null) {
            toString.append("Required Course Codes: ");
            for (String s : listOfRequiredCourseCodes.getCourseCode()) {
                toString.append(s).append(" ");
            }
            toString.append(System.getProperty("line.separator"));
        }*/
        return toString.toString();
    }

    @Override
    public boolean equals(Object o) {
        if(o == null) {
            return false;
        }
        
        if (o == this) {
            return true;
        }

        if (!(o instanceof Degree)) {
            return false;
        }

        BCG bcg = (BCG) o;
        if (!(this.title.equals(bcg.title))) {
            return false;
        }
        return this.listOfRequiredCourseCodes.equals(bcg.listOfRequiredCourseCodes);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(getDegreeTitle());
        hash = 41 * hash + Objects.hashCode(this.listOfRequiredCourseCodes);
        return hash;
    }
}
