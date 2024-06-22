import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
        ExecutorService executorService = Executors.newFixedThreadPool(1);
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
                                            formatToFlutter.write(student.toJson() + "\n");
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
                                        break;
                                    case "change password":
                                        username = scanFromFlutter.readLine();
                                        password = scanFromFlutter.readLine();
                                        student = findByUsername(username);
                                        if (student != null) {
                                            student.setPassword(password);
                                        }
                                        System.out.println("student password changed");
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
