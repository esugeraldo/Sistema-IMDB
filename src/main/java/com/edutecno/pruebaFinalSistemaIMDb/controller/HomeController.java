package com.edutecno.pruebaFinalSistemaIMDb.controller;

import java.security.Principal;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.edutecno.pruebaFinalSistemaIMDb.model.Rating;
import com.edutecno.pruebaFinalSistemaIMDb.model.Show;
import com.edutecno.pruebaFinalSistemaIMDb.service.GenericService;
import com.edutecno.pruebaFinalSistemaIMDb.service.UserService;

@Controller
public class HomeController {

    @Autowired
    private GenericService<Show> showServiceImpl;

    @Autowired
    private UserService userService;

    @GetMapping({"", "/home"})
    public ModelAndView home(Principal principal, Model model) {
        float sum = 0;
        for (Show show : showServiceImpl.findALL()) {
            for (Rating rating : show.getRatings()) {
                sum += rating.getValue();
            }
            show.setAverage(sum / show.getRatings().size());
            sum = 0;
        }
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        return new ModelAndView("index", "shows", showServiceImpl.findALL());
    }

    @GetMapping("/show/add-show")
    public ModelAndView goToAddShow() {
        return new ModelAndView("newShow");
    }

    @GetMapping("/signup")
    public ModelAndView goToSignup() {
        return new ModelAndView("signup");
    }

    @GetMapping("/login")
    public ModelAndView goToLogin() {
        return new ModelAndView("login");
    }

    @GetMapping("/favorites")
    public ModelAndView goToFavorites(Principal principal, Model model){
        float sum = 0;
        for (Show show : showServiceImpl.findALL()) {
            for (Rating rating : show.getRatings()) {
                sum += rating.getValue();
            }
            show.setAverage(sum / show.getRatings().size());
            sum = 0;
        }
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        return new ModelAndView("favorites", "favorites", userService.findUserByUsername(principal.getName()).getFavoritesShows());
    }

}
