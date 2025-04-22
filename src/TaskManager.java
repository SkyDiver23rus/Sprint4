import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class TaskManager {
    private final HashMap<Integer, Task> tasks = new HashMap<>();
    private final HashMap<Integer, Subtask> subtasks = new HashMap<>();
    private final HashMap<Integer, Epic> epics = new HashMap<>();

    private int idCounter = 0;


    public Task createTask(String title, String description, Status status) {
        Task task = new Task(++idCounter, title, description, status);
        tasks.put(task.getId(), task);
        return task;
    }


    public Subtask createSubtask(String title, String description, Status status, int epicId) {
        Subtask subtask = new Subtask(++idCounter, title, description, status, epicId);
        subtasks.put(subtask.getId(), subtask);


        if (epics.containsKey(epicId)) {
            epics.get(epicId).addSubtask(subtask.getId());
        }
        return subtask;
    }


    public Epic createEpic(String title, String description) {
        Epic epic = new Epic(++idCounter, title, description);
        epics.put(epic.getId(), epic);
        return epic;
    }


    public Task getTaskById(int id) {
        return tasks.get(id);
    }


    public Subtask getSubtaskById(int id) {
        return subtasks.get(id);
    }


    public Epic getEpicById(int id) {
        return epics.get(id);
    }


    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }


    public List<Subtask> getAllSubtasks() {
        return new ArrayList<>(subtasks.values());
    }


    public List<Epic> getAllEpics() {
        return new ArrayList<>(epics.values());
    }


    public void removeTaskById(int id) {
        tasks.remove(id);
    }


    public void removeSubtaskById(int id) {
        Subtask subtask = subtasks.remove(id);

        if (subtask != null) {
            Epic epic = epics.get(subtask.getEpicId());
            if (epic != null) {
                epic.getSubtaskIds().remove(Integer.valueOf(subtask.getId()));
                epic.updateStatus(this); // Обновляем статус эпика
            }
        }
    }


    public void removeEpicById(int id) {
        epics.remove(id);
    }


    public void removeAllTasks() {
        tasks.clear();
        subtasks.clear();
        epics.clear();
    }


    public List<Subtask> getSubtasksByEpic(int epicId) {
        List<Subtask> result = new ArrayList<>();
        if (epics.containsKey(epicId)) {
            for (int subtaskId : epics.get(epicId).getSubtaskIds()) {
                result.add(getSubtaskById(subtaskId));
            }
        }
        return result;
    }
}