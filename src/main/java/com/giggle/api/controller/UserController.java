package com.giggle.api.controller;

import com.giggle.api.model.component.UserInfo;
import com.giggle.api.model.handler.success.SuccessResponse;
import com.giggle.api.service.SuperUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private SuperUserService superUserService;

    @PostMapping(path = "")
    public ResponseEntity<?> addUser(@RequestBody @Valid UserInfo userInfo) {
        superUserService.addUser(userInfo);
        return new ResponseEntity<>(new SuccessResponse("User created"), HttpStatus.CREATED);
    }

    @GetMapping(path = "")
    public ResponseEntity<?> getUsers() {
        return new ResponseEntity<>(superUserService.getUsers(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") int id) {
        return new ResponseEntity<>(superUserService.getUserById(id), HttpStatus.OK);
    }

    @PutMapping(path = "")
    public ResponseEntity<?> updateUser(@RequestBody UserInfo userInfo) {
        superUserService.updateUser(userInfo);
        return new ResponseEntity<>(new SuccessResponse("User details updated"), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") int id) {
        superUserService.deleteUser(id);
        return new ResponseEntity<>(new SuccessResponse("User details removed"), HttpStatus.OK);
    }
}
