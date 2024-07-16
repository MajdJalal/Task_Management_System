package org.digitinary.taskManagement.entities;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ProjectTest {


    /**
     * tests that tasks get added successfully
     */
    @Test
    void lenOfTasksShouldBeTwoAdd() {
        Project project = new Project();
        project.addTask(new Task("123"));
        project.addTask(new Task("1234"));
        assertEquals(2, project.getTasks().size());
    }
    /**
     * tests that tasks get removed successfully
     */
    @Test
    void lenOfTasksShouldBeOneRemove() {
        Project project = new Project();
        project.addTask(new Task("123"));
        project.addTask(new Task("1234"));
        project.removeTask("123");
        assertEquals(1, project.getTasks().size());
    }
    /**
     * tests that i get the right num of done tasks
     */
    @Test
    void numOfDoneTasksShouldBeZero() {
        Project project = new Project();
        project.addTask(new Task("123"));
        project.addTask(new Task("1234"));
        project.addTask(new Task("12345"));
        project.addTask(new Task("324"));
        assertEquals(0, project.getNumOfDoneTasks());
    }
    /**
     * tests that i get the right num of waiting tasks
     */
    @Test
    void numOfWaitingTasksShouldBeFour() {
        Project project = new Project();
        project.addTask(new Task("123"));
        project.addTask(new Task("1234"));
        project.addTask(new Task("12345"));
        project.addTask(new Task("324"));
        assertEquals(4, project.getNumOfWaitingTasks());
    }

    /**
     * test that i return expired tasks that are not completed
     */
    @Test
    void numOfexceededUncompletedTasksShouldBeTwo() {
        Project project = new Project();
        Task t1  = new Task("123");
        Task t2  = new Task("1234");
        Task t3 = new Task("12345");
        Task t4  = new Task("123456");
        t1.setStatus(true);
        t2.setStatus(true);
        t1.setDueDate(LocalDate.of(2024, 8, 12));
        t2.setDueDate(LocalDate.of(2024, 7, 12));
        t3.setDueDate(LocalDate.of(2024, 6, 12));
        t4.setDueDate(LocalDate.of(2024, 7, 12));
        project.addTask(t1);
        project.addTask(t2);
        project.addTask(t3);
        project.addTask(t4);
        assertEquals(2, project.getTasksThatExceededDueDate().size());
    }




}
