package com.testgroup.controller;

import com.testgroup.api.UserService;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

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

    @GetMapping("/name")
    public BigInteger getByName(@RequestParam("fullName") String fullName) {
        return userService.getByName(fullName);
    }

    @GetMapping("/count")
    public BigInteger count() {
        return userService.count();
    }
}
