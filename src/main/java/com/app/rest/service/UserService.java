package com.app.rest.service;

import com.app.rest.exception.UserPersistenceException;
import com.app.rest.exception.UserNotFoundException;
import com.app.rest.model.dto.UserDTO;
import com.app.rest.dao.UserDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private static Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserDAO userDAO;

//    public List<UserDetail> getUsers() throws InterruptedException {
//        return userDao.findAll();
//    }
//    public Optional<UserDetail> getUser(Long id) throws InterruptedException {
//        return userDAO.findById(id);
//    }
//    public Long saveUser(UserDetail userDetail) throws InterruptedException {
//        return userDAO.save(userDetail).getId();
//    }
//    public UserDetail deleteUser(UserDetail userDetail) {
//        userDetail.setDeleted(true);
//        return userDAO.save(userDetail);
//    }

    public UserDTO findUserById(Long id) throws UserNotFoundException{
        try{
            return userDAO.retrieveUserById(id);
        } catch (UserNotFoundException e){
            LOGGER.error("Error trying retrieve user with id {}. Caused by[{}]", id, e.getMessage());
            throw e;
        }
    }

    public Long createNewUser(UserDTO user) throws UserPersistenceException {
        try {
            user.setDeleted(false);
            return userDAO.createNewUser(user);
        } catch (UserPersistenceException e) {
            LOGGER.error("Some error was present during save process of User");
            throw e;
        }
    }
}
