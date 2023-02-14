package com.backend.web.service;

import com.backend.web.exception.UserNotFoundException;
import com.backend.web.model.User;
import com.backend.web.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service("userServicce")
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User updateUser(Optional<User> user, User newUser){
        return user.map(currentUser ->{
            currentUser.setName(newUser.getName());
            currentUser.setUserName(newUser.getUserName());
            currentUser.setEmail(newUser.getEmail());
            return userRepository.save(currentUser);
        }).orElseThrow(() ->new UserNotFoundException(newUser.getId()));
    }


    public void deleteUser(Long id) {
        if(!userRepository.existsById(id)){
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
    }
}
