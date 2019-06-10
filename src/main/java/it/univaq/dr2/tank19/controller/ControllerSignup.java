package it.univaq.dr2.tank19.controller;


import it.univaq.dr2.tank19.model.Persona;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ControllerSignup {

    @PostMapping("/signup")
    public Persona formPost(@RequestParam(value = "username", defaultValue = "username") String username) {
        Persona persona = new Persona();
        persona.setUsername(username);
        System.out.println("Persona: " + persona.getUsername());
        return persona;
    }
}