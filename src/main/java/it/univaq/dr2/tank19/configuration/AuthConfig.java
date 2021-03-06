package it.univaq.dr2.tank19.configuration;

import it.univaq.dr2.tank19.service.ServiceGiocatore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class AuthConfig extends WebSecurityConfigurerAdapter {
    private final ServiceGiocatore serviceGiocatore;

    public AuthConfig(ServiceGiocatore serviceGiocatore) {
        this.serviceGiocatore = serviceGiocatore;
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/images/**", "/vendor/**", "/js/**", "/login*", "/css/**", "/pictures/**", "/fonts/**", "/registrazione", "/h2-console/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/index", true)
                //.failureUrl("/login.html?error=true")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .deleteCookies("JSESSIONID")
                .and()
                .headers().frameOptions().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // @formatter:off

        auth.userDetailsService(serviceGiocatore).passwordEncoder(getEncoder());

        // @formatter:on
    }

    @Bean
    public PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }
}
