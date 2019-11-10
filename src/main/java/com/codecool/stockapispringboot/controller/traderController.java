package com.codecool.stockapispringboot.controller;

import com.codecool.stockapispringboot.service.Trader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class traderController {

    @Autowired
    private Trader trader;

    @GetMapping("/buy/{stock}/{price}")
    public String buyShares(@PathVariable("stock") String stock, @PathVariable("price") String price) {

        double priceToDouble;

        try {
            priceToDouble = Double.parseDouble(price);
        } catch (NumberFormatException e) {
            return "Invalid input. Give a valid number as second argument.";
        }

        try {
            boolean purchased = trader.buy(stock, priceToDouble);
            if (purchased) {
                return "Purchased " + stock + " stock at $" + priceToDouble
                        + ", since its higher that the current price ($" + priceToDouble + ")";
            } else {
                return "Bid for " + stock + " was $" + priceToDouble
                        + " but the stock price is $" + priceToDouble + ", no purchase was made.";
            }
        } catch (Exception e) {
            return "There was an error while attempting to buy the stock: " + e.getMessage();
        }
    }
}
