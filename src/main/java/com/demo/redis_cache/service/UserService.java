package com.demo.redis_cache.service;

import com.demo.redis_cache.model.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> getAll();

    UserDTO getUser(Long id);

    UserDTO addUser(UserDTO userDTO);

    void deleteUser(Long id);
}
