package com.example.demo.Services.Impl;

import com.example.demo.Services.UserService;
import com.example.demo.dob.Role;
import com.example.demo.dob.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    BCryptPasswordEncoder bCryptPasswordEncoder;

   /* public boolean saveUser(User user) {
        User userFromDB = userRepository.findAllByFirstName(user.getUsername());

        if (userFromDB != null) {
            return false;
        }

        user.setPassword(passwordConfig.passwordEncoder(user.getPassword()));
        userRepository.save(user);
        return true;
    }*/

    public boolean deleteUser(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    public List<User> usergtList(Long idMin) {
        return em.createQuery("SELECT u FROM User u WHERE u.id > :paramId", User.class)
                .setParameter("paramId", idMin).getResultList();
    }

    public List<User> allUsers() {
        return (List<User>) userRepository.findAll();
    }

  /*  @Override
    public User save(User user) {
        return userRepository.save(user);
    }
*/
  public boolean save(User user) {
      User userFromDB = userRepository.findAllByFirstName(user.getUsername());

      if (userFromDB != null) {
          return false;
      }

      user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
      user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
      userRepository.save(user);
      return true;
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



