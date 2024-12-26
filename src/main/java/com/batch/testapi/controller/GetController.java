package com.batch.testapi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

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


    static int n;
    static int[][] distance;

    @GetMapping("/kebin")
    public int kebin(){
        return kebinTest();
    }

    public static int kebinTest() {

        n = 5;           // 유저 수
        // m = 5;           // 친구 관계 수 addEdge 5개로 현재 사용하지 않는 변수

        // distance 배열 초기화
        distance = new int[n + 1][n + 1];
        basicData(n);

        // 친구 관계(간선) 고정값(예시)
        // 여기서 (s, e)가 친구 관계이므로 거리 1로 설정하고 양방향으로 저장

        addEdge(1, 3);
        addEdge(1, 4);
        addEdge(4, 5);
        addEdge(4, 3);
        addEdge(3, 2);

        // 플로이드–워셜 알고리즘
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (distance[i][j] > distance[i][k] + distance[k][j]) {
                        distance[i][j] = distance[i][k] + distance[k][j];
                    }
                }
            }
        }

        // 케빈 베이컨의 수(각 유저가 갖는 최단 거리의 합) 계산 후 최솟값 찾기
        int minValue = Integer.MAX_VALUE;
        int answer = -1;
        for (int i = 1; i <= n; i++) {
            int sum = 0;
            for (int j = 1; j <= n; j++) {
                sum += distance[i][j];
            }
            if (sum < minValue) {
                minValue = sum;
                answer = i;
            }
        }

       return answer;
    }

    private static void basicData(int n){

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) {
                    distance[i][j] = 0;
                } else {
                    distance[i][j] = 10000001;  // 충분히 큰 수로 초기화(무한대)
                }
            }
        }

    }

    // 친구 관계(간선) 추가를 위한 메서드
    private static void addEdge(int s, int e) {
        distance[s][e] = 1;
        distance[e][s] = 1;
    }



    @GetMapping("/lambdaTest")
    public Map<String, Integer> lambdaTest() {

        Map<String, Integer> originalMap = new HashMap<>();
        originalMap.put("apple", 3);
        originalMap.put("banana", 5);
        originalMap.put("cherry", 7);
        originalMap.put("avocado", 4);

        // 'a'로 시작하는 키만 필터링하여 새로운 Map 생성
        Map<String, Integer> filteredMap = originalMap.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith("a") || entry.getKey().endsWith("y"))  // 키가 'a'로 시작하는 항목만 필터링
                .collect(Collectors.toMap(
                        Map.Entry::getKey,  // 기존 키 유지
                        Map.Entry::getValue  // 기존 값 유지
                ));

        // 결과 출력
        filteredMap.forEach((key, value) ->  logger.info(" key : {}, value : {}",  key ,value));

        return filteredMap;

    }

}
