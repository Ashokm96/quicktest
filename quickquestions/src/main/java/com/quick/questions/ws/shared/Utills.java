package com.quick.questions.ws.shared;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
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
}
