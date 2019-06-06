package it.univaq.dr2.tank19.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControllerLogin {

    @RequestMapping("/login")
    public String auth() {
        return "Wellcome to login";
    }

}
