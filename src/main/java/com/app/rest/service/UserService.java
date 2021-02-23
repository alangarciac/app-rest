package com.app.rest.service;

import com.app.rest.model.Item;
import com.app.rest.model.User;
import com.app.rest.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public List<User> getUsers() throws InterruptedException {
        return userRepo.findAll();
    }
    public Optional<User> getUser(Long id) throws InterruptedException {
        return userRepo.findById(id);
    }
    public Long saveUser(User user) throws InterruptedException {
        return userRepo.save(user).getId();
    }
    public User deleteUser(User user) {
        user.setDeleted(true);
        return userRepo.save(user);
    }
}
