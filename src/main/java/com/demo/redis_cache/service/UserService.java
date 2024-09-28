package com.demo.redis_cache.service;

import com.demo.redis_cache.model.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> getAll();

    UserDTO getUser(String id);

    void addUser(UserDTO userDTO);

    void deleteUser(String id);
}
