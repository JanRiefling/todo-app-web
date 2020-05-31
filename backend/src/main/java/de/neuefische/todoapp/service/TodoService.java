package de.neuefische.todoapp.service;

import de.neuefische.todoapp.db.TodoDB;
import de.neuefische.todoapp.model.Status;
import de.neuefische.todoapp.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {

    private TodoDB todoDB;

    @Autowired
    public TodoService(TodoDB todoDB){
        this.todoDB = todoDB;
    }

    public ArrayList<Task> getTasks(){
        return todoDB.getTasks();
    }

    public void addTask(Task task){
        todoDB.addTask(task);
    }


    public Task addDescription(String description) {
        return todoDB.createNewTaskWithDescription(description);
    }
}
