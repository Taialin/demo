package com.example.demo.controllers;

import com.example.demo.Services.UserService;
import com.example.demo.dob.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/api")
@RestController
@CrossOrigin("*")
@ComponentScan("com.example.demo")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/all_users")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public User registration(@RequestBody User user) {
       return userService.save(user);
   }


   @PostMapping("/doLogin")
   public String create(@RequestParam("firstName") String name, @RequestParam("secondName") String secondName,
                        @RequestParam("phoneNumber") String phoneNumber, @RequestParam("email") String email,
                        @RequestParam("password") String password, Model model){
        User user = new User();
        user.setFirstName(name);
        user.setSecondName(secondName);
        user.setPhoneNumber(phoneNumber);
        user.setEmail(email);
        user.setPassword(password);

        model.addAttribute("user", user);

        return "sss";
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
