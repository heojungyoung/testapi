package com.batch.testapi.controller;

import com.batch.testapi.redis.RedisRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.RedisKeyCommands;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/main/v1")
public class GetController {

    private final Logger logger = LoggerFactory.getLogger(GetController.class);

    private final RedisRepository redisRepository;
    private final StringRedisTemplate stringRedisTemplate;

    public GetController(RedisRepository redisRepository,StringRedisTemplate stringRedisTemplate ){
        this.redisRepository = redisRepository;
        this.stringRedisTemplate = stringRedisTemplate;
    }


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


    @GetMapping(value = "/delLikeRedis")
    public void delLikeDataByPipeline() {

        Set<String> vv = stringRedisTemplate.keys("svcSupport::*");

        stringRedisTemplate.executePipelined((RedisCallback<Object>) connection -> {
            StringRedisConnection stringRedisConnection = (StringRedisConnection) connection;
            if (!ObjectUtils.isEmpty(vv)) {
                for (String key : vv) {
                    stringRedisConnection.del(key);
                    logger.info("{} del redis " , key);
                }
            }
            return null;
        });


    }



    @GetMapping(value = "/scanRedis")
    public void scanByPipeline() {

        Set<byte[]> keysToDelete = new HashSet<>();
        stringRedisTemplate.execute((RedisCallback<Object>) connection -> {
            ScanOptions scanOptions = ScanOptions.scanOptions()
                    .match("svcSupport::*")
                    .count(1000)
                    .build();

            try (Cursor<byte[]> cursor = ((RedisKeyCommands) connection).scan(scanOptions)) {
                while (cursor.hasNext()) {
                    keysToDelete.add(cursor.next());
                }
            }
            return null;
        });

        stringRedisTemplate.executePipelined((RedisCallback<Object>) connection -> {
            StringRedisConnection stringRedisConnection = (StringRedisConnection) connection;
            for (byte[] key : keysToDelete) {
                String keyStr = new String(key, StandardCharsets.UTF_8);
                stringRedisConnection.del(keyStr);
            }
            return null;
        });
    }

}
