package com.matchtrade.coinbaseclient.dto;

import com.matchtrade.coinbaseclient.enums.Channels;

import java.util.List;

public class Channel {

    Channels name;
    List<String> product_ids;

    public Channel(Channels name, List<String> product_ids) {
        this.name = name;
        this.product_ids = product_ids;
    }

    public Channels getName() {
        return name;
    }

    public void setName(Channels name) {
        this.name = name;
    }

    public List<String> getProduct_ids() {
        return product_ids;
    }

    public void setProduct_ids(List<String> product_ids) {
        this.product_ids = product_ids;
    }
}
