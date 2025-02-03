package com.batch.testapi.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class RedisRepository {

    private final Logger logger = LoggerFactory.getLogger(RedisRepository.class);

    @Cacheable(key = "#version", value = "svcsupport")
    public void getHello(@RequestParam(name = "version", required = false) String version){
        logger.info(version);
        logger.info("getHello 메소드가 호출되었습니다.");
    }


}
