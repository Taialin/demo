package com.example.demo.controllers;

import com.example.demo.Services.UserService;
import com.example.demo.dob.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequestMapping("/api")
@RestController
@CrossOrigin("*")

public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all_users")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

   /* @RequestMapping(value = "/user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public User registration(@RequestBody User user) {
       return userService.save(user);
   }
*/

/*    @GetMapping("/registration")
    public String registration(@ModelAttribute User user, Model model) {
        User user_in = userService.save(user);
        model.addAttribute(user_in.getFirstName());
        System.out.println(user.getFirstName());

        return "registration";
    }*/

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
