package com.example.jpa.controller;

import com.example.jpa.domain.User;
import com.example.jpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ModelAndView list(Model model) {
            model.addAttribute("userList", userRepository.findAll());
            model.addAttribute("title", "用户管理");
            return new ModelAndView("user/list","userModel", model);
    }

    @GetMapping("/form")
    public ModelAndView createForm(Model model) {
        model.addAttribute("user", new User(null,null,null));
        model.addAttribute("title","创建用户");
        return new ModelAndView("user/form","userModel",model);
    }

    @GetMapping("/form/{id}")
    public ModelAndView updateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userRepository.findOne(id));
        model.addAttribute("title","更新用户");
        return new ModelAndView("user/form","userModel",model);
    }

    @PostMapping
    public ModelAndView saveOrUpdateUser(User user) {
        userRepository.save(user);
        return new ModelAndView("redirect:/user");
    }

    @GetMapping("{id}")
    public ModelAndView view(@PathVariable("id") Long id, Model model) {
        User user = userRepository.findOne(id);
        model.addAttribute("user",user);
        model.addAttribute("title","用户详情");
        return  new ModelAndView("user/view","userModel", model);
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id) {
        userRepository.delete(id);
        return  new ModelAndView("redirect:/user");
    }



}
