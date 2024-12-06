package com.expensetracker.controller;

import com.expensetracker.dto.CommonResponse;
import com.expensetracker.dto.LoginRequest;
import com.expensetracker.dto.UserRequest;
import com.expensetracker.dto.UserResponse;
import com.expensetracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

import org.springframework.validation.annotation.Validated;




@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<CommonResponse> addUser(@Valid @RequestBody UserRequest userRequest){
        CommonResponse response= userService.addUser(userRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> loginUser(@Valid @RequestBody LoginRequest loginRequest){
        UserResponse response=userService.loginUser(loginRequest);
        return ResponseEntity.ok(response);
    }
}
