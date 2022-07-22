package com.example.demo.controllers;

import com.example.demo.Services.UserService;
import com.example.demo.dob.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/registration")
public class RegistrationController {


  /*  @RequestMapping(method= RequestMethod.GET, produces = "text/html")
    public String index() {
        return "index";
    }*/

    @Autowired
    private UserRepository re;


    @Autowired
    private UserService userService;
    @GetMapping("/registration")
    public String registration(@ModelAttribute User user, Model model) {
       User user_in = re.save(user);
       model.addAttribute(user_in.getFirstName());

        return "registration";
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
   /* @PostMapping("/registration")
    public String addUser(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "registration";
        }
        if (!userService.save(userForm)) {
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "registration";
        }

        return "redirect:/";
    }*/

}
