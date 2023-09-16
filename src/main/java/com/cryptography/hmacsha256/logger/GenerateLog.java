package com.cryptography.hmacsha256.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.springframework.stereotype.Service;

@Service
public class GenerateLog {
	private static final Logger logger = (Logger) LogManager.getLogger(GenerateLog.class);

    public void doSomething() {
        logger.info("This is an info log message");
        logger.error("This is an error log message");
    }
}

