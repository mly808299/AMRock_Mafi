package CliProject.src;

import java.io.*;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

public class Teacher extends Person
        implements Serializable {
    private static List<Teacher> allTeachers = new ArrayList<>();
    private int numberOfLessons;
    private List<Course> courses = new ArrayList<>();

    public static void loadAllTeachers() {
        File file = new File("teacherDatabaseObjects.ser");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try (FileInputStream fileInputStream = new FileInputStream("teacherDatabaseObjects.ser");
                 ObjectInputStream objectReader = new ObjectInputStream(fileInputStream)) {
                while (fileInputStream.available() > 0) {
                    Teacher teacher = (Teacher) objectReader.readObject();
                    allTeachers.add(teacher);
                }
            } catch (IOException e) {
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Teacher(String firstName, String lastName, String username, String password, String id) throws DoublicateException, IOException {
        super(firstName, lastName, username, password, id, allTeachers);
        allTeachers.add(this);
        teacherUpdateDataBase();

    }

    private static void teacherTxtDataBase() throws IOException {
        try (Formatter teacherDatabase = new Formatter(new FileWriter("teacherDatabase.txt"))) {
            for (Teacher teacher : allTeachers) {
                teacherDatabase.format(teacher.getFirstName() + "^" + teacher.getLastName() + "^" + teacher.getId() + "^" + teacher.getUsername() + "^" + teacher.getPassword() + "\n");
                teacherDatabase.flush();
            }
        }
    }

    public static void teacherUpdateDataBase() throws DoublicateException, IOException {
        teacherTxtDataBase();
        saveToDatabaseObject();
    }

    private static void saveToDatabaseObject() {
        try (FileOutputStream databaseObjects = new FileOutputStream(new File("teacherDatabaseObjects.ser"));
             ObjectOutputStream teacherDatabaseObjects = new ObjectOutputStream(databaseObjects)) {
            for (Teacher teacher : allTeachers) {
                teacherDatabaseObjects.writeObject(teacher);
                teacherDatabaseObjects.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Teacher() {
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
            if (i.getCourseCode().equals(course.getCourseCode())) {
                for (Student j : i.getRegisteredStudents()) {
                    if (j.getId().equals(studentId)) {
                        for (Integer p : j.getCurrentSemesters().getScores().keySet()) {
                            if (p.equals(course.getCourseCode())) {
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
        return getId().equals(teacher.getId());
    }

    public void setExamCourse(int day, int month, int year, Course course) {
        DateDeadLine date = new DateDeadLine(day, month, year);
        var trueCourse = getCourse(course);
        if (trueCourse != null) {
            trueCourse.setFinalExamDate(date);
        }
    }

    public void addAssignment(Assignment newAssignment, Course course) throws InactiveCourseException {
        var trueCourse = getCourse(course);
        if (trueCourse != null) {
            trueCourse.addAssignment(newAssignment);
        }
    }

    public void removeAssignment(Assignment removeAssignment, Course course) throws InactiveCourseException {
        var trueCourse = getCourse(course);
        if (trueCourse != null) {
            trueCourse.removeAssignment(removeAssignment);
        }
    }


    @Override
    public String toString() {
        return "Teacher{" +
                "firstName='" + this.getFirstName() +
                ", lastName='" + this.getLastName() +
                ", id=" + this.getId() +
                ", numberOfLessons=" + numberOfLessons +
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
    public static Teacher findById(String id) throws NotFindTeacherException, TeacherIsEmptyException {
        if (Teacher.getAllTeachers().isEmpty()) {
            throw new TeacherIsEmptyException();
        } else {
            for (Teacher i : Teacher.getAllTeachers()) {
                if (i.getId().equals(id)) {
                    return i;
                }
            }
            throw new NotFindTeacherException();
        }
    }
    public void updateAssignmentActive(Assignment assignment, boolean active) {
        assignment.updateIsActive(active);
    }

    public void updateAssignmentDeadLine(Assignment assignment, int dateDeadLine) {
        assignment.updateDeadLine(dateDeadLine);
    }
    public void assignmentRemoveFromDatabase(Object id) throws DoublicateException, IOException, CourseISEmptyException, NotFindCourseOfSemester, AssignmentIsEmptyException, NotFindAssignmentException, NotFindStudentIdException, StudentIsEmptyException, TeacherIsEmptyException, NotFindTeacherException, InactiveCourseException {
            String idAssignment1 = (String) id;
            Assignment.findById(idAssignment1);
            for (Course i : Course.getAllCourses()) {
                for (Assignment j : i.getAssignments()) {
                    if (j.getId().equals(id)) {
                        i.removeAssignment(Assignment.findById(idAssignment1));
                    }
                }
            }
            Assignment.getAllAssignments().removeIf(i -> i.getId().equals(idAssignment1));
            Assignment.saveToDatabaseObject();

    }

    public static void setAllTeachers(List<Teacher> allTeachers) {
        Teacher.allTeachers = allTeachers;
    }
}