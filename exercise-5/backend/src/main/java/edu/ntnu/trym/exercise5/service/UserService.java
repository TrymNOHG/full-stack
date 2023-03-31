package edu.ntnu.trym.exercise5.service;

import edu.ntnu.trym.exercise5.dto.UserDTO;
import edu.ntnu.trym.exercise5.exception.UserNotFoundException;
import edu.ntnu.trym.exercise5.mapper.UserMapper;
import edu.ntnu.trym.exercise5.model.User;
import edu.ntnu.trym.exercise5.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDTO findUser(UserDTO user) {
        Optional<User> userInDB = userRepository.findUserByUsername(user.getUsername());
        userInDB.ifPresent(Objects::requireNonNull);
        return userMapper.toUserDTO(userInDB
                .orElseThrow(() -> new UserNotFoundException(user.getUsername())));
    }

    public User loadByUsername(String username) {
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
    }

}
