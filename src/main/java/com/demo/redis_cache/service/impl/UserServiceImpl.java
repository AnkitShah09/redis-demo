package com.demo.redis_cache.service.impl;

import com.demo.redis_cache.model.UserDTO;
import com.demo.redis_cache.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static String USER_KEY = "USER";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public List<UserDTO> getAll() {
        return redisTemplate.opsForHash().entries(USER_KEY)
                .values().stream().map(userDTO -> (UserDTO) userDTO).toList();
    }

    @Override
    public UserDTO getUser(String id) {
        return (UserDTO) redisTemplate.opsForHash().get(USER_KEY, id);
    }

    @Override
    public void addUser(UserDTO userDTO) {
        redisTemplate.opsForHash().put(USER_KEY, userDTO.getId(), userDTO);
    }

    @Override
    public void deleteUser(String id) {
        redisTemplate.opsForHash().delete(USER_KEY, id);
    }
}
