package com.fhv.hotelmanagement.view.viewServices;

import com.fhv.hotelmanagement.view.DTOs.DepositDTO;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DepositService {
    private HashMap<String, DepositDTO> deposits = new HashMap<>();
    private ArrayList<Long> reservierungsNummer = new ArrayList<>();

    public StringBuilder convertData() throws IOException {
        File file = new File("DatenBuchhaltung");

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

    public ArrayList<Long> parseData(StringBuilder data){
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
            }

            if(data.charAt(i)=='D'){
                i+=5;
                int y = i+9;
                while (i<y){
                    date+=data.charAt(i);
                    i++;
                }
            }

            if(data.charAt(i)=='A' && data.charAt(i+1)=='m'){
                i+=7;
                while (data.charAt(i)!='#' && data.charAt(i)!=';'){
                    amount+=data.charAt(i);
                    i++;
                }
            }

            if(data.charAt(i)=='I'){
                i+=5;
                int y = i+22;
                while (i<y){
                    iban+=data.charAt(i);
                    i++;
                }
            }

            if(data.charAt(i)==';'){
                deposits.put(reservationNumber, new DepositDTO(date,amount,iban, reservationNumber));
                reservierungsNummer.add(Long.valueOf(reservationNumber));
                date = "";
                amount = "";
                iban = "";
                reservationNumber = "";
            }
        }
        return reservierungsNummer;
    }
}
