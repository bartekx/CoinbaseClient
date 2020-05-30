package com.matchtrade.coinbaseclient.dto;

import com.matchtrade.coinbaseclient.enums.Channels;

import java.util.List;

public class Subscribe {

    private Channels type;
    private List<String> product_ids;
    private List<Channel> channels;

    public Subscribe(List<String> product_ids, List<Channel> channels) {
        this.type = Channels.subscribe;
        this.product_ids = product_ids;
        this.channels = channels;
    }

    public Channels getType() {
        return type;
    }

    public void setType(Channels type) {
        this.type = type;
    }

    public List<String> getProduct_ids() {
        return product_ids;
    }

    public void setProduct_ids(List<String> product_ids) {
        this.product_ids = product_ids;
    }

    public List<Channel> getChannels() {
        return channels;
    }

    public void setChannels(List<Channel> channels) {
        this.channels = channels;
    }
}
