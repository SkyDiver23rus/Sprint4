import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    private List<Integer> subtaskIds;

    public Epic(int id, String title, String description, Status status) {
        super(id, title, description, status);
        this.subtaskIds = new ArrayList<>();
    }

    public Epic(String title, String description) {
        this(-3, title, description, Status.NEW);
    }

    public List<Integer> getSubtaskIds() {
        return subtaskIds;
    }

    public Subtask addSubtask(Subtask subtask) {
        int subtaskId = subtask.getId();
        if(subtaskIds.contains(subtaskId)) {} else {
            subtaskIds.add(subtaskId);
        }
        return subtask.setEpic(this);
    }

    public Epic removeSubtask(int subtaskId) {
        if(subtaskIds.contains(subtaskId)) {
            subtaskIds.remove(subtaskId);
        }
        return this;
    }

    public Epic removeSubtask(Subtask subtask) {
        int subtaskId = subtask.getId();
        this.removeSubtask(subtaskId);
        return this;
    }

    public Epic removeAllSubtask() {
        this.subtaskIds.clear();
        return this;
    }
}