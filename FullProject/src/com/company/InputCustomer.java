package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

/*read the customer database and initialize the accountList*/
public class InputCustomer {
    public void readFile(String fileName, Vector<Account> accountList) {
        try {
            File inputFile = new File(fileName);
            Scanner scanner = new Scanner(inputFile);

            String line;
            String[] accInfo;
            String[] accServices;

            while (scanner.hasNextLine()) {

                accInfo = new String[5];
                accServices = new String[0];

                line = scanner.nextLine().trim();
                accInfo = line.split("_");

                Account newAccount = new Account(Integer.parseInt(accInfo[0]));
                newAccount.setCustomerName(accInfo[1]);
                newAccount.setAddress(accInfo[2]);
                newAccount.setPhone(accInfo[3]);

                if(accInfo.length==5) {
                    accServices = accInfo[4].split("-");

                    for (int i = 0; i < accServices.length; i++) {
                        newAccount.updateServices(accServices[i]);
                    }
                }
                accountList.add(newAccount);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
