package com.demo.redis_cache.controller;

import com.demo.redis_cache.model.UserDTO;
import com.demo.redis_cache.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserDTO getUserByID(@PathVariable("id") String id) {
        return userService.getUser(id);
    }

    @PostMapping
    public void addUser(@RequestBody UserDTO userDTO) {
        userService.addUser(userDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteUserByID(@PathVariable("id") String id) {
        userService.deleteUser(id);
    }
}
