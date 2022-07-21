package com.example.demo.controllers;

import com.example.demo.Services.UserService;
import com.example.demo.dob.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

 /*  @RequestMapping(value = "/user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public User registration(@RequestBody User user) {
       return userService.save(user);
   }*/


    @GetMapping("/all_users")
    @CrossOrigin(origins = "*")
    public List<User> getAllUsers() {
        return userService.findAll();
    }


    @GetMapping("/user_id")
    @CrossOrigin(origins = "*")
    public User getUserById(Long id) {
        return userService.findAllById(id).iterator().next();

    }

    @GetMapping("/user_phone")
    @CrossOrigin(origins = "*")
    public List<User> getPhoneNumber(Integer telephone) {
        return userService.findPhoneNumber(telephone);
    }
}
