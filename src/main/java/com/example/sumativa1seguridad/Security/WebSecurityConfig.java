package com.example.sumativa1seguridad.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
    // por defecto viene habilitado en spring security 6 
      // .csrf(csrf -> csrf.disable()) 
      .authorizeHttpRequests(auth -> auth
        .requestMatchers("/", "/recetas", "/buscar",
                         "/favicon.ico", "/error",
                         "/webjars/**", "/assets/**",
                         "/static/**", "/css/**", "/js/**", "/img/**", "/images/**").permitAll()
        .requestMatchers("/detalle/**").authenticated()  
        .anyRequest().authenticated()
      )
      
      .formLogin(form -> form
        .loginPage("/login").permitAll()
        .defaultSuccessUrl("/recetas", true)
      )
      .logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/recetas").permitAll());

    return http.build();
  }

  @Bean
  public org.springframework.security.crypto.password.PasswordEncoder passwordEncoder() {
      return new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
  }

}
