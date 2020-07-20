package com.company;

import java.util.Scanner;
import java.util.Vector;

public class Main {

    public static Vector<Account> accountList = new Vector<Account>();
    public static Vector<Service> servicesList = new Vector<Service>();

    public static final String customersFile = "customers.txt";
    public static final String invoicesFile = "invoices.txt";
    public static final String servicesFile = "services.txt";

    public static void main(String[] args) {

        //initializing service list and account list
        InputCustomer inputCustomer = new InputCustomer();
        InputInvoices inputInvoices = new InputInvoices();
        InputServices inputServices = new InputServices();

        inputCustomer.readFile(customersFile,accountList);
        inputInvoices.readFile(invoicesFile,accountList);
        inputServices.readFile(servicesFile,servicesList);

        mainMenu();


    }

    private static void mainMenu(){

        Scanner scanner = new Scanner(System.in);
        byte action=0;

        System.out.println("Welcome to Fictional Spa Treatments!");

        do {
            System.out.println("\nMain Menu:\n");
            System.out.println("\t1.New Customer");
            System.out.println("\t2.Existing Customer");
            System.out.println("\t3.List of Customers");
            System.out.println("\t4.List of Services");
            System.out.println("\t9.Exit\n");

            System.out.print("Selection: ");

            if(scanner.hasNextByte()) {
                action = scanner.nextByte();

                switch (action) {
                    case 1:     //create new account and add it to the list
                        Account acc = new Account(accountList.size());
                        acc.setAccountInfo();
                        accountList.add(acc);
                        accountMenu(accountList.size() - 1); //accountList.size()-1 : index of the newly added account
                        break;
                    case 2:     //menu for searching existing customers
                        searchMenu();
                        break;
                    case 3:     //print all customers
                        for (int i = 0; i < accountList.size(); i++) {
                            System.out.println("Customer " + i);
                            accountList.elementAt(i).printAccountInfo();
                            System.out.println();
                        }
                        break;
                    case 4:     //print all services
                        for (int i = 0; i < servicesList.size(); i++) {
                            System.out.println("Service " + i);
                            servicesList.elementAt(i).printService();
                            System.out.println();
                        }
                        break;
                    case 9:     //update "database" before exiting
                        OutputCustomers outputCustomers = new OutputCustomers();
                        outputCustomers.printToFile(accountList, customersFile);

                        OutputInvoices outputInvoices = new OutputInvoices();
                        outputInvoices.printToFile(accountList, invoicesFile);


                        System.out.println("Thank you!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Not a valid option!");
                        break;
                }
            }
            else{
                System.out.println("Please enter a number!");
                scanner.next();
            }
        }while (action!=9);

    }

    private static void searchMenu( ){ //menu for searching existing customers

        Scanner scanner = new Scanner(System.in);
        byte action=0;
        String query;

        do {
            System.out.println("\nAvailable Actions:\n");
            System.out.println("\t1.Search (Acc. No./Phone)");
            System.out.println("\t2.Show Available Options");
            System.out.println("\t9.Return\n");
            System.out.print("Selection: ");


            if(scanner.hasNextByte()) {
                action = scanner.nextByte();

                switch (action) {
                    case 1:
                        System.out.print("Input:");
                        query = scanner.next();

                        int accIndex = findAccountIndex(query); //find the index in accountlist of the requested account

                        if(accIndex!=-1)
                            accountMenu(accIndex);
                        else
                            System.out.println("Account not found!");

                        break;
                    case 2:
                        System.out.println("Customers: ");

                        for(int i=0; i<accountList.size();i++){
                            System.out.println(i + "." + accountList.elementAt(i).getCustomerName() + " - " + accountList.elementAt(i).getPhone());
                        }
                        break;
                    case 9:
                        mainMenu();
                        break;
                    default:
                        System.out.println("Not a valid option!");
                        break;
                }
            }
            else{
                System.out.println("Please enter a number!");
                scanner.next();
            }
        }while (action!=9);

    }

