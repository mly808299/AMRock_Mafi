import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class MultiSocket {
    private static final Cli cli = new Cli();

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8084);
        Student.loadAllStudent();
        Teacher.loadAllTeachers();
        Assignment.loadAllAssignments();
        Course.loadAllCourse();
        Admin.loadAllAdmins();
        Admin loginAdmin = Admin.getAllAdmins().get(0);
        ExecutorService executorService = Executors.newCachedThreadPool();
        System.out.println("server is running....");
        while (true) {
            executorService.execute(() -> {
                        synchronized (cli) {
                            try (Socket socket = serverSocket.accept();
                                 BufferedReader scanFromFlutter = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                                 BufferedWriter formatToFlutter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                            ) {
                                String page = scanFromFlutter.readLine();
                                switch (page) {
                                    case "login":
                                        String username = scanFromFlutter.readLine();
                                        String password = scanFromFlutter.readLine();
                                        Student student = findByUsername(username);
                                        if (student != null) {
                                            if (checkPassword(student, password)) {
                                                formatToFlutter.write("loggedIn successfully" + "\n");
                                                System.out.println("loggedIn successfully");
                                            } else {
                                                formatToFlutter.write("wrong password" + "\n");
                                                System.out.println("wrong password");
                                            }
                                        } else {
                                            formatToFlutter.write("user not found\n");
                                            System.out.println("user not found");
                                        }
                                        break;
                                    case "signup":
                                        username = scanFromFlutter.readLine();
                                        password = scanFromFlutter.readLine();
                                        if (findByUsername(username) != null) {
                                            formatToFlutter.write("duplicate username" + "\n");
                                            System.out.println("duplicate username");
                                        } else {
                                            formatToFlutter.write("signup in successfully" + "\n");
                                            System.out.println("signup in successfully");
                                            String id = String.valueOf(ThreadLocalRandom.current().nextInt(100000, 200000));
                                            new Student(" ", " ", username, password, id, 1);
                                        }
                                        break;
                                    case "delete Account":
                                        String id = scanFromFlutter.readLine();
                                        Student studentRemovalObj = new Student();
                                        loginAdmin.removeFromDatabase(id, studentRemovalObj);
                                        System.out.println("student removed");
                                        break;
                                    case "set profile":
                                        username = scanFromFlutter.readLine();
                                        student = findByUsername(username);
                                        if (student != null) {
                                            List<String> profile = new ArrayList<>();
                                            profile.add(student.getId());
                                            profile.add(String.valueOf(student.numberOfCurrentCursesToInt()));
                                            profile.add(Double.toString(student.totalAveragetoDouble()));
                                            profile.add(student.getLastName());
                                            profile.add(String.valueOf(student.registrationUnitstoInt()));
                                            profile.add(student.getFirstName());
                                            Gson gson = new Gson();
                                            System.out.println(gson.toJson(profile));
                                            formatToFlutter.write(gson.toJson(profile) + "\n");
                                        }
                                        System.out.println("student profile set");
                                        break;
                                    case "edit profile":
                                        username = scanFromFlutter.readLine();
                                        String firstName = scanFromFlutter.readLine();
                                        String lastName = scanFromFlutter.readLine();
                                        student = findByUsername(username);
                                        if (student != null) {
                                            student.setFirstName(firstName);
                                            student.setLastName(lastName);
                                        }
                                        System.out.println("student profile edited");
                                        Student.saveToDatabaseObject();
                                        Teacher.teacherUpdateDataBase();
                                        Course.saveToDatabaseObject();
                                        Assignment.saveToDatabaseObject();
                                        break;
                                    case "change password":
                                        username = scanFromFlutter.readLine();
                                        password = scanFromFlutter.readLine();
                                        student = findByUsername(username);
                                        if (student != null) {
                                            student.setPassword(password);
                                        }
                                        System.out.println("student password changed");
                                        Student.saveToDatabaseObject();
                                        Teacher.teacherUpdateDataBase();
                                        Course.saveToDatabaseObject();
                                        Assignment.saveToDatabaseObject();
                                        break;
                                    case "task page":
                                        username = scanFromFlutter.readLine();
                                        student = findByUsername(username);
                                        Gson gson = new Gson();
                                        String json = gson.toJson(student.getTasks());
                                        formatToFlutter.write(json + "\n");
                                        System.out.println("student tasks set");
                                        break;
                                    case "assignment page":
                                        username = scanFromFlutter.readLine();
                                        student = findByUsername(username);
                                        List<Assignment> assignments = new ArrayList<>();
                                        assert student != null;

                                        for (Course j : student.getCurrentSemesters().getCourses()) {
                                            assignments.addAll(j.getAssignments());
                                        }
                                        System.out.println(assignments);
                                        gson = new Gson();
                                        json = gson.toJson(assignments);
                                        formatToFlutter.write(json + "\n");
                                        System.out.println("student assignment set");
                                        break;
                                    case "Course page":
                                            username = scanFromFlutter.readLine();
                                            student = findByUsername(username);
                                            assert student != null;
                                            if (student.getSemesters() != null) {
                                                gson = new Gson();
                                                List<Course> coursesList = student.getCurrentSemesters().getCourses();
                                                for (Course j : coursesList) {
                                                    j.setTopStudentOfWeek();
                                                    j.setNumberOfRemainingAssignments();
                                                }
                                                JsonArray jsonArray = new JsonArray();
                                                for(Course i: coursesList){
                                                    jsonArray.add(i.toJson());
                                                }
                                                json = gson.toJson(jsonArray);
                                                formatToFlutter.write(json + "\n");
                                            }
                                            System.out.println("student Course page set");
                                        break;
                                    case "finishTask":
                                        username = scanFromFlutter.readLine();
                                        student = findByUsername(username);
                                        json = scanFromFlutter.readLine();
                                        gson = new Gson();
                                        Tasks[] tasks = gson.fromJson(json, Tasks[].class);
                                        assert student != null;
                                        student.setTasks((Arrays.stream(tasks).toList()));
                                        Student.saveToDatabaseObject();
                                        System.out.println(student.getTasks());
                                        System.out.println("student tasks finished");
                                        break;
                                    case "doneAssignment":
                                        json = scanFromFlutter.readLine();
                                        gson = new Gson();
                                        Assignment[] assignments1 = gson.fromJson(json, Assignment[].class);
                                        //                                        for(Assignment j: assignments1){
//                                            Assignment assignment = j;
//                                            Assignment assignmentStatic= Assignment.findById(j.getId());
//                                            assignmentStatic.updateIsActive(j.getActive());
//                                            assignmentStatic.setEstimateTime(j.getEstimateTime());
//                                            assignmentStatic.setDone(j.getDone());
//                                            for (Course i : Course.getAllCourses()) {
//                                                for (Assignment k : i.getAssignments()) {
//                                                    if (k.getId().equals(assignment.getId())) {
//                                                        k.updateIsActive(assignment.getActive());
//                                                        k.setDone(assignment.getDone());
//                                                        k.setEstimateTime(assignment.getEstimateTime());
//                                                    }
//                                                }
//                                            }
//                                        }
                                        Assignment.saveToDatabaseObject();
                                        Course.saveToDatabaseObject();
                                        Teacher.teacherUpdateDataBase();
                                        Student.saveToDatabaseObject();
                                        System.out.println("student assignment finished");
                                        break;
                                    case "addCourse":
                                        String courseCode = scanFromFlutter.readLine();
                                        username = scanFromFlutter.readLine();
                                        student = findByUsername(username);
                                        if (student != null) {
                                            try {
                                                Course course = Course.findById(Integer.parseInt(courseCode));
                                                loginAdmin.addStudentToCourse(student , course);
                                                formatToFlutter.write("true" + "\n");
                                            } catch (CourseISEmptyException | NotFindCourseOfSemester e) {
                                                formatToFlutter.write("false" + "\n");
                                            } catch (NumberFormatException e) {
                                                throw new RuntimeException(e);
                                            }
                                        }
                                        break;
                                    case "bestbadScore":
                                        username = scanFromFlutter.readLine();
                                        student = findByUsername(username);
                                        if (student != null) {
                                            Optional<Double> minOp = student.getCurrentSemesters().getScores().values().stream().min(Double::compareTo).get().describeConstable();
                                            Optional<Double> maxOp = student.getCurrentSemesters().getScores().values().stream().max(Double::compareTo).get().describeConstable();
                                            formatToFlutter.write(maxOp.map(Object::toString).orElse("0" + "\n"));
                                            formatToFlutter.write(minOp.map(Object::toString).orElse("0" + "\n"));
                                        }
                                        break;
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
            );
        }
    }

    public static Student findByUsername(String username) {
        if (!Student.getAllStudents().isEmpty()) {
            for (Student i : Student.getAllStudents()) {
                if (i.getUsername().equals(username)) {
                    return i;
                }
            }
        }
        return null;
    }

    public static boolean checkPassword(Student student, String password) {
        return student.getPassword().equals(password);
    }
}
