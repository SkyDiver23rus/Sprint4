import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    private List<Integer> subtaskIds;

    public Epic(int id, String title, String description, Status status) {
        super(id, title, description, status);
        this.subtaskIds = new ArrayList<>();
    }

    // Перегруженный конструктор, потому что все эпики по умолчанию новые
    // до того как к ним добавят сабтаски 
    public Epic(String title, String description) {
        this(-997, title, description, Status.NEW);
    }

    public List<Integer> getSubtaskIds() {
        return subtaskIds;
    }

    public Epic addSubtask(Subtask subtask) {
        int subtaskId = subtask.getId();
        if(subtaskIds.contains(subtaskId)) {} else {
            subtaskIds.add(subtaskId);
        }
        subtask.setEpic(this);
        return this;
    }

    // Удаление сабтаска из этого эпика по id
    public Epic removeSubtask(int subtaskId) {
        if(subtaskIds.contains(subtaskId)) {
            subtaskIds.remove(subtaskId);
        }
        return this;
    }

    // Удаление сабтаска из этого эпика по экземпляру (перегруженная версия)
    public Epic removeSubtask(Subtask subtask) {
        int subtaskId = subtask.getId();
        this.removeSubtask(subtaskId);
        return this;
    }

    // Удаление всех сабтасков из этого эпика
    public Epic removeAllSubtask() {
        this.subtaskIds.clear();
        return this;
    }
}