public class Main {

    /*
    Все задачи: [Task(id=0), Task(id=1)]
    Все эпики: [Epic(id=2)]
    Все подзадачи: [
        SubTask(id=3,of=Epic(id=2)), 
        SubTask(id=4,of=Epic(id=2)), 
        SubTask(id=5,of=Epic(id=2))
    ]
    Оставшиеся задачи после удаления: [Task(id=1)]
    Подзадачи эпика: [
        SubTask(id=3,of=Epic(id=2)), 
        SubTask(id=4,of=Epic(id=2)), 
        SubTask(id=5,of=Epic(id=2))
    ]
    Статус эпика после завершения подзадачи: IN_PROGRESS
    Все задачи после удаления всех: []
    */

    public static void main(String[] args) {
        TaskManager manager = new TaskManager();

        // Создание задач и эпиков
        Task task1 = manager.attachTask(new Task("Переезд", "Переезд в новую квартиру")); 
        Task task2 = manager.createTask("Мебель", "Покупка мебели");
        Epic epic1 = manager.attachEpic(new Epic("Переезд", "Эпик для переезда"));

        // Создание подзадач
        Subtask subtask1 = manager.attachSubtask(new Subtask("Упаковать вещи", "Упаковать все вещи в коробки", epic1)); 
        Subtask subtask2 = manager.attachSubtask(new Subtask("Покупка мебели", "Выбрать мебель", epic1));
        Subtask subtask3 = manager.createSubtask("Покупка мебели", "Заказать доставку", epic1);

        // Вывод всех задач
        System.out.println("Все задачи: " + manager.getAllTasks()); 
        System.out.println("Все эпики: " + manager.getAllEpics());
        System.out.println("Все подзадачи: " + manager.getAllSubtasks());

        // Удаление задач
        manager.removeTask(task1);  

        // Вывод оставшихся задач после удаления
        System.out.println("Оставшиеся задачи после удаления: " + manager.getAllTasks()); 
        // Получение подзадач эпика
        System.out.println("Подзадачи эпика: " + manager.getSubtasksByEpic(epic1)); 
        // Обновление статуса подзадачи и проверка статуса эпика
        subtask1.setStatus(Status.DONE); 
        // Обновление статуса эпика после изменения статуса подзадачи
        manager.updateEpicStatus(epic1); 

        System.out.println("Статус эпика после завершения подзадачи: " + epic1.getStatus());

        // Удаление всех задач
        manager.removeAllTasks(); 
        System.out.println("Все задачи после удаления всех: " + manager.getAllTasks());
    }
}