package miniProject.src;

public class Main {
    public static void main(String[] args) throws InvalidCourseException, NotFindCourseOfSemester, NotFindCurrentCourseException, NotFindStudentIdException, DoublicateAssignmentException, DoublicateException, DoublicateCourseException, InactiveCourseException {
//چک میکند که ببیند اضافه و حذف کردن درست کار میکند یا نه
//        Student student1 = new Student("Ali", "Mohammadi", "60", "54656" , "5487" , 1);
//        Student student2 = new Student("Reza", "Mohammadi", "87", "98965" , "8454" , 1);
//        Student student4 = new Student("Reza", "Alavi", "95408", "6589" , "8789" , 1);
//        Student student3 = new Student("Mohammad", "Khalibaf", "25655989", "799565" , "544545" , 1);
//        Student student5 = new Student("Ynes", "Miri", "65455", "656560" , "879898" , 1);
//        Teacher teacher1 = new Teacher("Ashkan", "Khalili", "54545454" , "545454" , "122" );
//        Course course1 = new Course("math", teacher1, 3, true, 35);
//        Course course2 = new Course("math", teacher1, 3, true, 365);
//        Course course3 = new Course("Ap", teacher1, 3, true, 369);
//        Admin admin1 = new Admin("ali" , "mohammadi" , "55454" , "659879" , "0111" );
//        teacher1.addStudentToCourse(student1 , course1);
//        teacher1.addStudentToCourse(student2, course1);
//        teacher1.addStudentToCourse(student3, course1);
//       teacher1.setScore(15.6 , "5487" , course1);
//        teacher1.setScore(39.0 , "8454" , course1);
//        teacher1.setScore(15.6 , "544545" , course1);
//        student1.printCursesOfEachTerm(1);
//        teacher1.setScore(0.0 , "8454" , course1);
//        course1.printFirstStudentByScore();
//        student2.printCursesOfEachTerm(1);
//        admin1.removeTeacher(teacher1 , course1);
//        System.out.println(teacher1.getCourses());
////        student1.printCursesOfEachTerm(1);
////        teacher1.removeStudentFromCurse(student1 , course1);
////       student1.printCursesOfEachTerm(1);
////        student1.changeNumberOfCurrentSemester(2);
////        student1.changeNumberOfCurrentSemester(2);
////        teacher1.addStudentToCourse(student2 ,course1);
////        teacher1.addStudentToCourse(student2 , course3);
//        course1.printRegisteredStudents();
////        course1.setActive(false);
////        course1.printRegisteredStudents();
////        student1.printCursesOfEachTerm(2);
//
//        //نمره رو استاد ست میکنه
////        teacher1.addStudentToCourse(student1 , course2);
////        teacher1.addStudentToCourse(student1 , course3);
////        student1.printCursesOfEachTerm(2);
//        //ترم رو عوض میکنه
////        student1.changeNumberOfCurrentSemester(2);
////        teacher1.addStudentToCourse(student1, course2);
////        teacher1.addStudentToCourse(student1, course3);
//        //حذف کردن دانش اموز
////        teacher1.addStudentToCourse(student1, course2);
////        teacher1.addStudentToCourse(student1, course3);
////        teacher1.removeStudentFromCurse(student1 , course2);
////        teacher1.removeStudentFromCurse(student1 , course3);
////        student1.printCursesOfEachTerm(1);
//        //حذف کردن استاد از درس
////        teacher1.removeCurseOfTeacher(course1);
////        System.out.println(teacher1.toString());
//        //ست کردن تاریخ امتحان
////        teacher1.addStudentToCourse(student1, course2);
////        teacher1.setScore(15.6 , 60 , course2);
////        teacher1.setExamCourse(1 , 12 , 2000 , course2);
////        student1.printCursesOfEachTerm(1);
//        //لیست دانش اموزان هر درس
////        teacher1.addStudentToCourse(student1, course2);
////        course2.printRegisteredStudents();
//        //برترین دانش اموزان از لحاظ نمره
////        teacher1.addStudentToCourse(student1, course1);
////        teacher1.addStudentToCourse(student2, course1);
////        teacher1.addStudentToCourse(student3, course1);
////        teacher1.addStudentToCourse(student4, course1);
////        teacher1.addStudentToCourse(student5, course1);
////        teacher1.setScore(12.6, 60, course1);
////        teacher1.setScore(13.6, 87, course1);
////        teacher1.setScore(13.6, 98, course1);
////        teacher1.setScore(13.6, 25, course1);
////        teacher1.setScore(13.6, 65, course1);
////        course1.printFirstStudentByScore();
//        //تعداد دانش اموزان هر درس و تعداد کلاس های هر دانش اموز تعداد استادان هر درس تعداد استادان هر کلاس
////                teacher1.addStudentToCourse(student1, course1);
////        teacher1.addStudentToCourse(student2, course1);
////        teacher1.addStudentToCourse(student3, course1);
////        teacher1.addStudentToCourse(student4, course1);
////        teacher1.addStudentToCourse(student5, course1);
////        System.out.println(course1.toString());
////        System.out.println(course1.getStudentsNumber());
////        System.out.println(teacher1);
////        student1.printNumberOfCurrentCurses();
////        student1.printNumberOfCurrentCurses();
//        //معدل هر ترم و معدل کل ترم ها
////        teacher1.addStudentToCourse(student1, course1);
////        teacher1.addStudentToCourse(student1, course3);
////        teacher1.addStudentToCourse(student1, course2);
////        teacher1.setScore(12.6, 60, course1);
////        teacher1.setScore(13.6, 60, course2);
////        teacher1.setScore(13.6, 60, course3);
////        student1.printCursesOfEachTerm(1);
////        student1.printRegisteredAverage();
////        student1.changeNumberOfCurrentSemester(2);
////        teacher1.addStudentToCourse(student1, course3);
////        teacher1.addStudentToCourse(student1, course2);
////        teacher1.setScore(13.0, 60, course2);
////        teacher1.setScore(13.0, 60, course3);
////        student1.printRegisteredAverage();
////        student1.printTotalAverage();
//        //تعریف کردن تمرین و پروژه توسط استاد
//
////        Assignment assignment1 = new Assignment("tamrin1" , AssignmentType.practice , 2 , true);
////        Assignment assignment2 = new Assignment("tamrin1" , AssignmentType.project , 3 , true);
////        teacher1.addAssignment(assignment1 , course2);
////        teacher1.addAssignment(assignment2 , course2);
////        System.out.println(course2.assignmentsToString());
////        teacher1.removeAssignment(assignment2 , course2);
////        System.out.println(course2.assignmentsToString());
////
////        System.out.println(student2.getRegisteredCourses().get(student2.getRegisteredCourses().indexOf(course2)).assignmentsToString());
////        Student student1 = new Student("Ali", "Mohammadi", 60, 1);
////        Student student2 = new Student("Reza", "Mohammadi", 87, 1);
////        Student student4 = new Student("Reza", "Alavi", 98, 1);
////        Student student3 = new Student("Mohammad", "Khalibaf", 25, 1);
////        Student student5 = new Student("Ynes", "Miri", 65, 1);
////        Teacher teacher1 = new Teacher("Ashkan", "Khalili", 57);
////        Course course2 = new Course("math", teacher1, 3, true , 366);
////        Course course1 = new Course("math", teacher1, 3, true , 35);
////        teacher1.addStudentToCourse(student1 , course2);
////        teacher1.addStudentToCourse(student2 , course1);
////        teacher1.addStudentToCourse(student3 , course1);
////        teacher1.addStudentToCourse(student4 , course1);
////        teacher1.addStudentToCourse(student5, course1);
////        teacher1.setScore(12.6 , 60 , course2);
////        teacher1.setScore(13.6 , 87 , course1);
////        teacher1.setScore(13.6 ,  98, course1);
////        teacher1.setScore(13.6 ,  25, course1);
////        teacher1.setScore(13.6 ,  65, course1);
////        course1.printFirstStudentByScore();
////        student1.printCursesOfEachTerm(1);

    }
}
