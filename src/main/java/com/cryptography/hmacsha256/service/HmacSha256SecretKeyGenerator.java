package com.cryptography.hmacsha256.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cryptography.hmacsha256.helper.FinalKey;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Component
public class HmacSha256SecretKeyGenerator {

    private static final Logger logger = LogManager.getLogger(HmacSha256SecretKeyGenerator.class);

    @Autowired
    private FinalKey finalkey;

    public byte[] generateSecretKey() {
        try {
            // Create a KeyGenerator for the HMAC-SHA256 algorithm
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");

            // Generate a secure random secret key
            SecretKey generatedKey = keyGenerator.generateKey();
            logger.info("Original Generated Key by generator: {}", generatedKey);

            // Convert the SecretKey to a byte array
            byte[] secretKeyBytes = generatedKey.getEncoded();

            logger.info("Generated key secretKeyBytes: {}", secretKeyBytes);

            String base64 = Base64.getEncoder().encodeToString(secretKeyBytes);
            logger.info("Generated key in String: {}", base64);

            finalkey.setSecretKey(base64);

            // Logging to verify that the key is generated
            logger.info("Secret Key Generated: {}", Base64.getEncoder().encodeToString(secretKeyBytes));

            return secretKeyBytes;

        } catch (NoSuchAlgorithmException e) {
            // Handle the exception appropriately
            logger.error("Error generating secret key", e);
            return null;
        }
    }
}
