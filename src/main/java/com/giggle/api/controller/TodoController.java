package com.giggle.api.controller;

import com.giggle.api.model.component.TodoInfo;
import com.giggle.api.model.handler.success.SuccessResponse;
import com.giggle.api.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/todo")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping(path = "")
    public ResponseEntity<?> getTodoList() {
        return new ResponseEntity<>(todoService.getTodo(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getTodo(@PathVariable("id") int id) {
        return new ResponseEntity<>(todoService.getTodoById(id), HttpStatus.OK);
    }

    @PostMapping(path = "", consumes = "Application/Json", produces = "Application/Json")
    public ResponseEntity<?> addTodo(@RequestBody @Valid TodoInfo todoInfo) {
        todoService.addTodo(todoInfo);
        return new ResponseEntity<>(new SuccessResponse("New Todo Added"), HttpStatus.CREATED);
    }

    @PutMapping(path = "", consumes = "Application/Json", produces = "Application/Json")
    public ResponseEntity<?> updateTodo(@RequestBody @Valid TodoInfo todoInfo) {
        todoService.updateTodo(todoInfo);
        return new ResponseEntity<>(new SuccessResponse("Todo Updated"), HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> removeTodo(@PathVariable("id") int id) {
        todoService.removeTodo(id);
        return new ResponseEntity<>(new SuccessResponse("Todo Deleted"), HttpStatus.ACCEPTED);
    }
}
