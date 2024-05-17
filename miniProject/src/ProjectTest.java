import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.assertEquals;
public class ProjectTest {
    @Test(expected =  DoublicateStudentException.class)
    public void doublicateStiudentTest() throws DoublicateStudentException {
        Student student1 = new Student("ali", "mohammadi", 69, 1);
        Student student2 = new Student("ali", "mohammadi", 69, 1);
        Student student3 = new Student("ali", "mohammadi", 69, 1);
        Student student4 = new Student("ali", "mohammadi", 69, 1);
    }
    @Test (expected = DoublicateCourseException.class)
        public void doublicateCourseTest() throws DoublicateCourseException, DoublicateTeacherException {
            Teacher teacher1 = new Teacher("sadegh", "ali acbari", 56);
            Course course1 = new Course("math", teacher1, 3, true , 36);
            Course course2 = new Course("math", teacher1, 3, true , 36);
        }
    @Test(expected = DoublicateTeacherException.class)
        public void doublicateTeacherTest() throws DoublicateTeacherException {
        Teacher teacher1 = new Teacher("sadegh", "ali acbari", 65);
        Teacher teacher2 = new Teacher("sadegh", "ali acbari", 65);
    }
    @Test(expected = DoublicateAssignmentException.class)
        public void doublicateAssighmentTest() throws DoublicateAssignmentException{
        Assignment assignment1 = new Assignment("tamrin1" , AssignmentType.project , 2 , true);
        Assignment assignment2 = new Assignment("tamrin1" , AssignmentType.project , 2 , true);
        }

}
