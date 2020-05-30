package com.matchtrade.coinbaseclient.service;

import com.matchtrade.coinbaseclient.dto.Channel;
import com.matchtrade.coinbaseclient.dto.CoinbaseTicker;
import com.matchtrade.coinbaseclient.dto.Subscribe;
import com.matchtrade.coinbaseclient.dto.Ticker;
import com.matchtrade.coinbaseclient.enums.Channels;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.socket.client.standard.AnnotatedEndpointConnectionManager;
import javax.websocket.WebSocketContainer;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@PropertySource("classpath:application.properties")
public class WebsocketConfiguration {

    private static Logger logger = LoggerFactory.getLogger(WebsocketConfiguration.class);
    @Value( "${coinbase.instruments}" )
    private List<String> instruments;
    @Value( "${coinbase.url}" )
    private String wsUrl;

    @Bean
    public AnnotatedEndpointConnectionManager connectionManager(WebsocketClient websocketClient) {
        logger.info("Configuring connection to url: {}", wsUrl);
        List<Channel> channels = new ArrayList<>();
        List<String> productIds = instruments.stream().map(i -> CurrenciesConverter.mapToCoinbase(i)).collect(Collectors.toList());
        channels.add(new Channel(Channels.ticker,productIds));
        Subscribe subscribeMessage = new Subscribe(productIds,channels);
        websocketClient.setSubscribeMessage(subscribeMessage);
        AnnotatedEndpointConnectionManager connectionManager = new AnnotatedEndpointConnectionManager(websocketClient, wsUrl);
        WebSocketContainer container = connectionManager.getWebSocketContainer();
        connectionManager.setAutoStartup(true);
        return connectionManager;
    }

    @Bean
    WebsocketClient websocketClient() {
        logger.info("Starting websocket.");
        return new WebsocketClient();
    }

    @Bean
    MapperFacade tickerMapper() {
        logger.info("Setting up tickers mapper");
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(CoinbaseTicker.class, Ticker.class)
                .field("product_id","instrument")
                .field("best_bid","bid")
                .field("best_ask","ask")
                .field("price","last")
                .field("time","time")
                .customize(new TickerCustomMapper())
                .register();
        MapperFacade mapper = mapperFactory.getMapperFacade();
        return mapper;
    }
}
