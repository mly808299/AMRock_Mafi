import java.io.Serializable;
import java.util.*;

public class Semester implements Serializable {
    private int numberOfCourses;
    private int numberOfUnits;
    private List<Course> courses = new ArrayList<>();
    private Map<Integer, Double> scores = new HashMap<>();
    private Double totalAverage;

    public Map<Integer, Double> getScores() {
        return scores;
    }

    public Semester(Course newCourse) {
        this.courses.add(newCourse);
        this.scores.put(newCourse.getCourseCode(), 0.0);
        numberOfCourses++;
        this.numberOfUnits += newCourse.getUnit();
    }

    public void setNumberOfUnits(int numberOfUnits) {
        this.numberOfUnits = numberOfUnits;
    }

    public void addCourses(Course newCurse) {
        courses.add(newCurse);
        this.scores.put(newCurse.getCourseCode(), 0.0);
        numberOfCourses++;
        this.numberOfUnits += newCurse.getUnit();
        calculateTotalAverageOfRegisteredSemester();
    }

    public void removeCourse(Course removeCourse) throws NotFindCourseOfSemester {
        if (!courses.contains(removeCourse)) {
            throw new NotFindCourseOfSemester();
        }
        courses.remove(removeCourse);
        scores.remove(removeCourse.getCourseCode());
        numberOfCourses--;
        this.numberOfUnits -= removeCourse.getUnit();
        calculateTotalAverageOfRegisteredSemester();
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
        for(Course course : courses) {
            this.scores.clear();
            this.scores.put(course.getCourseCode(), 0.0);
            numberOfCourses = 0;
            numberOfCourses++;
            this.numberOfUnits = 0;
            this.numberOfUnits += course.getUnit();
            calculateTotalAverageOfRegisteredSemester();
        }
    }

    public int getNumberOfCourses() {
        return numberOfCourses;
    }

    public int getNumberOfUnits() {
        return numberOfUnits;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public Double getTotalAverage() {
        calculateTotalAverageOfRegisteredSemester();
        return totalAverage;
    }

    public void calculateTotalAverageOfRegisteredSemester() {
        double newTotalAverage = 0.0;
        for (Double i : scores.values()) {
            if (i != null) {
                newTotalAverage += i;
            }
        }
        if (courses.isEmpty()) {
            totalAverage = 0.0;
            return;
        }
        System.out.println(courses.size());
        totalAverage = newTotalAverage / courses.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Semester semester = (Semester) o;
        return numberOfCourses == semester.numberOfCourses && numberOfUnits == semester.numberOfUnits && Objects.equals(courses, semester.courses) && Objects.equals(totalAverage, semester.totalAverage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfCourses, numberOfUnits, courses, totalAverage);
    }

}
