package com.batch.testapi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/main/v1")
public class GetController {


   // http://localhost:8080/main/v1/check
   // {{testApi}}/main/v1/check

    private final Logger LOGGER = LoggerFactory.getLogger(GetController.class);


    @RequestMapping(value = "/check", method = RequestMethod.GET)
    @Cacheable(key = "#version", value = "svcsupport")
    public String getHello(@RequestParam(name = "version", required = false) String version){
        LOGGER.info(version);
        LOGGER.info("getHello 메소드가 호출되었습니다.");
        return "check version";
    }
}
