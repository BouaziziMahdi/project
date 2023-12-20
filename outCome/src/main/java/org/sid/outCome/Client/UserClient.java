package org.sid.outCome.Client;

import org.sid.outCome.Entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name ="authentification", url = "http://localhost:8090/api/v1/auth")
public interface UserClient {
    @GetMapping(path="/user/{id}")
    public User findUserById(@PathVariable("id") int id);
}

