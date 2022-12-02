package com.fhv.hotelmanagement.view.viewServices;

import com.fhv.hotelmanagement.view.DTOs.BookedRoomCategoryDTO;
import com.fhv.hotelmanagement.view.DTOs.BookedRoomDTO;
import com.fhv.hotelmanagement.view.DTOs.BookingDTO;
import com.fhv.hotelmanagement.view.controller.viewController.CheckOutViewController;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.awt.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import static java.time.temporal.ChronoUnit.DAYS;


public class ApachePDFBoxServices {

    public static void main(String[] args) throws IOException {

        ApachePDFBoxServices pdfBoxServices = new ApachePDFBoxServices();

        String name = "Samuel";
        int minDistance = 10;
        System.out.println(pdfBoxServices.dynamicStringDistance(minDistance, name.length()));
        System.out.println("test");

        //create a table
//        String DEST = "src/main/resources/com/fhv/hotelmanagement/pdf/Rechnung"+"Test.pdf";
//
//        PDDocument doc = new PDDocument();
//        PDPage myPage = new PDPage();
//        doc.addPage(myPage);
//        PDPageContentStream cont = new PDPageContentStream(doc, myPage);
//
//        cont.setStrokingColor(Color.BLACK);
//        cont.setLineWidth(1);
//
//        int pageHeight = (int) myPage.getTrimBox().getHeight();
//        int pageWidth = (int) myPage.getTrimBox().getWidth();
//
//        int initX= 50;
//        int initY = pageHeight -50;
//        int cellHeight = 30;
//        int cellWidth = 100;
//
//        int colCount = 5;
//        int rowCount = 10;
//
//        for(int i =1; i <=rowCount; i++){
//            for(int j=1; j<=colCount; j++){
//                cont.addRect(initX, initY, cellWidth, -cellHeight);
//                initX = cellWidth;
//                String line8 = "Vorname:";
//                cont.beginText();
//
//                cont.setFont(PDType1Font.TIMES_ROMAN, 12);
//                cont.setLeading(14.5f);
//                cont.newLineAtOffset(25, 700);
//
//                cont.showText(line8);
//                cont.newLine();
//                cont.endText();
//            }
//            initX = 50;
//            initY-=cellHeight;
//        }
//        cont.stroke();
//        cont.close();
//        doc.save(DEST);
//        doc.close();
    }

