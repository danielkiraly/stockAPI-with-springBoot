package com.codecool.stockapispringboot.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class StockAPIService {

    @Autowired
    private RemoteURLReader remoteURLReader;

    public StockAPIService(RemoteURLReader remoteURLReader) {
        this.remoteURLReader = remoteURLReader;
    }

    public StockAPIService() {
    }

    private final String apiPath = "https://financialmodelingprep.com/api/v3/stock/real-time-price/%s";

    /**
     * Get stock price from iex and return as a double
     *
     * @param symbol Stock symbol, for example "aapl"
     **/
    public double getPrice(String symbol) throws IOException {
        String url = String.format(apiPath, symbol);
        String result = remoteURLReader.readFromUrl(url);
        JSONObject jsonObject = new JSONObject(result);
        String price = jsonObject.get("price").toString();
        return Double.parseDouble(price);
    }

    /**
     * Buys a share of the given stock at the current price. Returns false if the purchase fails
     */
    public boolean buy(String symbol) {
        // Stub. No need to implement this.
        return true;
    }

}
