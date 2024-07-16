package org.digitinary.taskManagement.entities;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskProcessor {

    ExecutorService executorService;

    public TaskProcessor() {
        this.executorService =  Executors.newFixedThreadPool(10);
    }

    public void processTask(Task task) {
        executorService.execute(task);
    }

}
