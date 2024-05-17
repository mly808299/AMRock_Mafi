import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Teacher {
    private static List<Teacher> allTeachers = new ArrayList<>();
    private String firstName;
    private String lastName;
    private int id;
    private int numberOfLessons;
    private List<Course> courses = new ArrayList<>();

    public Teacher(String firstName, String lastName, int id) throws DoublicateTeacherException {
        if (!allTeachers.isEmpty()) {
            for (Teacher i : allTeachers) {
                if (id == i.id)
                    throw new DoublicateTeacherException();
            }
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        allTeachers.add(this);
    }

    public void addCourseOfTeacher(Course newCourse) {
        courses.add(newCourse);
        numberOfLessons++;
    }
    public void removeCurseOfTeacher(Course removeCurse){
        courses.remove(removeCurse);
        numberOfLessons--;
    }
    public void setScore(Double newScore, int studentId, Course course) throws NotFindCurrentCourseException, NotFindCourseOfSemester, NotFindStudentIdException {
        boolean foundCourse = false;
        boolean foundStudentId = false;
        for (Course i : courses) {
            if (i.getCourseCode() == course.getCourseCode()) {
                for (Student j : i.getRegisteredStudents()) {
                    if (j.getId() == studentId) {
                        for (Integer p : j.getCurrentSemesters().getScores().keySet()) {
                            if (p == course.getCourseCode()) {
                                j.getCurrentSemesters().getScores().put(p, newScore);
                            }
                        }
                        foundStudentId = true;
                    }
                }
                foundCourse = true;
            }
        }
        if (!foundCourse) {
            throw new NotFindCurrentCourseException();
        }
        if (!foundStudentId) {
            throw new NotFindStudentIdException();
        }
    }

    public void addStudentToCourse(Student newStudent, Course thisCourse) throws InactiveCourseException, InvalidCurseException, NotFindCourseOfSemester {
        for (Course i : courses) {
            if (i.getCourseCode() == thisCourse.getCourseCode()) {
                i.addRegisteredStudents(newStudent);
                return;
            }
        }
        throw new InvalidCurseException();
    }

    public void removeStudentFromCurse(Student newStudent, Course thisCourse) throws InactiveCourseException, InvalidCurseException, NotFindCourseOfSemester {
        for (Course i : courses) {
            if (i.getCourseCode() == thisCourse.getCourseCode()) {
                i.removeRegisteredStudent(newStudent);
                return;
            }
        }
        throw new InvalidCurseException();
    }

    public static List<Teacher> getAllTeachers() {
        return allTeachers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return id == teacher.id && numberOfLessons == teacher.numberOfLessons && Objects.equals(firstName, teacher.firstName) && Objects.equals(lastName, teacher.lastName) && Objects.equals(courses, teacher.courses);
    }

    public int getId() {
        return id;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setExamCourse(int day, int month, int year, Course course) {
        DateDeadLine date = new DateDeadLine(day, month, year);
        for (Course i : courses) {
            if (course.getCourseCode() == i.getCourseCode()) {
                i.setFinalExamDate(date);
            }
        }
    }
    public void addAssignment(Assignment newAssignment , Course course){
        for(Course i : courses){
            if(i.getCourseCode() == course.getCourseCode()){
                i.addAssignment(newAssignment);
            }
        }
    }
    public void removeAssignment(Assignment  removeAssignment , Course course){
        for(Course i : courses){
            if(i.getCourseCode() == course.getCourseCode()){
                i.removeAssignment(removeAssignment);
            }
        }
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", id=" + id +
                ", numberOfLessons=" + numberOfLessons +
                "\n courses=" + courses +
                '}';
    }
}
