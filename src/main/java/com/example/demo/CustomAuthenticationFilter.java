package com.example.demo;

import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CustomAuthenticationFilter extends BasicAuthenticationFilter {

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    @SneakyThrows
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) {

        String accessToken = req.getHeader(HttpHeaders.AUTHORIZATION);

        if (accessToken != null) {

            accessToken = accessToken.replace("Bearer ", "");

            SignedJWT signedJWT = SignedJWT.parse(accessToken);
            JWTClaimsSet claims = signedJWT.getJWTClaimsSet();
            String[] scopes = claims.getStringArrayClaim("scopes");

            List<SimpleGrantedAuthority> authorities = Arrays.stream(scopes)
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());

            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(claims.getSubject(), null, authorities);

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        chain.doFilter(req, res);
    }

}
