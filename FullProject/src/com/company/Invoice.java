package com.company;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class Invoice {
    private String invNumber;
    private Date invDate;
    private float totalAmount;
    private Vector<Item> itemList = new Vector<Item>(); //holds all the purchases that are made in one invoice

    public Invoice(String accNo, int accInvoices) {
        this.invNumber = accNo + String.valueOf(accInvoices);
        invDate = new Date();
    }

    public Date getDate() {
        return invDate;
    }

    public String getInvNumber() {
        return invNumber;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    private void updateTotalAmount(Item newItem) {
        this.totalAmount += newItem.getItemAmount();
    }

    public void addItem(Item newItem){
        this.itemList.add(newItem);
        updateTotalAmount(newItem);
    }

    public int getItemsQuantity(){
        return itemList.size();
    }

    public Item getItem(int itemNo){
        return itemList.elementAt(itemNo);
    }

    public void printInvoice(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm"); //simplified date format

        System.out.println("Inv. No.: " + invNumber);
        System.out.println("Date: " + dateFormat.format(invDate));

        for(int i=0; i<itemList.size(); i++) {
            System.out.println("\tItem " + i);
            System.out.println("\t\tService: " + itemList.elementAt(i).getServiceName());
            System.out.println("\t\tQuantity: " + itemList.elementAt(i).getQuantity());
            System.out.println("\t\tAmount: " + itemList.elementAt(i).getItemAmount());
        }
        System.out.println("Total Amount: " + totalAmount + "\n");
    }
}
