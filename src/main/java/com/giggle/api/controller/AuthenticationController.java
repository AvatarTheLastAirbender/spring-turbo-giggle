package com.giggle.api.controller;

import com.giggle.api.model.component.UserInfo;
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
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private SuperUserService superUserService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping(path = "/auth", consumes = "Application/Json", produces = "Application/Json")
    public ResponseEntity<?> adminAuthentication(@RequestBody AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new SuccessResponse("Invalid credentials"), HttpStatus.NOT_ACCEPTABLE);
        }
        final UserInfo userInfo = superUserService.loadUserByUsername(request.getUsername());
        final String jwt = jwtUtil.generateToken(userInfo);
        return new ResponseEntity<>(new AuthenticationResponse(jwt), HttpStatus.OK);
    }

    @GetMapping(path = "/")
    public ResponseEntity<?> helloWorld() {
        return new ResponseEntity<>(new SuccessResponse("Todo Api Service Running"), HttpStatus.CREATED);
    }
}
