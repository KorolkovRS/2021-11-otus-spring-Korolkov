package ru.korolkovrs.spring23.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests()
                .antMatchers(HttpMethod.DELETE, "/api/v1/books/**").hasAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.POST, "/api/v1/books/**").hasAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/v1/books/**").hasAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/v1/comments/**").hasAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/v1/comments/**").hasAuthority("ROLE_ADMIN")
                .anyRequest().authenticated()
                .and()
                .logout()
                .logoutUrl("/api/v1/logout")
                .deleteCookies("JSESSIONID")
                .and()
                .formLogin();
    }
}