package com.example.springboot.learnspringboot.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private LoginAuthentication authentication;

    public LoginController(LoginAuthentication authentication) {
        this.authentication = authentication;
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String gotoLogin() {
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String gotoMain(@RequestParam String name, @RequestParam String password, ModelMap modelMap) {
        if(authentication.authenticate(name,password)) {
            modelMap.put("name", name);
            modelMap.put("password", password);
            return "main";
        }
        modelMap.put("error","try again");
        return "login";
    }
}
