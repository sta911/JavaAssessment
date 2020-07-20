package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

/*read previous history of invoices and include them in the respective accounts*/
public class InputInvoices {
    public void readFile(String fileName, Vector<Account> accountList) {

        try {
            File inputFile = new File(fileName);
            Scanner scanner = new Scanner(inputFile);

            String line;
            String accNo = "";
            String[] items;
            String[] itemInfo;

            while (scanner.hasNextLine()) {

                items = new String[0];

                line = scanner.nextLine().trim();
                if(line.contains(":")){
                    accNo= line.substring(0,line.length()-1);
                }
                else {
                    items = line.split("/");

                    Invoice newInvoice = new Invoice(accNo,Integer.parseInt(items[0]));

                    for(int i=1; i<items.length; i++) {
                        itemInfo = items[i].split("_");
                        Item newItem = new Item(itemInfo[1],Float.parseFloat(itemInfo[3])/Integer.parseInt(itemInfo[2]));
                        newItem.setQuantity(Integer.parseInt(itemInfo[2]));
                        newInvoice.addItem(newItem);
                    }
                    accountList.elementAt(Integer.parseInt(accNo)).addInvoice(newInvoice);
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
