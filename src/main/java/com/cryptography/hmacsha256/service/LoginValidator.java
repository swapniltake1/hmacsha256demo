package com.cryptography.hmacsha256.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cryptography.hmacsha256.helper.FinalKey;

@Component
public class LoginValidator {

    private static final Logger logger = LogManager.getLogger(LoginValidator.class);

    @Autowired
    private FinalKey finalkey;

    private String oldkey;

    public void setOldkey(String oldkey) {
        logger.info("Old key set in LoginValidator: {}", oldkey);
        this.oldkey = oldkey;
    }

    public boolean validateLogin(String receivedSignature) {
        logger.info("In validate login block");
        logger.info("Old Key: {}", oldkey);
        logger.info("Received Signature: {}", receivedSignature);

        if (oldkey.equals(receivedSignature)) {
            logger.info("Key matched successfully!");
            return true;
        }

        logger.info("Key Not Matched!");
        return false;
    }
}
