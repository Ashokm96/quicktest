package com.quick.questions.ws.shared;

import java.security.SecureRandom;
import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.quick.questions.ws.security.SecurityConstants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class Utills {

	private final Random RANDOM = new SecureRandom();
    private final String data = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public String generatedString(int len){
        return generatedRandomUserId(len);
    }
    public String generatedAddressId(int len){
        return generatedRandomUserId(len);
    }
    public String generatedRandomUserId(int length){
        StringBuilder returnedValue = new StringBuilder(length);
       for(int i = 0; i < length;i++){
           int randomNumber = RANDOM.nextInt(data.length());
           returnedValue.append(data.charAt(randomNumber));
       }
        return new String(returnedValue);
    }	
    
    public String generatePasswordResetToken(String userId){

		String token = Jwts.builder()
				.setSubject(userId)
				.setExpiration(new Date(System.currentTimeMillis()+SecurityConstants.PASSWORD_EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SecurityConstants.getTokenSecret())
				.compact();
		return token;
    }
    
    public static boolean hasTokenExpired(String token) {
		// TODO Auto-generated method stub
		boolean returnValue = false;
		try {
			 Claims claims = Jwts.parser()
						.setSigningKey(SecurityConstants.getTokenSecret())
						.parseClaimsJws(token)
						.getBody();
						 
						 Date tokenExperationDate = claims.getExpiration();
						 Date todayDate = new Date();
						 
						
						returnValue = tokenExperationDate.before(todayDate);
		} catch (ExpiredJwtException e) {
			// TODO: handle exception
			returnValue = true;
		}
		return returnValue;
	}

	public  String generateEmailVerificationToken(String userId) {
		// TODO Auto-generated method stub
		
		String token = Jwts.builder()
				.setSubject(userId)
				.setExpiration(new Date(System.currentTimeMillis()+SecurityConstants.EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SecurityConstants.getTokenSecret())
				.compact();
		return token;
	}	
}
