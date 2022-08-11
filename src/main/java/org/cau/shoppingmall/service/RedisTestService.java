package org.cau.shoppingmall.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.Set;

@RequiredArgsConstructor
@Service
public class RedisTestService {

    private final RedisTemplate<String,String> redisTemplate;
    String key = "searchRanking";

    public void addDataInRedis(String searchWord) {


        ZSetOperations<String, String> ops = redisTemplate.opsForZSet();
        try {
            ops.incrementScore(key,searchWord,1);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void getRankingList() {
        ZSetOperations<String, String> ops = redisTemplate.opsForZSet();
        Set<ZSetOperations.TypedTuple<String>> typedTuples = ops.reverseRangeWithScores(key, 0, 9);

        for(ZSetOperations.TypedTuple<String> typedTuple : typedTuples) {
            System.out.println(typedTuple.getValue()+" "+typedTuple.getScore());
        }

    }
}
