package com.demo.redis_cache.service.impl;

import com.demo.redis_cache.entity.User;
import com.demo.redis_cache.model.UserDTO;
import com.demo.redis_cache.repository.UserRepo;
import com.demo.redis_cache.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

//    private static String USER_KEY = "USER";

    private RedisTemplate<String, Object> redisTemplate;
    private UserRepo userRepo;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    @Cacheable(value = "user", unless = "#result == null")
    public List<UserDTO> getAll() {
//        return redisTemplate.opsForHash().entries(USER_KEY)
//                .values().stream().map(userDTO -> (UserDTO) userDTO).toList();
        System.out.println("GetAll method called");
        return userRepo.findAll()
                .stream().map(user -> objectMapper.convertValue(user, UserDTO.class)).toList();
    }

    @Override
    @Cacheable(value = "user", key = "#id", unless = "#result == null")
    public UserDTO getUser(Long id) {
        System.out.println("getUser Method called");
        return userRepo.findById(id)
                .map(user -> objectMapper.convertValue(user, UserDTO.class))
                .orElse(null);
    }

    @Override
    @CachePut(value = "user", key = "#userDTO.id")
    public UserDTO addUser(UserDTO userDTO) {
//        redisTemplate.opsForHash().put(USER_KEY, userDTO.getId(), userDTO);
        System.out.println("addUser Method called");
        return objectMapper.convertValue(
                userRepo.save(User.builder().id(userDTO.getId()).name(userDTO.getName()).build())
                , UserDTO.class
        );
    }

    @Override
    @CacheEvict(value = "user", key = "#id")
    public void deleteUser(Long id) {
//        redisTemplate.opsForHash().delete(USER_KEY, id);
        System.out.println("deleteUser Method called");
        userRepo.deleteById(id);
    }
}
