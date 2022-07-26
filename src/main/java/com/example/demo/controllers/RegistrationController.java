package com.example.demo.controllers;

import com.example.demo.Services.UserService;
import com.example.demo.dob.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/registration")
public class RegistrationController {


  /*  @RequestMapping(method= RequestMethod.GET, produces = "text/html")
    public String index() {
        return "index";
    }*/

    @Autowired
    private UserRepository re;

    @RequestMapping(method= RequestMethod.GET, produces = "text/html")
    public String index() {
        return "index";
    }
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }


    @PostMapping("/registration")
    public String adding(@ModelAttribute("useForm") @Valid User user, BindingResult bindingResult, Model model){
         if (bindingResult.hasErrors()) {
             return "registration";
         }
         if(!user.getPassword().equals(user.getPassword())) {
         model.addAttribute("passwordError" ,"Не совпадают ваши пароли");
         return  "registration";}
         if (!userService.save((user))){
             model.addAttribute(("usernameError"), "Не правильно введено имя");
             return "registration";
         }

         return "rejected:/";
    }


  /*  @Autowired
    private UserService userService;
    @GetMapping("/registration")
    public String registration(@ModelAttribute User user, Model model) {
       User user_in = re.save(user);
       model.addAttribute(user_in.getFirstName());

        return "registration";
    }*/
 /*   @PostMapping("/doLogin")
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
    }*/
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
