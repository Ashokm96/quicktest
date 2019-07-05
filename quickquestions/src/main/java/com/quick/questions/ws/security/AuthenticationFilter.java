package com.quick.questions.ws.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quick.questions.ws.SpringApplicationContext;
import com.quick.questions.ws.service.UserService;
import com.quick.questions.ws.shared.dto.UserDto;
import com.quick.questions.ws.ui.model.request.UserLoginRequestModel;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authenticationManager;
	
	public AuthenticationFilter(AuthenticationManager autManager) {
		this.authenticationManager = autManager;
	}
	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse resp) throws AuthenticationException{
		try {
			UserLoginRequestModel credentials = new ObjectMapper()
					.readValue(req.getInputStream(), UserLoginRequestModel.class); 
			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							credentials.getEmail(), credentials.getPassword(),
							new ArrayList<>()));
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse resp, FilterChain chain
			,Authentication auth) throws IOException, ServletException{
		String userName = ((User)auth.getPrincipal()).getUsername();
		String secrettoken = SecurityConstants.getTokenSecret();
		System.out.println("Secret token  +  "+secrettoken);
		String token = Jwts.builder()
		.setSubject(userName)
		.setExpiration(new Date(System.currentTimeMillis()+SecurityConstants.EXPIRATION_TIME))
		.signWith(SignatureAlgorithm.HS512, SecurityConstants.getTokenSecret())
		.compact();
		
		UserService userService = (UserService) SpringApplicationContext.getBean("userServiceImpl");
		UserDto userdto = userService.getUser(userName);
		
		resp.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX+token);
		resp.addHeader("userId", userdto.getUserId());
	}
}
