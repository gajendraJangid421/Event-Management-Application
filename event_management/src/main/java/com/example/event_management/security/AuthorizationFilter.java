//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower

package com.example.event_management.security;
import com.example.event_management.exception.UnAuthorisedException;
import com.example.event_management.model.Session;
import com.example.event_management.model.Users;
import com.example.event_management.repository.SessionRepository;
import com.example.event_management.repository.UsersRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.Objects;
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

    @Autowired
    ObjectMapper objectMapper;

       @Override
       protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String reqToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        String path=request.getRequestURI();
        if(path.equals("/api/login") || path.equals("/api/users")){
            if (reqToken != null && !reqToken.isEmpty()) {
                UsernamePasswordAuthenticationToken authentication = getAuthentication(request,response);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                filterChain.doFilter(request, response);
            } else {
                filterChain.doFilter(request, response);
            }
        }

        else{
            try {
                if (reqToken != null && !reqToken.isEmpty()) {
                    UsernamePasswordAuthenticationToken authentication = getAuthentication(request,response);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    filterChain.doFilter(request, response);
                } else {
                    throw new UnAuthorisedException("Token not found");
                }
            }catch (UnAuthorisedException e){
                
                response.setContentType("application/json");
                response.setStatus(e.getHttpStatus().value());
                response.getWriter().write(objectMapper.writeValueAsString(e));
            }


        }
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String reqToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        Optional<Session> userSession = isTokenExpired(reqToken,response);
        UsernamePasswordAuthenticationToken authenticationToken = null;
        try {
            if (userSession.equals("")) {
                throw new UnAuthorisedException("Token is invalid");
            } else {
                String userId = userSession.get().getUserId();
                Optional<Users> user = this.usersRepository.findById(userId);
                if (user.isEmpty()) {
                    throw new UnAuthorisedException("Unable to fetch user");
                } else {
                    SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.get().getRole().toString());
                    authenticationToken= new UsernamePasswordAuthenticationToken(user, null, Collections.singleton(grantedAuthority));
                }
            }
        }catch (UnAuthorisedException e){
            response.setContentType("application/json");
            response.setStatus(e.getHttpStatus().value());
            response.getWriter().write(objectMapper.writeValueAsString(e));
        }
        return authenticationToken;
    }

    public Optional<Session> isTokenExpired(String token,HttpServletResponse response) throws IOException {
        Optional<Session> session = sessionRepository.findByToken(token);
        try {
            if (Objects.isNull(session)) {
            return null;
            }
            long differenceInTime = difference(Timestamp.from(Instant.now()).toString(),
                    session.get().getTokenExpiry());
            if (differenceInTime >= 1000000) {
            sessionRepository.deleteById(session.get().getId());
            throw new UnAuthorisedException("Login Again");
            }
        }catch (UnAuthorisedException e){
            response.reset();
            response.setContentType("application/json");
            response.setStatus(e.getHttpStatus().value());
            response.getWriter().write(objectMapper.writeValueAsString(e));
        }
        return session;

    }

    public long difference(String now, String before){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");

        try {
            Date presentDate = sdf.parse(now);
            Date beforeDate = sdf.parse(before);

            return presentDate.getTime() - beforeDate.getTime();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}


