public class Subtask extends Task {
    private Epic epic;

    public Subtask(int id, String title, String description, Status status, Epic epic) {
        super(id, title, description, status);
        this.epic = epic;
    }

    public Subtask(String title, String description, Epic epic) {
        this(-2, title, description, Status.NEW, epic);
    }

    public int getEpic() {
        return epic;
    }

    // А вдруг мы решим переместить сабтаск в другой Эпик ??
    public Subtask setEpic(Epic epic){
        this.epic = epic;
        return this;
    }
}