package com.kaiburr.taskapi.controller;

import com.kaiburr.taskapi.model.Task;
import com.kaiburr.taskapi.model.TaskExecution;
import com.kaiburr.taskapi.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskController {
    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping("/tasks")
    public List<Task> list() {
        return service.findAll();
    }

    @PutMapping("/tasks")
    public Task create(@RequestBody Task t) {
        return service.save(t);
    }

    @PutMapping("/tasks/{id}/execute")
    public TaskExecution exec(@PathVariable String id) throws Exception {
        return service.executeLocally(id);
    }

    @DeleteMapping("/tasks/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
