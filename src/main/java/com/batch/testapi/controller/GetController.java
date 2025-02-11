package com.batch.testapi.controller;

import com.batch.testapi.redis.RedisRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.redis.core.StringRedisTemplate;

@RestController
@RequestMapping("/main/v1")
public class GetController {

    private final Logger logger = LoggerFactory.getLogger(GetController.class);

    private final RedisRepository redisRepository;

    public GetController(RedisRepository redisRepository ){
        this.redisRepository = redisRepository;
    }

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping(value = "/check")
    public String getHello(@RequestParam(name = "version", required = false) String version){

        try {
            redisRepository.getHello(version);
        } catch (Exception e){
            logger.error("redis error {}" , e.getMessage());
        }

        logger.info(version);

        return "check version";
    }


    @GetMapping(value = "/delRedis")
    public void delDataByPipeline() {

        stringRedisTemplate.executePipelined(
                (RedisCallback<Object>) connection -> {
                    connection.openPipeline();

                    for (int i = 0; i < 10; i++) {
                        StringRedisConnection stringRedisConnection = (StringRedisConnection) connection;
                        String key = "svcSupport::" + i;
                        stringRedisConnection.del(key);
                        logger.info("del {} " , key);

                    }

                    connection.closePipeline();
                    return null;
                }
        );


    }



}
