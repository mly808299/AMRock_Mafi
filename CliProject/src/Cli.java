import java.io.*;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Cli {
    public static String userid;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";

    //        System.out.println(ANSI_RED + "safdlkf " + ANSI_RESET);
//        System.out.println(ANSI_GREEN + "sddsf " + ANSI_RESET);
//        System.out.println(ANSI_YELLOW + "dfdsff " + ANSI_RESET);
//        System.out.println(ANSI_BLUE + "adfdf " + ANSI_RESET);
//        System.out.println(ANSI_PURPLE + " sdfs" + ANSI_RESET);
//        System.out.println(ANSI_CYAN + " sdfs" + ANSI_RESET);
//        System.out.println(ANSI_WHITE + "white" + ANSI_RESET);
    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void main(String[] args) throws TooManyAttemptsException, IOException, DoublicateException, InvalidOptionException {
        clearConsole();
        System.out.println(ANSI_YELLOW + "\t\t(Hello you are in cli project)" + ANSI_RESET);
        Scanner choseOptions = new Scanner(System.in);
        int choice;
        for (int i = 0; i < 10; i++) {
            String start = ("""
                    if you have an account please login.
                                        
                    Please choose option 1 or 2.
                    \t1:Admin login
                    \t2:Teacher login
                                      
                    if you don't have any account please sign up.
                                        
                    Please choose option
                    \t3:Teacher signup
                                        
                    if you want to leave please enter 0.""");
            System.out.println(ANSI_CYAN + start + ANSI_RESET);
            choice = choseOptions.nextInt();
            clearConsole();
            switch (choice) {
                case 1:
                    adminLogin(choseOptions);
                    break;
                case 2:
                    teacherLogin(choseOptions);
                    break;
                case 3:
                    Teacher newTeacher = new Teacher();
                    newTeacher = (Teacher) signUpPerson(choseOptions, newTeacher);
                    afterSignUp(choseOptions, newTeacher);
                    break;
                case 0:
                    choseOptions.close();
                    System.exit(0);
                    break;
                default:
                    try {
                        throw new InvalidOptionException("please enter true number");
                    } catch (InvalidOptionException e) {
                        e.printStackTrace();
                    }
            }

        }
        throw new TooManyAttemptsException();
    }

    private static void afterSignUp(Scanner choseOptions, Person newperson) throws InvalidOptionException, TooManyAttemptsException, DoublicateException, IOException {
        System.out.println(ANSI_GREEN + "\t\tYou signed up successfully" + "\n your uniq id is : " + newperson.getId() + " please don not forget your id!!!\n" + ANSI_RESET);
        System.out.println(ANSI_RED + "!!!If you forget you password there is no other way to login!!!\n" + ANSI_RESET);
        while (true) {
            System.out.print(ANSI_CYAN + """
                    please chose option:
                    0 : Exit
                    1 : Login
                    2 : CreateAnotherAccount
                    3 : backToLoginPage
                    """ + ANSI_RESET);
            int choseOption = choseOptions.nextInt();
            if (choseOption == 1) {
                clearConsole();
                if (newperson instanceof Admin) {
                    adminLogin(choseOptions);
                } else if (newperson instanceof Teacher) {
                    teacherLogin(choseOptions);
                }
            } else if (choseOption == 0) {
                System.exit(0);
            } else if (choseOption == 2) {
                clearConsole();
                Teacher newTeacher = new Teacher();
                newTeacher = (Teacher) signUpPerson(choseOptions, newTeacher);
                afterSignUp(choseOptions, newTeacher);
            } else if(choseOption == 3) {
                    String [] args = new String[1];
                    args[0] = "Cli";
                    Teacher.setAllTeachers(new ArrayList<>());
                    Student.setAllStudents(new ArrayList<>());
                    Course.setAllCourses(new ArrayList<>());
                    Assignment.setAllAssignments(new ArrayList<>());
                    Cli.main(args);
            }else {
                throw new InvalidOptionException("please enter true number");
            }
        }
    }

    public static Person findObjectInDataBase(String id, Person person) throws FileNotFoundException {
        if (person instanceof Admin) {
            Admin.loadAllAdmins();
            for (Admin admin : Admin.getAllAdmins()) {
                if (admin.getId().equals(id)) {
                    return admin;
                }
            }
        }
        if (person instanceof Teacher) {
            Teacher.loadAllTeachers();
            for (Teacher i : Teacher.getAllTeachers()) {
                if (id.equals(i.getId())) {
                    return i;
                }
            }
        }
        return null;
    }

    public static boolean checkAccount(Scanner choseOptions, String databaseLocation) throws ThereIsNotAnyAccountException {
        System.out.println(ANSI_PURPLE + "please enter your id: " + ANSI_RESET);
        choseOptions.nextLine();
        String id = choseOptions.nextLine();
        userid = id;
        clearConsole();
        System.out.println(ANSI_PURPLE + "please enter your username: " + ANSI_RESET);
        String username = choseOptions.nextLine();
        clearConsole();
        System.out.println(ANSI_PURPLE + "please enter your password:" + ANSI_RESET);
        String password = choseOptions.nextLine();
        clearConsole();
        try (Scanner database = new Scanner(new File(databaseLocation))) {
            while (database.hasNextLine()) {
                String line = database.nextLine();
                String[] parts = line.split("\\^");
                if (parts[2].equals(id) && parts[3].equals(username) && parts[4].equals(password)) {
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            throw new ThereIsNotAnyAccountException("Account not found");
        }
        return false;
    }

    private static Person signUpPerson(Scanner choseOptions, Person newPerson) throws TooManyAttemptsException, DoublicateException, IOException {
        boolean isValid = false;
        System.out.println(ANSI_PURPLE + "please enter your firstName:");
        choseOptions.nextLine();
        String firstName = choseOptions.nextLine();
        clearConsole();
        System.out.println("please enter your lastName:");
        String lastName = choseOptions.nextLine();
        clearConsole();
        String id = String.valueOf(ThreadLocalRandom.current().nextInt(100000, 200000));
        System.out.println("please enter your userName:");
        String username = choseOptions.nextLine();
        clearConsole();
        int numberOfAttempts = 0;
        String password = new String();
        while (!isValid) {
            if (numberOfAttempts == 5) {
                throw new TooManyAttemptsException();
            }
            numberOfAttempts++;
            System.out.println(ANSI_PURPLE + "please enter your password:" + ANSI_RESET);
            password = choseOptions.nextLine();
            isValid = isValidPassword(password, username);
            clearConsole();
            if (!isValid) {
                String inValidPassword = """
                        invalid password
                        The password must contain at least 8 letters and contain at least one uppercase letter,\s
                        one lowercase letter and one number, and it should not contain exactly the word username.
                        please wait to enter the password again...\s""";

                System.out.print(ANSI_RED + inValidPassword + ANSI_RESET);
                try {
                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                clearConsole();
            }

        }
        if (newPerson instanceof Teacher) {
            Teacher.loadAllTeachers();
            userid = id;
            return new Teacher(firstName, lastName, username, password, id);
        }
        return null;
    }

    public static boolean isValidPassword(String password, String specialWord) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?!.*" + specialWord + ").{8,}$";
        return password.matches(regex);
    }

    private static void adminLogin(Scanner choseOptions) {
        try {
            if (checkAccount(choseOptions, "adminDatabase.txt")) {
                Admin loginAdmin = new Admin();
                loginAdmin = (Admin) findObjectInDataBase(userid, loginAdmin);
                clearConsole();
                System.out.println(ANSI_GREEN + "You are logged in as " + loginAdmin.getFirstName() + " " + loginAdmin.getLastName() + ANSI_RESET);
                Student.loadAllStudent();
                Teacher.loadAllTeachers();
                Course.loadAllCourse();
                Assignment.loadAllAssignments();
                loginPageForAdmin(loginAdmin, choseOptions);
            } else {
                throw new UserNotFoundException("userNotFound");
            }
        } catch (ThereIsNotAnyAccountException | UserNotFoundException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (TooManyAttemptsException | IOException | DoublicateException | InvalidCourseException |
                 InactiveCourseException | NotFindCourseOfSemester | DoublicateCourseException |
                 CourseISEmptyException | DoublicateAssignmentException | NotFindAssignmentException |
                 AssignmentIsEmptyException | InvalidOptionException e) {
            throw new RuntimeException(e);
        }
    }

    private static void loginPageForAdmin(Admin loginAdmin, Scanner choseOptions) throws TooManyAttemptsException, DoublicateException, IOException, NotFindCourseOfSemester, InactiveCourseException, InvalidCourseException, DoublicateCourseException, CourseISEmptyException, DoublicateAssignmentException, AssignmentIsEmptyException, NotFindAssignmentException, InvalidOptionException {
        while (true) {
            System.out.println(ANSI_PURPLE + "please chose one of the following options to do the function:" + ANSI_RESET);
            System.out.println(ANSI_BLUE + """
                    1: Show List Of allStudents
                    2: Show Student Courses
                    3: Show List Of allTeachers
                    4: Show List Of Course Assignments
                    5: Add New Course To Database
                    6: Remove Course From Database
                    7: Add New Teacher To Database
                    8: Remove Teacher From Database
                    9: Add New Student To Database
                    10: Remove Student From Database
                    11: add Teacher to Course
                    12: remove Teacher from Course
                    13: add Student to Course
                    14: remove Student from Course
                    15: set Exam Time Course
                    16: Active or deActive course
                    17: Active or deActive Assignment
                    18: Update Assignment Deadline
                    19: Show list of allCourses
                    20: new Assigment To Database
                    21: remove Assignment from Database
                    22: Show list of allAssignments
                    23: Show list of Teacher Courses
                    24: add assignment to Course
                    25: remove assignment from Course
                    26: set score for Student Course
                    27: show average of each Term of Student
                    28: show TotalAverage of Student
                    29: show top students of Course\s
                    30: backToLoginPage
                    0: Exit""" + ANSI_RESET);
            int choice = 0;
            try {
                choice = choseOptions.nextInt();
            } catch (Exception e) {
                new InvalidOptionException("invalidOption").printStackTrace();
            }
            clearConsole();
            try {
                switch (choice) {
                    case 1:
                        if (Student.getAllStudents().isEmpty()) {
                            System.out.println(ANSI_RED + "There are no students in the database" + ANSI_RESET);
                        } else {
                            for (Student student : Student.getAllStudents()) {
                                System.out.println(ANSI_PURPLE + student.toString() + ANSI_RESET);
                            }
                        }
                        try {
                            Thread.sleep(6000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        clearConsole();
                        break;
                    case 2:
                        choseOptions.nextLine();
                        System.out.println(ANSI_YELLOW + "please enter student id:" + ANSI_RESET);
                        String id = choseOptions.nextLine();
                        Student.findById(id);
                        for (Student student : Student.getAllStudents()) {
                            if (student.getId().equals(id)) {
                                System.out.println(ANSI_PURPLE + student.registeredCoursestoString() + ANSI_RESET);
                                break;
                            }
                        }
                        try {
                            Thread.sleep(6000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        clearConsole();
                        break;
                    case 3:
                        if (Teacher.getAllTeachers().isEmpty()) {
                            throw new TeacherIsEmptyException();
                        } else {
                            for (Teacher teacher : Teacher.getAllTeachers()) {
                                System.out.println(ANSI_PURPLE + teacher.toString() + ANSI_RESET);
                            }
                        }
                        try {
                            Thread.sleep(6000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        clearConsole();
                        break;
                    case 4:
                        choseOptions.nextLine();
                        System.out.println(ANSI_YELLOW + "please enter courseCode:" + ANSI_RESET);
                        String idForAssignment = choseOptions.nextLine();
                        clearConsole();
                        Course.findById(Integer.valueOf(idForAssignment));
                        for (Course course : Course.getAllCourses()) {
                            if (course.getCourseCode().equals(Integer.valueOf(idForAssignment))) {
                                if (course.getAssignments().isEmpty()) {
                                    throw new AssignmentIsEmptyException();
                                } else {
                                    System.out.print(ANSI_PURPLE + course.assignmentsToString() + ANSI_RESET);
                                    break;
                                }
                            }
                        }
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        clearConsole();
                        break;
                    case 5:
                        System.out.println(ANSI_PURPLE + "please enter courseName:" + ANSI_RESET);
                        choseOptions.nextLine();
                        String courseName = choseOptions.nextLine();
                        clearConsole();
                        System.out.println(ANSI_PURPLE + "please enter unitOfCourse(it is Integer):" + ANSI_RESET);
                        int unit = choseOptions.nextInt();
                        clearConsole();
                        System.out.println(ANSI_PURPLE + "please define that the course is active or not with true or false\ntrue:active\nfalse:inactive" + ANSI_RESET);
                        Boolean isActive = choseOptions.nextBoolean();
                        clearConsole();
                        Integer courseCode = ThreadLocalRandom.current().nextInt(100000, 200000);
                        System.out.println(ANSI_GREEN + "\t\tcourse created successfully" + "\n your uniq codeCourse is : " + courseCode + " please don not forget your id!!!\n" + ANSI_RESET);
                        new Course(courseName, unit, isActive, courseCode);
                        try {
                            Thread.sleep(6000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        clearConsole();
                        Course.saveToDatabaseObject();
                        Teacher.teacherUpdateDataBase();
                        Student.saveToDatabaseObject();
                        break;
                    case 6:
                        System.out.println(ANSI_PURPLE + "please enter courseCode:" + ANSI_RESET);
                        choseOptions.nextLine();
                        Integer courseCodeRemoval = choseOptions.nextInt();
                        clearConsole();
                        Course courseRemovalObj = new Course();
                        loginAdmin.removeFromDatabase(courseCodeRemoval, courseRemovalObj);
                        System.out.println(ANSI_GREEN + "Course removed successfully" + ANSI_RESET);
                        try {
                            Thread.sleep(4000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        clearConsole();
                        Course.saveToDatabaseObject();
                        Teacher.teacherUpdateDataBase();
                        Student.saveToDatabaseObject();
                        break;
                    case 7:
                        choseOptions.nextLine();
                        signUpTeacher(choseOptions);
                        System.out.println(ANSI_GREEN + "Teacher added successfully" + ANSI_RESET);
                        try {
                            Thread.sleep(4000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        clearConsole();
                        Course.saveToDatabaseObject();
                        Teacher.teacherUpdateDataBase();
                        Student.saveToDatabaseObject();
                        break;
                    case 8:
                        choseOptions.nextLine();
                        System.out.println(ANSI_PURPLE + "please enter teacherId:" + ANSI_RESET);
                        String teacherIdRemoval = choseOptions.nextLine();
                        clearConsole();
                        Teacher teacherRemovalObj = new Teacher();
                        loginAdmin.removeFromDatabase(teacherIdRemoval, teacherRemovalObj);
                        System.out.println(ANSI_GREEN + "Teacher removed successfully" + ANSI_RESET);
                        try {
                            Thread.sleep(4000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        clearConsole();
                        Course.saveToDatabaseObject();
                        Teacher.teacherUpdateDataBase();
                        Student.saveToDatabaseObject();
                        break;
                    case 9:
                        signUpStudent(choseOptions);
                        try {
                            Thread.sleep(4000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        clearConsole();
                        Course.saveToDatabaseObject();
                        Teacher.teacherUpdateDataBase();
                        Student.saveToDatabaseObject();
                        break;
                    case 10:
                        choseOptions.nextLine();
                        System.out.println(ANSI_PURPLE + "please enter studentId:" + ANSI_RESET);
                        String studentRemoval = choseOptions.nextLine();
                        clearConsole();
                        Student studentRemovalObj = new Student();
                        loginAdmin.removeFromDatabase(studentRemoval, studentRemovalObj);
                        System.out.println(ANSI_GREEN + "Student removed successfully" + ANSI_RESET);
                        try {
                            Thread.sleep(4000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        clearConsole();
                        Course.saveToDatabaseObject();
                        Teacher.teacherUpdateDataBase();
                        Student.saveToDatabaseObject();
                        break;
                    case 11:
                        choseOptions.nextLine();
                        Teacher teacherToCourse = new Teacher();
                        Course courseAddTeacher = new Course();
                        teacherToCourse = (Teacher) findById(teacherToCourse, choseOptions);
                        courseAddTeacher = (Course) findById(courseAddTeacher, choseOptions);
                        loginAdmin.addTeacherToCourse(teacherToCourse, courseAddTeacher);
                        System.out.println(ANSI_GREEN + "Teacher added successfully" + ANSI_RESET);
                        try {
                            Thread.sleep(4000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        clearConsole();
                        Course.saveToDatabaseObject();
                        Teacher.teacherUpdateDataBase();
                        Student.saveToDatabaseObject();
                        break;
                    case 12:
                        choseOptions.nextLine();
                        Teacher removalTeacher = new Teacher();
                        Course removalCourse = new Course();
                        removalTeacher = (Teacher) findById(removalTeacher, choseOptions);
                        removalCourse = (Course) findById(removalCourse, choseOptions);
                        loginAdmin.removeTeacherFormCourse(removalTeacher, removalCourse);
                        System.out.println(ANSI_GREEN + "Teacher removed successfully" + ANSI_RESET);
                        try {
                            Thread.sleep(4000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        clearConsole();
                        Course.saveToDatabaseObject();
                        Teacher.teacherUpdateDataBase();
                        Student.saveToDatabaseObject();
                        break;
                    case 13:
                        choseOptions.nextLine();
                        Student student = new Student();
                        Course course = new Course();
                        student = (Student) findById(student, choseOptions);
                        course = (Course) findById(course, choseOptions);
                        loginAdmin.addStudentToCourse(student, course);
                        System.out.println(ANSI_GREEN + "Student added successfully" + ANSI_RESET);
                        try {
                            Thread.sleep(4000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        clearConsole();
                        Course.saveToDatabaseObject();
                        Teacher.teacherUpdateDataBase();
                        Student.saveToDatabaseObject();
                        break;
                    case 14:
                        choseOptions.nextLine();
                        Student removalStudent = new Student();
                        Course removalCourse2 = new Course();
                        removalStudent = (Student) findById(removalStudent, choseOptions);
                        removalCourse2 = (Course) findById(removalCourse2, choseOptions);
                        loginAdmin.removeStudentFromCourse(removalStudent, removalCourse2);
                        System.out.println(ANSI_GREEN + "Student removed successfully" + ANSI_RESET);
                        try {
                            Thread.sleep(4000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        clearConsole();
                        Course.saveToDatabaseObject();
                        Teacher.teacherUpdateDataBase();
                        Student.saveToDatabaseObject();
                        break;
                    case 15:
                        choseOptions.nextLine();
                        Course setExamCourse = new Course();
                        setExamCourse = (Course) findById(setExamCourse, choseOptions);
                        System.out.println(ANSI_PURPLE + "please enter the day of exam in form of Integer:" + ANSI_RESET);
                        Integer dayOfExam = choseOptions.nextInt();
                        System.out.println(ANSI_PURPLE + "please enter the month of exam in form of Integer:" + ANSI_RESET);
                        Integer monthOfExam = choseOptions.nextInt();
                        System.out.println(ANSI_PURPLE + "please enter the year of exam int form of Integer:" + ANSI_RESET);
                        Integer yearOfExam = choseOptions.nextInt();
                        loginAdmin.setExamCourse(dayOfExam, monthOfExam, yearOfExam, setExamCourse);
                        System.out.println(ANSI_GREEN + "Exam set successfully" + ANSI_RESET);
                        try {
                            Thread.sleep(4000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        clearConsole();
                        Course.saveToDatabaseObject();
                        Teacher.teacherUpdateDataBase();
                        Student.saveToDatabaseObject();
                        break;
                    case 16:
                        choseOptions.nextLine();
                        Course availableCourse = new Course();
                        availableCourse = (Course) findById(availableCourse, choseOptions);
                        System.out.println(ANSI_PURPLE + """
                                please chose Course is active or not...
                                true: active course
                                false: inactive course""" + ANSI_RESET);
                        boolean active = choseOptions.nextBoolean();
                        loginAdmin.updateCourse(availableCourse, active);
                        System.out.println(ANSI_GREEN + "Course update successfully" + ANSI_RESET);
                        try {
                            Thread.sleep(4000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        clearConsole();
                        Course.saveToDatabaseObject();
                        Teacher.teacherUpdateDataBase();
                        Student.saveToDatabaseObject();
                        break;
                    case 17:
                        choseOptions.nextLine();
                        Assignment assignment1 = new Assignment();
                        System.out.println(ANSI_PURPLE + "please enter assignmentId:" + ANSI_RESET);
                        String assignmentId1 = choseOptions.nextLine();
                        assignment1 = Assignment.findById(assignmentId1);
                        System.out.println(ANSI_PURPLE + """
                                please chose Course is active or not...
                                true: active course
                                false: inactive course""" + ANSI_RESET);
                        boolean activeAssignment = choseOptions.nextBoolean();
                        loginAdmin.updateAssignmentActive(assignment1, activeAssignment);
                        System.out.println(ANSI_GREEN + "Assignment update successfully" + ANSI_RESET);
                        try {
                            Thread.sleep(4000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        clearConsole();
                        Course.saveToDatabaseObject();
                        Teacher.teacherUpdateDataBase();
                        Student.saveToDatabaseObject();
                        break;
                    case 18:
                        choseOptions.nextLine();
                        Assignment assignment2 = new Assignment();
                        System.out.println(ANSI_PURPLE + "please enter assignmentId:" + ANSI_RESET);
                        String assignmentId2 = choseOptions.nextLine();
                        clearConsole();
                        assignment2 = Assignment.findById(assignmentId2);
                        System.out.println(ANSI_PURPLE + "please enter deadline(please enter number of days that remain to finish the time of assignment):" + ANSI_RESET);
                        Integer deadlineAssignment = choseOptions.nextInt();
                        loginAdmin.updateAssignmentDeadLine(assignment2, deadlineAssignment);
                        System.out.println(ANSI_GREEN + "Deadline update successfully" + ANSI_RESET);
                        try {
                            Thread.sleep(4000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        clearConsole();
                        Course.saveToDatabaseObject();
                        Teacher.teacherUpdateDataBase();
                        Student.saveToDatabaseObject();
                        break;
                    case 19:
                        choseOptions.nextLine();
                        if (Course.getAllCourses().isEmpty()) {
                            throw new CourseISEmptyException();
                        } else {

                            for (Course f : Course.getAllCourses()) {
                                System.out.println(ANSI_CYAN + f.toString() + ANSI_RESET);
                            }
                        }
                        try {
                            Thread.sleep(4000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        clearConsole();
                        break;
                    case 20:
                        choseOptions.nextLine();
                        System.out.println(ANSI_PURPLE + "please enter assignmentName:" + ANSI_RESET);
                        String assignmentName = choseOptions.nextLine();
                        System.out.println(ANSI_PURPLE + "please define assignmentType:\n1:Project\n2:practice" + ANSI_RESET);
                        Integer option = choseOptions.nextInt();
                        AssignmentType assignmentType = null;
                        switch (option) {
                            case 1:
                                assignmentType = AssignmentType.project;
                                break;
                            case 2:
                                assignmentType = AssignmentType.practice;
                                break;
                        }
                        System.out.println(ANSI_PURPLE + "please define that the course is active or not with true or false\nactive:true\ninactive:false" + ANSI_RESET);
                        Boolean isActiveAssignment = choseOptions.nextBoolean();
                        String assignmentId = String.valueOf(ThreadLocalRandom.current().nextInt(100000, 200000));
                        System.out.println(ANSI_PURPLE + "please enter deadLine of assignment(please enter number of days that remain to finish the time of assignment):" + ANSI_RESET);
                        Integer deadline = choseOptions.nextInt();
                        System.out.println(ANSI_GREEN + "\t\tassignment created successfully" + "\n your uniq assignmentId is : " + assignmentId + " please don not forget your id!!!\n" + ANSI_RESET);
                        new Assignment(assignmentName, assignmentType, deadline, isActiveAssignment, assignmentId);
                        try {
                            Thread.sleep(4000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        clearConsole();
                        Course.saveToDatabaseObject();
                        Teacher.teacherUpdateDataBase();
                        Student.saveToDatabaseObject();
                        break;
                    case 21:
                        choseOptions.nextLine();
                        System.out.println(ANSI_PURPLE + "please enter assignmentId:" + ANSI_RESET);
                        String assignmentIdRemoval = choseOptions.nextLine();
                        Assignment assignment = new Assignment();
                        loginAdmin.removeFromDatabase(assignmentIdRemoval, assignment);
                        System.out.println(ANSI_GREEN + "Assignment removed successfully" + ANSI_RESET);
                        try {
                            Thread.sleep(4000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        clearConsole();
                        Course.saveToDatabaseObject();
                        Teacher.teacherUpdateDataBase();
                        Student.saveToDatabaseObject();
                        break;
                    case 22:
                        choseOptions.nextLine();
                        if (Assignment.getAllAssignments().isEmpty()) {
                            System.out.println(ANSI_RED + "There are no assignment in the database" + ANSI_RESET);
                        } else {
                            for (Assignment f : Assignment.getAllAssignments()) {
                                System.out.println(ANSI_CYAN + f.toString() + ANSI_RESET);
                            }
                        }
                        try {
                            Thread.sleep(4000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        clearConsole();
                        break;
                    case 23:
                        choseOptions.nextLine();
                        Teacher teacherId1 = new Teacher();
                        teacherId1 = (Teacher) findById(teacherId1, choseOptions);
                        for (Course i : teacherId1.getCourses()) {
                            System.out.println(ANSI_CYAN + i.toString() + ANSI_RESET);
                        }
                        try {
                            Thread.sleep(4000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        clearConsole();
                        break;
                    case 24:
                        choseOptions.nextLine();
                        Assignment removalAssignment = new Assignment();
                        Course removalCourse3 = new Course();
                        removalAssignment = (Assignment) findById(removalAssignment, choseOptions);
                        removalCourse3 = (Course) findById(removalCourse3, choseOptions);
                        loginAdmin.addAssignment(removalAssignment, removalCourse3);
                        System.out.println(ANSI_GREEN + "Assignment added successfully" + ANSI_RESET);
                        try {
                            Thread.sleep(4000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        clearConsole();
                        Course.saveToDatabaseObject();
                        Teacher.teacherUpdateDataBase();
                        Student.saveToDatabaseObject();
                        break;
                    case 25:
                        choseOptions.nextLine();
                        Assignment removalAssignment1 = new Assignment();
                        Course removalCourse4 = new Course();
                        removalAssignment = (Assignment) findById(removalAssignment1, choseOptions);
                        removalCourse4 = (Course) findById(removalCourse4, choseOptions);
                        loginAdmin.removeAssignment(removalAssignment, removalCourse4);
                        System.out.println(ANSI_GREEN + "Assignment removed successfully" + ANSI_RESET);
                        try {
                            Thread.sleep(4000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        clearConsole();
                        Course.saveToDatabaseObject();
                        Teacher.teacherUpdateDataBase();
                        Student.saveToDatabaseObject();
                        break;
                    case 26:
                        choseOptions.nextLine();
                        Course setScoreCourse = new Course();
                        Student setScoreStudent = new Student();
                        setScoreStudent = (Student) findById(setScoreStudent, choseOptions);
                        setScoreCourse = (Course) findById(setScoreCourse, choseOptions);
                        System.out.println(ANSI_YELLOW + "please enter student score:" + ANSI_RESET);
                        Double studentScore = choseOptions.nextDouble();
                        loginAdmin.setScoreForStudent(studentScore, setScoreStudent.getId(), setScoreCourse);
                        System.out.println(ANSI_GREEN + "score set successfully" + ANSI_RESET);
                        try {
                            Thread.sleep(4000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        clearConsole();
                        Student.saveToDatabaseObject();
                        Course.saveToDatabaseObject();
                        Teacher.teacherUpdateDataBase();
                        break;
                    case 27:
                        choseOptions.nextLine();
                        Student student1 = new Student();
                        student1 = (Student) findById(student1, choseOptions);
                        System.out.println(ANSI_GREEN + "Student average is:"+student1.registeredAveragetoDouble() + ANSI_RESET);
                        try {
                            Thread.sleep(4000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        clearConsole();
                        break;
                    case 28:
                        choseOptions.nextLine();
                        Student student2 = new Student();
                        student2 = (Student) findById(student2, choseOptions);
                        System.out.println(ANSI_GREEN +"Student total average is:"+ student2.totalAveragetoDouble() + ANSI_RESET);
                        try {
                            Thread.sleep(4000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        clearConsole();
                        break;
                    case 29:
                        choseOptions.nextLine();
                        Course course2 = new Course();
                        course2 = (Course) findById(course2, choseOptions);
                        System.out.println(ANSI_GREEN + course2.firstStudentByScoreToString() + ANSI_RESET);
                        try {
                            Thread.sleep(4000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        clearConsole();
                        break;
                    case 30:
                        String [] args = new String[1];
                        args[0] = "Cli";
                        Teacher.getAllTeachers().clear();
                        Student.getAllStudents().clear();
                        Course.getAllCourses().clear();
                        Assignment.getAllAssignments().clear();
                        Admin.getAllAdmins().clear();
                        Cli.main(args);

                        break;
                    case 0:
                        choseOptions.close();
                        System.exit(0);
                        break;
                    default:
                        try {
                            throw new InvalidOptionException("please enter true number");
                        } catch (InvalidOptionException e) {
                            e.printStackTrace();
                        }
                }
            } catch (Exception e) {
                e.printStackTrace();
                new InvalidOptionException("please enter the correct input").printStackTrace();
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e1) {
                    e.printStackTrace();
                }
                clearConsole();
            }


        }
    }

    private static void teacherLogin(Scanner choseOptions) {
        try {
            if (checkAccount(choseOptions, "teacherDatabase.txt")) {
                Teacher loginTeacher = new Teacher();
                loginTeacher = (Teacher) findObjectInDataBase(userid, loginTeacher);
                clearConsole();
                System.out.println(ANSI_GREEN + "You are logged in as " + loginTeacher.getFirstName() + " " + loginTeacher.getLastName() + ANSI_RESET);
                Student.loadAllStudent();
                Teacher.loadAllTeachers();
                Course.loadAllCourse();
                Assignment.loadAllAssignments();
                loginPageForTeacher(loginTeacher, choseOptions);
            } else {
                throw new UserNotFoundException("userNotFound");
            }
        } catch (ThereIsNotAnyAccountException | UserNotFoundException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (TooManyAttemptsException | IOException | DoublicateException | InvalidCourseException |
                 InactiveCourseException | NotFindCourseOfSemester | DoublicateCourseException |
                 CourseISEmptyException | DoublicateAssignmentException | NotFindAssignmentException |
                 AssignmentIsEmptyException | InvalidOptionException e) {
            throw new RuntimeException(e);
        }
    }

    public static void signUpStudent(Scanner choseOptions) throws TooManyAttemptsException, DoublicateException, IOException {
        boolean isValid = false;
        System.out.println(ANSI_PURPLE + "please enter student firstName:");
        choseOptions.nextLine();
        String firstName = choseOptions.nextLine();
        clearConsole();
        System.out.println("please enter student lastName:");
        String lastName = choseOptions.nextLine();
        clearConsole();
        String id = String.valueOf(ThreadLocalRandom.current().nextInt(100000, 200000));
        System.out.println("please enter student userName:");
        String username = choseOptions.nextLine();
        System.out.println("please enter student numberOfCurrentSemester:");
        int numberOfCurrentSemester = choseOptions.nextInt();
        choseOptions.nextLine();
        clearConsole();
        int numberOfAttempts = 0;
        String password = new String();
        while (!isValid) {
            if (numberOfAttempts == 5) {
                throw new TooManyAttemptsException();
            }
            numberOfAttempts++;
            System.out.println(ANSI_PURPLE + "please enter student password:" + ANSI_RESET);
            password = choseOptions.nextLine();
            isValid = isValidPassword(password, username);
            clearConsole();
            if (!isValid) {
                String inValidPassword = """
                        invalid password
                        The password must contain at least 8 letters and contain at least one uppercase letter,\s
                        one lowercase letter and one number, and it should not contain exactly the word username.
                        please wait to enter the password again....\s""";

                System.out.print(ANSI_RED + inValidPassword + ANSI_RESET);
                try {
                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                clearConsole();
            }

        }
        new Student(firstName, lastName, username, password, id, numberOfCurrentSemester);
        System.out.println(ANSI_GREEN + "\t\tYou signed up successfully" + "\n your uniq id is : " + id + " please don not forget your id!!!\n" + ANSI_RESET);
    }

    public static void signUpTeacher(Scanner choseOptions) throws TooManyAttemptsException, DoublicateException, IOException {
        boolean isValid = false;
        System.out.println(ANSI_PURPLE + "please enter teacher firstName:");
        String firstName = choseOptions.nextLine();
        clearConsole();
        System.out.println("please enter teacher lastName:");
        String lastName = choseOptions.nextLine();
        clearConsole();
        String id = String.valueOf(ThreadLocalRandom.current().nextInt(100000, 200000));
        System.out.println("please enter teacher userName:");
        String username = choseOptions.nextLine();
        int numberOfAttempts = 0;
        String password = new String();
        while (!isValid) {
            if (numberOfAttempts == 5) {
                throw new TooManyAttemptsException();
            }
            numberOfAttempts++;
            System.out.println(ANSI_PURPLE + "please enter teacher password:" + ANSI_RESET);
            password = choseOptions.nextLine();
            isValid = isValidPassword(password, username);
            clearConsole();
            if (!isValid) {
                String inValidPassword = """
                        invalid password
                        The password must contain at least 8 letters and contain at least one uppercase letter,\s
                        one lowercase letter and one number, and it should not contain exactly the word username.
                        please wait to enter the password again......\s""";

                System.out.print(ANSI_RED + inValidPassword + ANSI_RESET);
                try {
                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                clearConsole();
            }

        }
        new Teacher(firstName, lastName, username, password, id);
        System.out.println(ANSI_GREEN + "\t\tYou signed up successfully" + "\n your uniq id is : " + id + " please don not forget your id!!!\n" + ANSI_RESET);
    }

    public static Object findById(Object object, Scanner choseOptions) throws NotFindStudentIdException, StudentIsEmptyException, TeacherIsEmptyException, NotFindTeacherException, CourseISEmptyException, NotFindCourseOfSemester, AssignmentIsEmptyException, NotFindAssignmentException {
        if (object instanceof Student) {
            System.out.println(ANSI_YELLOW + "please enter student id:" + ANSI_RESET);
            String id = choseOptions.nextLine();
            clearConsole();
            if (Student.getAllStudents().isEmpty()) {
                throw new StudentIsEmptyException();
            } else {
                for (Student i : Student.getAllStudents()) {
                    if (i.getId().equals(id)) {
                        return i;
                    }
                }
                throw new NotFindStudentIdException();
            }
        } else if (object instanceof Teacher) {
            System.out.println(ANSI_YELLOW + "please enter teacher id:" + ANSI_RESET);
            String id = choseOptions.nextLine();
            clearConsole();
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
        } else if (object instanceof Course) {
            if (Course.getAllCourses().isEmpty()) {
                throw new CourseISEmptyException();
            } else {
                System.out.println(ANSI_YELLOW + "please enter courseCode:" + ANSI_RESET);
                String id = choseOptions.nextLine();
                clearConsole();
                for (Course i : Course.getAllCourses()) {
                    if (i.getCourseCode().equals(Integer.valueOf(id))) {
                        return i;
                    }
                }
                throw new NotFindCourseOfSemester();
            }
        } else if (object instanceof Assignment) {
            if (Assignment.getAllAssignments().isEmpty()) {
                throw new AssignmentIsEmptyException();
            } else {
                System.out.println(ANSI_YELLOW + "please enter assignmentId:" + ANSI_RESET);
                String id = choseOptions.nextLine();
                clearConsole();
                for (Assignment i : Assignment.getAllAssignments()) {
                    if (i.getId().equals(id)) {
                        return i;
                    }
                }
                throw new NotFindAssignmentException();
            }
        }
        return null;
    }

    private static void loginPageForTeacher(Teacher loginTeacher, Scanner choseOptions) throws TooManyAttemptsException, DoublicateException, IOException, NotFindCourseOfSemester, InactiveCourseException, InvalidCourseException, DoublicateCourseException, CourseISEmptyException, DoublicateAssignmentException, AssignmentIsEmptyException, NotFindAssignmentException, InvalidOptionException {
        while (true) {
            System.out.println(ANSI_PURPLE + "please chose one of the following options to do the function:" + ANSI_RESET);
            System.out.println(ANSI_BLUE + """
                    1: Show List Of Students
                    2: Show List Of Course Assignments
                    3: set Exam Time Course
                    4: Active or deActive Assignment
                    5: Update Assignment Deadline
                    6: new Assigment To Database
                    7: remove Assignment from Database
                    8: add assignment to Course
                    9: remove assignment from Course
                    10: set score for Student Course
                    11: show top students of Course\s
                    12: show yourCourses
                    13: backToLoginPage
                    0: Exit""" + ANSI_RESET);
            int choice = 0;
            try {
                choice = choseOptions.nextInt();
            } catch (Exception e) {
                new InvalidOptionException("invalidOption").printStackTrace();
            }
            clearConsole();
            try {
                switch (choice) {
                    case 1:
                        String courseCode1 = choseOptions.nextLine();
                        Course course6 = new Course();
                        course6 = (Course) findById(course6 , choseOptions);
                        if (course6.getRegisteredStudents().isEmpty()) {
                            System.out.println(ANSI_RED + "There are no students in the database" + ANSI_RESET);
                        } else {
                            for (Student student : course6.getRegisteredStudents()) {
                                System.out.println(ANSI_PURPLE + student.toString() + ANSI_RESET);
                            }
                        }
                        try {
                            Thread.sleep(6000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        clearConsole();
                        break;
                    case 2:
                        choseOptions.nextLine();
                        System.out.println(ANSI_YELLOW + "please enter courseCode:" + ANSI_RESET);
                        String idForAssignment = choseOptions.nextLine();
                        Course.findById(Integer.valueOf(idForAssignment));
                        for (Course course : Course.getAllCourses()) {
                            if (course.getCourseCode().equals(Integer.valueOf(idForAssignment))) {
                                if (course.getAssignments().isEmpty()) {
                                    throw new AssignmentIsEmptyException();
                                } else {
                                    System.out.println(ANSI_PURPLE + course.assignmentsToString() + ANSI_RESET);
                                    break;
                                }
                            }
                        }
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        clearConsole();
                        break;
                    case 3:
                        choseOptions.nextLine();
                        Course setExamCourse = new Course();
                        setExamCourse = (Course) findById(setExamCourse, choseOptions);
                        System.out.println(ANSI_PURPLE + "please enter the day of exam in form of Integer:" + ANSI_RESET);
                        Integer dayOfExam = choseOptions.nextInt();
                        System.out.println(ANSI_PURPLE + "please enter the month of exam in form of Integer:" + ANSI_RESET);
                        Integer monthOfExam = choseOptions.nextInt();
                        System.out.println(ANSI_PURPLE + "please enter the year of exam int form of Integer:" + ANSI_RESET);
                        Integer yearOfExam = choseOptions.nextInt();
                        loginTeacher.setExamCourse(dayOfExam, monthOfExam, yearOfExam, setExamCourse);
                        System.out.println(ANSI_GREEN + "Exam set successfully" + ANSI_RESET);
                        Student.saveToDatabaseObject();
                        Course.saveToDatabaseObject();
                        Teacher.teacherUpdateDataBase();
                        try {
                            Thread.sleep(4000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        clearConsole();
                        break;
                    case 4:
                        choseOptions.nextLine();
                        Assignment assignment1 = new Assignment();
                        System.out.println(ANSI_PURPLE + "please enter assignmentId:" + ANSI_RESET);
                        String assignmentId1 = choseOptions.nextLine();
                        assignment1 = Assignment.findById(assignmentId1);
                        System.out.println(ANSI_PURPLE + """
                                please chose Course is active or not...
                                true: active course
                                false: inactive course""" + ANSI_RESET);
                        boolean activeAssignment = choseOptions.nextBoolean();
                        loginTeacher.updateAssignmentActive(assignment1, activeAssignment);
                        System.out.println(ANSI_GREEN + "Assignment update successfully" + ANSI_RESET);
                        Student.saveToDatabaseObject();
                        Course.saveToDatabaseObject();
                        Teacher.teacherUpdateDataBase();
                        try {
                            Thread.sleep(4000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        clearConsole();
                        break;
                    case 5:
                        choseOptions.nextLine();
                        Assignment assignment2 = new Assignment();
                        System.out.println(ANSI_PURPLE + "please enter assignmentId:" + ANSI_RESET);
                        String assignmentId2 = choseOptions.nextLine();
                        clearConsole();
                        assignment2 = Assignment.findById(assignmentId2);
                        System.out.println(ANSI_PURPLE + "please enter deadline(please enter number of days that remain to finish the time of assignment):" + ANSI_RESET);
                        Integer deadlineAssignment = choseOptions.nextInt();
                        clearConsole();
                        loginTeacher.updateAssignmentDeadLine(assignment2, deadlineAssignment);
                        System.out.println(ANSI_GREEN + "Deadline update successfully" + ANSI_RESET);
                        Student.saveToDatabaseObject();
                        Course.saveToDatabaseObject();
                        Teacher.teacherUpdateDataBase();
                        try {
                            Thread.sleep(4000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        clearConsole();
                        break;
                    case 6:
                        choseOptions.nextLine();
                        System.out.println(ANSI_PURPLE + "please enter assignmentName:" + ANSI_RESET);
                        String assignmentName = choseOptions.nextLine();
                        System.out.println(ANSI_PURPLE + "please define assignmentType:\n1:Project\n2:practice" + ANSI_RESET);
                        Integer option = choseOptions.nextInt();
                        AssignmentType assignmentType = null;
                        switch (option) {
                            case 1:
                                assignmentType = AssignmentType.project;
                                break;
                            case 2:
                                assignmentType = AssignmentType.practice;
                                break;
                        }
                        System.out.println(ANSI_PURPLE + "please define that the course is active or not with true or false\nactive:true\ninactive:false" + ANSI_RESET);
                        Boolean isActiveAssignment = choseOptions.nextBoolean();
                        String assignmentId = String.valueOf(ThreadLocalRandom.current().nextInt(100000, 200000));
                        System.out.println(ANSI_PURPLE + "please enter deadLine of assignment(please enter number of days that remain to finish the time of assignment):" + ANSI_RESET);
                        Integer deadline = choseOptions.nextInt();
                        System.out.println(ANSI_GREEN + "\t\tassignment created successfully" + "\n your uniq assignmentId is : " + assignmentId + " please don not forget your id!!!\n" + ANSI_RESET);
                        new Assignment(assignmentName, assignmentType, deadline, isActiveAssignment, assignmentId);
                        try {
                            Thread.sleep(6000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        clearConsole();
                        break;
                    case 7:
                        choseOptions.nextLine();
                        System.out.println(ANSI_PURPLE + "please enter assignmentId:" + ANSI_RESET);
                        String assignmentIdRemoval = choseOptions.nextLine();
                        loginTeacher.assignmentRemoveFromDatabase(assignmentIdRemoval);
                        System.out.println(ANSI_GREEN + "Assignment removed successfully" + ANSI_RESET);
                        try {
                            Thread.sleep(4000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        clearConsole();
                        break;
                    case 8:
                        choseOptions.nextLine();
                        Assignment removalAssignment = new Assignment();
                        Course removalCourse3 = new Course();
                        removalAssignment = (Assignment) findById(removalAssignment, choseOptions);
                        removalCourse3 = (Course) findById(removalCourse3, choseOptions);
                        loginTeacher.addAssignment(removalAssignment, removalCourse3);
                        System.out.println(ANSI_GREEN + "Assignment added successfully" + ANSI_RESET);
                        Student.saveToDatabaseObject();
                        Course.saveToDatabaseObject();
                        Teacher.teacherUpdateDataBase();
                        break;
                    case 9:
                        choseOptions.nextLine();
                        Assignment removalAssignment1 = new Assignment();
                        Course removalCourse4 = new Course();
                        removalAssignment = (Assignment) findById(removalAssignment1, choseOptions);
                        removalCourse4 = (Course) findById(removalCourse4, choseOptions);
                        loginTeacher.removeAssignment(removalAssignment, removalCourse4);
                        System.out.println(ANSI_GREEN + "Assignment removed successfully" + ANSI_RESET);
                        try {
                            Thread.sleep(4000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        clearConsole();
                        Student.saveToDatabaseObject();
                        Course.saveToDatabaseObject();
                        Teacher.teacherUpdateDataBase();
                        break;
                    case 10:
                        choseOptions.nextLine();
                        Course setScoreCourse = new Course();
                        Student setScoreStudent = new Student();
                        setScoreStudent = (Student) findById(setScoreStudent, choseOptions);
                        setScoreCourse = (Course) findById(setScoreCourse, choseOptions);
                        System.out.println(ANSI_YELLOW + "please enter student score:" + ANSI_RESET);
                        Double studentScore = choseOptions.nextDouble();
                        loginTeacher.setScore(studentScore, setScoreStudent.getId(), setScoreCourse);
                        System.out.println(ANSI_GREEN + "score set successfully" + ANSI_RESET);
                        try {
                            Thread.sleep(4000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        clearConsole();
                        Student.saveToDatabaseObject();
                        Course.saveToDatabaseObject();
                        Teacher.teacherUpdateDataBase();
                        break;
                    case 11:
                        choseOptions.nextLine();
                        Course course2 = new Course();
                        course2 = (Course) findById(course2, choseOptions);
                        System.out.println(ANSI_GREEN + course2.firstStudentByScoreToString() + ANSI_RESET);
                        try {
                            Thread.sleep(4000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        clearConsole();
                        break;
                    case 12:
                        if(loginTeacher.getCourses().isEmpty())
                            throw new CourseISEmptyException();
                        for(Course course:loginTeacher.getCourses()){
                            System.out.println(ANSI_CYAN+course+ANSI_RESET);
                        }
                        try {
                            Thread.sleep(4000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        clearConsole();
                        break;
                    case 13:
                        String [] args = new String[1];
                        args[0] = "Cli";
                        Teacher.getAllTeachers().clear();
                        Student.getAllStudents().clear();
                        Course.getAllCourses().clear();
                        Assignment.getAllAssignments().clear();
                        Admin.getAllAdmins().clear();
                        Cli.main(args);
                        break;
                    case 0:
                        choseOptions.close();
                        System.exit(0);
                        break;
                    default:
                        try {
                            throw new InvalidOptionException("please enter true number");
                        } catch (InvalidOptionException e) {
                            e.printStackTrace();
                        }
                }
            } catch (Exception e) {
                e.printStackTrace();
                new InvalidOptionException("please enter the correct input").printStackTrace();
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e1) {
                    e.printStackTrace();
                }
                clearConsole();
            }
        }
    }
}
