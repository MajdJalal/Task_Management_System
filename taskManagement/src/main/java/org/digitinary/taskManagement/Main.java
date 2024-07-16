package org.digitinary.taskManagement;

import org.digitinary.taskManagement.Exceptions.InvalidEmailException;
import org.digitinary.taskManagement.Exceptions.InvalidIdException;
import org.digitinary.taskManagement.entities.Project;
import org.digitinary.taskManagement.entities.Task;
import org.digitinary.taskManagement.entities.TaskProcessor;
import org.digitinary.taskManagement.entities.User;

import java.time.LocalDate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //create a new User
        User user = null;
        try{
            user = new User("123", "majd", "mjd@gmail.com");
        }
        catch (InvalidEmailException | InvalidIdException e) {
            System.out.println(e.getMessage() + " please try again");
            System.exit(1);
        }

        user.addUser();
        //create new task
        Task t1 = new Task( "123", "Report", "Complete report", false, 1, LocalDate.of(2024, 7, 15));
        Task t2= new Task( "1234", "Report2", "Complete exam", false, 3, LocalDate.of(2024, 9, 15));
        t1.addTask();
        t2.addTask();
        //create a new project
        Project proj = new Project(Task.tasksList);//assign all the tasks available to this project


        //executing all tasks in project proj
        TaskProcessor taskProcessor = new TaskProcessor();
        for(Task t : proj.getTasks().values()) {
            if(t.isStatus()) continue;
            taskProcessor.processTask(t);
        }



    }
}