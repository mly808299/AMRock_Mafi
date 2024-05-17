package miniProject.src;

import java.util.ArrayList;
import java.util.List;

public class Admin extends Person {
    private static List<Admin > allAdmins = new ArrayList<>();

    public Admin(String firstName, String lastName, String username, String password, String id) throws DoublicateException {
        super(firstName,  lastName,  username,  password,  id , allAdmins);
        allAdmins.add(this);
    }
    public void addTeacher(Teacher teacher , Course course){
        course.setTeacher(teacher);
    }
    public void removeTeacher(Teacher teacher , Course course){
        course.removeTeacher(teacher);
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

    public void removeStudentFromCurse(Student newStudent, Course thisCourse) throws InactiveCourseException, InvalidCourseException, NotFindCourseOfSemester {
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
    public void addAssignment(Assignment newAssignment , Course course){
        for(Course i : Course.getAllCourses()){
            if(i.getCourseCode() == course.getCourseCode()){
                i.addAssignment(newAssignment);
            }
        }
    }
    public void removeAssignment(Assignment  removeAssignment , Course course){
        for(Course i : Course.getAllCourses()){
            if(i.getCourseCode() == course.getCourseCode()){
                i.removeAssignment(removeAssignment);
            }
        }
    }
    public void updateCourse(Course course , boolean active){
        course.setActive(active);
    }
    public void updateAssignmentActive(Assignment  assignment , boolean active){
        assignment.updateIsActive(active);
    }
    public void updateAssignmentDeadLine(Assignment  assignment , int dateDeadLine){
        assignment.updateDeadLine(dateDeadLine);
    }
}

