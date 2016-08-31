package com.topstar.usermanager.service;

import com.topstar.usermanager.model.User;


import java.util.List;

/**
 * Created by Bitpunk on 16.08.16.
 */
public interface UserService {
    public void addUser(User user);
    public void updateUser(User user);
    public void removeUser(int id);
    public User getUserByID(int id);
    public List<User> listUsers();
    public List<User> searchByName(User user);

    public void start();
    public void pageDown();
    public void pageUp();
    public void end();
}
