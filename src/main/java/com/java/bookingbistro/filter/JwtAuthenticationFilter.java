package com.java.bookingbistro.filter;

import com.java.bookingbistro.util.JwtTokenUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();

    private static final List<String> EXCLUDE_URLS = Arrays.asList("/sign-in","/sign-up", "/favicon.ico", "/swagger-ui.html");
    private static final String H2_CONSOLE_PATH = "/h2-console";
    private static final String SWAGGER_UI_PATH = "/webjars/springfox-swagger-ui";

    private static final String ACCESS_CONTROL_REQUEST_HEADERS = "access-control-request-headers";
//    : authorization,content-type

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String path = request.getRequestURI();
        // Skip filtering for the specified paths
        if (EXCLUDE_URLS.contains(path) || path.contains(H2_CONSOLE_PATH) || path.contains(SWAGGER_UI_PATH)) {
            chain.doFilter(request, response);
            return;
        }
        if(request.getHeader(ACCESS_CONTROL_REQUEST_HEADERS) != null) {
            //Allowing pre-flight requests
            chain.doFilter(request, response);
            return;
        }

        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            if (jwtTokenUtil.validateToken(token)) {
                String username = jwtTokenUtil.getUsernameFromToken(token);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        username, null, new ArrayList<>());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or missing token");
            return;
        }
        chain.doFilter(request, response);
    }
}