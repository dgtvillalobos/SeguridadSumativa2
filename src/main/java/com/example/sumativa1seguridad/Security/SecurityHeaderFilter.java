package com.example.sumativa1seguridad.Security;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class SecurityHeaderFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse http = (HttpServletResponse) response;

http.setHeader("Content-Security-Policy",
    "default-src 'self'; " +
    "script-src 'self' 'unsafe-inline' https://cdn.jsdelivr.net https://cdn.jsdelivr.net/npm/sweetalert2@11; " +
    "style-src 'self' 'unsafe-inline' https://cdn.jsdelivr.net; " +
    "img-src 'self' data:; " +
    "font-src 'self' https://cdn.jsdelivr.net; " +
    "object-src 'none'; " +
    "frame-ancestors 'none'; " +
    "base-uri 'self'; " +
    "form-action 'self';");

    //   http.setHeader("Content-Security-Policy",
    // "default-src 'self'; " +
    // "script-src 'self' 'unsafe-inline' https://cdn.jsdelivr.net https://cdn.jsdelivr.net/npm/sweetalert2@11; " +
    // "style-src 'self' 'unsafe-inline' https://cdn.jsdelivr.net; " +
    // "img-src 'self' data:;");


        http.setHeader("X-Content-Type-Options", "nosniff");
        http.setHeader("X-Frame-Options", "DENY");
        http.setHeader("Referrer-Policy", "no-referrer");

        chain.doFilter(request, response);
    }
}
