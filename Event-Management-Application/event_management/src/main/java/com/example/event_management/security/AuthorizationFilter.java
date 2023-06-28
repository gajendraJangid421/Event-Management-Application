package com.example.event_management.security;
import com.example.event_management.exception.NotFoundException;
import com.example.event_management.model.Session;
import com.example.event_management.model.Users;
import com.example.event_management.repository.SessionRepository;
import com.example.event_management.repository.UsersRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.*;


@Configuration
public class AuthorizationFilter extends OncePerRequestFilter {
    private final String Auth_Token_Header="token";
    @Autowired
    SessionRepository sessionRepository;
    @Autowired
    UsersRepository usersRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        String reqToken=request.getHeader(Auth_Token_Header);
        if(reqToken == null || reqToken.isEmpty()){
            chain.doFilter(request,response);

        }
        else {
            UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(request, response);
            }

        }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request){
         String reqToken=request.getHeader(Auth_Token_Header);
         Optional<Session> userSession=sessionRepository.findByToken(reqToken);
         if(userSession.isEmpty()){
             throw new NotFoundException("Token is invalid");
         }

         String userId=userSession.get().getUserId();
         Optional<Users> user=usersRepository.findById(userId);
         if(user.isEmpty()){
             throw new NotFoundException("Unable to fetch user");
         }

        SimpleGrantedAuthority grantedAuthority=new SimpleGrantedAuthority(user.get()
                .getRole());
         return new UsernamePasswordAuthenticationToken(user,null,
                 Collections.singleton(grantedAuthority));
    }
}
