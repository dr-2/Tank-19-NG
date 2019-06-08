package it.univaq.dr2.tank19.controller;

import it.univaq.dr2.tank19.model.Persona;
import it.univaq.dr2.tank19.service.SecurityService;
import it.univaq.dr2.tank19.service.ServicePersona;
import it.univaq.dr2.tank19.validator.ValidatorPersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String registration(@ModelAttribute("userForm") Persona formPersona, BindingResult bindingResult) {
        validatorPersona.validate(formPersona, bindingResult);
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        servicePersona.save(formPersona);
        securityService.autoLogin(formPersona.getUsername(), formPersona.getPasswordConfirm());
        return "redirect:/wellcome";
    }

    @GetMapping("/login")
    public String login(Model model, String errore, String logout) {
        if (errore != null) {
            model.addAttribute("error", "La combinazione username-password usata non è corretta");
        }
        if (logout != null) {
            model.addAttribute("message", "Logout avvenuto con successo");
        }
        return "login";
    }

    @GetMapping({"/", "/wellcome"})
    public String wellcome(Model model) {
        return "wellcome";
    }

}
