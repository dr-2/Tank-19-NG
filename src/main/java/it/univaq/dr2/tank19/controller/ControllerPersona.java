package it.univaq.dr2.tank19.controller;

import it.univaq.dr2.tank19.model.Persona;
import it.univaq.dr2.tank19.service.SecurityService;
import it.univaq.dr2.tank19.service.ServicePersona;
import it.univaq.dr2.tank19.validator.ValidatorPersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class ControllerPersona {
    @Autowired
    private ServicePersona servicePersona;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private ValidatorPersona validatorPersona;

    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("userForm", new Persona());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(Model model, @ModelAttribute("userForm") Persona formPersona, BindingResult bindingResult) {
        validatorPersona.validate(formPersona, bindingResult);
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            System.out.print(bindingResult);
            model.addAttribute("usernameErrors", bindingResult.getFieldErrors("username"));
            return "registration";
        }
        servicePersona.save(formPersona);
        securityService.autoLogin(formPersona.getUsername(), formPersona.getPasswordConfirm());
        return "redirect:/welcome";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "La combinazione username-password usata non è corretta");
        }
        if (logout != null) {
            model.addAttribute("message", "Logout avvenuto con successo");
        }
        return "login";
    }

    @GetMapping({"/", "/welcome"})
    public String welcome(Model model) {
        return "welcome";
    }

}