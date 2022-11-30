package com.fhv.hotelmanagement.view.viewServices;

import com.fhv.hotelmanagement.view.DTOs.BookedRoomCategoryDTO;
import com.fhv.hotelmanagement.view.DTOs.BookingDTO;
import com.fhv.hotelmanagement.view.controller.viewController.CheckOutViewController;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;
import java.math.BigDecimal;

import static java.time.temporal.ChronoUnit.DAYS;


public class ApachePDFBox {


    public void createBill(CheckOutViewController viewController) {

        BookingDTO bookingDTO = viewController.getUseCaseController().getBooking();

        String DEST = "src/main/resources/com/fhv/hotelmanagement/pdf/Rechnung"+bookingDTO.getNumber()+ bookingDTO.getCustomer().getFirstName()+bookingDTO.getCustomer().getLastName()+".pdf";

        try (PDDocument doc = new PDDocument()) {

            PDPage myPage = new PDPage();
            doc.addPage(myPage);

            try (PDPageContentStream cont = new PDPageContentStream(doc, myPage)) {
                cont.beginText();
                cont.setFont(PDType1Font.TIMES_ROMAN, 12);
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


                //line in the pdf file
                String line8="Vorname:"+bookingDTO.getCustomer().getFirstName();
                String line9="Nachname:"+bookingDTO.getCustomer().getLastName();
                String line10="Räume:"+bookingDTO.getBookedRooms();
                String line1 ="Package:"+ bookingDTO.getBoard().getName();
                String line2 ="Nächte:"+String.valueOf(nights);
                String line3 ="Summe für Zimmer:"+String.valueOf(totalRoomPrice);
                String line4  ="Summe für Package:"+String.valueOf(totalSumNet);
                String line5 ="+20% MwSt:"+String.valueOf(salesTax.setScale(2));
                String line6 ="Ortstaxe:"+String.valueOf(touristTax.setScale(2));
                String line7 ="Gesamtsumme (Brutto):"+String.valueOf(totalSumGross.setScale(2));
                
                cont.showText(line8);
                cont.newLine();

                cont.showText(line9);
                cont.newLine();

                cont.showText(line10);
                cont.newLine();

                cont.showText(line1);
                cont.newLine();

                cont.showText(line2);
                cont.newLine();

                cont.showText(line3);
                cont.newLine();

                cont.showText(line4);
                cont.newLine();

                cont.showText(line5);
                cont.newLine();

                cont.showText(line6);
                cont.newLine();

                cont.showText(line7);
                cont.newLine();

                cont.endText();
            }

            doc.save(DEST);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
