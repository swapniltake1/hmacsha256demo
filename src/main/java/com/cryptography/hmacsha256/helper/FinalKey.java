package com.cryptography.hmacsha256.helper;

import org.springframework.stereotype.Component;

@Component
public class FinalKey {

    private String secretKey;

    public FinalKey() {
        // Default constructor
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String generatedKey) {
    	System.out.println("Key set succesfully in FinalKey :: " + generatedKey);
        this.secretKey = generatedKey;
	}
}
