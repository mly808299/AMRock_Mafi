package miniProject.src;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

enum AssignmentType {practice, project}

public class Assignment {
    private static List<Assignment> allAssignments = new ArrayList<>();
    private AssignmentType assignmentType;
    private String assignmentName;
    private int deadline;
    private boolean isActive;

    public Assignment(String assignmentName, AssignmentType assignmentType, int deadline, boolean isActive) throws DoublicateAssignmentException {
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
        allAssignments.add(this);
    }

    public void updateIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public void updateDeadLine(int deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "assignmentType=" + assignmentType +
                ", assignmentName='" + assignmentName + '\'' +
                ", deadline=" + deadline +
                ", isActive=" + isActive
                ;
    }
}
