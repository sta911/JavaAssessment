package com.company;

import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Vector;

public class Account {

    private String accountNumber;
    private String customerName;
    private String address;
    private String phone;
    private float accountBalance;
    private Vector<String> listOfServices = new Vector<String>(); //holds the services used by each customer
    private Vector<Invoice> listOfInvoices = new Vector<Invoice>(); //invoice history for each customer

    public Account(int accountNumber){
        this.accountNumber = String.valueOf(accountNumber);
        this.accountBalance = 0;
    }

    public String getAccountNumber(){
        return this.accountNumber;
    }

    public float getAccountBalance(){
        return this.accountBalance;
    }

    public void setAccountInfo(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Customer Full Name: ");
        customerName = scanner.nextLine().trim();
        System.out.print("Customer Address: ");
        address = scanner.nextLine().trim();
        System.out.print("Customer Phone: ");
        phone = scanner.nextLine().trim();
    }

    public void printAccountInfo(){
        System.out.println("Acc. No.: " + accountNumber);
        System.out.println("Customer Name: " + customerName);
        System.out.println("Address: " + address);
        System.out.println("Phone: " + phone);
        System.out.println("Account Balance: " + accountBalance);
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getAddress() {
        return address;
    }

    public Vector<String> getListOfServices() {
        return listOfServices;
    }

    public Vector<Invoice> getListOfInvoices() {
        return listOfInvoices;
    }

    public int getNumberOfInvoices() {
        return listOfInvoices.size();
    }

    private void updateAccountBalance(float newCharge){
        this.accountBalance+=newCharge;
    }

    public void updateServices(String newService){
        if (!listOfServices.contains(newService))
            listOfServices.add(newService);
    }
    public void addInvoice(Invoice newInvoice){
        listOfInvoices.add(newInvoice);
        updateAccountBalance(newInvoice.getTotalAmount());
        for(int j=0; j<newInvoice.getItemsQuantity(); j++) {
            updateServices(newInvoice.getItem(j).getServiceName());
        }

    }

    public void printInvoices(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm"); //simplified date format

        for(int i=0; i<listOfInvoices.size(); i++) {
            System.out.println("Inv. No.: " + listOfInvoices.elementAt(i).getInvNumber());
            System.out.println("Date: " + dateFormat.format(listOfInvoices.elementAt(i).getDate()));

            for(int j=0; j<listOfInvoices.elementAt(i).getItemsQuantity(); j++) {
                System.out.println("\tItem " + j);
                System.out.println("\t\tService: " + listOfInvoices.elementAt(i).getItem(j).getServiceName());
                System.out.println("\t\tQuantity: " + listOfInvoices.elementAt(i).getItem(j).getQuantity());
                System.out.println("\t\tAmount: " + listOfInvoices.elementAt(i).getItem(j).getItemAmount());
            }
            System.out.println("Total Amount: " + listOfInvoices.elementAt(i).getTotalAmount() + "\n");
        }
    }

    public void printServices(){
        for(int i=0; i<listOfServices.size(); i++)         {
            System.out.println("\t" + listOfServices.elementAt(i));
        }
    }
}
