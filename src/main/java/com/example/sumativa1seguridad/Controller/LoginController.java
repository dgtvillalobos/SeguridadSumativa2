package com.example.sumativa1seguridad.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.sumativa1seguridad.Security.JWTAuthtenticationConfig;
import com.example.sumativa1seguridad.Servicio.MyUserDetailsService;

@RestController
public class LoginController {

  @Autowired JWTAuthtenticationConfig jwtAuthtenticationConfig;
  @Autowired MyUserDetailsService userDetailsService;
  @Autowired PasswordEncoder encoder;

  @PostMapping("/api/login") // mejor separarlo del form login de Thymeleaf
  public String login(@RequestParam("user") String username,
                      @RequestParam("password") String rawPassword) {
    var userDetails = userDetailsService.loadUserByUsername(username);
    if (!encoder.matches(rawPassword, userDetails.getPassword())) {
      throw new RuntimeException("Invalid login");
    }
    return jwtAuthtenticationConfig.getJWTToken(username);
  }
}
