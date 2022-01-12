package com.edutecno.pruebaFinalSistemaIMDb.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.edutecno.pruebaFinalSistemaIMDb.model.Rating;
import com.edutecno.pruebaFinalSistemaIMDb.model.Show;
import com.edutecno.pruebaFinalSistemaIMDb.model.User;
import com.edutecno.pruebaFinalSistemaIMDb.service.GenericService;
import com.edutecno.pruebaFinalSistemaIMDb.service.UserService;

@Controller
@RequestMapping("/show")
public class ShowController {

    @Autowired
    GenericService<Show> showService;

    @Autowired
    GenericService<Rating> ratingService;

    @Autowired
    UserService userService;

    @PostMapping("/addShow")
    public ModelAndView addShow(@ModelAttribute("show") Show show) {
        showService.create(show);

        return new ModelAndView("newShow");

    }

    @GetMapping("/update/{id}")
    public ModelAndView updateShow(@PathVariable Long id) {
        Show show = showService.findById(id);
        return new ModelAndView("editShow", "show", show);
    }

    @PostMapping("/update")
    public ModelAndView updateShow(@ModelAttribute("show") Show show) {
        showService.update(show);
        return new ModelAndView("redirect:/home");
    }

    @GetMapping("/delete")
    public ModelAndView deleteShow(@RequestParam Long id, Model model) {
        showService.delete(showService.findById(id));
        return new ModelAndView("redirect:/home");
    }

    @GetMapping("/rating/{id}")
    public ModelAndView goToRating(@PathVariable Long id, Model model) {

        Show show = showService.findById(id);
        List<Integer> values = new ArrayList<>();
        float sum = 0;
        for (Rating rating : show.getRatings()) {
            values.add(rating.getValue());
            sum += (float) rating.getValue();
        }
        model.addAttribute("val1", Collections.frequency(values, 1));
        model.addAttribute("val2", Collections.frequency(values, 2));
        model.addAttribute("val3", Collections.frequency(values, 3));
        model.addAttribute("val4", Collections.frequency(values, 4));
        model.addAttribute("val5", Collections.frequency(values, 5));
        model.addAttribute("prom", (float) sum / values.size());
        return new ModelAndView("ratingShow", "show", show);

    }

    @PostMapping("/addRating")
    public ModelAndView addRAting(@ModelAttribute Rating rating, @RequestParam(name = "showId") Long id, Model model) {
        rating.setShow(showService.findById(id));
        ratingService.create(rating);

        return goToRating(id, model);

    }

    @GetMapping("/addFavorite")
    public ModelAndView addFavorite(@RequestParam(name = "id") Long id, Principal principal) {
        System.out.println(showService.findById(id).getShowTitle());
        System.out.println(userService.findUserByUsername(principal.getName()).getEmail());
        Show show = showService.findById(id);
        User user = userService.findUserByUsername(principal.getName());
        if (!show.getUsers().contains(userService.findUserByUsername(principal.getName()))) {
            show.getUsers().add(user);
        }
        showService.update(show);

        return new ModelAndView("redirect:/home");

    }

}
