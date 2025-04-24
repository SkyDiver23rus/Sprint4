import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class TaskManager {
    private final HashMap<Integer, Task> tasks = new HashMap<>();
    private final HashMap<Integer, Subtask> subtasks = new HashMap<>();
    private final HashMap<Integer, Epic> epics = new HashMap<>();

    private int idCounter = 0;

    private int generateNextId(){
        return idCounter++;
    }

    public Task createTask(Task task) {
        task.setId(generateNextId());
        tasks.put(task.getId(), task);
        return task;
    }

    public Subtask createSubtask(Subtask subtask) {
        subtask.setId(generateNextId());
        subtask.getEpic().addSubtask(subtask);
        subtasks.put(subtask.getId(), subtask);
        return subtask;
    }

    public Epic createEpic(Epic epic) {
        epic.setId(generateNextId());
        epics.put(epic.getId(), epic);
        return epic;
    }

    public Epic updateEpicStatus(Epic epic) {
        if (epic.getSubtaskIds().isEmpty()) {
            epic.setStatus(Status.NEW);
            return epic;
        }

        boolean allDone = true; boolean allNew = true; 

        for (int subtaskId : epic.getSubtaskIds()) {
            Status status = subtasks.get(subtaskId).getStatus();
            switch(status){
                case Status.IN_PROGRESS: 
                    epic.setStatus(Status.IN_PROGRESS);
                    return epic; 

                case Status.NEW: 
                    allDone = false;
                    break; 
                case Status.DONE: 
                    allNew = false;
                    break; 
            }
        }

        if (allDone) {
            epic.setStatus(Status.DONE);
        } else if (allNew){
            epic.setStatus(Status.NEW);
        } else {
            // Нет ни одного in_progress, но зато есть N done и M new
            // Таск не новый, но и не выполнен.. а значит in_progress
            epic.setStatus(Status.IN_PROGRESS);
        }

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


    public void removeTask(int id) {
        this.tasks.remove(id);
    }
    public void removeTask(Task task) {
        if (task == null) {} else {
            this.tasks.remove(task.getId());
        }
    }

    public void removeSubtask(int id) {
        Subtask subtask = subtasks.remove(id);
        this.removeSubtask(subtask);
    }
    public void removeSubtask(Subtask subtask) {
        if (subtask == null) {} else {
            Epic epic = subtask.getEpic();
            epic.removeSubtask(subtask);
            this.updateEpicStatus(epic); 
            this.epics.put(epic.getId(), epic);
        }
    }

    public void removeEpic(int id) {
        Epic epic = epics.remove(id);
        this.removeEpic(epic);
    }
    public void removeEpic(Epic epic) {
        if (epic == null) {} else {
            for(int subtaskId : epic.getSubtaskIds()){
                epic.removeSubtask(subtaskId);
                subtasks.remove(subtaskId);
            }
        }
    }

    public void removeAllTasks() {
        this.tasks.clear();
    }

    public void removeAllSubTasks() {
        for(Epic epic : this.getAllEpics()){
            epic.removeAllSubtask();
            this.updateEpicStatus(epic); 
        }
        this.subtasks.clear();
    }

    public void removeAllEpic() {
        this.epics.clear();
        this.subtasks.clear();    
    }

    public void removeAll() {
        this.removeAllTasks();
        this.removeAllSubTasks();
        this.removeAllEpic();
    }


    public List<Subtask> getSubtasksByEpic(int epicId) {
        if (this.epics.containsKey(epicId)){
            return this.getSubtasksByEpic(this.epics.get(epicId));
        } else {
            return null;
        }
    }
    public List<Subtask> getSubtasksByEpic(Epic epic) {
        if (epic == null) return null;

        List<Subtask> result = new ArrayList<>();
        for (int subtaskId : epic.getSubtaskIds()) {
            result.add(getSubtaskById(subtaskId));
        }
        return result;
    }
}