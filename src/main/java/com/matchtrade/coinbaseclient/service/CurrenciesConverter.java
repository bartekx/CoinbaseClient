package com.matchtrade.coinbaseclient.service;

public class CurrenciesConverter {

    private static final String DELIMITER = "-";

    public static String mapToCoinbase(String currency){
        return currency.substring(0,3)+DELIMITER+currency.substring(3,6);
    }

    public static String mapFromCoinbase(String currency){
        return currency.substring(0,3)+currency.substring(4,7);
    }
}
