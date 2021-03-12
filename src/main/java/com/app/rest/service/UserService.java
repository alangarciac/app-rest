package com.app.rest.service;

import com.app.rest.exception.UserPersistenceException;
import com.app.rest.exception.UserNotFoundException;
import com.app.rest.model.dto.UserDTO;
import com.app.rest.dao.UserDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    private static Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserDAO userDAO;

    public UserDTO findUserById(Long id) throws UserNotFoundException{
        try{
            return userDAO.retrieveUserById(id);
        } catch (UserNotFoundException e){
            LOGGER.error("Error trying to retrieve user with id {}. Caused by[{}]", id, e.getMessage());
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

    public UserDTO deleteUser(Long id) throws UserPersistenceException, UserNotFoundException {
        try{
        return userDAO.delete(id);
        } catch (UserNotFoundException | UserPersistenceException e){
        LOGGER.error("Error trying to delete user with id {}. Caused by[{}]", id, e.getMessage());
        throw e;
    }
    }

    public List<UserDTO> getUsers() throws UserPersistenceException {
        return userDAO.findAll();
    }

    public UserDTO updateUser(Long id, UserDTO userDTO) throws UserNotFoundException, UserPersistenceException {
        try {
            return userDAO.update(id, userDTO);
        } catch (UserNotFoundException | UserPersistenceException ie) {
            LOGGER.debug(ie.getMessage());
            throw ie;
        }
    }
    
}
