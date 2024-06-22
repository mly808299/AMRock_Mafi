import java.io.*;
import java.util.*;

public class Admin extends Person {
    private static List<Admin> allAdmins = new ArrayList<>();

    public static void loadAllAdmins() {
        try (Scanner database = new Scanner(new File("adminDatabase.txt"))) {
            while (database.hasNextLine()) {
                String line = database.nextLine();
                String[] parts = line.split("\\^");
                allAdmins.add(new Admin(parts[0], parts[1], parts[3], parts[4], parts[2]));
            }
        } catch (DoublicateException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Admin(String firstName, String lastName, String username, String password, String id) throws DoublicateException, IOException {
        super(firstName, lastName, username, password, id, allAdmins);
    }

    public Admin() {
    }

    public void addTeacherToCourse(Teacher teacher, Course course) throws InactiveCourseException {
        course.setTeacher(teacher);
    }

    public void removeTeacherFormCourse(Teacher teacher, Course course) throws InactiveCourseException {
        course.removeTeacher(teacher);
    }

    public void removeFromDatabase(Object id, Object objectType) throws DoublicateException, IOException, CourseISEmptyException, NotFindCourseOfSemester, AssignmentIsEmptyException, NotFindAssignmentException, NotFindStudentIdException, StudentIsEmptyException, TeacherIsEmptyException, NotFindTeacherException, InactiveCourseException {
        if (objectType instanceof Student) {
            String studentId = (String) id;
            Student.findById(studentId);
            for (int i = Course.getAllCourses().size()-1 ; i>=0 ; i--) {
                for (Student j : Course.getAllCourses().get(i).getRegisteredStudents()) {
                    if (j.getId().equals(studentId)) {
                        Course.getAllCourses().remove(i);
                    }
                }
            }
            Student.getAllStudents().removeIf(i -> i.getId().equals(studentId));
            Student.saveToDatabaseObject();
        } else if (objectType instanceof Teacher) {
            String teacherId = (String) id;
            Teacher.findById(teacherId);
            for (Course i : Course.getAllCourses()) {
                if (i.getTeacher() != null) {
                    if (i.getTeacher().getId().equals(teacherId)) {
                        i.removeTeacher(Teacher.findById(teacherId));
                    }
                }
            }
            Teacher.getAllTeachers().removeIf(i -> i.getId().equals(teacherId));
            Teacher.teacherUpdateDataBase();
        } else if (objectType instanceof Course) {
            Integer courseCode = (Integer) id;
            for (Student i : Student.getAllStudents()) {
                i.removeCurses(Course.findById(courseCode), i.getNumberOfCurrentSemester());
            }
            for (Teacher i : Teacher.getAllTeachers()) {
                i.removeCurseOfTeacher(Course.findById(courseCode));
            }
            Course.getAllCourses().removeIf(i -> i.getCourseCode().equals(courseCode));
            Course.saveToDatabaseObject();
        } else if (objectType instanceof Assignment) {
            String idAssignment = (String) id;
            Assignment.findById(idAssignment);
            for (Course i : Course.getAllCourses()) {
                for (Assignment j : i.getAssignments()) {
                    if (j.getId().equals(id)) {
                        i.removeAssignment(Assignment.findById(idAssignment));
                    }
                }
            }
            Assignment.getAllAssignments().removeIf(i -> i.getId().equals(idAssignment));
            Assignment.saveToDatabaseObject();
        }
    }

    public void addStudentToCourse(Student newStudent, Course thisCourse) throws InactiveCourseException, InvalidCourseException, NotFindCourseOfSemester {
        for (Course i : Course.getAllCourses()) {
            if (i.getCourseCode() == thisCourse.getCourseCode()) {
                i.addRegisteredStudents(newStudent);
                return;
            }
        }
        throw new InvalidCourseException();
    }

    public void removeStudentFromCourse(Student newStudent, Course thisCourse) throws InactiveCourseException, InvalidCourseException, NotFindCourseOfSemester {
        for (Course i : Course.getAllCourses()) {
            if (i.getCourseCode() == thisCourse.getCourseCode()) {
                i.removeRegisteredStudent(newStudent);
                return;
            }
        }
        throw new InvalidCourseException();
    }

    public void setExamCourse(int day, int month, int year, Course course) {
        DateDeadLine date = new DateDeadLine(day, month, year);
        for (Course i : Course.getAllCourses()) {
            if (course.getCourseCode() == i.getCourseCode()) {
                i.setFinalExamDate(date);
            }
        }
    }

    public void addAssignment(Assignment newAssignment, Course course) throws InactiveCourseException {
        for (Course i : Course.getAllCourses()) {
            if (i.getCourseCode() == course.getCourseCode()) {
                i.addAssignment(newAssignment);
            }
        }
    }

    public void removeAssignment(Assignment removeAssignment, Course course) throws InactiveCourseException {
        for (Course i : Course.getAllCourses()) {
            if (i.getCourseCode() == course.getCourseCode()) {
                i.removeAssignment(removeAssignment);
            }
        }
    }

    public void updateCourse(Course course, boolean active) {
        course.setActive(active);
    }

    public void updateAssignmentActive(Assignment assignment, boolean active) {
        assignment.updateIsActive(active);
    }

    public void updateAssignmentDeadLine(Assignment assignment, int dateDeadLine) {
        assignment.updateDeadLine(dateDeadLine);
    }

    public static List<Admin> getAllAdmins() {
        return allAdmins;
    }
    public void setScoreForStudent(Double newScore, String studentId, Course course) throws NotFindCurrentCourseException, NotFindCourseOfSemester, NotFindStudentIdException, ThisCourseDoseNotHaveAnyTeacherException, StudentIsEmptyException, NotFindStudentInCourseException {
        Teacher teacher = course.getTeacher();
        if(teacher == null){
            throw new ThisCourseDoseNotHaveAnyTeacherException();
        }
        Student student = Student.findById(studentId);
        if(!course.getRegisteredStudents().contains(student)){
            throw new NotFindStudentInCourseException();
        }
        teacher.setScore(newScore , studentId , course);
    }
}

