package de.neuefische.todoapp.db;

import de.neuefische.todoapp.model.Status;
import de.neuefische.todoapp.model.Task;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TodoDB {

    private ArrayList<Task> tasks = new ArrayList<>();

    public TodoDB() {
        tasks.add(new Task("efb23", "Buy Milk", Status.OPEN));
        tasks.add(new Task("12", "Buy Cookies", Status.IN_PROGRESS));
        tasks.add(new Task("123", "Boil Milk", Status.OPEN));
        tasks.add(new Task("1234", "Throw Milk", Status.DONE));
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public String getDescriptionFromTodoDB(int index){
        return tasks.get(index).getDescription();
    }

    public void clearDB() {
        tasks.clear();
    }

    public void addTask(Task task){
        tasks.add(task);
    }

    public Task createNewTaskWithDescription(String description){
        int id = 1;
        id++;
        String newId = "A" + id;
        Task newTask = new Task(newId, description, Status.OPEN);
        return newTask;
    }
}
