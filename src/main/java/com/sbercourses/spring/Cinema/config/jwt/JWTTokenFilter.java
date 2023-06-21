/*
package com.sbercourses.spring.Cinema.config.jwt;

import com.sbercourses.spring.Cinema.service.userdetails.CustomUserDetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component

public class JWTTokenFilter extends OncePerRequestFilter {

    private final JWTTokenUtil jwtTokenUtil;

    private final CustomUserDetailService customUserDetailService;

    public JWTTokenFilter(JWTTokenUtil jwtTokenUtil, CustomUserDetailService customUserDetailService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.customUserDetailService = customUserDetailService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = null;
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(header==null || !header.startsWith("Bearer "))
        {
            filterChain.doFilter(request,response);
            return;
        }
        //ПОлучаем токен
        token = header.split(" ")[1].trim();
        UserDetails userDetails = customUserDetailService.loadUserByUsername(jwtTokenUtil.getUserNameFromToken(token));

        //Подтверждение токена

        if (!jwtTokenUtil.validateToken(token,userDetails))
        {
            filterChain.doFilter(request,response);
            return;
        }
        //Установка пользователя в спринг сесурити+
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request,response);



    }
}
*/
