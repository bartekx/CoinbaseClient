package com.matchtrade.coinbaseclient.dto;

import com.matchtrade.coinbaseclient.enums.Channels;

import java.util.List;

public class Unsubscribe {

    private Channels type;
    private List<String> channels;

    public Channels getType() {
        return type;
    }

    public void setType(Channels type) {
        this.type = type;
    }

    public List<String> getChannels() {
        return channels;
    }

    public void setChannels(List<String> channels) {
        this.channels = channels;
    }
}
