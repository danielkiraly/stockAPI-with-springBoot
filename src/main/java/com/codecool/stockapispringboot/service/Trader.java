package com.codecool.stockapispringboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Trader {

    @Autowired
    private StockAPIService stockService;

    public boolean buy(String symbol, double bid) throws IOException {
        double price = stockService.getPrice(symbol);

        boolean result;
        if (price <= bid) {
            result = true;
            stockService.buy(symbol);
        } else {
            result = false;
        }
        return result;
    }

}
