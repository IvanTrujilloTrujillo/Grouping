package com.ironhack.edgeservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.csrf().ignoringAntMatchers("/groups")
                .ignoringAntMatchers("/groups/*")
                .ignoringAntMatchers("/sites")
                .ignoringAntMatchers("/sites/*")
                .ignoringAntMatchers("/reviews");
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/groups").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/groups/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/sites").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/sites/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/reviews").hasRole("ADMIN")
                .anyRequest().permitAll();
    }
}
