public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();


        Task task1 = manager.createTask("Переезд", "Переезд в новую квартиру", Status.NEW); // Создание задач и эпиков
        Task task2 = manager.createTask("Мебель", "Покупка мебели", Status.NEW);
        Epic epic1 = manager.createEpic("Переезд", "Эпик для переезда");


        Subtask subtask1 = manager.createSubtask("Упаковать вещи", "Упаковать все вещи в коробки", Status.NEW, epic1.getId()); // Создание подзадач
        Subtask subtask2 = manager.createSubtask("Покупка мебели", "Выбрать мебель", Status.NEW, epic1.getId());
        Subtask subtask3 = manager.createSubtask("Покупка мебели", "Заказать доставку", Status.NEW, epic1.getId());


        System.out.println("Все задачи: " + manager.getAllTasks()); // Вывод всех задач
        System.out.println("Все эпики: " + manager.getAllEpics());
        System.out.println("Все подзадачи: " + manager.getAllSubtasks());


        manager.removeTaskById(task1.getId());  // Удаление задач


        System.out.println("Оставшиеся задачи после удаления: " + manager.getAllTasks()); // Вывод оставшихся задач после удаления


        System.out.println("Подзадачи эпика: " + manager.getSubtasksByEpic(epic1.getId())); // Получение подзадач эпика


        subtask1.setStatus(Status.DONE); // Обновление статуса подзадачи и проверка статуса эпика


        epic1.updateStatus(manager); // Обновление статуса эпика после изменения статуса подзадачи

        System.out.println("Статус эпика после завершения подзадачи: " + epic1.getStatus());


        manager.removeAllTasks(); // Удаление всех задач

        System.out.println("Все задачи после удаления всех: " + manager.getAllTasks());
    }
}