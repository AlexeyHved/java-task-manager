
import manager.HttpTaskManager;
import server.KVServer;
import epic.Epic;
import epic.SubTask;
import epic.Task;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Map;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {
        KVServer kvServer = new KVServer();
        kvServer.start();
        HttpTaskManager httpTaskManager = new HttpTaskManager("http://localhost:");

        httpTaskManager.createTask(new Task("1-5", "12345",
                LocalDateTime.of(2023, Month.JUNE, 1, 0, 0), Duration.ofMinutes(10)));
        httpTaskManager.createTask(new Task("5-10", "5678910",
                LocalDateTime.of(2023, Month.JUNE, 2, 0, 0), Duration.ofMinutes(10)));
        httpTaskManager.createEpic(new Epic("drive", "moto"));
        httpTaskManager.createSubTask(new SubTask("sprint 3", "OOP", 3,
                LocalDateTime.of(2023, Month.JUNE, 5, 0, 0), Duration.ofMinutes(10)));
        httpTaskManager.createSubTask(new SubTask("learning", "Java course", 3,
                LocalDateTime.of(2023, Month.JUNE, 6, 0, 0), Duration.ofMinutes(10)));
        httpTaskManager.getTaskById(1);
        httpTaskManager.getTaskById(2);
        httpTaskManager.getEpicById(3);
        httpTaskManager.getEpicById(4);
        httpTaskManager.getSubTaskById(5);

        httpTaskManager.save();

        Map<Integer, Task> taskMap = httpTaskManager.getTaskMap();
        Map<Integer, Epic> epicMap = httpTaskManager.getEpicMap();
        Map<Integer, SubTask> subTaskMap = httpTaskManager.getSubTaskMap();
        Set<Task> prioritizedSet = httpTaskManager.getPrioritizedSet();

        httpTaskManager.save();

        HttpTaskManager otherManager = new HttpTaskManager("http://localhost:");
        otherManager.load();

        Map<Integer, Task> otherManagerTaskMap = otherManager.getTaskMap();
        Map<Integer, Epic> otherManagerEpicMap = otherManager.getEpicMap();
        Map<Integer, SubTask> otherManagerSubTaskMap = otherManager.getSubTaskMap();
        Set<Task> otherManagerPrioritizedSet = otherManager.getPrioritizedSet();

        System.out.println(taskMap);
        System.out.println(otherManagerTaskMap);
        System.out.println();
        System.out.println(epicMap);
        System.out.println(otherManagerEpicMap);
        System.out.println();
        System.out.println(subTaskMap);
        System.out.println(otherManagerSubTaskMap);
        System.out.println();
        System.out.println(prioritizedSet);
        System.out.println(otherManagerPrioritizedSet);

        kvServer.stop();
    }
}
