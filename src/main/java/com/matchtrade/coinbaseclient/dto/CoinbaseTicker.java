package com.matchtrade.coinbaseclient.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.matchtrade.coinbaseclient.enums.Channels;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CoinbaseTicker {

    private Channels type;
    private String product_id;
    private Double best_bid;
    private Double best_ask;
    private Double price;
    private Date time;

    public CoinbaseTicker() {
    }

    public CoinbaseTicker(Channels type, String product_id, Double best_bid, Double best_ask, Double price, Date time) {
        this.type = type;
        this.product_id = product_id;
        this.best_bid = best_bid;
        this.best_ask = best_ask;
        this.price = price;
        this.time = time;
    }

    public Channels getType() {
        return type;
    }

    public void setType(Channels type) {
        this.type = type;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public Double getBest_bid() {
        return best_bid;
    }

    public void setBest_bid(Double best_bid) {
        this.best_bid = best_bid;
    }

    public Double getBest_ask() {
        return best_ask;
    }

    public void setBest_ask(Double best_ask) {
        this.best_ask = best_ask;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "CoinbaseTicker{" +
                "type=" + type +
                ", product_id='" + product_id + '\'' +
                ", best_bid='" + best_bid + '\'' +
                ", best_ask='" + best_ask + '\'' +
                ", price='" + price + '\'' +
                ", time=" + time +
                '}';
    }
}
