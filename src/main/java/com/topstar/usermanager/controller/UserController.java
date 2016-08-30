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
    private int pageNumber = 0;

    @Autowired(required = true)
    @Qualifier(value = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "users", method = RequestMethod.GET)
    public String listUsers(Model model){
        List<User> userList = this.userService.listUsers();
        int size = userList.size()/10;

        int from = 0, to = 0;
        if (pageNumber < size) {
            from = pageNumber * 10;
            to = from + 10;
        } else if (pageNumber == size) {
            from = pageNumber * 10;
            to = userList.size() - 1;
        }
        List<User> res = new ArrayList<User>();
        if (userList.size() >= from) {
            res.addAll(userList.subList(from, to));
        }

        model.addAttribute("user", new User());
        model.addAttribute("listUsers", res);

        return "users";
    }


    @RequestMapping(value = "/users/start", method = RequestMethod.POST)
    public String start(Model model) {
        pageNumber = 0;
        listUsers(model);
        return "users";
    }
    @RequestMapping(value = "/users/pagedown", method = RequestMethod.POST)
    public String pageDown(Model model) {
        if (pageNumber > 0) {
            pageNumber -= 1;
        }
        listUsers(model);
        return "users";
    }
    @RequestMapping(value = "/users/pageup", method = RequestMethod.POST)
    public String pageUp(Model model) {
        if (this.userService.listUsers().size()/10 > pageNumber || (this.userService.listUsers().size()/10 == pageNumber & (this.userService.listUsers().size()/10)%pageNumber != 0))
        {
            pageNumber += 1;
        }
        listUsers(model);
        return "users";
    }
    @RequestMapping(value = "/users/end", method = RequestMethod.POST)
    public String end(Model model) {
        listUsers(model);
        return "users";
    }

    @RequestMapping(value = "/users/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user){
        pageNumber = 0;
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
        listUsers(model);
        return "users";
    }

    @RequestMapping("userdata/{id}")
    public String userData(@PathVariable("id") int id, Model model){
        model.addAttribute("user", this.userService.getUserByID(id));

        return "userdata";
    }

    @RequestMapping(value = "/users/search", method = RequestMethod.POST)
    public String searchByName(@ModelAttribute("user") User user, Model model){
        model.addAttribute("user", new User());
        model.addAttribute("searchByName", this.userService.searchByName(user));
        this.userService.searchByName(user);


        return "users";
    }


}
