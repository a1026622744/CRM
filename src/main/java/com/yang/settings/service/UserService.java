package com.yang.settings.service;

import com.yang.exception.LoginException;
import com.yang.settings.domain.User;

import java.util.List;

public interface UserService {
    User login(String loginAct, String loginPwd, String ip) throws LoginException;

    List<User> getUserList();
}
