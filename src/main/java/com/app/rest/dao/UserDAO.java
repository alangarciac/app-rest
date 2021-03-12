package com.app.rest.dao;

import com.app.rest.exception.UserNotFoundException;
import com.app.rest.exception.UserPersistenceException;
import com.app.rest.exception.UserNotFoundException;
import com.app.rest.exception.UserPersistenceException;
import com.app.rest.format.DateFormat;
import com.app.rest.model.dto.UserDTO;
import com.app.rest.model.dto.UserDTO;
import com.app.rest.model.persistence.UserDetail;
import com.app.rest.model.persistence.UserDetail;
import com.app.rest.repository.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserDAO {

    @Autowired
    private UserRepo userRepo;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDAO.class);


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


    public UserDTO delete(Long id) throws  UserNotFoundException, UserPersistenceException {
        try {
            Optional<UserDetail> oiDet = userRepo.findById(id);
            if (oiDet.isPresent()) {
                oiDet.get().setDeleted(true);
                return new UserDTO(userRepo.save(oiDet.get()));
            } else {
                String message = MessageFormat.format("Error trying retrieve user with id {0}. Caused by[{1}]", id, "user not found");
                throw new UserNotFoundException(message);
            }
        } catch (DataAccessException ie) {
            String message = MessageFormat.format("Error trying to save user  with id {0}. Caused by[{1}]", id, ie.getMessage());
            LOGGER.error(message);
            throw new UserPersistenceException(message);
        }
    }

    public List<UserDTO> findAll() throws  UserPersistenceException {
        List<UserDTO> usersDTO = new ArrayList<>();
        try {
            List<UserDetail> users = userRepo.findAll();
            for (UserDetail userDetail : users) {
                usersDTO.add(new UserDTO(userDetail));
            }
            return  usersDTO;
        }  catch (DataAccessException ie) {
            String message = MessageFormat.format("Error trying retrieve user. Caused by[{0}]", ie.getMessage());
            throw new UserPersistenceException(message);
        }
    }

    public UserDTO update(Long id, UserDTO userDTO) throws UserNotFoundException, UserPersistenceException {
        try {
            Optional<UserDetail> oiDTO = userRepo.findById(id);
            if (oiDTO.isPresent()) {
                UserDetail userDetail = oiDTO.get();
                userDetail.setName(userDTO.getName());
                userDetail.setEmail(userDTO.getEmail());
                userDetail.setDeleted(userDTO.isDeleted());
                return new UserDTO(userRepo.save(userDetail));
            } else {
                String message = MessageFormat.format("Error trying to update user {0}. Caused by[{1}]", id, "user not found");
                throw new UserNotFoundException(message);
            }
        } catch (DataAccessException e) {
            String message = MessageFormat.format("Error trying to update user {0}. Caused by[{1}]", id, e.getMessage());
            LOGGER.error(message);
            throw new UserPersistenceException(message);
        }
    }
}