    public void createBill(CheckOutViewController viewController) {

        BookingDTO bookingDTO = viewController.getUseCaseController().getBooking();

        String DEST = "src/main/resources/com/fhv/hotelmanagement/pdf/Rechnung" + bookingDTO.getNumber() + bookingDTO.getCustomer().getFirstName() + bookingDTO.getCustomer().getLastName() + ".pdf";

        try (PDDocument doc = new PDDocument()) {

            PDPage myPage = new PDPage();
            doc.addPage(myPage);

            try (PDPageContentStream cont = new PDPageContentStream(doc, myPage)) {
                cont.beginText();
                cont.setFont(PDType1Font.COURIER, 12);
                cont.setLeading(14.5f);
                cont.newLineAtOffset(25, 700);

                BigDecimal totalBoardPrice = new BigDecimal(0).setScale(2);
                int amountGuests = bookingDTO.getAmountGuests();
                BigDecimal boardPrice = bookingDTO.getPricePerNightForBoard();

                int nights = (int) DAYS.between(bookingDTO.getArrivalDate(), bookingDTO.getDepartureDate());
                BigDecimal totalRoomPrice = new BigDecimal(0).setScale(2);

                for (BookedRoomCategoryDTO c : bookingDTO.getBookedRoomCategories()) {
                    BigDecimal price = c.getPricePerNight().multiply(new BigDecimal(c.getAmount())).multiply(BigDecimal.valueOf(nights));
                    totalRoomPrice = totalRoomPrice.add(price);
                }

                if (boardPrice != null) {
                    boardPrice = boardPrice.multiply(new BigDecimal(nights).multiply(new BigDecimal(amountGuests)));
                    totalBoardPrice = totalBoardPrice.add(boardPrice);
                }

                BigDecimal totalSumNet = new BigDecimal(0);
                totalSumNet = totalSumNet.add(totalRoomPrice);
                totalSumNet = totalSumNet.add(totalBoardPrice);

                BigDecimal SALEXTAX = new BigDecimal("0.2");
                BigDecimal TOURISTTAXPERNIGHT = new BigDecimal("1.5");
                BigDecimal salesTax = totalSumNet.multiply(SALEXTAX);

                BigDecimal touristTax = new BigDecimal(0);
                touristTax = touristTax.add(BigDecimal.valueOf(amountGuests).multiply(TOURISTTAXPERNIGHT));
                touristTax = touristTax.multiply(BigDecimal.valueOf(nights));

                BigDecimal totalSumGross = totalSumNet.add(salesTax).add(touristTax);

                StringBuilder rooms = new StringBuilder();
                ArrayList<BookedRoomDTO> bookedRoomDTOs = bookingDTO.getBookedRooms();
                for (BookedRoomDTO bookedRoomDTO : bookedRoomDTOs) {
                    rooms.append(bookedRoomDTO.getRoom().getNumber()).append(",");
                }

                //line in the pdf file
                String line001 = "Kunde:" + bookingDTO.getCustomer().getFirstName() + " " + bookingDTO.getCustomer().getLastName();
                cont.showText(line001);
                cont.newLine();
                cont.newLine();

                String line01 = "Anzahl" + dynamicStringDistance(20, 6) + "Zimmer" + dynamicStringDistance(20, 6) + "Preis pro Nacht" + dynamicStringDistance(25, 15) + "Preis";
                cont.showText(line01);
                cont.newLine();

                String line02 = "---------------------------------------------------------------------------";
                cont.showText(line02);
                cont.newLine();

                if(bookingDTO.getBookedRoomCategories().size()>0){
                    System.out.println(bookingDTO.getBookedRooms().size());
                    String category0 = bookingDTO.getBookedRooms().get(0).getRoom().getCategory().getName();
                    String pricePerNight0= String.valueOf(bookingDTO.getBookedRooms().get(0).getRoom().getCategory().getPricePerNight());


                    String amount0 = String.valueOf(bookingDTO.getBookedRoomCategories().get(0).getAmount());
                    String line03 =amount0+dynamicStringDistance(20, amount0.length()) + category0 + dynamicStringDistance(20, category0.length()) + pricePerNight0+",-" + dynamicStringDistance(20, pricePerNight0.length()+2);
                    cont.showText(line03);
                    cont.newLine();
                }

                if(bookingDTO.getBookedRoomCategories().size()>1){
                    String category1 = bookingDTO.getBookedRooms().get(1).getRoom().getCategory().getName();
                    String pricePerNight1= String.valueOf(bookingDTO.getBookedRooms().get(1).getRoom().getCategory().getPricePerNight());

                    String amount1 = String.valueOf(bookingDTO.getBookedRoomCategories().get(1).getAmount());
                    String line04 =amount1+dynamicStringDistance(20, amount1.length()) + category1 + dynamicStringDistance(20, category1.length()) + pricePerNight1+",-" + dynamicStringDistance(20, pricePerNight1.length()+2);
                    cont.showText(line04);
                    cont.newLine();
                }

                if(bookingDTO.getBookedRoomCategories().size()>2){
                    String category2 = bookingDTO.getBookedRooms().get(2).getRoom().getCategory().getName();
                    String pricePerNight2= String.valueOf(bookingDTO.getBookedRooms().get(2).getRoom().getCategory().getPricePerNight());

                    String amount2 = String.valueOf(bookingDTO.getBookedRoomCategories().get(2).getAmount());
                    String line05 =amount2+dynamicStringDistance(20, amount2.length()) + category2 + dynamicStringDistance(20, category2.length()) + pricePerNight2+",-" + dynamicStringDistance(20, pricePerNight2.length()+2);
                    cont.showText(line05);
                    cont.newLine();
                }

                if(bookingDTO.getBookedRoomCategories().size()>3){
                    String category3 = bookingDTO.getBookedRooms().get(3).getRoom().getCategory().getName();
                    String pricePerNight3= String.valueOf(bookingDTO.getBookedRooms().get(3).getRoom().getCategory().getPricePerNight());

                    String amount3 = String.valueOf(bookingDTO.getBookedRoomCategories().get(3).getAmount());
                    String line06 =amount3+dynamicStringDistance(20, amount3.length()) + category3 + dynamicStringDistance(20, category3.length()) + pricePerNight3+",-" + dynamicStringDistance(20, pricePerNight3.length()+2);
                    cont.showText(line06);
                    cont.newLine();
                }

//
//                String line04 = "Placeh" + dynamicStringDistance(20, 6) + "Doppelzimmer" + dynamicStringDistance(20, 12) + "80,-" + dynamicStringDistance(20, 4);
//                cont.showText(line04);
//                cont.newLine();
//
//                String line05 = "Placeh" + dynamicStringDistance(20, 6) + "Familienzimmer" + dynamicStringDistance(20, 14) + "120,-" + dynamicStringDistance(20, 5);
//                cont.showText(line05);
//                cont.newLine();
//
//                String line06 = "Placeh" + dynamicStringDistance(20, 6) + "Suite" + dynamicStringDistance(20, 5) + "150,-" + dynamicStringDistance(20, 4);
//                cont.showText(line06);
//                cont.newLine();

                String line07 = "---------------------------------------------------------------------------";
                cont.showText(line07);
                cont.newLine();

                String night = String.valueOf(nights);
                String line08 = "Placeh" + dynamicStringDistance(20, 6) + "Nächte" + dynamicStringDistance(20, 6) + nights + "x" + dynamicStringDistance(20, night.length() + 1);
                cont.showText(line08);
                cont.newLine();

                String spacing = "---------------------------------------------------------------------------";
                cont.showText(spacing);
                cont.newLine();

                String spacing1 = " ";
                String spacing2 = " ";
                cont.showText(spacing1);
                cont.newLine();
                cont.showText(spacing2);
                cont.newLine();

                String line09 = "Zimmernummer" + dynamicStringDistance(20, 12) + "Package" + dynamicStringDistance(20, 7) + "Preis pro Nacht" + dynamicStringDistance(25, 15) + "Preis";
                cont.showText(line09);
                cont.newLine();

                String line10 = "---------------------------------------------------------------------------";
                cont.showText(line10);
                cont.newLine();

                String line11 = rooms + "" + dynamicStringDistance(20, rooms.length()) + "Vollpension" + dynamicStringDistance(20, 11) + "60,-" + dynamicStringDistance(20, 4);
                cont.showText(line11);
                cont.newLine();

                String line12 = rooms + "" + dynamicStringDistance(20, rooms.length()) + "Halbpension" + dynamicStringDistance(20, 11) + "80,-" + dynamicStringDistance(20, 4);
                cont.showText(line12);
                cont.newLine();

                String line13 = rooms + "" + dynamicStringDistance(20, rooms.length()) + "Früchstück" + dynamicStringDistance(20, 10) + "120,-" + dynamicStringDistance(20, 5);
                cont.showText(line13);
                cont.newLine();

                String line14 = rooms + "" + dynamicStringDistance(20, rooms.length()) + "Ohne" + dynamicStringDistance(20, 4) + "150,-" + dynamicStringDistance(20, 5);
                cont.showText(line14);
                cont.newLine();

                String line15 = "---------------------------------------------------------------------------";
                cont.showText(line15);
                cont.newLine();

                String line16 = "Placeh" + dynamicStringDistance(20, 6) + "Nächte" + dynamicStringDistance(20, 6) + nights + "x" + dynamicStringDistance(20, night.length());
                cont.showText(line16);
                cont.newLine();

                String line17 = "---------------------------------------------------------------------------";
                cont.showText(line17);
                cont.newLine();

                String line18 = dynamicStringDistance(40, 0) + "Summe für Zimmer:" + dynamicStringDistance(25, 17) + totalRoomPrice;
                cont.showText(line18);
                cont.newLine();

                String line19 = dynamicStringDistance(40, 0) + "Summe für Package:" + dynamicStringDistance(25, 18) + totalBoardPrice;
                cont.showText(line19);
                cont.newLine();

                String spacing3 = dynamicStringDistance(40, 0) + "-----------------------------------";
                cont.showText(spacing3);
                cont.newLine();

                String line20 = dynamicStringDistance(40, 0) + "Gesamtsumme (Netto):" + dynamicStringDistance(25, 20) + totalSumNet;
                cont.showText(line20);
                cont.newLine();

                String line21 = dynamicStringDistance(40, 0) + "+20% MwSt:" + dynamicStringDistance(25, 10) + salesTax.setScale(2);
                cont.showText(line21);
                cont.newLine();

                String line22 = dynamicStringDistance(40, 0) + "Ortstaxe:" + dynamicStringDistance(25, 9) + touristTax.setScale(2);
                cont.showText(line22);
                cont.newLine();

                String spacing4 = dynamicStringDistance(40, 0) + "-----------------------------------";
                cont.showText(spacing4);
                cont.newLine();

                String line23 = dynamicStringDistance(40, 0) + "Gesamtsumme (Brutto):" + dynamicStringDistance(25, 21) + totalSumGross.setScale(2);
                cont.showText(line23);
                cont.newLine();
            }

            doc.save(DEST);
            //infinite booking hack
//            String easteregg = String.valueOf(bookingDTO.getBookedRooms().get(0).getBooking().getBookedRooms().get(0).getBooking().getBookedRooms().get(0).getBooking().getBookedRooms().get(0).getBooking().getBookedRooms().get(0).getBooking().getBookedRooms().get(0).getBooking().getBookedRooms().get(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public StringBuilder dynamicStringDistance(int minDistanceLength, int wordLength) {
        StringBuilder dynamicDistance = new StringBuilder();
        int addEmptySpace = minDistanceLength - wordLength;

        for (int i = 0; i < addEmptySpace; i++) {
            dynamicDistance.append(" ");
        }
        return dynamicDistance;
    }

    public ArrayList unduplicateArrayList(ArrayList duplicatedValuesInArrayList){
        ArrayList undplicatedArray = new ArrayList<>();

        for(int i=0;i<duplicatedValuesInArrayList.size();i++){

        }
        return undplicatedArray;
    }

}
