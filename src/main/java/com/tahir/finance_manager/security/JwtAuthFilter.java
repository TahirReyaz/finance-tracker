package com.tahir.finance_manager.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.tahir.finance_manager.entities.User;
import com.tahir.finance_manager.repositories.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
  private final UserRepository userRepository;
  private final AuthUtil authUtil;
  private final HandlerExceptionResolver handlerExceptionResolver;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse reponse, FilterChain filterChain)
      throws ServletException {
    try {
      log.info("incoming request: {}", request.getRequestURI());

      final String requestTokenHeader = request.getHeader("Authorization");
      if (requestTokenHeader == null || !requestTokenHeader.startsWith("Bearer")) {
        filterChain.doFilter(request, reponse);
        return;
      }

      String token = requestTokenHeader.split("Bearer ")[1];
      String username = authUtil.getUsernameFromToken(token);

      if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        log.info("Username: " + username);
        User user = userRepository.findByUsername(username).orElseThrow();
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
            user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        filterChain.doFilter(request, reponse);
      }
    } catch (Exception ex) {
      handlerExceptionResolver.resolveException(request, reponse, null, ex);
    }
  }
}