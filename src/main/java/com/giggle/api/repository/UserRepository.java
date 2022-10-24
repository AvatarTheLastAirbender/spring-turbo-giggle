package com.giggle.api.repository;

import com.giggle.api.model.component.UserInfo;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserInfo, Integer> {
}
