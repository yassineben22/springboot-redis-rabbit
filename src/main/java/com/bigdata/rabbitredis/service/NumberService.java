package com.bigdata.rabbitredis.service;

import com.bigdata.rabbitredis.configuration.RabbitConfig;
import com.bigdata.rabbitredis.model.SquareData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class NumberService {

    private final RabbitTemplate rabbitTemplate;
    private final RedisTemplate<String, Object> redisTemplate;

    public NumberService(RabbitTemplate rabbitTemplate, RedisTemplate<String, Object> redisTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.redisTemplate = redisTemplate;
    }

    /**
     * Sends the number to RabbitMQ queue.
     */
    public void sendNumberToQueue(int number) {
        rabbitTemplate.convertAndSend(RabbitConfig.QUEUE, number);
    }

    /**
     * Retrieves the stored data from Redis.
     */
    public SquareData getFromRedis(int number) {
        String redisKey = "number:" + number;
        return (SquareData) redisTemplate.opsForValue().get(redisKey);
    }

    public List<SquareData> getAllFromRedis() {
        // Match all keys that follow the pattern "number:*"
        Set<String> keys = redisTemplate.keys("number:*");

        List<SquareData> numbers = new ArrayList<>();
        if (keys != null) {
            for (String key : keys) {
                SquareData data = (SquareData) redisTemplate.opsForValue().get(key);
                if (data != null) {
                    numbers.add(data);
                }
            }
        }
        return numbers;
    }
}