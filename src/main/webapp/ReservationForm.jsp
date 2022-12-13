<%--
  Created by IntelliJ IDEA.
  User: clara
  Date: 12.12.2022
  Time: 11:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

<ul style="opacity: 0.9">
    <li><img src="" alt=""></li>
    <li><a class="oben" href="#about">About</a></li>
    <li><a class="oben" href="#contact">Contact</a></li>
    <li><a class="oben" href="ReservationForm.jsp">Reservations</a></li>
    <li><a class="active, oben" href="index.html">Home</a></li>
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


                    <!-- Anreise und Abreise Datum (Wieso in tabelle? damit es nebeneinander geht) -->
                    <div class="input-control">
                        <table id="date">
                            <tr>
                                <td><label for="arrivalDate" class="bold">Choose a arrival date: <br/></label>
                                    <input type="date" id="arrivalDate" name="arrivalDate"
                                           value=${sessionScope.arrivalDate}></td>
                                <td><label for="departureDate" style="margin-left: 50px" class="bold">Choose a departure
                                    date:<br/> </label>
                                    <input type="date" id="departureDate" name="departureDate"
                                           value=${sessionScope.departureDate}></td>
                            </tr>
                        </table>
                        <div class="error"></div>
                    </div>

                    <!-- Anzahl der Gäste -->
                    <div class="input-control">
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
                                           max=${sessionScope.maxSingleRooms}> <!--testen-->
                                    <div class="error"></div>
                                </div>
                            </td>
                            <td>
                                <div class="input-control">
                                    <label for="doubleroom" style="margin-left: 50px" class="bold">Double
                                        Room<br/></label>
                                    <input class="sizesmall" type="number" id="doubleroom" name="doubleroom" min="0"
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
                                           max=${sessionScope.maxFamilyRooms}>
                                    <div class="error"></div>
                                </div>
                            </td>
                            <td>
                                <div class="input-control">
                                    <label for="suite" style="margin-left: 50px" class="bold">Suite <br/></label>
                                    <input class="sizesmall" type="number" id="suite" name="suite" min="0"
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
                                    <div class="error"></div>
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
                            <option value="select">--Please select--</option>
                            <option value="Oesterreich">Österreich</option>
                            <option value="Deutschland">Deutschland</option>
                            <option value="Schweiz">Schweiz</option>
                            <option value="andere">andere</option>
                        </select>
                        <div class="error"></div>
                    </div>

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
                                    <div class="error"></div>
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
                                    <div class="error"></div>
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

                    <!-- Buttons -->
                    <button type="reset" class="reset">Reset</button>
                    <input type="hidden" name="dispatchto" value="newReservation">
                    <button type="submit" class="send">Send</button> <!--submit type damit enter taste auch submitted-->
                </form>
            </div>
        </div>
    </div>
</div>
</body>
<script src="FormValidation.js"></script>
</html>
