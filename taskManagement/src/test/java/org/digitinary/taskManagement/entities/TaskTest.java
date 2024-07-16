package org.digitinary.taskManagement.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {


    @BeforeEach
    void setUp() {
        Task.tasksList.clear();  // Reset the shared usersList before each test
    }

    /**
     * Tests that adding two tasks updates the tasks list size correctly.
     */
    @Test
    void tasksListShouldGetUpdatedByTwo(){
        int len = Task.tasksList.size();
        Task t1  = new Task("123");
        Task t2  = new Task("1234");
        t1.addTask();
        t2.addTask();
        assertEquals(Task.tasksList.size(), len + 2);
    }

    /**
     * Tests that adding a task that already exists returns false.
     */
    @Test
    void shouldGetFalseForAlreadyExistingTaskAdd() {
        Task t1  = new Task("123");
        t1.addTask();
        assertFalse(t1.addTask());
    }
    /**
     * Tests that removing a task that does not exist returns false.
     */
    @Test
    void shouldGetFalseForAlreadyExistingTaskRemove() {
        Task t1  = new Task("123");;
        assertFalse(t1.removeTask());
    }

    /**
     * Tests that removing a task decreases the tasks list size correctly.
     */
    @Test
    void tasksListShouldGetDecreasedByOne(){
        int len = Task.tasksList.size();
        Task t1  = new Task("123");
        Task t2  = new Task("1234");
        t1.addTask();
        t2.addTask();
        t1.removeTask();
        assertEquals(Task.tasksList.size(), len + 1);
    }

    /**
     * Tests that updating task values works correctly.
     */
    @Test
    void taskValuesShouldGetUpdated() {
        Task t1 = new Task( "123", "Report", "Complete report", false, 1, LocalDate.of(2024, 7, 15));
        t1.addTask();
        t1.updateTask( "Finish exam", "exam", false, 1, LocalDate.of(2024, 8, 16));
        Task t = Task.tasksList.get(t1.getId());
        assertEquals(t.getTitle(), "Finish exam");
        assertEquals(t.getDescription(), "exam");
        assertEquals(t.getDueDate(), LocalDate.of(2024, 8, 16));
    }

    /**
     * Tests that two tasks with status set to true are counted as done tasks.
     */
    @Test
    void shouldGetTwoDoneTasks(){
        Task t1  = new Task("123");
        Task t2  = new Task("1234");
        t1.setStatus(true);
        t2.setStatus(true);
        t1.addTask();
        t2.addTask();
        assertEquals(2, Task.getDoneTasks().size());
    }

    /**
     * Tests that one task with status set to true is counted as a done task.
     */
    @Test
    void shouldGetOneDoneTasks(){
        Task t1  = new Task("123");
        Task t2  = new Task("1234");
        t1.setStatus(true);
        t1.addTask();
        t2.addTask();
        assertEquals(1, Task.getDoneTasks().size());
    }

    /**
     * Tests that two tasks with status set to false are counted as waiting tasks.
     */
    @Test
    void shouldGetTwoWaitingTasks(){
        Task t1  = new Task("123");
        Task t2  = new Task("1234");
        t1.addTask();
        t2.addTask();
        assertEquals(2, Task.getWaitingTasks().size());
    }

    /**
     * Tests that two tasks with priority set to 2 are counted correctly.
     */
    @Test
    void shouldGetTwoTasksOfPriority2(){
        Task t1  = new Task("123");
        Task t2  = new Task("1234");
        Task t3  = new Task("12345");
        Task t4  = new Task("123456");
        t1.setPriority(2);
        t2.setPriority(1);
        t3.setPriority(2);
        t4.setPriority(5);
        t1.addTask();
        t2.addTask();
        t3.addTask();
        t4.addTask();
        assertEquals(2, Task.getTasksOfPriority(2).size());
    }

    /**
     * Tests that two tasks with specific due date are counted correctly.
     */
    @Test
    void shouldGetTwoOfDateTasks(){
        Task t1  = new Task("123");
        Task t2  = new Task("1234");
        Task t3  = new Task("12345");
        Task t4  = new Task("123456");
        t1.setDueDate(LocalDate.of(2024, 7, 15));
        t2.setDueDate(LocalDate.of(2025, 8, 17));
        t3.setDueDate(LocalDate.of(2023, 1, 12));
        t4.setDueDate(LocalDate.of(2024, 7, 15));
        t1.addTask();
        t2.addTask();
        t3.addTask();
        t4.addTask();
        assertEquals(2, Task.getTasksOfDate(LocalDate.of(2024, 7, 15)).size());
    }


}