package miniProject.src;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Teacher extends Person{
    private static List<Teacher> allTeachers = new ArrayList<>();
    private int numberOfLessons;
    private List<Course> courses = new ArrayList<>();

    public Teacher(String firstName, String lastName, String username, String password, String id ) throws DoublicateException {
        super(firstName,  lastName,  username,  password,  id , allTeachers);
        allTeachers.add(this);
    }

    public void addCourseOfTeacher(Course newCourse) {
        courses.add(newCourse);
        numberOfLessons++;
    }

    public void removeCurseOfTeacher(Course removeCurse) {
        courses.remove(removeCurse);
        numberOfLessons--;
    }

    public void setScore(Double newScore, String studentId, Course course) throws NotFindCurrentCourseException, NotFindCourseOfSemester, NotFindStudentIdException {
        boolean foundCourse = false;
        boolean foundStudentId = false;
        for (Course i : courses) {
            if (i.getCourseCode() == course.getCourseCode()) {
                for (Student j : i.getRegisteredStudents()) {
                    if (j.getId().equals(studentId)) {
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

    public void addStudentToCourse(Student newStudent, Course course) throws InactiveCourseException, InvalidCourseException, NotFindCourseOfSemester {
        var trueCourse = getCourse(course);
        if (trueCourse != null) {
            trueCourse.addRegisteredStudents(newStudent);
        } else
            throw new InvalidCourseException();
    }

    public void removeStudentFromCourse(Student newStudent, Course course) throws InactiveCourseException, InvalidCourseException, NotFindCourseOfSemester {
        var trueCourse = getCourse(course);
        if (trueCourse != null) {
            trueCourse.removeRegisteredStudent(newStudent);
        } else
            throw new InvalidCourseException();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return getId().equals(teacher.getId()) && numberOfLessons == teacher.numberOfLessons && Objects.equals(getFirstName(), teacher.getFirstName()) && Objects.equals(getLastName(), teacher.getLastName()) && Objects.equals(courses, teacher.courses);
    }

    public void setExamCourse(int day, int month, int year, Course course) {
        DateDeadLine date = new DateDeadLine(day, month, year);
        var trueCourse = getCourse(course);
        if (trueCourse != null) {
            trueCourse.setFinalExamDate(date);
        }
    }

    public void addAssignment(Assignment newAssignment, Course course) {
        var trueCourse = getCourse(course);
        if (trueCourse != null) {
            trueCourse.addAssignment(newAssignment);
        }
    }

    public void removeAssignment(Assignment removeAssignment, Course course) {
        var trueCourse = getCourse(course);
        if (trueCourse != null) {
            trueCourse.removeAssignment(removeAssignment);
        }
    }

    @Override
    public String toString() {
        return "miniProject.Teacher{" +
                "firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", id=" + getId() +
                ", numberOfLessons=" + numberOfLessons +
                "\n courses=" + courses +
                '}';
    }

    private Course getCourse(Course course) {
        for (Course i : courses) {
            if (i.getCourseCode() == course.getCourseCode()) {
                return i;
            }
        }
        return null;
    }

    public static List<Teacher> getAllTeachers() {
        return allTeachers;
    }
    public List<Course> getCourses() {
        return courses;
    }
}