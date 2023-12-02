package com.tkgroupbd.pusti.api.configs.ipandbrowserconfig;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RequestInfoFilter extends OncePerRequestFilter{
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // Get the IP address from the request
        String ip = request.getRemoteAddr();

        // You can use request.getHeader("User-Agent") to extract browser information
        String browser = request.getHeader("User-Agent");

        // Store the IP and browser information in a thread-local variable or some shared context
        RequestInfoContextHolder.setIp(ip);
        RequestInfoContextHolder.setBrowser(browser);

        filterChain.doFilter(request, response);
    }
}
