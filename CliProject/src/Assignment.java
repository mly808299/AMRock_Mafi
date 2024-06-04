import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

enum AssignmentType {practice, project}

public class Assignment implements Serializable{
    private static List<Assignment> allAssignments = new ArrayList<>();
    private AssignmentType assignmentType;
    private String assignmentName;
    private int deadline;
    private boolean isActive;
    private String id;
    public static void loadAllAssignments() {
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

    public Assignment(String assignmentName, AssignmentType assignmentType, int deadline, boolean isActive , String id) throws DoublicateAssignmentException {
        if (!allAssignments.isEmpty()) {
            for (Assignment i : allAssignments) {
                if (Objects.equals(assignmentName, i.assignmentName) && assignmentType == i.assignmentType)
                    throw new DoublicateAssignmentException();
            }
        }
        this.assignmentName = assignmentName;
        this.assignmentType = assignmentType;
        this.isActive = isActive;
        this.deadline = deadline;
        this.id = id;
        allAssignments.add(this);
        saveToDatabaseObject();
    }

    public void updateIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public void updateDeadLine(int deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "Assignment{"+
                "assignmentType=" + assignmentType +
                ", assignmentName='" + assignmentName + '\'' +
                ", deadline=" + deadline +
                ", isActive=" + isActive +
                ", id='" + id + '\'';
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
}
