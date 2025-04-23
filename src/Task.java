import java.util.Objects;

public class Task {
    private final int id;
    private bool managed;

    private String title;
    private String description;
    private Status status;

    public Task(int id, String title, String description, Status status) {
        this.id = id;
        this.managed = false;

        this.title = title;
        this.description = description;
        this.status = status;
    }

    public Task(String title, String description) {
        this(-1, title, description, Status.NEW);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (this.managed) {} else {
            this.id = id;
            this.managed = true;
        }
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}