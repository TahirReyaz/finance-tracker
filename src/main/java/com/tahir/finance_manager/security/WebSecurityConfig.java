package com.tahir.finance_manager.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {

  private final JwtAuthFilter jwtAuthFilter;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        .csrf(csrfConfig -> csrfConfig.disable())
        .sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(
            auth -> auth
                .requestMatchers("/public/**", "/auth/**", "/").permitAll()

                // Expense Type permissions
                .requestMatchers(HttpMethod.GET, "/expense-type/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/expense-type").authenticated()
                .requestMatchers(HttpMethod.PUT, "/expense-type/**").authenticated()
                .requestMatchers(HttpMethod.DELETE, "/expense-type/**").authenticated()

                // Expense permissions
                .requestMatchers("/expense", "/expense/**").authenticated()

                // Expense Group permissions
                .requestMatchers("/expense-group", "/expense-group/**").authenticated()

                // Expense Limit permissions
                .requestMatchers("/expense-limit", "/expense-limit/**").authenticated()

                // Stat permissions
                .requestMatchers("/stats").authenticated()

                // .requestMatchers("/admin/**").hasRole("ADMIN"))
                .anyRequest().authenticated())
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
    return httpSecurity.build();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
    return configuration.getAuthenticationManager();
  }

  // @Bean
  // UserDetailsService userDetailsService() {
  // UserDetails user1 = User.withUsername("admin")
  // .password(passwordEncoder.encode("pass"))
  // .roles("ADMIN").build();

  // return new InMemoryUserDetailsManager(user1);

  // }
}
