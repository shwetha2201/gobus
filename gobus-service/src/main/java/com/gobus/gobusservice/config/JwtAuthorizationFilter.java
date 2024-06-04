package com.gobus.gobusservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.*;

@Component
@AllArgsConstructor
@Slf4j
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtil;
    private final ObjectMapper mapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Map<String, Object> errorDetails = new HashMap<>();

        try {
            String accessToken = jwtUtil.resolveToken(request);
            if (accessToken == null) {
                filterChain.doFilter(request, response);
                return;
            }
            log.info("Token: {}", accessToken);
            Claims claims = jwtUtil.resolveClaims(request);

            if (claims != null & jwtUtil.validateClaims(claims)) {
                String email = claims.getSubject();
                log.info("email: {}", email);

                List<GrantedAuthority> authorities = claims.get("roles", List.class).stream().map(authority -> new SimpleGrantedAuthority(authority.toString())).toList();
                Authentication authentication =
                        new UsernamePasswordAuthenticationToken(email, "", authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            log.error("Error during jwt filter", e);
            errorDetails.put("message", "Authentication Error");
            errorDetails.put("details", e.getMessage());
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            mapper.writeValue(response.getWriter(), errorDetails);
        }
        filterChain.doFilter(request, response);
    }
}
