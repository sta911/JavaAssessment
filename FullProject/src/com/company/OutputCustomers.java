package com.company;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

/*all customer information is printed in a specific format in a file
 * this is done just before exiting the program*/
public class OutputCustomers {

    public void printToFile(Vector<Account> accountList, String fileName){

        {
            try (FileWriter outputFile = new FileWriter(fileName);
                 BufferedWriter buffer = new BufferedWriter(outputFile);
                 PrintWriter printOut = new PrintWriter(buffer);)
            {

                for(int i=0; i<accountList.size();  i++){
                    printOut.print(accountList.elementAt(i).getAccountNumber() + "_" + accountList.elementAt(i).getCustomerName() + "_" + accountList.elementAt(i).getAddress() + "_" + accountList.elementAt(i).getPhone()+ "_");
                    for(int j=0; j<accountList.elementAt(i).getListOfServices().size(); j++){
                        printOut.print(accountList.elementAt(i).getListOfServices().elementAt(j) + "-");
                    }
                    printOut.println();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
