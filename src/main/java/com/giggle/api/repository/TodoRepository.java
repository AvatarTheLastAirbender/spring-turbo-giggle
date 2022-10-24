package com.giggle.api.repository;

import com.giggle.api.model.component.TodoInfo;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<TodoInfo, Integer> {
}

