import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    private List<Integer> subtaskIds;

    public Epic(int id, String title, String description) {
        super(id, title, description, Status.NEW);
        this.subtaskIds = new ArrayList<>();
    }

    public List<Integer> getSubtaskIds() {
        return subtaskIds;
    }

    public void addSubtask(int subtaskId) {

        subtaskIds.add(subtaskId);
    }

    public void updateStatus(TaskManager taskManager) {
        if (subtaskIds.isEmpty()) {
            setStatus(Status.NEW);
            return;
        }

        boolean allDone = true;
        boolean anyInProgress = false;

        for (int subtaskId : subtaskIds) {
            Status status = taskManager.getSubtaskById(subtaskId).getStatus();

            if (status != Status.DONE) {
                allDone = false;
                if (status == Status.IN_PROGRESS) {
                    anyInProgress = true;
                }
            }
        }

        if (allDone) {
            setStatus(Status.DONE);
        } else if (anyInProgress) {
            setStatus(Status.IN_PROGRESS);
        } else {
            setStatus(Status.NEW);
        }
    }
}
