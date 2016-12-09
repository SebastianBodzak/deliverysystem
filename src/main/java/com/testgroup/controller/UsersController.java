package com.testgroup.controller;

import com.testgroup.api.CreateUserRequest;
import com.testgroup.api.UserService;
import org.springframework.web.bind.annotation.*;

/**
 * @author beata.ilowiecka@impaqgroup.com on 09.12.16.
 */
@RestController
@RequestMapping("/user")
public class UsersController {

    UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/add")
    public Long add(@RequestBody CreateUserRequest request) {
        return userService.addUser(request);
    }

    @GetMapping("/{id}")
    public String get(@PathVariable Long id) {
        return userService.getUser(id);
    }
}
