package com.bigdata.rabbitredis.configuration;

import com.bigdata.rabbitredis.model.SquareData;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Date;


@Configuration
public class RabbitConfig {
    public static final String QUEUE = "numbersQueue";
    private final RedisTemplate<String, Object> redisTemplate;

    public RabbitConfig(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Bean
    public Queue queue() {
        return new Queue(QUEUE, false);
    }

    @RabbitListener(queues = RabbitConfig.QUEUE)
    public void processNumber(int number) {
        int squared = number * number;
        SquareData data = new SquareData(number, squared, new Date());
        redisTemplate.opsForValue().set("number:" + number, data);
        System.out.println("Processed and stored: " + data);
    }
}