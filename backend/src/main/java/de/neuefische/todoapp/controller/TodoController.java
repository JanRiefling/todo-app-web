package de.neuefische.todoapp.controller;

import de.neuefische.todoapp.model.AddTodoData;
import de.neuefische.todoapp.model.IdObject;
import de.neuefische.todoapp.model.Status;
import de.neuefische.todoapp.model.Task;
import de.neuefische.todoapp.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api")
public class TodoController {

    private TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService){
        this.todoService = todoService;
    }

    @GetMapping("todo")
    public ArrayList<Task> getAllTodo(){
        return todoService.getTasks();
    }

    @PutMapping("todo")
    public Task addTodoItem(@RequestBody AddTodoData descriptionObject){
        return todoService.addDescription(descriptionObject.getDescription());
    }

    @GetMapping(value = "todo/{id}")
    public Task deleteTask(@PathVariable IdObject id){
        return todoService.deleteTask(id.getId());
    }

    @DeleteMapping("todo/{id}")
    public Task deleteTask2(@PathVariable IdObject id){
        return todoService.deleteTask(id.getId());
    }

}
