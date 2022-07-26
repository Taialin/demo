package com.example.demo.repository;

import com.example.demo.dob.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    User findAllByFirstName(String FirstName);

    List<User> findAllByPassword(String password);

}
