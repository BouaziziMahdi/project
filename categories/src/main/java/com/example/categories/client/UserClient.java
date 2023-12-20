package com.example.categories.client;

import com.example.categories.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name ="authentification", url = "http://localhost:8222/api/v1/auth")
public interface UserClient {
        @GetMapping(path="/user/{userId}")
        public User findUserById(@PathVariable Integer userId);
    }

