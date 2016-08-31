package com.topstar.usermanager.controller;


import com.topstar.usermanager.model.User;
import com.topstar.usermanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    private UserService userService;

    @Autowired(required = true)
    @Qualifier(value = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "users", method = RequestMethod.GET)
    public String listUsers(Model model){
        List<User> userList = this.userService.listUsers();
        model.addAttribute("user", new User());
        model.addAttribute("listUsers", userList);

        return "users";
    }


    @RequestMapping(value = "/users/start", method = RequestMethod.POST)
    public String start(Model model) {
        userService.start();
        listUsers(model);
        return "users";
    }
    @RequestMapping(value = "/users/pagedown", method = RequestMethod.POST)
    public String pageDown(Model model) {
        userService.pageDown();
        listUsers(model);
        return "users";
    }
    @RequestMapping(value = "/users/pageup", method = RequestMethod.POST)
    public String pageUp(Model model) {
        userService.pageUp();
        listUsers(model);
        return "users";
    }
    @RequestMapping(value = "/users/end", method = RequestMethod.POST)
    public String end(Model model) {
        userService.end();
        listUsers(model);
        return "users";
    }

    @RequestMapping(value = "/users/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user){
        if(user.getId() == 0){
            this.userService.addUser(user);
        }else {
            this.userService.updateUser(user);
        }
        return "redirect:/users";
    }

    @RequestMapping("/remove/{id}")
    public String removeUser(@PathVariable("id") int id){
        this.userService.removeUser(id);

        return "redirect:/users";
    }

    @RequestMapping("edit/{id}")
    public String editUser(@PathVariable("id") int id, Model model){
        model.addAttribute("user", this.userService.getUserByID(id));
        model.addAttribute("listUsers", this.userService.listUsers());
        return "users";
    }

    @RequestMapping(value = "/users/search", method = RequestMethod.POST)
    public String searchByName(@ModelAttribute("user") User user, Model model){
        model.addAttribute("user", new User());
        model.addAttribute("searchByName", this.userService.searchByName(user));
        this.userService.searchByName(user);


        return "users";
    }


}
