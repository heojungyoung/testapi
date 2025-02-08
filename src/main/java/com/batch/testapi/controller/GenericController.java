package com.batch.testapi.controller;


import com.batch.testapi.generic.GenericMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Hashtable;
import java.util.Map;

@RestController
@RequestMapping("/main/gen")
public class GenericController {

    private static final Logger logger = LoggerFactory.getLogger(GenericController.class);

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


    @GetMapping(value = "/gen_hash")
    public Hashtable<Integer, String> gen_hashTest() {

        Hashtable<String, Integer> table1 = new Hashtable<>();
        table1.put("One", 1);
        table1.put("Two", 2);
        table1.put("Three", 3);
        printHashtable(table1);

        Hashtable<Integer, String> table2 = new Hashtable<>();
        table2.put(1, "One");
        table2.put(2, "Two");
        table2.put(3, "Three");

        printHashtable(table2);

        Hashtable<Double, Double> table3 = new Hashtable<>();
        table3.put(1.0, 2.0);

        printHashtable(table3);

        return table2;

    }

    public static <K, V> void printHashtable(Hashtable<K, V> table) {
        for (Map.Entry<K, V> entry : table.entrySet()) {
            logger.info("Key : {}  , Value : {} ",   entry.getKey(), entry.getValue());
        }
    }



}
