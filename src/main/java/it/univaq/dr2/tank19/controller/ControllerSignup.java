package it.univaq.dr2.tank19.controller;


import it.univaq.dr2.tank19.model.Persona;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ControllerSignup {
    @RequestMapping(method = RequestMethod.POST, value = "/signup")
    public void nuovaPersona(@RequestBody Persona persona) {
        System.out.println("Username della nuova persona: " + persona.getUsername());
    }
}
