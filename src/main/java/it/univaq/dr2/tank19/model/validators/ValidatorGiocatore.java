package it.univaq.dr2.tank19.model.validators;

import it.univaq.dr2.tank19.model.Giocatore;
import it.univaq.dr2.tank19.service.ServiceGiocatore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ValidatorGiocatore implements Validator {

    private final ServiceGiocatore serviceGiocatore;

    @Value("${tank.password.lunghezzaminima}")
    private Integer minPasswdLen;

    @Value("${tank.password.lunghezzamassima}")
    private Integer maxPasswdLen;

    @Value("${tank.username.lunghezzaminima}")
    private Integer minUnameLen;

    @Value("${tank.username.lunghezzamassima}")
    private Integer maxUnameLen;

    public ValidatorGiocatore(ServiceGiocatore serviceGiocatore) {
        this.serviceGiocatore = serviceGiocatore;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Giocatore.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Giocatore giocatore = (Giocatore) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (giocatore.getUsername().length() < minUnameLen || giocatore.getUsername().length() > maxUnameLen) {
            errors.rejectValue("username", "Size.userForm.username", "Scegli uno username tra 6 e 32 caratteri");
        }
        if (serviceGiocatore.findByUsername(giocatore.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username", "Username già in uso");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (giocatore.getPassword().length() < minPasswdLen || giocatore.getPassword().length() > maxPasswdLen) {
            errors.rejectValue("password", "Size.userForm.password", "Scegli una password tra 8 e 32 caratteri");
        }
        if (!giocatore.getPasswordConfirm().equals(giocatore.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm", "Le password inserite non corrispondono");
        }
    }
}
