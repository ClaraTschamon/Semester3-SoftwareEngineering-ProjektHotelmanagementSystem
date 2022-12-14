package com.fhv.hotelmanagement.view.viewServices;

import java.io.*;

public class DepositService {
    public static void main(String[] args) throws IOException {
        DepositService depositService = new DepositService();
        depositService.convertData();
    }

    public void convertData() throws IOException {
        File file = new File("DatenBuchhaltung");
        System.out.println(file.exists());

        FileInputStream fis = new FileInputStream(file);
        InputStreamReader inputStreamReader = new InputStreamReader(fis);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String line;
        StringBuilder data = new StringBuilder();

        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
            data.append(line);
        }
    }

    public void parseData(String data){


    }
}
