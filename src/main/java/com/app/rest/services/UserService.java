package com.app.rest.services;

import com.app.rest.model.User;
import com.app.rest.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public List<User> getUsers() throws InterruptedException {
        return userRepo.findAll();
    }
}
