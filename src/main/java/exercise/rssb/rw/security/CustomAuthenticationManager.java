package exercise.rssb.rw.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * The Class CustomAuthenticationManager.
 *
 * @author Christian Iradukunda
 */
public class CustomAuthenticationManager implements AuthenticationManager {

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		if(authentication.getPrincipal().equals("rssb") && authentication.getCredentials().equals("Test#2021")){
			return authentication;
		} else{
			throw new BadCredentialsException("Username or Password are incorrect");
		}
	}
}