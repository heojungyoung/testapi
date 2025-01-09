package com.batch.testapi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/main/v1")
public class GetController {

   // -Dspring.profiles.active=test

   // http://localhost:8080/main/v1/check
   // {{testApi}}/main/v1/check

    private final Logger logger = LoggerFactory.getLogger(GetController.class);

    @GetMapping(value = "/check")
    @Cacheable(key = "#version", value = "svcsupport")
    public String getHello(@RequestParam(name = "version", required = false) String version){
        logger.info(version);
        logger.info("getHello 메소드가 호출되었습니다.");
        return "check version";
    }
}
