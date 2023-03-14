package com.spring.adapters.controller;

import com.spring.adapters.vo.request.UserRequestVO;
import com.spring.domain.entity.User;
import com.spring.domain.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserRequestVO request){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(request));
    }

}
