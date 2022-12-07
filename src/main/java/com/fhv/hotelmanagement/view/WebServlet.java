package com.fhv.hotelmanagement.view;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@jakarta.servlet.annotation.WebServlet(urlPatterns = "/reservation")
public class WebServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        writer.println("Make a reservation here");

        response.setContentType("text/html");

        //html code here
        writer.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <title>Make Reservation</title>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"keywords\" content=\"Bibliothek, Blons, Ausleihen, Leihen, Bibliohteksabo,\n" +
                "            Bücher, Filme, Zeitschriften, Spiele, Ludothek\">\n" +
                "    <meta name=\"description\" content=\"Webseite der Bibliothek Blons\">\n" +
                "    <meta name=\"author\" content=\"Clara Tschamon\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <link rel=\"stylesheet\" type=\"text/css\" href=\"WebStylesheet.css\">\n" +
                "</head>\n" +
                "<body>\n" +
                "<!--zurückpfeil-->\n" +
                "<div class=\"topbar\">\n" +
                "    <div class=\"left\">\n" +
                "        <a href=\"index.html\">\n" +
                "            <img src=\"zurueckpfeil.png\" id=\"back\" alt=\"backArrow\">\n" +
                "        </a>\n" +
                "    </div>\n" +
                "</div>\n" +
                "\n" +
                "<div class=\"centerContent\">\n" +
                "    <h1>Make a Reservation</h1>\n" +
                "    <div class=\"container\">\n" +
                "        <form id=\"form\"\n" +
                "              action=\"\"\n" +
                "              method=\"POST\">\n" +
                "\n" +
                "            <!-- Arrival Date -->\n" +
                "            <!-- Departure Date -->\n" +
                "            <!-- Number of Guests -->\n" +
                "            <!-- Select Room -->\n" +
                "\n" +
                "\n" +
                "            <div class=\"input-control\">\n" +
                "                <label id=\"board\" class=\"bold\">Board</label>\n" +
                "                <div>\n" +
                "                    <label for=\"fullBoard\"></label>\n" +
                "                    <input type=\"radio\" name=\"Board\" id=\"fullBoard\" value=\"Full Board\">\n" +
                "                    Full Board\n" +
                "                </div>\n" +
                "                <div>\n" +
                "                    <label for=\"halfBoard\"></label>\n" +
                "                    <input type=\"radio\" name=\"Board\" id=\"halfBoard\" value=\"Half Board\">\n" +
                "                    Half Board\n" +
                "                </div>\n" +
                "                <div>\n" +
                "                    <label for=\"justBreakfast\"></label>\n" +
                "                    <input type=\"radio\" name=\"Board\" id=\"justBreakfast\" value=\"Just Breakfast\">\n" +
                "                    Just Breakfast\n" +
                "                </div>\n" +
                "                <div>\n" +
                "                    <label for=\"noPackage\"></label>\n" +
                "                    <input type=\"radio\" name=\"Board\" id=\"noPackage\" value=\"No Package\">\n" +
                "                    No Package\n" +
                "                </div>\n" +
                "                <div class=\"error\"></div>\n" +
                "            </div>\n" +
                "\n" +
                "            <div class=\"input-control\">\n" +
                "                <label for=\"firstName\" class=\"bold\">First Name <br/></label>\n" +
                "                <input name=\"First Name\" id=\"firstName\" type=\"text\">\n" +
                "                <div class=\"error\"></div>\n" +
                "            </div>\n" +
                "\n" +
                "            <div class=\"input-control\">\n" +
                "                <label for=\"lastName\" class=\"bold\">Last Name <br/> </label>\n" +
                "                <input name=\"Last Name\" id=\"lastName\" type=\"text\">\n" +
                "                <div class=\"error\"></div>\n" +
                "            </div>\n" +
                "\n" +
                "            <div class=\"input-control\">\n" +
                "                <label for=\"nationality\" class=\"bold\">Nationality <br/></label>\n" +
                "                <select name=\"Nationality\" id=\"nationality\">\n" +
                "                    <option value=\"select\">--Please select--</option>\n" +
                "                    <option value=\"Oesterreich\">Österreich</option>\n" +
                "                    <option value=\"Deutschland\">Deutschland</option>\n" +
                "                    <option value=\"Schweiz\">Schweiz</option>\n" +
                "                    <option value=\"andere\">andere</option>\n" +
                "                </select>\n" +
                "                <div class=\"error\"></div>\n" +
                "            </div>\n" +
                "\n" +
                "            <!-- Date of Birth Datepicker -->\n" +
                "\n" +
                "            <div class=\"input-control\">\n" +
                "                <label for=\"street\" class=\"bold\">Street <br/></label>\n" +
                "                <input name=\"Street\" id=\"street\" type=\"text\">\n" +
                "                <div class=\"error\"></div>\n" +
                "            </div>\n" +
                "\n" +
                "            <div class=\"input-control\">\n" +
                "                <label for=\"houseNumber\" class=\"bold\">HNr. <br/></label>\n" +
                "                <input name=\"HouseNumber\" id=\"houseNumber\" type=\"text\">\n" +
                "                <div class=\"error\"></div>\n" +
                "            </div>\n" +
                "\n" +
                "            <div class=\"input-control\">\n" +
                "                <label for=\"city\" class=\"bold\">City <br/></label>\n" +
                "                <input name=\"City\" id=\"city\" type=\"text\">\n" +
                "                <div class=\"error\"></div>\n" +
                "            </div>\n" +
                "\n" +
                "            <div class=\"input-control\">\n" +
                "                <label for=\"zipCode\" class=\"bold\">ZIP Code <br/></label>\n" +
                "                <input name=\"ZIPCode\" id=\"zipCode\" type=\"text\">\n" +
                "                <div class=\"error\"></div>\n" +
                "            </div>\n" +
                "\n" +
                "            <div class=\"input-control\">\n" +
                "                <label for=\"country\" class=\"bold\">Country <br/></label>\n" +
                "                <input name=\"Country\" id=\"country\" type=\"text\">\n" +
                "                <div class=\"error\"></div>\n" +
                "            </div>\n" +
                "\n" +
                "            <div class=\"input-control\">\n" +
                "                <label for=\"phoneNumber\" class=\"bold\">Phone Number <br/></label>\n" +
                "                <input name=\"PhoneNumber\" id=\"phoneNumber\" type=\"text\">\n" +
                "                <div class=\"error\"></div>\n" +
                "            </div>\n" +
                "\n" +
                "            <div class=\"input-control\">\n" +
                "                <label for=\"email\" class=\"bold\">E-Mail Address <br/> </label>\n" +
                "                <input name=\"Email Address\" id=\"email\" type=\"text\">\n" +
                "                <div class=\"error\"></div>\n" +
                "            </div>\n" +
                "\n" +
                "            <button type=\"reset\">Reset</button>\n" +
                "            <button type=\"submit\">Send</button> <!--submit type damit enter taste auch submitted-->\n" +
                "        </form>\n" +
                "    </div>\n" +
                "</div>\n" +
                "</body>\n" +
                "<script src=\"FormValidation.js\"></script>\n" +
                "</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
