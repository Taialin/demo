package com.example.demo.Services;

import com.example.demo.dob.User;

import java.util.List;

public interface UserService /*extends UserDetailsService*/ {
    User save(User user);

    User getUserByPassword(String password);
    List<User> findAll();

    List<User> findPhoneNumber(Integer telephone);

    List<User> findAllById(Long Id);

    void delete(User user);
}
