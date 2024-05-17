package miniProject.src;

import java.util.*;

public class Student extends Person
        implements Comparable<Student> {
    private static List<Student> allStudents = new ArrayList<>();
    private Map<Integer, Semester> semesters = new HashMap<>();
    private int numberOfCurrentSemester;
    private Double totalAverage = 0.0;
    private Double averageOfRegisteredSemester;

    public Student(String firstName, String lastName, String username, String password, String id, int numberOfCurrentSemester) throws DoublicateException {
        super(firstName,  lastName,  username,  password,  id , allStudents);
        this.numberOfCurrentSemester = numberOfCurrentSemester;
        allStudents.add(this);
    }

    public void addCourses(Course newCourse, Integer numberOfSemester) {
        if (semesters.containsKey(numberOfSemester)) {
            semesters.get(numberOfSemester).addCourses(newCourse);
        } else {
            semesters.put(numberOfSemester, new Semester(newCourse));
        }
    }

    public void removeCurses(Course removeCurse, Integer numberOfSemester) throws NotFindCourseOfSemester {
        semesters.get(numberOfSemester).removeCourse(removeCurse);
    }

    public void calculateTotalAverageOfRegisteredSemester() {
        averageOfRegisteredSemester = semesters.get(this.numberOfCurrentSemester).getTotalAverage();
    }

    public void calculateTotalAverage() {
        int size = 0;
        for (Semester i : semesters.values()) {
            for (Double j : i.getScores().values()) {
                totalAverage += j;
                size++;
            }
        }
        if (size == 0) {
            totalAverage = 0.0;
            return;
        }
        totalAverage = totalAverage / size;
    }

    @Override
    public int compareTo(Student o) {
        return this.getFirstName().compareTo(o.getFirstName());
    }

    public String registeredCoursestoString() {
        return cursesOfEachTermtoString(this.numberOfCurrentSemester);
    }

    public void printRegisteredCourses() {
        System.out.println(registeredCoursestoString());
    }

    public String cursesOfEachTermtoString(Integer termNumber) {
        String stringCourses = "";
        Iterator<Double> iteratorScore = semesters.get(termNumber).getScores().values().iterator();
        if (semesters.get(termNumber).getCourses().isEmpty()) {
            return "There is no lesson for this term " + termNumber + " yet.";
        }
        for (Course i : semesters.get(termNumber).getCourses()) {
            Double score = iteratorScore.next();
            String scoreString;
            if (score == null)
                scoreString = "There is no grade for this lesson yet.";
            else scoreString = "Your grade is : " + score;
            stringCourses += i.toString() + ", " + scoreString + "\n";
        }
        return stringCourses;
    }

    public void printCursesOfEachTerm(Integer termNumber) {
        System.out.println(cursesOfEachTermtoString(termNumber));
    }

    public Double registeredAveragetoDouble() {
        calculateTotalAverageOfRegisteredSemester();
        return averageOfRegisteredSemester;
    }

    public void printRegisteredAverage() {
        System.out.println(registeredAveragetoDouble());
    }

    public Double totalAveragetoDouble() {
        calculateTotalAverage();
        return totalAverage;
    }

    public void printTotalAverage() {
        System.out.println(totalAveragetoDouble());
    }

    public int registrationUnitstoInt() {
        return semesters.get(this.numberOfCurrentSemester).getNumberOfUnits();
    }

    public void printRegistrationUnits() {
        System.out.println(registrationUnitstoInt());
    }

    public String studentToString() {
        return getFirstName() + " " + getLastName() + " id:" + getId() + "\n";
    }

    public void printStudent() {
        System.out.println(studentToString());
    }

    public int numberOfCurrentCursesToInt() {
        return semesters.get(this.numberOfCurrentSemester).getNumberOfCourses();
    }

    public void printNumberOfCurrentCurses() {
        System.out.println(numberOfCurrentCursesToInt());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(getId(), student.getId()) && numberOfCurrentSemester == student.numberOfCurrentSemester && Objects.equals(getFirstName(), student.getFirstName()) && Objects.equals(getLastName(), student.getLastName()) && Objects.equals(semesters, student.semesters) && Objects.equals(totalAverage, student.totalAverage) && Objects.equals(averageOfRegisteredSemester, student.averageOfRegisteredSemester);
    }

    public void changeNumberOfCurrentSemester(int numberOfCurrentSemester) {
        this.numberOfCurrentSemester = numberOfCurrentSemester;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getFirstName(), getId(), semesters, numberOfCurrentSemester, totalAverage, averageOfRegisteredSemester);
    }

    //getters and setters
    public Double getCourseScore(Course courseFinder) throws NotFindCurrentCourseException {
        for (Semester i : semesters.values()) {
            for (Integer j : i.getScores().keySet()) {
                if (j == courseFinder.getCourseCode()) {
                    return i.getScores().get(j);
                }
            }
        }
        throw new NotFindCurrentCourseException();
    }
    public List<Course> getRegisteredCourses() {
        if (semesters.isEmpty())
            return null;
        return semesters.get(this.numberOfCurrentSemester).getCourses();
    }

    public Semester getCurrentSemesters() throws NotFindCourseOfSemester {
        for (Integer i : semesters.keySet()) {
            if (numberOfCurrentSemester == i) {
                return semesters.get(i);
            }
        }
        throw new NotFindCourseOfSemester();
    }

    public static List<Student> getAllStudents() {
        return allStudents;
    }

    public int getNumberOfCurrentSemester() {
        return numberOfCurrentSemester;
    }
}