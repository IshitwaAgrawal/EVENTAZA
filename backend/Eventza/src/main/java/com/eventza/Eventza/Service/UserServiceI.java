package com.eventza.Eventza.Service;

import com.eventza.Eventza.model.User;
import com.eventza.Eventza.model.UserSignUp;

public interface UserServiceI {
    User registerNewUserAccount(UserSignUp userSignUp)throws Exception;
}
