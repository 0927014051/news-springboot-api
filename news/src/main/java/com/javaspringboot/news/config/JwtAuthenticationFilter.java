package com.javaspringboot.news.config;


import com.javaspringboot.news.controller.AuthenticationController;
import com.javaspringboot.news.dto.JwtAuthenticationResponse;
import com.javaspringboot.news.services.JWTService;
import com.javaspringboot.news.services.UserService;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JWTService jwtService;
    private final UserService userService;
    // @Override
    // protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
    //         throws ServletException, IOException {

                
    //             System.err.println("checkout");
    //     //The final keyword is a non-access modifier used for classes, attributes and methods, which makes them non-changeable (impossible to inherit or override).
    //     //means: Từ finalkhóa là một công cụ sửa đổi không truy cập được sử dụng cho các lớp, thuộc tính và phương thức, khiến chúng không thể thay đổi (không thể kế thừa hoặc ghi đè).
    //     final String authHeader = request.getHeader("Authorization");
    //     final String jwt;
    //     final String userEmail;
    //     if(StringUtils.isEmpty(authHeader) || !org.apache.commons.lang3.StringUtils.startsWith(authHeader,"Bearer")){
    //         filterChain.doFilter(request,response);
    //         return;
    //     }
    //     jwt = authHeader.substring(7);
    //     userEmail = jwtService.extractUserName(jwt);
    //     if(StringUtils.isNotEmpty(userEmail) && SecurityContextHolder.getContext().getAuthentication() == null){
    //         UserDetails userDetails = userService.userDetailsService().loadUserByUsername(userEmail);
    //         if(jwtService.isTokenValid(jwt,userDetails)){
    //             SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
    //             UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
    //                 userDetails,null,userDetails.getAuthorities()
    //             );
    //             token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

    //             securityContext.setAuthentication(token);
    //             SecurityContextHolder.setContext(securityContext);
    //         }
    //     }
    //     filterChain.doFilter(request,response);
    // }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String jwt = extractTokenFromResponse(); // Trích xuất token từ JwtAuthenticationResponse
        if (StringUtils.isNotEmpty(jwt) && SecurityContextHolder.getContext().getAuthentication() == null) {
            String userEmail = jwtService.extractUserName(jwt);
            UserDetails userDetails = userService.userDetailsService().loadUserByUsername(userEmail);
            if (jwtService.isTokenValid(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        filterChain.doFilter(request, response);
    }


    private String extractTokenFromResponse() {
       JwtAuthenticationResponse res = AuthenticationController.getJwtSiginController();
       if(res != null){
        return res.getToken();
       }
       return "";
    }



}
