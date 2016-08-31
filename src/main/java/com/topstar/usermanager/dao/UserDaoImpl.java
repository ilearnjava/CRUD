package com.topstar.usermanager.dao;

import com.topstar.usermanager.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class UserDaoImpl implements UserDao{
    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
    private int pageNumber = 0;
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(user);
        logger.info("User added. Info: " + user);
    }

    @Override
    public void updateUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(user);
        logger.info("User updated. Info: " + user);
    }

    @Override
    public void removeUser(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = (User) session.load(User.class, new Integer(id));
        if (user != null) {
            session.delete(user);
            logger.info("User deleted. Info: " + user);
        }

    }

    @Override
    public User getUserByID(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = (User) session.load(User.class, new Integer(id));
        logger.info("User loaded. Info: " + user);
        return user;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        Session session = this.sessionFactory.getCurrentSession();
        List<User> users = session.createQuery("from User").list();
        int size = users.size()/10;
        int from = 0, to = 0;
        if (pageNumber < size) {
            from = pageNumber * 10;
            to = from + 10;
        } else if (pageNumber == size) {
            from = pageNumber * 10;
            to = users.size() - 1;
        }
        List<User> res = new ArrayList<User>();
        if (users.size() >= from) {
            res.addAll(users.subList(from, to));
        }
        for (User user : res) {
            logger.info("Users list: " + user);
        }
        return res;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> searchByName(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        List<User> users = session.createQuery("from User where USER_NAME = '" + user.getName() + "'").list();
        for (User u : users) {
            logger.info("Users serchByName: " + u);
        }
        return users;
    }

    @Override
    public void start() {
        pageNumber = 0;
    }

    @Override
    public void pageDown() {
        if (pageNumber > 0) {
            pageNumber -= 1;
        }
    }

    @Override
    public void pageUp() {
        Session session = this.sessionFactory.getCurrentSession();
        List<User> users = session.createQuery("from User").list();
        if (users.size()/10 > pageNumber || (users.size()/10 == pageNumber & (users.size()/10)%pageNumber != 0))
        {
            pageNumber += 1;
        }
    }

    @Override
    public void end() {

    }
}
