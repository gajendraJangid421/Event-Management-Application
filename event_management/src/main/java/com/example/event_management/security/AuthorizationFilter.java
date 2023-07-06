//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower

package com.example.event_management.security;
import com.example.event_management.exception.UnAuthorisedException;
import com.example.event_management.model.Session;
import com.example.event_management.model.Users;
import com.example.event_management.repository.SessionRepository;
import com.example.event_management.repository.UsersRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
public class AuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    SessionRepository sessionRepository;
    @Autowired
    UsersRepository usersRepository;

       @Override
       protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String reqToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        String path=request.getRequestURI();
        if(path.equals("/api/login")){
            if (reqToken != null && !reqToken.isEmpty()) {
                UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                filterChain.doFilter(request, response);
            } else {
                filterChain.doFilter(request, response);
            }
        }

        else{
            if (reqToken != null && !reqToken.isEmpty()) {
                UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                filterChain.doFilter(request, response);
            } else {
                throw new UnAuthorisedException("Token not found");
            }

        }



    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String reqToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        Optional<Session> userSession = this.sessionRepository.findByToken(reqToken);
        if (userSession.isEmpty()) {
            throw new UnAuthorisedException("Token is invalid");
        } else {
            String userId = (userSession.get()).getUserId();
            Optional<Users> user = this.usersRepository.findById(userId);
            if (user.isEmpty()) {
                throw new UnAuthorisedException("Unable to fetch user");
            } else {
                SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.get().getRole().toString());
                return new UsernamePasswordAuthenticationToken(user, null, Collections.singleton(grantedAuthority));
            }
        }
    }
}
