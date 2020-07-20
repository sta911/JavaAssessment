package com.company;

public class Item{

    private int quantity;
    private float itemAmount;
    private String serviceName;
    private float servicePrice;

    public Item(String serviceName, float servicePrice) {
        this.serviceName = serviceName;
        this.servicePrice = servicePrice;
        this.quantity = 0;
    }

    public String getServiceName(){
        return this.serviceName;
    }
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        updateItemAmount();
    }

    public float getItemAmount() {
        return itemAmount;
    }

    private void updateItemAmount() {
        this.itemAmount = this.quantity * this.servicePrice;
    }
}
