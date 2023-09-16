package com.cryptography.hmacsha256.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cryptography.hmacsha256.helper.FinalKey;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Component
public class HmacSha256SecretKeyGenerator {

    @Autowired
    private FinalKey finalkey;

    public  byte[] generateSecretKey() {
        try {
            // Create a KeyGenerator for the HMAC-SHA256 algorithm
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");

            // Generate a secure random secret key
            SecretKey generatedKey = keyGenerator.generateKey();
            System.out.println("original Generated Key by generator ::"+generatedKey);

            // Convert the SecretKey to a byte array
            byte[] secretKeyBytes = generatedKey.getEncoded();
            
            System.out.println("Generate key secretKeyBytes:: "+secretKeyBytes);
            
            
            String base64 = Base64.getEncoder().encodeToString(secretKeyBytes);
            System.out.println("Generate key in String:: "+base64);
            

            finalkey.setSecretKey(base64);

         

            // Logging to verify that the key is generated
            System.out.println("Secret Key Generated:: " + Base64.getEncoder().encodeToString(secretKeyBytes));

            return secretKeyBytes;
            
        } catch (NoSuchAlgorithmException e) {
            // Handle the exception appropriately
            e.printStackTrace();
            return null;
        }
    }
}
