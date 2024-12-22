package com.batch.testapi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

@RestController
@RequestMapping("/main/v1")
public class GetController {


   // http://localhost:8080/main/v1/check
   // {{testApi}}/main/v1/check

    private final Logger logger = LoggerFactory.getLogger(GetController.class);


    @GetMapping(value = "/check")
    public String getHello(@RequestParam(name = "version", required = false) String version){
        logger.info(version);
        logger.info("getHello 메소드가 호출되었습니다.");
        return "check version";
    }


    @GetMapping("/greet")
    public Map<String, String> greet() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Hello, World!");
        response.put("status", "success");
        return response; // Automatically serialized to JSON
    }


    @GetMapping("/treemap")
    public SortedMap<Integer, String> treemapTest() {

        TreeMap<Integer, String> treeMap = new TreeMap<>();

        for(int i = 20 ; i >0; i-=2 ){
            treeMap.put(i, i + "value");
        }

        return treeMap;
    }

}
