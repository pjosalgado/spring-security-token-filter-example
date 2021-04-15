package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(new SecurityExceptionHandler())
                .authenticationEntryPoint(new SecurityExceptionHandler())
                .and()
                .authorizeRequests()
                .antMatchers("/hello", "/hello/*").hasAuthority("xpto")
                .anyRequest().permitAll()
                .and()
                .addFilter(new CustomAuthenticationFilter(authenticationManager()))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

}
