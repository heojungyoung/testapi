package com.batch.testapi.controller;

import com.batch.testapi.redis.RedisRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/main/v1")
public class GetController {

    private final Logger logger = LoggerFactory.getLogger(GetController.class);

    private final RedisRepository redisRepository;

    public GetController(RedisRepository redisRepository ){
        this.redisRepository = redisRepository;
    }

    @GetMapping(value = "/check")
    public String getHello(@RequestParam(name = "version", required = false) String version){

        try {
            redisRepository.getHello(version);
        } catch (Exception e){
            logger.error("redis error {}" , e.getMessage());
        }

        logger.info(version);
        logger.info("redis fail or success");
        return "check version";
    }
}
