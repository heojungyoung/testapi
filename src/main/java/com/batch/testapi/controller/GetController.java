package com.batch.testapi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/main/v1")
public class GetController {

    private final Logger LOGGER = LoggerFactory.getLogger(GetController.class);

    // http://localhost:8080/main/v1/check
    // {{testApi}}/main/v1/check
    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public String getHello() {
        LOGGER.info("getHello 메소드가 호출되었습니다.");
        return "check version";
    }
}
