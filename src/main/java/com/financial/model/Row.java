package com.financial.model;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.*;

public class Row {

    private StringProperty name = new SimpleStringProperty();
    private StringProperty isin = new SimpleStringProperty();
    private StringProperty issuer = new SimpleStringProperty();
    private StringProperty index = new SimpleStringProperty();
    private StringProperty ticker = new SimpleStringProperty();
    private IntegerProperty quantity = new SimpleIntegerProperty();
    private DoubleProperty buyingPrice = new SimpleDoubleProperty();
    private DoubleProperty lastPrice = new SimpleDoubleProperty();

    private DoubleProperty amount = new ReadOnlyDoubleWrapper();
    private DoubleProperty amountVariation = new ReadOnlyDoubleWrapper();
    private DoubleProperty variation = new ReadOnlyDoubleWrapper();

    public Row(Row vRow) {
       this(vRow.getName(),vRow.getIsin(),vRow.getIssuer(), vRow.getIndex(),vRow.getQuantity(),vRow.getBuyingPrice(),vRow.getLastPrice());
       setTicker(vRow.getTicker());
    }

    public Row(String name, String isin, int quantity, double buyingPrice,  double lastPrice) {
        this( name,  isin, null,null,  quantity,  buyingPrice,   lastPrice);
    }

    public Row(String name, String isin, String issuer,String index, int quantity, double buyingPrice,  double lastPrice) {
        setName(name);
        setIsin(isin);
        setIssuer(issuer);
        setIndex(index);
        setQuantity(quantity);
        setBuyingPrice(buyingPrice);
        setLastPrice(lastPrice);
        amount.bind(this.lastPrice.multiply(this.quantity));
        amountVariation.bind(this.lastPrice.subtract(this.buyingPrice).multiply(this.quantity));
        variation.bind(this.lastPrice.divide(this.buyingPrice).subtract(1));
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getIsin() {
        return isin.get();
    }

    public StringProperty isinProperty() {
        return isin;
    }

    public void setIsin(String isin) {
        this.isin.set(isin);
    }

    public int getQuantity() {
        return quantity.get();
    }

    public IntegerProperty quantityProperty() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }

    public double getLastPrice() {
        return lastPrice.get();
    }

    public DoubleProperty lastPriceProperty() {
        return lastPrice;
    }

    public void setLastPrice(double lastPrice) {
        this.lastPrice.set(lastPrice);
    }

    public double getAmount() {
        return amount.get();
    }

    public DoubleProperty amountProperty() {
        return amount;
    }


    public String getIssuer() {
        return issuer.get();
    }

    public StringProperty issuerProperty() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer.set(issuer);
    }

    public String getIndex() {
        return index.get();
    }

    public StringProperty indexProperty() {
        return index;
    }

    public void setIndex(String index) {
        this.index.set(index);
    }

    public double getBuyingPrice() {
        return buyingPrice.get();
    }

    public DoubleProperty buyingPriceProperty() {
        return buyingPrice;
    }

    public void setBuyingPrice(double buyingPrice) {
        this.buyingPrice.set(buyingPrice);
    }

    public void setAmount(double amount) {
        this.amount.set(amount);
    }

    public double getAmountVariation() {
        return amountVariation.get();
    }

    public DoubleProperty amountVariationProperty() {
        return amountVariation;
    }

    public void setAmountVariation(double amountVariation) {
        this.amountVariation.set(amountVariation);
    }

    public double getVariation() {
        return variation.get();
    }

    public DoubleProperty variationProperty() {
        return variation;
    }

    public void setVariation(double variation) {
        this.variation.set(variation);
    }
    public String getTicker() {
        return ticker.get();
    }

    public StringProperty tickerProperty() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker.set(ticker);
    }


}
