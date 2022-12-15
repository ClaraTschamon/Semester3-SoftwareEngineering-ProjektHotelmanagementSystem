package com.fhv.hotelmanagement.view.viewServices;

import com.fhv.hotelmanagement.view.DTOs.DepositDTO;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DepositService {
    private HashMap<String, DepositDTO> deposits = new HashMap<>();

    public static void main(String[] args) throws IOException {
        DepositService depositService = new DepositService();
        depositService.parseData(depositService.convertData());

//        Res#=12345#Date=01NOV2022#Amount=123.34;Date=01NOV2022#
//        Res#=12346#Amount=50.0#IBAN=AT07123412341234123412;

//        System.out.println(depositService.deposits.get("12345").getAmount());
//        System.out.println(depositService.deposits.get("12345").getDate());
//        System.out.println(depositService.deposits.get("12345").getIban());
//        System.out.println(depositService.deposits.get("12346").getAmount());
//        System.out.println(depositService.deposits.get("12346").getDate());
//        System.out.println(depositService.deposits.get("12346").getIban());
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
            data.append(line);
        }
        return data;
    }

    public void parseData(StringBuilder data){
        String date = "";
        String amount = "";
        String iban = "";
        String reservationNumber = "";

        for(int i = 0; i<data.length();i++){
            if(data.charAt(i)=='R'){
                i+=5;
                while (data.charAt(i)!='#' && data.charAt(i)!=';'){
                    reservationNumber+=data.charAt(i);
                    i++;
                }
                System.out.println(reservationNumber);
            }

            if(data.charAt(i)=='D'){
                i+=5;
                int y = i+9;
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

            if(data.charAt(i)==';'){
                deposits.put(reservationNumber, new DepositDTO(date,amount,iban, reservationNumber));
                date = "";
                amount = "";
                iban = "";
                reservationNumber = "";
            }
        }
    }
}
