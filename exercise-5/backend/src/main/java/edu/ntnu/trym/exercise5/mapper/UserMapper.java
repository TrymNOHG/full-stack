package edu.ntnu.trym.exercise5.mapper;

import edu.ntnu.trym.exercise5.dto.UserCreationDTO;
import edu.ntnu.trym.exercise5.dto.UserDTO;
import edu.ntnu.trym.exercise5.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toUser(UserCreationDTO userDTO) {
        return new User(userDTO.getUsername(), userDTO.getPassword(), userDTO.getEmail());
    }
    public UserDTO toUserDTO(User user) {
        return new UserDTO(user.getUsername(), user.getEmail());
    }

}
