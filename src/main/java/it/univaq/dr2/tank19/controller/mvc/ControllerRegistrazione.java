package it.univaq.dr2.tank19.controller.mvc;

import it.univaq.dr2.tank19.model.Giocatore;
import it.univaq.dr2.tank19.model.validators.ValidatorGiocatore;
import it.univaq.dr2.tank19.service.ServiceGiocatore;
import it.univaq.dr2.tank19.service.ServiceSicurezza;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ControllerRegistrazione {
    private final ServiceGiocatore serviceGiocatore;
    private final ValidatorGiocatore validatorGiocatore;
    private final ServiceSicurezza serviceSicurezza;

    public ControllerRegistrazione(ServiceGiocatore serviceGiocatore, ValidatorGiocatore validatorGiocatore, ServiceSicurezza serviceSicurezza) {
        this.serviceGiocatore = serviceGiocatore;
        this.validatorGiocatore = validatorGiocatore;
        this.serviceSicurezza = serviceSicurezza;
    }

    @GetMapping("/registrazione")
    public String registration(Model model) {
        model.addAttribute("userForm", new Giocatore());

        return "registrazione";
    }

    @PostMapping("/registrazione")
    public String registration(Model model, @ModelAttribute("userForm") Giocatore formGiocatore, BindingResult bindingResult) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        validatorGiocatore.validate(formGiocatore, bindingResult);
        formGiocatore.setPassword(encoder.encode(formGiocatore.getPassword()));
        if (bindingResult.hasErrors()) {
            // System.out.print(bindingResult); // Test
            model.addAttribute("usernameErrors", bindingResult.getFieldErrors("username"));
            return "registrazione";
        }
        serviceGiocatore.save(formGiocatore);
        serviceSicurezza.autoLogin(formGiocatore.getUsername(), formGiocatore.getPasswordConfirm());
        return "redirect:/index";
    }

}
