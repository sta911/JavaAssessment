package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

/*read the available services from a file and initialize the serviceList*/
public class InputServices {
    public void readFile(String fileName, Vector<Service> servicesList) {

        try {
            File inputFile = new File(fileName);
            Scanner scanner = new Scanner(inputFile);

            String line;
            String[] serviceInfo;

            while (scanner.hasNextLine()) {

                serviceInfo = new String[0];

                line = scanner.nextLine().trim();

                serviceInfo = line.split("_");

                Service newService = new Service();
                newService.setServiceName(serviceInfo[0]);
                newService.setPrice(Float.parseFloat(serviceInfo[1]));
                newService.setServiceDescription(serviceInfo[2]);

                servicesList.add(newService);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
