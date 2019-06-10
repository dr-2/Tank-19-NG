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
    public void validate(Object o, Errors errors){
        Persona persona = (Persona)o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if(persona.getUsername().length() < 6 || persona.getUsername().length() > 32){
            errors.rejectValue("username", "Size.userForm.username", "Scegli uno username tra 6 e 32 caratteri");
        }
        if(servicePersona.findByUsername(persona.getUsername()) != null){
            errors.rejectValue("username", "Duplicate.userForm.username", "Username già in uso");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if(persona.getPassword().length() < 8 || persona.getPassword().length() > 32){
            errors.rejectValue("password", "Size.userForm.password", "Scegli una password tra 8 e 32 caratteri");
        }
        if(! persona.getPasswordConfirm().equals(persona.getPassword())){
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm", "Le password inserite non corrispondono");
        }
    }
}

