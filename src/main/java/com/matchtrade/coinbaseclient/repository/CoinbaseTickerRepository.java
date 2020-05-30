package com.matchtrade.coinbaseclient.repository;

import com.matchtrade.coinbaseclient.dto.CoinbaseTicker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CoinbaseTickerRepository {

    private static Logger logger = LoggerFactory.getLogger(CoinbaseTickerRepository.class);
    private Map<String, CoinbaseTicker> tickers;

    public CoinbaseTickerRepository() {
        this.tickers = new HashMap<>();
    }

    public Iterable<CoinbaseTicker> getTickers() {
        return tickers.values();
    }

    public void saveTicker(CoinbaseTicker ticker) {
        logger.debug("Saving ticker: {}", ticker);
        tickers.put(ticker.getProduct_id(),ticker);
    }
}
