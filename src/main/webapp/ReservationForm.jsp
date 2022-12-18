<%--
  Created by IntelliJ IDEA.
  User: clara
  Date: 12.12.2022
  Time: 11:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en">
<head>
    <title>Make Reservation</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="WebStylesheet.css">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body style="font-family: Arial;
background-image: url('Background.jpg');
background-repeat: no-repeat;
background-attachment: fixed">
<!-- Navbar-->
<ul style="margin-top: 0; height: 70px; background-color: #21273d;">
    <li><a class="oben" style="font-size: 20px" href="#about">About</a></li>
    <li><a class="oben" style="font-size: 20px" href="#contact">Contact</a></li>
    <!--<li><a class="oben" href="reservationVorlage.html">Reservations</a></li>-->
    <li><a class="oben" style="font-size: 20px" href="ReservationForm.jsp">Reservations</a></li>
    <li><a class="active, oben" style="font-size: 20px" href="index.html">Home</a></li>
    <li><img src="Logo.png" style="height: 50px;"></li>
</ul>

<!-- Der weiße Hintergrund-->
<div style="float: left">
    <div class="whitebackground">
        <!--zurückpfeil-->
        <div class="topbar">

            <div class="left">
                <a href="index.html">
                    <img src="zurueckpfeil.png" id="back" alt="backArrow">
                </a>
            </div>
        </div>

        <!-- Überschrift -->
        <div class="centerContent">
            <h1>Make a Reservation</h1>
            <div class="container">
                <form id="form"
                      action="Controller"
                      name="registration"
                      method="POST">


                    <!-- Anzahl der Gäste -->
                    <p>Selected Arrival Date: ${sessionScope.arrivalDate}</p>
                    <p>Selected Departure Date: ${sessionScope.departureDate}</p>
                    <div class="input-control" style="margin-top: 50px">
                        <label for="people-input" class="bold">Number of guests:<br/></label>
                        <input class="sizebig" type="number" id="people-input" name="people-input" min="1" max="50"
                               value=${sessionScope.amountGuests}><br><br>
                        <div class="error"></div>
                    </div>

                    <!-- Zimmer -->
                    <!-- erste Zeile der Zimmer -->
                    <table id="room1">
                        <tr>
                            <td>
                                <div class="input-control">
                                    <label for="singleroom" class="bold">Single Room<br/></label>
                                    <input class="sizesmall" type="number" id="singleroom" name="singleroom" min="0"
                                           value="0"
                                           max=${sessionScope.maxSingleRooms}> <!--testen-->
                                    <div class="error"></div>
                                </div>
                            </td>
                            <td>
                                <div class="input-control">
                                    <label for="doubleroom" style="margin-left: 50px" class="bold">Double
                                        Room<br/></label>
                                    <input class="sizesmall" type="number" id="doubleroom" name="doubleroom" min="0"
                                           value="0"
                                           max=${sessionScope.maxDoubleRooms}>
                                    <div class="error"></div>
                                </div>
                            </td>
                        </tr>
                    </table>
                    <!-- zweite Zeile der Zimmer -->
                    <table id="room2">
                        <tr>
                            <td>
                                <div class="input-control">
                                    <label for="familyroom" class="bold">Family Room<br/></label>
                                    <input class="sizesmall" type="number" id="familyroom" name="familyroom" min="0"
                                           value="0"
                                           max=${sessionScope.maxFamilyRooms}>
                                    <div class="error"></div>
                                </div>
                            </td>
                            <td>
                                <div class="input-control">
                                    <label for="suite" style="margin-left: 50px" class="bold">Suite <br/></label>
                                    <input class="sizesmall" type="number" id="suite" name="suite" min="0" value="0"
                                           max=${sessionScope.maxSuites}>
                                    <div class="error"></div>
                                </div>
                            </td>
                        </tr>
                    </table>

                    <!-- Board -->
                    <div class="input-control">
                        <label id="board" class="bold">Board</label>
                        <!-- Fullboard -->
                        <div>
                            <label for="fullBoard"></label>
                            <input type="radio" name="Board" id="fullBoard" value="Full Board">
                            Full Board
                        </div>
                        <!-- halfboard -->
                        <div>
                            <label for="halfBoard"></label>
                            <input type="radio" name="Board" id="halfBoard" value="Half Board">
                            Half Board
                        </div>
                        <!-- just breakfast -->
                        <div>
                            <label for="justBreakfast"></label>
                            <input type="radio" name="Board" id="justBreakfast" value="Just Breakfast">
                            Just Breakfast
                        </div>
                        <!-- no package -->
                        <div>
                            <label for="noPackage"></label>
                            <input type="radio" name="Board" id="noPackage" value="No Package">
                            No Package
                        </div>
                        <div class="error"></div>
                    </div>

                    <!-- Vorname und Nachname -->
                    <table id="name">
                        <tr>
                            <!-- Vorname -->
                            <td>
                                <div class="input-control">
                                    <label for="firstName" class="bold">First Name <br/></label>
                                    <input class="name" name="firstname" id="firstName" type="text">
                                    <div class="error"></div>
                                </div>
                            </td>

                            <!-- Nachname -->
                            <td>
                                <div class="input-control">
                                    <label for="lastName" style="margin-left: 50px" class="bold">Last Name <br/></label>
                                    <input class="name" name="lastname" id="lastName" type="text">
                                    <div class="error" style="margin-left: 50px"></div>
                                </div>
                            </td>
                        </tr>
                    </table>

                    <!-- Geburtsdatum -->
                    <div class="input-control">
                        <label for="birthdate" class="bold">Choose your birthday:<br/></label>
                        <input type="date" id="birthdate" name="birthdate" class="sizebig">
                        <div class="error"></div>
                    </div>

                    <!-- Nationalität -->
                    <div class="input-control">
                        <label for="nationality" class="bold">Nationality <br/></label>
                        <select name="Nationality" id="nationality" class="sizebig">
                            <option value="select" selected>--Please select--</option>
                            <option>Africa</option>
                            <option>Asia</option>
                            <option>Australia</option>
                            <option>Austria</option>
                            <option>Belgium</option>
                            <option>Bulgaria</option>
                            <option>Central America</option>
                            <option>Croatia</option>
                            <option>Cyprus</option>
                            <option>Czech Republic</option>
                            <option>Denmark</option>
                            <option>Estonia</option>
                            <option>Finnland</option>
                            <option>France</option>
                            <option>Germany</option>
                            <option>Greece</option>
                            <option>Hungary</option>
                            <option>Ireland</option>
                            <option>Italy</option>
                            <option>Latvia</option>
                            <option>Liechtenstein</option>
                            <option>Luxembourg</option>
                            <option>Malta</option>
                            <option>Netherlands</option>
                            <option>North America</option>
                            <option>Poland</option>
                            <option>Portugal</option>
                            <option>Romania</option>
                            <option>Slovakia</option>
                            <option>South America</option>
                            <option>Spain</option>
                            <option>Sweden</option>
                            <option >Switzerland</option>
                        </select>
                        <div class="error"></div>
                    </div>

                    <h3>Home Address</h3>
                    <!-- Adresse -->
                    <table id="adress1">
                        <tr>
                            <!-- Straße -->
                            <td>
                                <div class="input-control">
                                    <label for="street" class="bold">Street<br/></label>
                                    <input class="sizebig" name="Street" id="street" type="text">
                                    <div class="error"></div>
                                </div>
                            </td>
                            <!-- HNr. -->
                            <td>
                                <div class="input-control">
                                    <label for="houseNumber" class="bold" style="margin-left: 50px">HNr.
                                        <br/></label>
                                    <input class="sizesmall" name="HouseNumber" id="houseNumber" type="text">
                                    <div class="error" style="margin-left: 50px"></div>
                                </div>
                            </td>
                        </tr>
                    </table>

                    <table id="adress2">
                        <tr>
                            <!-- Stadt -->
                            <td>
                                <div class="input-control">
                                    <label for="city" class="bold">City<br/></label>
                                    <input class="sizebig" name="City" id="city" type="text">
                                    <div class="error"></div>
                                </div>
                            </td>
                            <!-- PLZ -->
                            <td>
                                <div class="input-control">
                                    <label for="zipCode" class="bold" style="margin-left: 50px">ZIP Code
                                        <br/></label>
                                    <input class="sizesmall" name="ZIPCode" id="zipCode" type="text">
                                    <div class="error" style="margin-left: 50px"></div>
                                </div>
                            </td>
                        </tr>
                    </table>

                    <!-- Land -->
                    <div class="input-control">
                        <label for="country" class="bold">Country <br/></label>
                        <input class="sizebig" name="Country" id="country" type="text">
                        <div class="error"></div>
                    </div>

                    <!-- Telefonnummer -->
                    <div class="input-control">
                        <label for="phoneNumber" class="bold">Phone Number <br/></label>
                        <input class="sizebig" name="PhoneNumber" id="phoneNumber" type="text">
                        <div class="error"></div>
                    </div>

                    <!-- Email -->
                    <div class="input-control">
                        <label for="email" class="bold">E-Mail Address <br/> </label>
                        <input class="sizebig" name="Email Address" id="email" type="text">
                        <div class="error"></div>
                    </div>


                    <!-- Payment Method -->
                    <div class="input-control">
                        <label for="paymentmethod" class="bold"><h3>Payment method</h3> <br/></label>
                        <select name="Paymentmethod" id="paymentmethod" class="sizebig" style="margin-top: -30px"
                                onchange="creditCardSelected()">
                            <option value="select" selected>--Please select--</option>
                            <option value="Credit card">Credit card</option>
                            <option value="Bill">Bill</option>
                        </select>
                        <div class="error"></div>
                    </div>

                    <!-- Creditcardnumber -->
                    <div class="input-control">
                        <label for="creditcardnumber" class="bold">Credit Card Number<br/></label>
                        <input class="sizebig" name="CreditCardNumber" id="creditcardnumber" type="text" placeholder="1234 1234 1234 1234">
                        <div class="error"></div>
                    </div>

                    <table id="Creditcardinformation">
                        <!-- Security Number / ABlaufdatum -->
                        <td>
                            <div class="input-control">
                                <label for="securitynumber" class="bold">Security Number<br/></label>
                                <input class="sizesmall" name="SecurityNumber" id="securitynumber" type="text" placeholder="123">
                                <div class="error"></div>
                            </div>
                        </td>
                        <td>
                            <div class="input-control" style="margin-left: 50px">
                                <label for="expirationdate" class="bold">Expariation Date
                                    <br/></label>
                                <input class="sizesmall" name="ExpirationDate" id="expirationdate" type="text" placeholder="12/24">
                                <div class="error"></div>
                            </div>
                        </td>
                        </tr>
                    </table>

                    <h3>Billing Address</h3>
                    <!-- Rechnungsadresse_______________________________________________________________________________________________________________ -->
                    <table id="billingaddress1">
                        <input type="checkbox" id="addressisbillingaddress" name="isBillingAddress"
                               onchange="copyAddress()">
                        <lable for="addressisbillingaddress" style="margin-left: 10px">The billing address is the same
                            as the home address.
                        </lable>
                        <tr>
                            <!-- Rechnungsstraße -->
                            <td>
                                <div class="input-control">
                                    <label for="billingstreet" class="bold">Street<br/></label>
                                    <input class="sizebig" name="BillingStreet" id="billingstreet" type="text">
                                    <div class="error"></div>
                                </div>
                            </td>
                            <!-- Rechnungs HNr. -->
                            <td>
                                <div class="input-control" style="margin-left: 50px">
                                    <label for="billinghouseNumber" class="bold">HNr.
                                        <br/></label>
                                    <input class="sizesmall" name="BillingHouseNumber" id="billinghouseNumber"
                                           type="text">
                                    <div class="error"></div>
                                </div>
                            </td>
                        </tr>
                    </table>

                    <table id="billingaddress2">
                        <tr>
                            <!-- Rechnungsstadt -->
                            <td>
                                <div class="input-control">
                                    <label for="billingcity" class="bold">City<br/></label>
                                    <input class="sizebig" name="BillingCity" id="billingcity" type="text">
                                    <div class="error"></div>
                                </div>
                            </td>
                            <!-- Rechnungs PLZ -->
                            <td>
                                <div class="input-control" style="margin-left: 50px">
                                    <label for="billingzipCode" class="bold">ZIP Code
                                        <br/></label>
                                    <input class="sizesmall" name="BillingZIPCode" id="billingzipCode" type="text">
                                    <div class="error"></div>
                                </div>
                            </td>
                        </tr>
                    </table>

                    <!-- Rechnungsland -->
                    <div class="input-control">
                        <label for="billingcountry" class="bold">Country <br/></label>
                        <input class="sizebig" name="BillingCountry" id="billingcountry" type="text">
                        <div class="error"></div>
                    </div>

                    <label for="comment" class="bold">Comment <br/></label>
                    <textarea id="comment" name="Comment" rows="4" cols="50" placeholder="optional"></textarea>

                    <div style="margin-top: 30px">
                        <!-- Buttons -->
                        <button type="reset" class="reset">Reset</button>
                        <input type="hidden" name="dispatchto" value="newReservation">
                        <button type="submit" class="send">Send</button>
                        <!--submit type damit enter taste auch submitted-->
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
<script src="ReservationFormValidation.js"></script>
</html>
