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
//            System.out.println(line);
            data.append(line);
        }

        return data;
    }

    public void parseData(StringBuilder data){
        String reservationNumber = "";
        String date = "";
        String amount = "";
        String iban = "";

//        Res#=12345#Date=01NOV2022#Amount=123.34;Date=01NOV2022#
//        Res#=12346#Amount=50.0#IBAN=AT07123412341234123412;

        for(int i = 0; i<data.length();i++){
            if(data.charAt(i)=='R'){
                i+=5;
                int y = i+5;
                while (i<y){
                    reservationNumber+=data.charAt(i);
                    i++;
                }
                System.out.println(reservationNumber);
            }

            if(data.charAt(i)=='D'){
                i+=5;
                int y = i+8;
                while (i<y){
                    date+=data.charAt(i);
                    i++;
                }
                System.out.println(date);
            }

            if(data.charAt(i)=='A' && data.charAt(i+1)=='m'){
                i+=7;
                while (data.charAt(i)!='#' && data.charAt(i)!=';'){
                    amount+=data.charAt(i);
                    i++;
                }
                System.out.println(amount);
            }

            if(data.charAt(i)=='I'){
                i+=5;
                int y = i+22;
                while (i<y){
                    iban+=data.charAt(i);
                    i++;
                }
                System.out.println(iban);
            }

        }
    }
}
