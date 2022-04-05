package com.egrine.mailspammer.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/v1/users").permitAll()
                .antMatchers("/api/v1/templates").authenticated()
                .antMatchers("/api/v1/{userId}/recipients").authenticated()
                .antMatchers("/api/v1/users/{id}").authenticated()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/api/v1/users/login")
                .successHandler(ajaxAuthenticationSuccessHandler())
                .failureHandler(ajaxAuthenticationFailureHandler())
                .permitAll()
                .and()
                .logout()
                // So it accepts GET /logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .permitAll()
                .and()
                .headers().frameOptions().disable();
    }

    private AuthenticationFailureHandler ajaxAuthenticationFailureHandler() {
        return new AjaxAuthenticationFailureHandler();
    }

    private AuthenticationSuccessHandler ajaxAuthenticationSuccessHandler() {
        return new AjaxAuthenticationSuccessHandler();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
