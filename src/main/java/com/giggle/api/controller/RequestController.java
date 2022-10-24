package com.giggle.api.controller;

import com.giggle.api.model.component.UserDetails;
import com.giggle.api.model.handler.auth.AuthenticationRequest;
import com.giggle.api.model.handler.auth.AuthenticationResponse;
import com.giggle.api.model.handler.success.SuccessResponse;
import com.giggle.api.security.util.JwtUtil;
import com.giggle.api.service.SuperUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestController {

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
            return new ResponseEntity<>(new SuccessResponse("Invalid credentials"), HttpStatus.NOT_ACCEPTABLE);
        }
        final UserDetails customUserDetails = superUserService.loadUserByUsername(request.getUsername());
        final String jwt = jwtUtil.generateToken(customUserDetails);
        return new ResponseEntity<>(new AuthenticationResponse(jwt), HttpStatus.OK);
    }

    @GetMapping(path = "/")
    public String helloWorld() {
        return "Welcome to Turbo initializer";
    }
}
