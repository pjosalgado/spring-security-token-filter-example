package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

public class SecurityExceptionHandler implements AuthenticationEntryPoint, AccessDeniedHandler {

    @Override
    @SneakyThrows
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException ex) {
        OutputStream out = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out, ex.getMessage());
        out.flush();
    }

    @Override
    @SneakyThrows
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException ex) {
        OutputStream out = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out, ex.getMessage());
        out.flush();
    }

}
