package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * Created by user on 09.07.2016.
 */
@Controller
public class HomeController {
    private int RANGE = 5;

    private void startUp(HttpSession session) {

    }

    @RequestMapping("/home")
    public String showHome(Model model, HttpSession session) {

        //Start up. Get number of followers and following and store in model.
        startUp(session);


            //User without any spittles, for instance right after the registration.

            model.addAttribute("isNext", "no");
            model.addAttribute("anchorEnd", "0");


        model.addAttribute("isPrevious", "no");
        model.addAttribute("anchorStart", "0");

        return "home";
    }

    @RequestMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @RequestMapping("/registration")
    public String showRegistrationForm(Model model) {


        return "registerNewUser";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registerSpitter( Model model) {

        return "registrationCompleted";
    }

    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public String updateHomePage(HttpSession session, Model model, @RequestParam String direction,
                                 @RequestParam String anchorStart, @RequestParam String anchorEnd) {

        int start = Integer.parseInt(anchorStart);
        int end = Integer.parseInt(anchorEnd);

        model.addAttribute("anchorStart", start);
        model.addAttribute("anchorEnd", end);
        if(start > 0) {
            model.addAttribute("isPrevious", "yes");
        } else {
            model.addAttribute("isPrevious", "no");
        }

            model.addAttribute("isNext", "no");


        return "home";
    }
}
