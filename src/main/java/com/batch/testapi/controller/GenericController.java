package com.batch.testapi.controller;


import com.batch.testapi.generic.GenericMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/main/gen")
public class GenericController {

    private final Logger logger = LoggerFactory.getLogger(GenericController.class);

    @GetMapping(value = "/gen")
    public String genTest(){

        GenericMethod genericMethod = new GenericMethod();
        String str1 = genericMethod.method("안녕");

        logger.info(str1);
        int k = genericMethod.method(1);

        logger.info("{}", k);

        boolean bool = genericMethod.method2(2.5, 2.5);
        logger.info("{}", bool);
        genericMethod.method3("국어", 80);
        return "gen";

    }

}
