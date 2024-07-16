package org.digitinary.taskManagement.entities;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskProcessor {

    ExecutorService executorService;

    /**
     * this method creates a pool of threads of size 10
     */
    public TaskProcessor() {
        this.executorService =  Executors.newFixedThreadPool(10);
    }

    /**
     * this method execute a runnable task's run method
     * @param task
     */
    public void processTask(Task task) {
        executorService.execute(task);
    }

}
