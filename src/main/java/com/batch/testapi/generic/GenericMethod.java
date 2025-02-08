package com.batch.testapi.generic;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenericMethod {


       private final Logger logger = LoggerFactory.getLogger(GenericMethod.class);

       public <T> T method(T t) {
           return t;
       }

       public <T> boolean method2(T t1, T t2) {
           return t1.equals(t2);
       }

       public <K, V> void method3(K k, V v) {
           logger.info("{}:{}", k, v);
       }



}
