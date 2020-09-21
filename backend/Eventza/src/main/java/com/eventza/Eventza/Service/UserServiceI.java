package com.eventza.Eventza.Service;

import com.eventza.Eventza.DTO.UserDTO;
import com.eventza.Eventza.model.User;

public interface UserServiceI {
    User registerNewUserAccount(UserDTO userDTO)throws Exception;
}
