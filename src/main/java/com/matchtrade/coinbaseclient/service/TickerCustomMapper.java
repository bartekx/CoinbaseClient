package com.matchtrade.coinbaseclient.service;

import com.matchtrade.coinbaseclient.dto.CoinbaseTicker;
import com.matchtrade.coinbaseclient.dto.Ticker;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;

public class TickerCustomMapper extends CustomMapper<CoinbaseTicker, Ticker> {
    @Override
    public void mapAtoB(CoinbaseTicker a, Ticker b, MappingContext context) {
        b.setInstrument(CurrenciesConverter.mapFromCoinbase(a.getProduct_id()));
    }
}
