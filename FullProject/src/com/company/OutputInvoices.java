package com.company;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

/*all invoices are printed in a specific format in a file
* this is done just before exiting the program*/
public class OutputInvoices {

    public void printToFile(Vector<Account> accountList,String fileName ){

        try (FileWriter outputFile = new FileWriter(fileName);
             BufferedWriter buffer = new BufferedWriter(outputFile);
             PrintWriter printOut = new PrintWriter(buffer);)
        {


            for(int i=0; i<accountList.size();i++) {
                printOut.println(accountList.elementAt(i).getAccountNumber()+":");

                for(int j=0; j<accountList.elementAt(i).getNumberOfInvoices(); j++) {
                    printOut.print(j + "/");
                    for (int k = 0; k < accountList.elementAt(i).getListOfInvoices().elementAt(j).getItemsQuantity(); k++) {
                        printOut.print(k + "_");
                        printOut.print(accountList.elementAt(i).getListOfInvoices().elementAt(j).getItem(k).getServiceName() + "_");
                        printOut.print(accountList.elementAt(i).getListOfInvoices().elementAt(j).getItem(k).getQuantity() + "_");
                                printOut.print(accountList.elementAt(i).getListOfInvoices().elementAt(j).getItem(k).getItemAmount() + "/");
                    }
                    printOut.println();
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}
