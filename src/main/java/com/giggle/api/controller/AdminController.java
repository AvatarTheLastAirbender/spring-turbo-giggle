package com.giggle.api.controller;

import com.giggle.api.model.component.TodoInfo;
import com.giggle.api.model.component.CustomUserDetails;
import com.giggle.api.model.handler.auth.AuthenticationRequest;
import com.giggle.api.model.handler.auth.AuthenticationResponse;
import com.giggle.api.model.handler.success.SuccessResponse;
import com.giggle.api.security.util.JwtUtil;
import com.giggle.api.service.SuperUserService;
import com.giggle.api.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminController {

    @Autowired
    private TodoService todoService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private SuperUserService superUserService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping(path = "/auth", consumes = "Application/Json", produces = "Application/Json")
    public ResponseEntity<?> adminAuthentication(@RequestBody AuthenticationRequest request) {
        System.out.println(request.getUsername() + " " + request.getPassword());
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (BadCredentialsException e) {
            new ResponseEntity<>(new SuccessResponse("Invalid credentials"), HttpStatus.NOT_ACCEPTABLE);
        }
        final CustomUserDetails customUserDetails = superUserService.loadUserByUsername(request.getUsername());
        final String jwt = jwtUtil.generateToken(customUserDetails);
        return new ResponseEntity<>(new AuthenticationResponse(jwt), HttpStatus.OK);
    }

    @GetMapping(path = "/")
    public ResponseEntity<?> helloWorld() {
        return new ResponseEntity<>(new SuccessResponse("Service Running"), HttpStatus.CREATED);
    }

    @GetMapping(path = "/todo")
    public ResponseEntity<?> getTodoList() {
        return new ResponseEntity<>(todoService.getTodo(), HttpStatus.OK);
    }

    @PostMapping(path = "/todo", consumes = "Application/Json", produces = "Application/Json")
    public ResponseEntity<?> addTodo(@RequestBody TodoInfo todoInfo) {
        todoService.addTodo(todoInfo);
        return new ResponseEntity<>(new SuccessResponse("Added"), HttpStatus.CREATED);
    }

    @PutMapping(path = "/todo", consumes = "Application/Json", produces = "Application/Json")
    public ResponseEntity<?> updateTodo(@RequestBody TodoInfo todoInfo) {
        todoService.updateTodo(todoInfo);
        return new ResponseEntity<>(new SuccessResponse("Updated"), HttpStatus.CREATED);
    }
}
