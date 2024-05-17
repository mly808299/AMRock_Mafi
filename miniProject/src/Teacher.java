package miniProject.src;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Teacher {
    private static List<Teacher> allTeachers = new ArrayList<>();
    private String firstName;
    private String lastName;
    private String id;
    private int numberOfLessons;
    private List<Course> courses = new ArrayList<>();

    public Teacher(String firstName, String lastName, String id) throws DoublicateTeacherException {
        if (!allTeachers.isEmpty()) {
            for (Teacher i : allTeachers) {
                if (id.equals(i.id))
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
        return id.equals(teacher.id) && numberOfLessons == teacher.numberOfLessons && Objects.equals(firstName, teacher.firstName) && Objects.equals(lastName, teacher.lastName) && Objects.equals(courses, teacher.courses) && Objects.equals(id, teacher.lastName);
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
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", id=" + id +
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

    public String getId() {
        return this.id;
    }

    public List<Course> getCourses() {
        return courses;
    }
}