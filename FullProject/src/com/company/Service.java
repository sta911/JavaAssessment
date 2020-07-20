package com.company;

public class Service {
    private String serviceName;
    private String serviceDescription;
    private float price;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void printService(){
        System.out.println("Name: " + serviceName);
        System.out.println("Details: " + serviceDescription);
        System.out.println("Price: " + price);
    }

    public void printServiceMenu(){
        System.out.print(serviceName);
        System.out.println(" (" + price + ")");
    }
}
