package com.gobus.gobusservice.service;

import com.gobus.gobusservice.dto.ChangePasswordRequest;
import com.gobus.gobusservice.dto.UserDto;
import com.gobus.gobusservice.model.User;
import com.gobus.gobusservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepo;

    public UserDto register(UserDto userdto) {
        try {
            if (userRepo.existsByEmailId(userdto.getEmailId())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "User account already exists for this emailId");
            }
            log.info("Saving user to the database");
            User savedUser = userRepo.save(UserUtils.dtoToEntity(userdto));
            log.info("Successfully saved the user {} to the db", savedUser.getUserId());
            return UserUtils.entityToDto(savedUser);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            log.error("Failed to create user account", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong!!! Please try later.");
        }
    }

    public UserDto login(String emailId, String pwd) {
        try {
            Optional<User> user = userRepo.findByEmailId(emailId);
            log.info("checking for user in database...");
            if (user.isEmpty()) {
                log.error("user not found...");
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found for the EmailId..");
            }
            log.info("validating username and password....");
            if (!user.get().getPassword().equals(UserUtils.convertToMD5(pwd))) {
                log.error("username and password doesn't match");
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Username or Password...");
            }
            return UserUtils.entityToDto(user.get());
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong!!");
        }
    }

    public UserDto getByEmailId(String emailId) {
        try {
            Optional<User> user = userRepo.findByEmailId(emailId);
            log.info("Checking for user in database for given email {}", emailId);
            if (user.isEmpty()) {
                log.error("user not found for given email", emailId);
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found for the given email Id..");
            }
            log.info("Returning the user {} ", user.get());
            return UserUtils.entityToDto(user.get());
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            log.error("Failed to retrieve user by email id", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong!!");
        }
    }

    public UserDto get(int id) {
        try {
            Optional<User> user = userRepo.findById(id);
            log.info("checking for user in database for given id...");
            if (user.isEmpty()) {
                log.error("user not found for given id...");
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found for the given Id..");
            }
            log.info("Returning the user {} ", user.get());
            return UserUtils.entityToDto(user.get());
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            log.error("Failed to retrieve user by id", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong!!");
        }
    }

    public UserDto update(Integer id, UserDto userdto){
        try {
            Optional<User> user = userRepo.findById(id);
            if (user.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found for this id");
            }
            User dbUser = user.get();
            UserUtils.copyFromDto(userdto, dbUser);
            return UserUtils.entityToDto(userRepo.save(dbUser));
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            log.error("Failed to update user account", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong!!");
        }
    }

    public void resetPassword(int id, ChangePasswordRequest changePasswordRequest) {
        try {
            Optional<User> user = userRepo.findById(id);
            if (user.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found for this id");
            }
            User dbUser = user.get();
            String encodedCurPass = UserUtils.convertToMD5(changePasswordRequest.getCurrentPassword());
            String encodedNewPass = UserUtils.convertToMD5(changePasswordRequest.getNewPassword());
            if (!encodedCurPass.equals(dbUser.getPassword())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Current Password!");
            } else if (encodedCurPass.equals(encodedNewPass)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password cannot be same!");
            }
            dbUser.setPassword(encodedNewPass);
            userRepo.save(dbUser);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            log.error("Failed to update user account", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong!!");
        }
    }
}

