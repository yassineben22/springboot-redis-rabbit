package com.bigdata.rabbitredis.controller;

import com.bigdata.rabbitredis.model.SquareData;
import com.bigdata.rabbitredis.service.NumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/messages")
public class MainController {

    @Autowired
    private NumberService numberService;

    @PostMapping("/store/{number}")
    public String storeSquare(@PathVariable int number) {
        numberService.sendNumberToQueue(number);
        return "Number squared and stored in Redis!";
    }

    @GetMapping("/retrieve/{number}")
    public SquareData retrieveSquare(@PathVariable int number) {
        return numberService.getFromRedis(number);
    }

    @GetMapping("/retrieve/all")
    public List<SquareData> retrieveAllSquares() {
        return numberService.getAllFromRedis();
    }
}
