package miniProject.src;

import java.util.*;
import java.util.List;

class DateDeadLine {
    int day;
    int month;
    int year;

    public DateDeadLine(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    @Override
    public String toString() {
        return day +
                "/" + month +
                "/" + year;
    }
}

public class Course {
    private static List<Course> allCourses = new ArrayList<>();
    private String courseName;
    private Teacher teacher;
    private int courseCode;
    private List<Student> registeredStudents = new ArrayList<>();
    private int numberOfStudents;
    private boolean isActive;
    private List<Assignment> assignments = new ArrayList<>();
    private int assignmentsNumber;
    private int unit;
    private DateDeadLine finalExamDate;

    public int getUnit() {
        return unit;
    }

    public Course(String courseName, int unit, boolean isActive, int courseCode) throws DoublicateCourseException {
        if (!allCourses.isEmpty()) {
            for (Course i : allCourses) {
                if (i.courseName.equals(courseName) && i.unit == unit && i.isActive == isActive && i.getCourseCode() == courseCode)
                    throw new DoublicateCourseException();
            }
        }
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.unit = unit;
        setActive(isActive);
        allCourses.add(this);
    }

    public Course(String courseName, Teacher teacher, int unit, boolean isActive, int courseCode) throws DoublicateCourseException {
        this(courseName, unit, isActive, courseCode);
        setTeacher(teacher);
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
        for (Teacher i : Teacher.getAllTeachers()) {
            if (i.getId().equals(teacher.getId())) {
                teacher.addCourseOfTeacher(this);
            }
        }
    }

    public void setFinalExamDate(DateDeadLine finalExamDate) {
        this.finalExamDate = finalExamDate;
    }

    public DateDeadLine getFinalExamDate() {
        return finalExamDate;
    }

    public void addRegisteredStudents(Student newStudent) throws InactiveCourseException {
        if (!isActive)
            throw new InactiveCourseException();
        registeredStudents.add(newStudent);
        numberOfStudents++;
        for (Student i : Student.getAllStudents()) {
            if (i.getId().equals(newStudent.getId())) {
                i.addCourses(this, i.getNumberOfCurrentSemester());
            }
        }
    }

    public void removeRegisteredStudent(Student removeStudent) throws InactiveCourseException, NotFindCourseOfSemester {
        if (!isActive)
            throw new InactiveCourseException();
        registeredStudents.remove(removeStudent);
        numberOfStudents--;
        removeStudent.removeCurses(this, removeStudent.getNumberOfCurrentSemester());
    }

    public String registeredStudentsToString() throws InactiveCourseException {
        String studentsStrnig = "";
        if (!isActive)
            throw new InactiveCourseException();
        for (Student i : registeredStudents) {
            studentsStrnig += i.studentToString();
        }
        return studentsStrnig;
    }

    public void printRegisteredStudents() throws InactiveCourseException {
        System.out.println(registeredStudentsToString());
    }

    public String firstStudentByScoreToString() throws NotFindCurrentCourseException {
        Comparator<Student> comparator = new Comparator<>() {
            @Override
            public int compare(Student o1, Student o2) {
                try {
                    if (o1.getCourseScore(Course.this) == null) {
                        return -1;
                    } else if (o2.getCourseScore(Course.this) == null) {
                        return 1;
                    }
                    return o1.getCourseScore(Course.this).compareTo(o2.getCourseScore(Course.this));
                } catch (NotFindCurrentCourseException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        String topStudent = "";
        registeredStudents.sort(comparator);
        for (Student i : registeredStudents) {
            if (Objects.equals(i.getCourseScore(this), registeredStudents.getLast().getCourseScore(this))) {
                topStudent += i.studentToString();
            }
        }
        return topStudent;
    }

    public void printFirstStudentByScore() throws NotFindCurrentCourseException {
        System.out.println(firstStudentByScoreToString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return numberOfStudents == course.numberOfStudents && isActive == course.isActive && assignmentsNumber == course.assignmentsNumber && unit == course.unit && Objects.equals(courseName, course.courseName) && Objects.equals(teacher, course.teacher) && Objects.equals(registeredStudents, course.registeredStudents) && Objects.equals(assignments, course.assignments) && Objects.equals(finalExamDate, course.finalExamDate);
    }

    public List<Student> getRegisteredStudents() {
        return registeredStudents;
    }

    public int getCourseCode() {
        return courseCode;
    }

    @Override
    public String toString() {
        return "courseName='" + courseName + '\'' +
                ", courseCode=" + courseCode +
                ", isActive=" + isActive +
                ", finalExamDate=" + finalExamDate +
                ", units=" + unit;
    }

    public void addAssignment(Assignment newassignment) {
        assignments.add(newassignment);
        assignmentsNumber++;
    }

    public void removeAssignment(Assignment removeAssignment) {
        assignments.remove(removeAssignment);
        assignmentsNumber--;
    }

    public String assignmentsToString() {
        String assignmentString = "";
        for (Assignment i : assignments) {
            assignmentString += i.toString() + "\n";
        }
        return assignmentString;
    }

    public int getAssignmentsNumber() {
        return assignmentsNumber;
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    public void setActive(boolean active) {
        if (!active) {
            numberOfStudents = 0;
            registeredStudents.clear();
        }
        isActive = active;
    }
}
