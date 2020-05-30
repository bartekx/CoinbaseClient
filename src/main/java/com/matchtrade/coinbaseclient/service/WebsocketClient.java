package com.matchtrade.coinbaseclient.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.matchtrade.coinbaseclient.dto.CoinbaseTicker;
import com.matchtrade.coinbaseclient.dto.Subscribe;
import com.matchtrade.coinbaseclient.enums.Channels;
import com.matchtrade.coinbaseclient.repository.CoinbaseTickerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.websocket.*;
import java.util.Arrays;

@ClientEndpoint
public class WebsocketClient {

    private static Logger logger = LoggerFactory.getLogger(WebsocketClient.class);
    private Session webSocketSession;
    private final ObjectReader objectReader;
    private final ObjectWriter objectWriter;
    private Subscribe subscribeMessage;
    @Autowired
    CoinbaseTickerRepository tickersRepository;

    public WebsocketClient() {
        ObjectMapper objectMapper = new ObjectMapper();
        this.objectReader = objectMapper.readerFor(new TypeReference<CoinbaseTicker>() {});
        this.objectWriter = objectMapper.writer();
    }

    public Subscribe getSubscribeMessage() {
        return subscribeMessage;
    }

    public void setSubscribeMessage(Subscribe subscribeMessage) {
        this.subscribeMessage = subscribeMessage;
    }

    @OnOpen
    public void onOpen(Session webSocketSession) {
        logger.info("Opening websocket.");
        this.webSocketSession = webSocketSession;
        this.sendSubscribe();
    }

    @OnClose
    public void onClose(Session webSocketSession, CloseReason reason) {
        logger.info("Closing websocket. Reason: {}", reason);
    }
    @OnMessage
    public void onMessage(String message) {
        logger.debug("Received message: {}", message);
        try {
            CoinbaseTicker ticker = objectReader.readValue(message);
            if(ticker.getType() == Channels.ticker){
                tickersRepository.saveTicker(ticker);
            }
        } catch (JsonProcessingException e) {
            logger.warn("Faild when trying to deserialize message: {}", message);
        }
    }

    private void sendSubscribe() {
        if(subscribeMessage != null)
        {
            String jsonMessage = null;
            try {
                logger.info("Starting subscribe channels: {}, with instruments: {}", Arrays.toString(subscribeMessage.getChannels().stream().map(c -> c.getName()).toArray()), Arrays.toString(subscribeMessage.getProduct_ids().toArray()));
                jsonMessage = objectWriter.writeValueAsString(subscribeMessage);
            } catch (JsonProcessingException e) {
                logger.error("Faild when serialize subscribe channels: {}, with instruments: {}", Arrays.toString(subscribeMessage.getChannels().stream().map(c -> c.getName()).toArray()), Arrays.toString(subscribeMessage.getProduct_ids().toArray()));
            }
            sendMessage(jsonMessage);
        }
        else{
            logger.error("Faild when trying to subscribe channels - no subscribe message set.");
        }
    }

    private void sendMessage(String message){
        logger.debug("Sending message: {}", message);
        this.webSocketSession.getAsyncRemote().sendText(message);
    }
}
