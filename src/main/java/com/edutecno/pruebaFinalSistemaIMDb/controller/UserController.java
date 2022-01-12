package com.edutecno.pruebaFinalSistemaIMDb.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.edutecno.pruebaFinalSistemaIMDb.model.Role;
import com.edutecno.pruebaFinalSistemaIMDb.model.User;
import com.edutecno.pruebaFinalSistemaIMDb.service.UserService;

@Controller
@RequestMapping("")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @PostMapping("/login")
    public String login(@RequestParam(required = false) Boolean error, Model modelo) {
        modelo.addAttribute("error", error);
        return "login";
    }

    @PostMapping("/signup")
    public ModelAndView register(@ModelAttribute User user) {
        Role role = Role.ADMIN;
        /* Role role = Role.USUARIO; */
        user.setRoles(role);
        user.setPassword(encoder.encode(user.getPassword()));
        userService.signup(user);
        return new ModelAndView("redirect:/login");

    }



}
