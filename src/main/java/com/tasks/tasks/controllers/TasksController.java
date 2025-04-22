package com.tasks.tasks.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.tasks.tasks.models.Task;
// import org.springframework.beans.factory.annotation.Autowired;
import com.tasks.tasks.repositories.TasksRepository;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;





@RestController
@RequestMapping("/api/v1/tasks")
public class TasksController {

    final TasksRepository repo;

    public TasksController(TasksRepository repository){
        this.repo = repository;
    }

    @GetMapping("")
    public List<Task> findAll() {
        return repo.getTaskDetails();
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@Valid @RequestBody Task task) {
        repo.save(task);
    }
    
    @GetMapping("/{id}")
    public Task findById(@PathVariable Integer id) {
        return repo.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not"));
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        repo.deleteById(id);
    }
    
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody Task task) {
        if(repo.existsById(id)){
            repo.deleteById(id);
            repo.save(task);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "invalid task id");
        }
    }
     
}
