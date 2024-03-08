package com.lontsi.devsocialmedia.services;

import com.lontsi.devsocialmedia.exception.UserException;
import com.lontsi.devsocialmedia.models.User;

import java.util.List;

public interface UserService {

    public User findUserById(Long userId) throws UserException;

    public User findUserByJwt(String jwt) throws UserException;

    public User updateUser(Long userId, User user) throws UserException;

    public User followUser(Long userId, User user) throws UserException;

    public List<User> searchUser(String query);
}
