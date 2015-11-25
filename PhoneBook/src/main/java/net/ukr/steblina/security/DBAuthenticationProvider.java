package net.ukr.steblina.security;

import java.sql.SQLWarning;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import net.ukr.steblina.models.User;
import net.ukr.steblina.models.UserDAO;

@Service
@Profile("db")
public class DBAuthenticationProvider extends CustomAuthenticationProvider {

    @Autowired
    private UserDAO userDAO;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName();
        String password = authentication.getCredentials().toString();
        User user=null;
		try {
			user = userDAO.getByLogin(login);
		} catch (SQLWarning e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        if(user==null)
        	return null;
        
       if(user.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()))){
            List<GrantedAuthority> grantedAuths = new ArrayList<>();
            grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
            Authentication auth = new UsernamePasswordAuthenticationToken(login, password, grantedAuths);
            return auth;
        } else {
            return null;
        }
    }
 
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }



}
