public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();


        Task task1 = manager.createTask(new Task("Переезд", "Переезд в новую квартиру"); // Создание задач и эпиков
        Task task2 = manager.createTask(new Task("Мебель", "Покупка мебели"));
        Epic epic1 = manager.createEpic(new Epic("Переезд", "Эпик для переезда"));


        Subtask subtask1 = manager.createSubtask(new Subtask("Упаковать вещи", "Упаковать все вещи в коробки", epic1)); // Создание подзадач
        Subtask subtask2 = manager.createSubtask(new Subtask("Покупка мебели", "Выбрать мебель", epic1));
        Subtask subtask3 = manager.createSubtask(new Subtask("Покупка мебели", "Заказать доставку", epic1));


        System.out.println("Все задачи: " + manager.getAllTasks()); // Вывод всех задач
        System.out.println("Все эпики: " + manager.getAllEpics());
        System.out.println("Все подзадачи: " + manager.getAllSubtasks());


        manager.removeTaskById(task1.getId());  // Удаление задач


        System.out.println("Оставшиеся задачи после удаления: " + manager.getAllTasks()); // Вывод оставшихся задач после удаления


        System.out.println("Подзадачи эпика: " + manager.getSubtasksByEpic(epic1.getId())); // Получение подзадач эпика


        subtask1.setStatus(Status.DONE); // Обновление статуса подзадачи и проверка статуса эпика


        manager.updateEpicStatus(epic1); // Обновление статуса эпика после изменения статуса подзадачи

        System.out.println("Статус эпика после завершения подзадачи: " + epic1.getStatus());


        manager.removeAllTasks(); // Удаление всех задач

        System.out.println("Все задачи после удаления всех: " + manager.getAllTasks());
    }
}