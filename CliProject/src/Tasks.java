import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Tasks implements Serializable {
    private String clock;
    private String date;
    private String taskTitle;
    private boolean isAm;
    private boolean isDone = false;
    private boolean isEnd = false;


    public Tasks( boolean isEnd, String clock, String date, String taskTitle, boolean isAm, boolean isDone) {
        this.clock = clock;
        this.date = date;
        this.taskTitle = taskTitle;
        this.isAm = isAm;
        this.isDone = isDone;
        this.isEnd = isEnd;
    }

    @Override
    public String toString() {
        return "Tasks{" +
                "clock='" + clock + '\'' +
                ", date='" + date + '\'' +
                ", taskTitle='" + taskTitle + '\'' +
                ", isAm=" + isAm +
                ", isDone=" + isDone +
                '}';
    }
}
