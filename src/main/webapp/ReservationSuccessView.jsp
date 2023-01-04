<%--
  Created by IntelliJ IDEA.
  User: clara
  Date: 12.12.2022
  Time: 11:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
            <h1>The reservation was successfully!</h1>

        </div>
    </div>
</div>
</body>
<script src="ReservationFormValidation.js"></script>
</html>
