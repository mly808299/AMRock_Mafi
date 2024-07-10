import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Assignment implements Serializable{
    private static List<Assignment> allAssignments = new ArrayList<>();
    private String assignmentName;
    private String date;
    private String time;
    private boolean isAm;
    private boolean isActive;
    private String description;
    private boolean isDone;
    private String id;
    private String estimateTime;
    public static void loadAllAssignments() {
        allAssignments.clear();
        File file = new File("assignmentDatabaseObjects.ser");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try (FileInputStream fileInputStream = new FileInputStream("assignmentDatabaseObjects.ser");
                 ObjectInputStream objectReader = new ObjectInputStream(fileInputStream)) {
                while (fileInputStream.available() > 0) {
                    Assignment assignment = (Assignment) objectReader.readObject();
                    allAssignments.add(assignment);
                }
            } catch (IOException e) {
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static void saveToDatabaseObject() {
        try (FileOutputStream databaseObjects = new FileOutputStream(new File("assignmentDatabaseObjects.ser"));
             ObjectOutputStream teacherDatabaseObjects = new ObjectOutputStream(databaseObjects)) {
            for (Assignment assignment : allAssignments) {
                teacherDatabaseObjects.writeObject(assignment);
                teacherDatabaseObjects.flush();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Assignment(String assignmentName, boolean isActive , String id , String date , String time , boolean isAm , String description , String estimateTime) throws DoublicateAssignmentException {
//        if (!allAssignments.isEmpty()) {
//            for (Assignment i : allAssignments) {
//                if (Objects.equals(assignmentName, i.assignmentName) )
//                    throw new DoublicateAssignmentException();
//            }
//        }
        this.assignmentName = assignmentName;
        this.isActive = isActive;
        this.date = date;
        this.time = time;
        this.id = id;
        this.isAm = isAm;
        this.description = description;
        this.estimateTime = estimateTime;
        allAssignments.add(this);
        saveToDatabaseObject();
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public void updateIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public void updateDeadLine(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "assignmentName='" + assignmentName + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", isAm=" + isAm +
                ", isActive=" + isActive +
                ", description='" + description + '\'' +
                ", isDone=" + isDone +
                ", id='" + id + '\'' +
                '}';
    }

    public static List<Assignment> getAllAssignments() {
        return allAssignments;
    }

    public String getId() {
        return id;
    }
    public static Assignment findById(String id) throws AssignmentIsEmptyException, NotFindAssignmentException {
        if (Assignment.getAllAssignments().isEmpty()) {
            throw new AssignmentIsEmptyException();
        } else {
            for (Assignment i : Assignment.getAllAssignments()) {
                if (i.getId().equals(id)) {
                    return i;
                }
            }
            throw new NotFindAssignmentException();
        }
    }
    public Assignment(){
    }

    public static void setAllAssignments(List<Assignment> allAssignments) {
        Assignment.allAssignments = allAssignments;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public String getDate() {
        return date;
    }

    public String getEstimateTime() {
        return estimateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean getActive() {
        return isActive;
    }

    public boolean getDone() {
        return isDone;
    }

    public void setEstimateTime(String estimateTime) {
        this.estimateTime = estimateTime;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
