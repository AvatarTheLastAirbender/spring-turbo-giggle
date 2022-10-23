package com.giggle.api.service;

import com.giggle.api.model.component.TodoInfo;
import com.giggle.api.model.handler.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    public List<TodoInfo> getTodo() {
        List<TodoInfo> Todo = new ArrayList<>();
        todoRepository.findAll().forEach(Todo::add);
        return Todo;
    }

    public void addTodo(TodoInfo todoInfo) {
        todoRepository.save(todoInfo);
    }

    public void updateTodo(TodoInfo todoInfo) throws NotFoundException {
        List<TodoInfo> Todo = getTodo();
        TodoInfo updateTodo = Todo.stream().filter(todoInfo1 -> todoInfo1.getId() == todoInfo.getId()).findFirst().orElse(null);
        String message = todoInfo.getMessage();
        String date = todoInfo.getDate();
        if (updateTodo == null) throw new NotFoundException("Invalid Id");
        updateTodo.setMessage(message);
        updateTodo.setDate(date);
        System.out.println(updateTodo);
    }
}
