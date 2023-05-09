package com.application.socialnetwork.service;

import com.application.socialnetwork.entity.User;

import java.util.List;

public interface UserService {

    User getUserById(Long id);

    List<User> getListOfUsers();

    void createUser(User user);

    void deleteUser(Long id);
}
