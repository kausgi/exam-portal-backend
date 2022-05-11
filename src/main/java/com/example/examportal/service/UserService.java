package com.example.examportal.service;

import com.example.examportal.model.User;
import com.example.examportal.model.UserRole;

import java.util.Set;

public interface UserService {
    public User createUser(User user, Set<UserRole> userRoles) throws Exception;
    public User getUser(String user_name) throws Exception;

    void delete_user(String user_name);

    User updateUser(String user_name, User user) throws Exception;
}
