package com.app.rest.service;

import com.app.rest.model.persistence.UserDetail;
import com.app.rest.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public List<UserDetail> getUsers() throws InterruptedException {
        return userRepo.findAll();
    }
    public Optional<UserDetail> getUser(Long id) throws InterruptedException {
        return userRepo.findById(id);
    }
    public Long saveUser(UserDetail userDetail) throws InterruptedException {
        return userRepo.save(userDetail).getId();
    }
    public UserDetail deleteUser(UserDetail userDetail) {
        userDetail.setDeleted(true);
        return userRepo.save(userDetail);
    }
}
