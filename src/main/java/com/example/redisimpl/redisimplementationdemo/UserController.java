package com.example.redisimpl.redisimplementationdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    RedisTemplate<String,User> redisTemplate;

    @PostMapping("/set-value")
    public void addValue(@RequestParam("key") String key, @RequestBody User user) {
        redisTemplate.opsForValue().set(key,user);

    }

    @GetMapping("/get-value")
    public  User getValue(@RequestParam("key") String key) {
        return (User)redisTemplate.opsForValue().get(key);
    }

    @PostMapping("/lpush")
    public void addToList(@RequestParam("key") String key, @RequestBody User user) {
        redisTemplate.opsForList().leftPush(key,user);
    }

    @GetMapping("/get-list")
    public List<User> getList(@RequestParam("key") String key) {
        return redisTemplate.opsForList().range(key,0,-1);
    }


}
