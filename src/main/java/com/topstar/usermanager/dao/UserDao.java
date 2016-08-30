package com.topstar.usermanager.dao;

import com.topstar.usermanager.model.User;
import java.util.List;


public interface UserDao {
    public void addUser(User user);
    public void updateUser(User user);
    public void removeUser(int id);
    public User getUserByID(int id);
    public List<User> listUsers();
    public List<User> searchByName(User user);
}
