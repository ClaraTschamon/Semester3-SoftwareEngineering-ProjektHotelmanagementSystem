package com.fhv.hotelmanagement.view.viewServices;

import java.io.*;

public class DepositService {
    public static void main(String[] args) throws IOException {
        DepositService depositService = new DepositService();
        depositService.parseData(depositService.convertData());
    }

    public StringBuilder convertData() throws IOException {
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

        return data;
    }

    public void parseData(StringBuilder data){
        String reservationNumber = "";
        String date = "";
        String amount = "";
        String IBAN = "";
        String match = "";

        for(int i = 0; i<data.length();i++){
            System.out.println(data.charAt(i));
            if(data.charAt(i)=='R'){
                reservationNumber+=data.charAt(i+5);
                reservationNumber+=data.charAt(i+6);
                reservationNumber+=data.charAt(i+7);
                reservationNumber+=data.charAt(i+8);
                reservationNumber+=data.charAt(i+9);
            }

            if(data.charAt(i)=='D'){
                date+=data.charAt(i+5);
                date+=data.charAt(i+5);
                date+=data.charAt(i+5);
                date+=data.charAt(i+5);
                date+=data.charAt(i+5);
            }

        }
    }
}
