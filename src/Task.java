import java.util.Objects;

public class Task {
    private int id;

    private String title;
    private String description;
    private Status status;

    public Task(int id, String title, String description, Status status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
    }

    // Перегруженный конструктор, потому что все таски по умолчанию новые
    public Task(String title, String description) {
        this(-999, title, description, Status.NEW);
    }

    public int getId() {
        return id;
    }

    public void setId(int id, boolean forced) {
        if (forced || this.id < 0) {
            this.id = id;
        }
    }

    public void setId(int id) {
        this.setId(id, false);
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

    // Возвращение this позволяет писать fluent код
    public Task setStatus(Status status) {
        this.status = status;
        return this;
    }


    @Override
    public String toString() {
        return this.getClass().getName() + "(id=" + String.valueOf(this.getId()) + ")";
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