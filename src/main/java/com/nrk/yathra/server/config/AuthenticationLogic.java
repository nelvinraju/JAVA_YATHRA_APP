package com.nrk.yathra.server.config;

import com.nrk.yathra.server.entity.Users;
import com.nrk.yathra.server.repo.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuthenticationLogic implements AuthenticationProvider {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName();
        String pwd = authentication.getCredentials().toString();
        Users user = usersRepository.readByUserName(userName);
        if(null != user && user.getUserId() >0 &&
                passwordEncoder.matches(pwd,user.getUserPassword())){
            return new UsernamePasswordAuthenticationToken(
                    user.getUserName(), null, getGrantedAuthorities(user.getUserRole()));
        }else{
            throw new BadCredentialsException("Invalid credentials!");
        }
    }
    private List<GrantedAuthority> getGrantedAuthorities(String roles) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+roles));
        return grantedAuthorities;
    }
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
