package com.application.socialnetwork.controller;

import com.application.socialnetwork.entity.User;
import com.application.socialnetwork.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public User aboutUser(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/listOfUsers")
    public List<User> listOfUsers() {
        return userService.getListOfUsers();
    }

    @PostMapping("/createUser")
    public void createUser(@RequestBody User user) {
        userService.createUser(user);
    }

    @DeleteMapping("/deleteUser/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
