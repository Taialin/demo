package com.example.demo.Services.Impl;

import com.example.demo.Services.UserService;
import com.example.demo.dob.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserRepository userRepository;
   /* @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
*/
    @Autowired
    public UserServiceImpl(UserRepository userRepository) { this.userRepository = userRepository; }

/*
    @Override
    public boolean save(User user) {
        User existingUser = userRepository.findAllByFirstName(user.getFirstName());
        if (existingUser != null) {
            return false;
        }
        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return true;
    }*/

    @Override
    public User save(User user) {

            return userRepository.save(user);
    }

    @Override
    public User getUserByPassword(String password) {
        List<User> users = userRepository.findAllByPassword(password);
        if (users == null || users.isEmpty()) {
            return null;
        }
        return users.iterator().next();
    }

    @Override
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }


    @Override
    public List<User> findPhoneNumber(Integer phoneNumber) {
        return findAll().stream().filter(user -> phoneNumber.equals(user.getPhoneNumber())).collect(Collectors.toList());
    }

    @Override
    public List<User> findAllById(Long id) {
        return (List<User>) userRepository.findAllById(Collections.singleton(id));
    }


    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

 /*   @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findAllByFirstName(username);
    }*/
}



