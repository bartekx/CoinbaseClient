package com.matchtrade.coinbaseclient.controller;

import com.matchtrade.coinbaseclient.dto.Ticker;
import com.matchtrade.coinbaseclient.service.TickerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TickerController {

    @Autowired
    private TickerService tickerService;

    @GetMapping
    public List<Ticker> getAllTickers()
    {
        return tickerService.getAllTickers();
    }
}
