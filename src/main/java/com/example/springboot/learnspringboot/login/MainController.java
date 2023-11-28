package com.example.springboot.learnspringboot.login;

import org.springframework.boot.autoconfigure.pulsar.PulsarProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class MainController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String gotoMainPage(ModelMap model) {
        model.put("name", getLoggedinUsername());
        return "main";
    }
    private String getLoggedinUsername() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
