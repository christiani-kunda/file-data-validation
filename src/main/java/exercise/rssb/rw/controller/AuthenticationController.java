package exercise.rssb.rw.controller;

import exercise.rssb.rw.config.JwtConfig;
import exercise.rssb.rw.util.AuthenticationRequest;
import exercise.rssb.rw.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;


@RestController
@CrossOrigin
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtConfig jwtConfig;

	@PostMapping(value = "/authenticate")
	public Response<String> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response){
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
			final String token = jwtConfig.generateToken(authenticationRequest.getUsername());

			return new Response<>(token);
		} catch (Exception ex){
			response.setStatus(401);
			return new Response<>(ex);
		}
	}

}