    private static void accountMenu(int accIndex){ //print account info and make new purchases

        Scanner scanner = new Scanner(System.in);
        byte action=0;

        do {
            System.out.println("\n" + accountList.elementAt(accIndex).getCustomerName());
            System.out.println("Account Balance: " + accountList.elementAt(accIndex).getAccountBalance() + "\n" );
            System.out.println("\t1.Print Account Info");
            System.out.println("\t2.Print Services");
            System.out.println("\t3.Print Invoices");
            System.out.println("\t4.Buy Service");
            System.out.println("\t9.Return\n");
            System.out.print("Selection: ");

            if(scanner.hasNextByte()) {
                action = scanner.nextByte();

                switch (action) {
                    case 1:
                        accountList.elementAt(accIndex).printAccountInfo();
                        break;
                    case 2:
                        System.out.println("\nService History:");
                        accountList.elementAt(accIndex).printServices();
                        break;
                    case 3:
                        System.out.println("\nInvoice History:");
                        accountList.elementAt(accIndex).printInvoices();
                        break;
                    case 4:     //create a new invoice to be added in the currently selected account
                        //the invoice constructor takes the account number and current number of invoices in the account for automatic generation of the invoice number
                        Invoice newInvoice = new Invoice(accountList.elementAt(accIndex).getAccountNumber(),accountList.elementAt(accIndex).getNumberOfInvoices());

                        buyingMenu(accIndex,newInvoice);

                        accountList.elementAt(accIndex).addInvoice(newInvoice);
                        break;
                    case 9:
                        mainMenu();
                        break;
                    default:
                        System.out.println("Not a valid option!");
                        break;
                }
            }
        else{
            System.out.println("Please enter a number!");
            scanner.next();
        }
        }while (action!=9);

    }

    private static void buyingMenu(int accIndex, Invoice newInvoice){

        Scanner scanner = new Scanner(System.in);
        int quantity =0;
        byte action=1;
        int serviceIndex;

        do {

            switch (action) {
                case 1:
                    System.out.println("\nAvailable Services:"); //print available services
                    for(int i=0; i<servicesList.size();i++){
                        System.out.print(i + ". ");
                        servicesList.elementAt(i).printServiceMenu();
                    }

                    System.out.print("Service: ");
                    if(scanner.hasNextInt()) {
                        serviceIndex = scanner.nextInt();
                        if(serviceIndex>=servicesList.size()){
                            System.out.println("Please select one of the available options");
                            continue;
                        }
                    }
                    else{
                        System.out.println("Please enter a number!");
                        scanner.next();
                        continue;
                    }

                    System.out.print("Quantity: ");
                    if(scanner.hasNextInt()) {
                        quantity = scanner.nextInt();
                    }
                    else{
                        System.out.println("Please enter a number!");
                        scanner.next();
                        continue;
                    }
                    //create new item for each service selected
                    Item newItem = new Item(servicesList.elementAt(serviceIndex).getServiceName(),servicesList.elementAt(serviceIndex).getPrice());


                    newItem.setQuantity(quantity);

                    newInvoice.addItem(newItem);

                    System.out.println();
                    newInvoice.printInvoice();
                    break;
                case 9:
                    mainMenu();
                    break;
                default:
                    System.out.println("Not a valid option!");
                    break;
            }

            System.out.println("\nBuying Menu:");
            System.out.println("\t1.Buy another Service");
            System.out.println("\t9.Return\n");
            System.out.print("Selection: ");

            if(scanner.hasNextByte()) {
                action = scanner.nextByte();
            }
            else{
                System.out.println("Please enter a number!");
                scanner.next();
                action = 0; //fail safe
            }
        }while (action!=9);

    }



    private static int findAccountIndex(String query){ //find account index in the accountList
        for(int i=0; i<accountList.size(); i++){
            if(accountList.elementAt(i).getAccountNumber().equals(query) || accountList.elementAt(i).getPhone().equals(query))
            {
                return i;
            }
        }
        return -1;
    }

}
