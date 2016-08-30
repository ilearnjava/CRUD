package com.topstar.usermanager.service;

import com.topstar.usermanager.dao.UserDao;
import com.topstar.usermanager.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService{


    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public void addUser(User user) {
        this.userDao.addUser(user);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        this.userDao.updateUser(user);
    }

    @Override
    @Transactional
    public void removeUser(int id) {
        this.userDao.removeUser(id);
    }

    @Override
    @Transactional
    public User getUserByID(int id) {
        User user = this.userDao.getUserByID(id);
        return user;
    }

    @Override
    @Transactional
    public List<User> listUsers() {
        List<User> users = userDao.listUsers();
        return users;
    }

    @Override
    @Transactional
    public List<User> searchByName(User user) {
        return this.userDao.searchByName(user);
    }
}
