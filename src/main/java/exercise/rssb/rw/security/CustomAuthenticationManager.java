package exercise.rssb.rw.security;

import exercise.rssb.rw.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * The Class CustomAuthenticationManager.
 *
 * @author Christian Iradukunda
 */
public class CustomAuthenticationManager implements AuthenticationManager {

	@Autowired
	private CustomUserDetailsService userDetailsService;

	// implemented a simple custom authentication manager to just check that credentials provided are right.
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		final UserDetails userDetails = userDetailsService.loadUserByUsername(String.valueOf(authentication.getPrincipal()));
		if(authentication.getPrincipal().equals(userDetails.getUsername()) && authentication.getCredentials().equals(userDetails.getPassword())){
			return authentication;
		} else{
			throw new BadCredentialsException("Username or Password are incorrect");
		}
	}
}