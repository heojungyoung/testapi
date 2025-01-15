package com.batch.testapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class AppRunner implements ApplicationRunner {
    private final Environment environment;

    public AppRunner(Environment environment) {
        this.environment = environment;
    }

    private final Logger logger = LoggerFactory.getLogger(AppRunner.class);

    @Override
    public void run(ApplicationArguments args) {

        if(logger.isErrorEnabled()){
            logger.info("===================다중 프로파일 테스트===================");
            logger.info("Active profiles : {} " , Arrays.toString(environment.getActiveProfiles()));
            logger.info("test url : {}" , environment.getProperty("test.url"));
            logger.info("====================================================");
        }

    }
}