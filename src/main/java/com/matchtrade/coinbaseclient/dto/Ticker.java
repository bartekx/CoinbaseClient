package com.matchtrade.coinbaseclient.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Ticker {

    private String instrument;
    private Double bid;
    private Double ask;
    private Double last;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private Date time;

    public Ticker(String instrument, Double bid, Double ask, Double last, Date time) {
        this.instrument = instrument;
        this.bid = bid;
        this.ask = ask;
        this.last = last;
        this.time = time;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    public Double getBid() {
        return bid;
    }

    public void setBid(Double bid) {
        this.bid = bid;
    }

    public Double getAsk() {
        return ask;
    }

    public void setAsk(Double ask) {
        this.ask = ask;
    }

    public Double getLast() {
        return last;
    }

    public void setLast(Double last) {
        this.last = last;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
