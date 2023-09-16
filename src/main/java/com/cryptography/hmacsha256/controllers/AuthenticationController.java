package com.cryptography.hmacsha256.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cryptography.hmacsha256.service.HmacSha256SecretKeyGenerator;
import com.cryptography.hmacsha256.service.LoginValidator;
import com.cryptography.hmacsha256.helper.FinalKey;

import java.util.Base64;

@RestController
@RequestMapping("/api")
public class AuthenticationController {

    private static final Logger logger = LogManager.getLogger(AuthenticationController.class);

    @Autowired
    private LoginValidator loginValidator;

    @Autowired
    private FinalKey finalKey;

    @Autowired
    private HmacSha256SecretKeyGenerator keygenerator;

    @GetMapping("/generate-secret-key")
    public ResponseEntity<String> generateSecretKey() {
        try {
            byte[] secretKeyBytes = keygenerator.generateSecretKey();

            logger.info("Secret Key in Bytes: {}", secretKeyBytes);

            if (secretKeyBytes != null) {
                String base64Key = Base64.getEncoder().encodeToString(secretKeyBytes);

                loginValidator.setOldkey(base64Key);

                logger.info("Secret Key in String: {}", base64Key);
                return ResponseEntity.ok(base64Key);
            } else {
                return ResponseEntity.badRequest().body("Error retrieving secret key");
            }
        } catch (Exception e) {
            logger.error("Error generating secret key", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
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
            logger.info("Login successful for loginId: {}", loginId);
            return ResponseEntity.ok("Login successful");
        } else {
            // Login is invalid
            logger.warn("Invalid login attempt for loginId: {}", loginId);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid login credentials");
        }
    }
}
