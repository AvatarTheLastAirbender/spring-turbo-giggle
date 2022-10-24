package com.giggle.api.service;

import com.giggle.api.model.component.TodoInfo;
import com.giggle.api.model.handler.exception.NotFoundException;
import com.giggle.api.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    public List<TodoInfo> getTodo() {
        List<TodoInfo> Todo = new ArrayList<>();
        todoRepository.findAll().forEach(Todo::add);
//        updateAllRepeatedTasks(Todo);
        return Todo;
    }

    private void updateAllRepeatedTasks(List<TodoInfo> ListOfTodo) {
        ListOfTodo.forEach(todoInfo -> {
            if (todoInfo.getRepeated()) {
                System.out.println(Date.parse(todoInfo.getDate()));
            }
        });
    }

    public void addTodo(TodoInfo todoInfo) {
        todoRepository.save(todoInfo);
    }

    public void updateTodo(TodoInfo todoInfo) throws NotFoundException {
        TodoInfo updateTodo = getTodo().stream().filter(todoInfo1 -> todoInfo1.getId() == todoInfo.getId()).findFirst().orElse(null);
        if (updateTodo == null) throw new NotFoundException("Invalid Id");
        addTodo(todoInfo);
    }

    public void removeTodo(int id) {
        TodoInfo deleteRequestedTodo = getTodo().stream().filter(todoInfo1 -> todoInfo1.getId() == id).findFirst().orElse(null);
        if (deleteRequestedTodo == null) throw new NotFoundException("Invalid Id");
        todoRepository.delete(deleteRequestedTodo);
    }

    public Object getTodoById(int id) {
        TodoInfo requestedTodo = getTodo().stream().filter(todoInfo1 -> todoInfo1.getId() == id).findFirst().orElse(null);
        if (requestedTodo == null) throw new NotFoundException("Invalid Id");
        return requestedTodo;
    }
}
