package org.digitinary.taskManagement.entities;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Task class
 */
public class Task implements Runnable{

    public static Map<String, Task> tasksList = new HashMap<>();
    private final String id;
    private String title;
    private String description;
    private boolean status;//true-> done false -> waiting
    private int priority;//higher the num , more important
    private LocalDate dueDate;

    private final Object lock = new Object();

    /**
     * @param id
     * @param title
     * @param description
     * @param status
     * @param priority
     * @param dueDate
     */
    public Task(String id, String title, String description, boolean status, int priority, LocalDate dueDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.dueDate = dueDate;
    }


    /**
     * @param id
     */
    public Task(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }



    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isStatus() {
        return status;
    }

    public int getPriority() {
        return priority;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    /**
     * add task to the collection of tasks we have
     * @return true if successfully added , false if already exists
     */
    public boolean addTask(){
        if(tasksList.containsKey(this.getId())) return false;
        tasksList.put(this.getId(), this);
        return true;
    }

    /**
     * remove task from the collection of tasks we have
     * @return true if successfully removed , false if doesnt exist
     */
    public boolean removeTask() {
        if(!tasksList.containsKey(this.getId())) return false;
        tasksList.remove(this.getId());
        return true;
    }

    /**
     * update the existing task(in the collection of tasks we have) with a set of new values
     * @param title
     * @param description
     * @param status
     * @param priority
     * @param dueDate
     * @return true if successfully updated , false if doesnt exist
     */
    public boolean updateTask(String title, String description, boolean status, int priority, LocalDate dueDate) {
        if(!tasksList.containsKey(this.getId())) return false;
        Task updatedTask = new Task(this.getId(), title, description, status, priority, dueDate);
        tasksList.put(this.getId(), updatedTask);
        return true;
    }

    /**
     * @return a list of all done tasks(status == true)
     */
    public static List<Task> getDoneTasks(){
        Stream<Task> stream = tasksList.values().stream();
        return stream.filter(Task::isStatus).collect(Collectors.toList());
    }

    /**
     * @return  list of all waiting tasks(status == false)
     */
    public static List<Task> getWaitingTasks(){
        Stream<Task> stream = tasksList.values().stream();
        return stream.filter(t -> !t.isStatus()).collect(Collectors.toList());
    }

    /**
     * @param priority
     * @return all tasks of the passed priority
     */
    public static List<Task> getTasksOfPriority(int priority){
        Stream<Task> stream = tasksList.values().stream();
        return stream.filter(t -> t.getPriority() == priority).collect(Collectors.toList());
    }

    /**
     * @param newDate
     * @return all tasks that match with the passed date
     */
    public static List<Task> getTasksOfDate(LocalDate newDate){
        Stream<Task> stream = tasksList.values().stream();
        return stream.filter(t -> t.getDueDate().isEqual(newDate)).collect(Collectors.toList());
    }


    @Override
    public void run() {
        synchronized (lock) {
            System.out.println(this.getId());
            System.out.println(this.getDescription());
            System.out.println(this.isStatus());
            System.out.println(this.getTitle());
            System.out.println(this.getPriority());
            System.out.println(this.getDueDate());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            this.setStatus(true);
            System.out.println("Task " + this.getId() + " is completed on thread " + Thread.currentThread().getName());
        }



    }
}
