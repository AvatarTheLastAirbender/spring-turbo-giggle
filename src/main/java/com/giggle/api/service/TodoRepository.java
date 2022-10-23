package com.giggle.api.service;

import com.giggle.api.model.component.TodoInfo;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<TodoInfo, String> {
}
