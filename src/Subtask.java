public class Subtask extends Task {
    private Epic epic;

    public Subtask(int id, String title, String description, Status status, Epic epic) {
        super(id, title, description, status);
        this.epic = epic;
    }

    // Перегруженный конструктор, потому что все сабтаски по умолчанию новые
    public Subtask(String title, String description, Epic epic) {
        this(-998, title, description, Status.NEW, epic);
    }

    // Возвращаем ссылку на эпик
    public Epic getEpic() {
        return epic;
    }

    // Реализация не используется. 
    // Но вдруг мы решим переместить сабтаск в другой Эпик ))
    public Subtask setEpic(Epic epic){
        this.epic = epic;
        return this;
    }
    
    @Override
    public String toString() {
        return "SubTask(id=" + String.valueOf(this.getId()) + ",of=" + this.getEpic() + ")";
    }
}