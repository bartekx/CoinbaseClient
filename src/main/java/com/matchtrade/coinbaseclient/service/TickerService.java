package com.matchtrade.coinbaseclient.service;

import com.matchtrade.coinbaseclient.dto.CoinbaseTicker;
import com.matchtrade.coinbaseclient.dto.Ticker;
import com.matchtrade.coinbaseclient.repository.CoinbaseTickerRepository;
import ma.glasnost.orika.MapperFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TickerService {

    private static Logger logger = LoggerFactory.getLogger(TickerService.class);
    @Autowired
    MapperFacade mapper;
    @Autowired
    CoinbaseTickerRepository tickersRepository;

    public List<Ticker> getAllTickers() {
        logger.info("Getting all tickers.");
        Iterable<CoinbaseTicker> tickers = tickersRepository.getTickers();
        return mapper.mapAsList(tickers,Ticker.class);
    }
}
