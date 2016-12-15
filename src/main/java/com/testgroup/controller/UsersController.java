package com.testgroup.controller;

import com.testgroup.api.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author beata.ilowiecka@impaqgroup.com on 09.12.16.
 */
@RestController
@RequestMapping("/users")
public class UsersController {

    private UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable Long id) {
        return userService.getById(id);
    }
}
