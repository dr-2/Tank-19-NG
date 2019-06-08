package it.univaq.dr2.tank19.validator;

import it.univaq.dr2.tank19.model.Persona;
import it.univaq.dr2.tank19.service.ServicePersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ValidatorPersona implements Validator {
    @Autowired
    private ServicePersona servicePersona;

    @Override
    public boolean supports(Class<?> aClass){
        return Persona.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errori){
        Persona persona = (Persona)o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errori, "username", "NotEmpty");
        if(persona.getUsername().length() < 6 || persona.getUsername().length() > 32){
            errori.rejectValue("username", "Size.userForm.username");
        }
        if(servicePersona.findByUsername(persona.getUsername()) != null){
            errori.rejectValue("username", "Duplicate.userForm.username");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errori, "password", "NotEmpty");
        if(persona.getPassword().length() < 8 || persona.getPassword().length() > 32){
            errori.rejectValue("password", "Size.userForm.password");
        }
        if(! persona.getPasswordConfirm().equals(persona.getPassword())){
            errori.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }
    }
}

