package org.digitinary.taskManagement.entities;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Project {

    private Map<String, Task> tasks;

    /**
     * empty constructor
     */
    public Project() {
         this.tasks = new HashMap<>();
    }


    /**
     * @param tasks
     */
    public Project(Map<String, Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * @return the tasks
     */
    public Map<String, Task> getTasks() {
        return tasks;
    }

    /**
     * @param task
     * @return true if added, false if already exists
     */
    public boolean addTask(Task task){
        if(tasks.containsKey(task.getId())) return false;
        tasks.put(task.getId(), task);//O(1)
        return true;
    }

    /**
     * @param id
     * @return true if removed, false if doesnt exist
     */
    public boolean removeTask(String id){
        if(!tasks.containsKey(id)) return false;
        tasks.remove(id);//O(1)
        return true;
    }

    /**
     * @return number of done tasks in this project
     */
    public int getNumOfDoneTasks(){
        Stream<Task> stream = tasks.values().stream();
        return (int) stream.filter(Task::isStatus).count();
    }

    /**
     * @return number of waiting tasks in this project
     */
    public int getNumOfWaitingTasks(){
        Stream<Task> stream = tasks.values().stream();
        return (int) stream.filter(t -> !t.isStatus()).count();
    }

    /**
     * sort the tasks in the project based on the priority(highest to lowest)
     * @return sorted tasks based on priority
     */
    public List<Task> sortTasksByPriority(){
        return tasks.values().stream().sorted(new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                return t2.getPriority() - t1.getPriority();
            }
        }).collect(Collectors.toList());
    }

    /**
     * @return list of all tasks that exceeded the DueDate and not completed
     */
    public List<Task> getTasksThatExceededDueDate() {
        LocalDate currDate = LocalDate.now();
        return tasks.values().stream().filter(t -> t.getDueDate().isBefore(currDate) && !t.isStatus())
                .collect(Collectors.toList());
    }








}
