package com.app.rest.dao;

import com.app.rest.exception.UserNotFoundException;
import com.app.rest.exception.UserPersistenceException;
import com.app.rest.format.DateFormat;
import com.app.rest.model.dto.UserDTO;
import com.app.rest.model.persistence.ItemDetail;
import com.app.rest.model.persistence.UserDetail;
import com.app.rest.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserDAO {

    @Autowired
    private UserRepo userRepo;

    public UserDTO retrieveUserById(Long id) throws UserNotFoundException, IllegalStateException {
        UserDetail userDetail = Optional.ofNullable(userRepo.findById(id)).get()
                .orElseThrow(() -> new UserNotFoundException(String.format("User with id[%s] not found.", id)));
        return new UserDTO(userDetail);
    }

    public Long createNewUser(UserDTO user) throws UserPersistenceException {
        UserDetail userDetail = new UserDetail(
                user.getName(),
                user.getEmail(),
                user.isDeleted()
        );
        return Optional.ofNullable(userRepo.save(userDetail).getId()).orElseThrow(UserPersistenceException::new);
    }
}
