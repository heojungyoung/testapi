package com.batch.testapi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;

@RestController
@RequestMapping("/main/map")
public class MapController {

    private final Logger logger = LoggerFactory.getLogger(MapController.class);

    @GetMapping(value = "/hashtable")
    public Hashtable<String, Integer> hashTableTest(){

        Hashtable<String, Integer> hashtable = new Hashtable<>();

        // Adding key-value pairs to the Hashtable
        hashtable.put("Apple", 1);
        hashtable.put("Banana", 2);
        hashtable.put("Cherry", 3);

        // Retrieve and print a value by its key
        Integer bananaValue = hashtable.get("Banana");
        logger.info(" Value for key 'Banana {}" , bananaValue);
        logger.info("\nIterating using Enumeration:");

        Enumeration<String> keys = hashtable.keys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            logger.info("Key: {} , value : {} ", key , hashtable.get(key));
        }


        for (Map.Entry<String, Integer> entry : hashtable.entrySet()) {
            logger.info("entry Key: {} , entry value : {}" , entry.getKey() , entry.getValue());
        }

        return hashtable;
    }
}
