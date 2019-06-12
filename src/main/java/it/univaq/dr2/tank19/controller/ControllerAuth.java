package it.univaq.dr2.tank19.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControllerAuth {

    @RequestMapping("/login")
    public String getLogin() {
        return "login";
    }


    @RequestMapping("/index")
    public String getIndex() {
        return "index";
    }


}
