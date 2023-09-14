package pl.hibernate.study.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AuthProviderImpl implements AuthenticationProvider {
    private final UserDetailsServiceRealisation userDetailsServiceRealisation;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthProviderImpl(UserDetailsServiceRealisation userDetailsServiceRealisation, PasswordEncoder passwordEncoder) {
        this.userDetailsServiceRealisation = userDetailsServiceRealisation;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        String encodedPassword = passwordEncoder.encode(password);
        UserDetails personDetails = userDetailsServiceRealisation.loadUserByUsername(username);
        if(!passwordEncoder.matches(password, encodedPassword))
            throw new BadCredentialsException("Incorrect password");
        return new UsernamePasswordAuthenticationToken(personDetails, password, personDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
