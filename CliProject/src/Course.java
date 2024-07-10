import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

class DateDeadLine implements Serializable {
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

public class Course implements Serializable {
    private static List<Course> allCourses = new ArrayList<>();
    private String topStudentOfWeek;
    private String courseName;
    private Teacher teacher;
    private Integer courseCode;
    private List<Student> registeredStudents = new ArrayList<>();
    private int numberOfStudents;
    private boolean isActive;
    private List<Assignment> assignments = new ArrayList<>();
    private int assignmentsNumber;
    private int unit;
    private String finalExamDate;
    private int numberOfRemainingAssignments = 0;

    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("courseName", courseName);
        Gson gson = new Gson();
        jsonObject.addProperty("teacherName", teacher == null ? " " : teacher.getFirstName() + " " + teacher.getLastName());
        jsonObject.addProperty("numberOfStudents", numberOfStudents);
        jsonObject.addProperty("assignmentsNumber", assignmentsNumber);
        jsonObject.addProperty("unit", unit);
        jsonObject.addProperty("topStudentOfWeek", topStudentOfWeek);
        jsonObject.addProperty("numberOfRemainingAssignments", numberOfRemainingAssignments);
        jsonObject.addProperty("finalExamDate", finalExamDate);
        return jsonObject;
    }

    public void setTopStudentOfWeek() throws NotFindCurrentCourseException {
        this.topStudentOfWeek = firstStudentByScoreToString();
    }

    public static void loadAllCourse() {
        allCourses.clear();
        File file = new File("courseDatabaseObjects.ser");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try (FileInputStream fileInputStream = new FileInputStream("courseDatabaseObjects.ser");
                 ObjectInputStream objectReader = new ObjectInputStream(fileInputStream)) {
                while (fileInputStream.available() > 0) {
                    Course course = (Course) objectReader.readObject();
                    allCourses.add(course);
                }
            } catch (IOException e) {
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void saveToDatabaseObject() {
        try (FileOutputStream databaseObjects = new FileOutputStream(new File("courseDatabaseObjects.ser"));
             ObjectOutputStream teacherDatabaseObjects = new ObjectOutputStream(databaseObjects)) {
            try {
                if (allCourses != null) {
                    for (Course i : allCourses) {
                        if (i.getAssignments() != null) {
                            List<Assignment> newAssignments = new ArrayList<>();
                            for (Assignment k : i.getAssignments()) {
                                newAssignments.add(Assignment.findById(k.getId()));
                            }
                            i.setAssignments(newAssignments);
                        }
//                        if (i.getRegisteredStudents() != null) {
//                            for (Student k : i.getRegisteredStudents()) {
//                                k = Student.findById(k.getId());
//                            }
//                        }
                    }
                }
            } catch (AssignmentIsEmptyException | NotFindAssignmentException e) {
            }
            for (Course course : allCourses) {
                teacherDatabaseObjects.writeObject(course);
                teacherDatabaseObjects.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int getUnit() {
        return unit;
    }

    public Course(String courseName, int unit, boolean isActive, int courseCode, String finalExamDate) throws DoublicateCourseException, NotFindCurrentCourseException {
        if (!allCourses.isEmpty()) {
            for (Course i : allCourses) {
                if (i.courseName.equals(courseName) && i.unit == unit && i.isActive == isActive && i.getCourseCode() == courseCode)
                    throw new DoublicateCourseException();
            }
        }
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.unit = unit;
        this.finalExamDate = finalExamDate;
        setActive(isActive);
        allCourses.add(this);
        saveToDatabaseObject();
    }

    public Course(String courseName, Teacher teacher, int unit, boolean isActive, int courseCode, String finalExamDate) throws DoublicateCourseException, InactiveCourseException, NotFindCurrentCourseException {
        this(courseName, unit, isActive, courseCode, finalExamDate);
        setTeacher(teacher);

    }

    public void setTeacher(Teacher teacher) throws InactiveCourseException {
        if (!isActive)
            throw new InactiveCourseException();
        this.teacher = teacher;
        for (Teacher i : Teacher.getAllTeachers()) {
            if (i.getId().equals(teacher.getId())) {
                teacher.addCourseOfTeacher(this);
            }
        }
    }

    public void removeTeacher(Teacher teacher) throws InactiveCourseException {
        if (!isActive)
            throw new InactiveCourseException();
        this.teacher = null;
        for (int i = Teacher.getAllTeachers().size() - 1; i >= 0; i--) {
            if (Teacher.getAllTeachers().get(i).getId().equals(teacher.getId())) {
                teacher.removeCurseOfTeacher(this);
            }
        }
    }

    public void setFinalExamDate(String finalExamDate) {
        this.finalExamDate = finalExamDate;
    }

    public String getFinalExamDate() {
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
        assert registeredStudents != null;
        registeredStudents.sort(comparator);
        topStudent = registeredStudents.getFirst().getFirstName() + " " + registeredStudents.getFirst().getLastName();
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
        return courseCode.equals(course.getCourseCode());
    }

    public List<Student> getRegisteredStudents() {
        return registeredStudents;
    }

    public Integer getCourseCode() {
        return courseCode;
    }

    public Course() throws NotFindCurrentCourseException {
    }

    @Override
    public String toString() {
        if (teacher == null) {
            return "Course{" +
                    "courseName='" + courseName + '\'' +
                    ", courseCode=" + courseCode +
                    ", isActive=" + isActive +
                    ", unit=" + unit +
                    ", finalExamDate=" + finalExamDate +
                    ", teacherName=" + null +
                    '}';
        }
        return "Course{" +
                "courseName='" + courseName + '\'' +
                ", courseCode=" + courseCode +
                ", isActive=" + isActive +
                ", unit=" + unit +
                ", finalExamDate=" + finalExamDate +
                ", teacherName=" + teacher.getFirstName() + " " + teacher.getLastName() + ", teacher id=" + teacher.getId() +
                '}';
    }

    public void addAssignment(Assignment newassignment) throws InactiveCourseException {
        if (!isActive)
            throw new InactiveCourseException();
        assignments.add(newassignment);
        assignmentsNumber++;
    }

    public void removeAssignment(Assignment removeAssignment) throws InactiveCourseException {
        if (!isActive)
            throw new InactiveCourseException();
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

    public static List<Course> getAllCourses() {
        return allCourses;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public static Course findById(Integer id) throws CourseISEmptyException, NotFindCourseOfSemester {
        if (Course.getAllCourses().isEmpty()) {
            throw new CourseISEmptyException();
        } else {
            for (Course i : Course.getAllCourses()) {
                if (i.getCourseCode().equals(id)) {
                    return i;
                }
            }
            throw new NotFindCourseOfSemester();
        }
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public static void setAllCourses(List<Course> allCourses) {
        Course.allCourses = allCourses;
    }

    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }

    public void setNumberOfRemainingAssignments() {
        int sum = 0;
        for (Assignment i : assignments) {
            if (i.getActive() && !i.getDone()) {
                sum++;
            }
        }
        numberOfRemainingAssignments = sum;
    }

}
