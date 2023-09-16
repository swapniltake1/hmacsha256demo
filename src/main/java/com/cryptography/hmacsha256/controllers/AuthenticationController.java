package com.cryptography.hmacsha256.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cryptography.hmacsha256.service.HmacSha256SecretKeyGenerator;
import com.cryptography.hmacsha256.service.LoginValidator;
import com.cryptography.hmacsha256.helper.FinalKey;

import java.util.Arrays;
import java.util.Base64;

@RestController
@RequestMapping("/api")
public class AuthenticationController {

    @Autowired
    private LoginValidator loginValidator;

    @Autowired
    private FinalKey finalKey;
    
    @Autowired
    private HmacSha256SecretKeyGenerator keygenerator;

    @GetMapping("/generate-secret-key")
    public ResponseEntity<String> generateSecretKey() {
        // Retrieve the secret key from FinalKey
        //   String secretKeyBytes = finalKey.getSecretKey();
        // log for debug
           
       // System.out.println("Secret Key inn conntroller:: " + secretKeyBytes );

        	byte[] secretKeyBytes = keygenerator.generateSecretKey();
         
        	System.out.println("Secret Key inn conntroller in Bytes:: " + secretKeyBytes );
        
        if (secretKeyBytes != null) {
           
           String base64Key = Base64.getEncoder().encodeToString(secretKeyBytes);
           
           loginValidator.setOldkey(base64Key);
            
           System.out.println("Secret Key inn conntroller in strinng :: " +  base64Key);
            return ResponseEntity.ok(base64Key);
            
        } else {
            return ResponseEntity.badRequest().body("Error retrieving secret key");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestParam String loginId,
            @RequestParam String password,
            @RequestParam String receivedSignature) {
    	
        boolean isValid = loginValidator.validateLogin(receivedSignature);

        if (isValid) {
            // Login is valid
            return ResponseEntity.ok("Login successful");
        } else {
            // Login is invalid
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid login credentials");
        }
    }
}
