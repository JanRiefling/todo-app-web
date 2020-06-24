package de.neuefische.todoapp.controller;

import de.neuefische.todoapp.db.TodoDB;
import de.neuefische.todoapp.model.Status;
import de.neuefische.todoapp.model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TodoControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private TodoDB todoDB;


    @BeforeEach
    public void resetDatabase(){
        todoDB.clearDB();
    }


    @Test
    public void getAllTodoTodoDBLengthShouldEqual1() {
        //GIVEN
        todoDB.addTask(new Task("123", "Google RestTemplate", Status.OPEN));
        //WHEN
        ResponseEntity<Task[]> response = restTemplate.getForEntity("http://localhost:" + port + "/api/todo", Task[].class);
        HttpStatus statusCode = response.getStatusCode();
        Task[] tasks = response.getBody();

        //THEN
        assertEquals(HttpStatus.OK, statusCode);
        assertEquals(1, tasks.length);
    }

   /* @Test
    public void addTaskByDescriptionShouldBeTrue(){
        //GIVEN

        HttpEntity<Task> requestEntity = new HttpEntity<>(todoDB.createNewTaskWithDescription("Drink Water"));
        //WHEN
        ResponseEntity<Task> putResponse = restTemplate.exchange("http://localhost:" + port + "/api/todo", HttpMethod.PUT, requestEntity, Task.class);
        //String description = task.getDescription();
        //THEN
        assertEquals(HttpStatus.OK, putResponse.getStatusCode());
        assertEquals(new Task("A2", "Drink Water", Status.OPEN), putResponse.getBody());
        // Wat? assertTrue(todoDB.getTasks().contains(new Task("A2", "Drink Water", Status.OPEN)));
    }*/


    @Test
    public void deleteTaskWithPathVariableShouldReturnLength3(){
        //GIVEN
        todoDB.addTask(new Task("123", "Google RestTemplate", Status.OPEN));
        todoDB.addTask(new Task("423", "Drink Water", Status.OPEN));
        todoDB.addTask(new Task("523", "Google ResponseStatus", Status.IN_PROGRESS));
        todoDB.addTask(new Task("623", "Google Exception", Status.OPEN));
        todoDB.deleteTask("623");
        //WHEN
        ResponseEntity<Task[]> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "/api/todo", Task[].class);
        HttpStatus statusCode = responseEntity.getStatusCode();
        Task[] tasks = responseEntity.getBody();

        //THEN
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        assertEquals(3, tasks.length);
    }

    @Test
    public void updateTaskShouldReturnStatusOpenIsStatusOpen(){
        //GIVEN
        HttpEntity<Task> requestEntity = new HttpEntity<>(todoDB.createNewTaskWithDescription("Brush the bush"));
        //WHEN
        ResponseEntity<Task> putResponse = restTemplate.exchange("http://localhost:" + port + "/api/todo", HttpMethod.PUT, requestEntity, Task.class);

        //THEN
        assertEquals(Status.OPEN, requestEntity.getBody().getStatus());

    }

    /*@Test
    public void updateTaskShouldReturnStatusCheanged(){
        //GIVEN

        HttpEntity<AddTodoData> requestEntity = new HttpEntity<>(todoDB.updateStatus("134"));
        //WHEN
        ResponseEntity<Task> putResponse = restTemplate.exchange("http://localhost:" + port + "/api/todo", HttpMethod.PUT, requestEntity, Task.class);
       Task newTask = requestEntity.getBody();
        String id = requestEntity.getBody().getId();
        todoDB.
        HttpStatus responseStatusException = putResponse.getStatusCode();
        //THEN
        assertEquals(HttpStatus.BAD_REQUEST, responseStatusException);

    }*/


}
