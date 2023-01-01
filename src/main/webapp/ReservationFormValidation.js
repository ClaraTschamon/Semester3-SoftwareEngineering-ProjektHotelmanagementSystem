//ReservationForm.jsp
const form = document.getElementById('form');

const board = document.getElementById('board');
const fullBoard = document.getElementById('fullBoard');
const halfBoard = document.getElementById('halfBoard');
const justBreakfast = document.getElementById('justBreakfast');
const noPackage = document.getElementById('noPackage');


const firstName = document.getElementById('firstName');
const lastName = document.getElementById('lastName');
const nationality = document.getElementById('nationality');
const street = document.getElementById('street');
const houseNumber = document.getElementById('houseNumber');
const city = document.getElementById('city');
const zipCode = document.getElementById('zipCode');
const country = document.getElementById('country');
const phoneNumber = document.getElementById('phoneNumber');
const email = document.getElementById('email');
const billingStreet = document.getElementById('billingstreet');
const billingHouseNumber = document.getElementById('billinghouseNumber');
const billingCity = document.getElementById('billingcity');
const billingZipCode = document.getElementById('billingzipCode');

const arrivalDate = document.getElementById('arrivalDate')
const creditCard = document.getElementById('creditcardnumber');
const securityNumber = document.getElementById('securitynumber');
const expirationDate = document.getElementById('expirationdate');
const billingCountry = document.getElementById('billingcountry');

const singleRoom = document.getElementById('singleroom');
const doubleRoom = document.getElementById('doubleroom');
const familyRoom = document.getElementById('familyroom');
const suite = document.getElementById('suite')

const peopleInput = document.getElementById('people-input');

let hasError = false;

form.addEventListener('submit', e => {
    e.preventDefault();
    validateInputs(e);

    if (!hasError) {
        form.submit();
    }
    hasError = false
});


const setError = (element, message) => {
    const inputControl = element.parentElement;
    const errorDisplay = inputControl.querySelector('.error');

    errorDisplay.innerText = message;
    inputControl.classList.add('error');
    inputControl.classList.remove('success');
    hasError = true;
};

const setSuccess = element => {
    const inputControl = element.parentElement;
    const errorDisplay = inputControl.querySelector('.error');

    errorDisplay.innerText = '';
    inputControl.classList.add('success');
    inputControl.classList.remove('error');
};

function copyAddress() {
    // Get the checkbox and the home and billing address input fields
    const checkbox = document.getElementById('addressisbillingaddress');
    const homeStreet = document.getElementById('street');
    const homeHouseNumber = document.getElementById('houseNumber');
    const homeCity = document.getElementById('city');
    const homeZipCode = document.getElementById('zipCode');
    const homecountry = document.getElementById('country');
    const billingStreet = document.getElementById('billingstreet');
    const billingHouseNumber = document.getElementById('billinghouseNumber');
    const billingCity = document.getElementById('billingcity');
    const billingZipCode = document.getElementById('billingzipCode');
    const billingcountry = document.getElementById('billingcountry');

    if (checkbox.checked) {
        billingStreet.value = homeStreet.value;
        billingHouseNumber.value = homeHouseNumber.value;
        billingCity.value = homeCity.value;
        billingZipCode.value = homeZipCode.value;
        billingcountry.value = homecountry.value;
    } else {
        billingStreet.value = "";
        billingHouseNumber.value = "";
        billingCity.value = "";
        billingZipCode.value = "";
        billingcountry.value = "";
    }
}

function creditCardSelected() {
    const paymentMethod = document.getElementById('paymentmethod');
    const creditCardNumber = document.getElementById('creditcardnumber');
    const securityNumber = document.getElementById('securitynumber');
    const expirationDate = document.getElementById('expirationdate');

    if (paymentMethod.value === 'Bill') {
        creditCardNumber.disabled = true;
        securityNumber.disabled = true;
        expirationDate.disabled = true;
    } else {
        creditCardNumber.disabled = false;
        securityNumber.disabled = false;
        expirationDate.disabled = false;
    }
}


function checkAge() {
    const birthdate = document.getElementById("birthdate").value;

    if (!birthdate) {
        setError(document.getElementById("birthdate"), "Please enter a valid birthdate.");
        return;
    }


    const birthdateObject = new Date(birthdate);
    const currentDate = new Date();
    const age = currentDate.getFullYear() - birthdateObject.getFullYear();

    if (age < 18) {
        setError(document.getElementById("birthdate"), "Please enter a date of birth that is over 18 years old.");
    }
}


const validateInputs = () => {
    //trim() removes all the whitespaces that the string has
    const firstNameValue = firstName.value.trim();
    const lastNameValue = lastName.value.trim();
    const streetValue = street.value.trim();
    const houseNumberValue = houseNumber.value.trim();
    const cityValue = city.value.trim();
    const zipCodeValue = zipCode.value.trim();
    const countryValue = country.value.trim();
    const phoneNumberValue = phoneNumber.value.trim();
    const emailValue = email.value.trim();
    const creditCardValue = creditCard.value.trim();
    const securityNumberValue = securityNumber.value.trim();
    const expirationDateValue = expirationDate.value.trim();
    const billingStreetValue = billingStreet.value.trim();
    const billingCityValue = billingCity.value.trim();
    const billingPostalCodeValue = billingZipCode.value.trim();
    const billingHouseNumberValue = billingHouseNumber.value.trim();
    const billingCountryValue = billingCountry.value.trim();



//Nina
    /*
    const singleRoomInput = document.querySelector('#singleroom');
    const doubleRoomInput = document.querySelector('#doubleroom');
    const familyRoomInput = document.querySelector('#familyroom');
    const suiteInput = document.querySelector('#suite');

     */

    /*
    const singleRooms = Number(document.querySelector('#singleroom').value);
    const doubleRooms = Number(document.querySelector('#doubleroom').value);
    const familyRooms = Number(document.querySelector('#familyroom').value);
    const suites = Number(document.querySelector('#suite').value);

     */

    const singleRoomValue = singleRoom.value.trim();
    const doubleRoomValue = doubleRoom.value.trim();
    const familyRoomValue = familyRoom.value.trim();
    const suiteValue = suite.value.trim();
    const peopleInputValue = peopleInput.value.trim();

    //const paymentMethodSelect = document.querySelector('#paymentmethod');
    //const nationalitySelect = document.querySelector('#nationality');

    if (singleRoomValue === "0" && doubleRoomValue === "0" && familyRoomValue === "0" && suiteValue === "0") {
        setError(singleRoom, 'Choose at least one room!');
        setError(doubleRoom, 'Choose at least one room!');
        setError(familyRoom, 'Choose at least one room!');
        setError(suite, 'Choose at least one room!');
    } else {
        setSuccess(singleRoom);
        setSuccess(doubleRoom);
        setSuccess(familyRoom);
        setSuccess(suite);
    }

    if (peopleInputValue > 0 &&
        peopleInputValue - singleRoomValue - (doubleRoomValue * 2) - (familyRoomValue * 4) - (suiteValue * 4) <= 0) {
        setSuccess(peopleInput);
    } else {
        setError(peopleInput, 'Room size doesnt match with the amount of guests')
    }


//firstname
    if (firstNameValue === '') {
        setError(firstName, 'Required field');
    } else if (!firstNameValue.match(/^([ÄäÖöÜüßa-zA-Z -])*$/)) {
        setError(firstName, 'Incorrect input')
    } else {
        setSuccess(firstName);
    }

//lastname
    if (lastNameValue === '') {
        setError(lastName, 'Required field')
    } else if (!lastNameValue.match(/^([ÄäÖöÜüßa-zA-Z -])*$/)) {
        setError(lastName, 'Incorrect input')
    } else {
        setSuccess(lastName)
    }

//birthday




//Nationalität
    /*
    //Wenn --please select-- dann soll anderes ausgewählt werden NATIONALITY
    if (nationalitySelect.value === 'select') {
        // Wenn ja, rufe setError auf, um eine Fehlermeldung anzuzeigen
        setError(nationalitySelect, 'Please select a Nationality method');
    } else {
        // Wenn nicht, entferne die Klassen "error" und "success" vom Elternelement und füge die Klasse "success" hinzu
        inputControl.classList.remove('error');
        inputControl.classList.add('success');
    }*/


//street
    if (streetValue === '') {
        setError(street, 'Required field');
    } else if (!streetValue.match(/^([ÄäÖöÜüß0-9a-zA-Z. /-])*$/)) {
        setError(street, 'Incorrect input')
    } else {
        setSuccess(street);
    }

//housenumber
    if (houseNumberValue === '') {
        setError(houseNumber, 'Required field');
    } else if (!houseNumberValue.match(/^([a-zA-Z0-9_]){1,5}/)) {
        setError(houseNumber, 'Incorrect input')
    } else {
        setSuccess(houseNumber);
    }

//city
    if (cityValue === '') {
        setError(city, 'Required field');
    } else if (!cityValue.match(/^([ÄäÖöÜüßa-zA-Z. /-])*$/)) {
        setError(city, 'Incorrect input')
    } else {
        setSuccess(city);
    }
//zipcode
    if (zipCodeValue === '') {
        setError(zipCode, 'Required field');
    } else if (!zipCodeValue.match(/^([ÄäÖöÜüß0-9a-zA-Z]{3,6})$/)) {
        setError(zipCode, 'Incorrect input')
    } else {
        setSuccess(zipCode);
    }
//country
    if (countryValue === '') {
        setError(country, 'Required field');
    } else if (!countryValue.match(/^([öÖäÄüÜßa-zA-Z -])*$/)) {
        setError(country, 'Incorrect input')
    } else {
        setSuccess(country);
    }

//phonenumber
    if (phoneNumberValue === '') {
        setError(phoneNumber, 'Required field');
    } else if (!phoneNumberValue.match(/^[+]?[0-9 /]{7,16}/)) {
        setError(phoneNumber, 'Incorrect input')
    } else {
        setSuccess(phoneNumber);
    }


//email
    if (emailValue === '') {
        setError(email, 'Required field');
    } else if (!emailValue.match(/^(?=.{1,64}@)[A-Za-z0-9_-]+(\.[A-Za-z0-9_-]+)*@[^-][ÄäÖöÜüA-Za-z0-9-]+(\.[ÄäÖöÜüA-Za-z0-9-]+)*(\.[A-Za-z ]{2,})$/)) {
        setError(email, 'Incorrect input')
    } else {
        setSuccess(email);
    }

//Payment

/*
//Versuch 1
    var paymentMethod = document.getElementById("paymentmethod");
    var errorDiv = paymentMethod.nextElementSibling;

    if (paymentMethod.value === "select") {
        paymentMethod.style.borderColor = "red";
        errorDiv.innerHTML = "Bitte auswählen";
    } else {
        paymentMethod.style.borderColor = "";
        errorDiv.innerHTML = "";
    }
}
//Versuch 2
    //Wenn --please select-- dann soll anderes ausgewählt werden
    if (paymentMethodSelect.value === 'select') {
        // Wenn ja, rufe setError auf, um eine Fehlermeldung anzuzeigen
        setError(paymentMethodSelect, 'Please select a payment method');
    } else {
        // Wenn nicht, entferne die Klassen "error" und "success" vom Elternelement und füge die Klasse "success" hinzu
        inputControl.classList.remove('error');
        inputControl.classList.add('success');
    }*/

//kreditkartenummer
    if (creditCardValue === '') {
        setError(creditCard, 'Required field');
    } else if (!(creditCardValue.match(/(^[ ]{0,}[0-9]{4}[ ]{0,}[0-9]{4}[ ]{0,}[0-9]{4}[ ]{0,}[0-9]{4}[ ]{0,}$)/) ||
        creditCardValue.match(/(^[ ]{0,}[0-9]{14,16}[ ]{0,}$)/) ||
        creditCardValue.match(/(^[ ]{0,}[0-9]{4}[ ]{0,}[0-9]{6}[ ]{0,}[0-9]{4}[ ]{0,}$)/) ||
        creditCardValue.match(/(^[ ]{0,}[0-9]{4}[ ]{0,}[0-9]{6}[ ]{0,}[0-9]{5}[ ]{0,}$)/))) {
        setError(creditCard, 'Incorrect Input');
    } else {
        setSuccess(creditCard);
    }

//sicherheitsnummer
    if (securityNumberValue === '') {
        setError(securityNumber, 'Required field');
    } else if (!securityNumberValue.match(/^''{*}[0-9]{3,5}''{*}$/)) {
        setError(securityNumber, 'Incorrect input');
    } else {
        setSuccess(securityNumber);
    }

    if (!(fullBoard.checked === true ||
        halfBoard.checked === true ||
        justBreakfast.checked === true ||
        noPackage.checked === true)) {
        setError(board, 'Required field');
    } else {
        setSuccess(board);
    }

    if (nationality.value === 'select') {
        setError(nationality, 'Required field');
    } else {
        setSuccess(nationality);
    }


//ablaufdatum
    const today = new Date();
    const expirationDateString = new Date(Date.parse(expirationDateValue));

    const expire = document.getElementById('expirationdate').value;
    if (expire === '') {
        setError(expirationDate, 'Requiered field');
    } else if (!expire.match(/^(0[1-9]|1[0-2])\/[0-9]{2}$/)) {
        setError(expirationDate, 'wrong format');
    } else {
        // get current year and month
        const d = new Date();
        const currentYear = d.getFullYear();
        const currentMonth = d.getMonth() + 1;
        // get parts of the expiration date
        const parts = expire.split('/');
        const year = parseInt(parts[1], 10) + 2000;
        const month = parseInt(parts[0], 10);
        // compare the dates
        if (expirationDateString < today) {
            setError(expirationDate, 'expire date has passed');
        } else {
            setSuccess(expirationDate);
        }
    }


//billingstreet
    if (billingStreetValue === '') {
        setError(billingStreet, 'Required field');
    } else if (!billingStreetValue.match(/^([ÄäÖöÜüß0-9a-zA-Z. /-])*$/)) {
        setError(billingStreet, 'Incorrect input')
    } else {
        setSuccess(billingStreet);
    }

//billingcity
    if (billingCityValue === '') {
        setError(billingCity, 'Required field');
    } else if (!billingCityValue.match(/^([ÄäÖöÜüßa-zA-Z. /-])*$/)) {
        setError(billingCity, 'Incorrect input')
    } else {
        setSuccess(billingCity);
    }

//billingpostalcode
    if (billingPostalCodeValue === '') {
        setError(billingZipCode, 'Required field');
    } else if (!billingPostalCodeValue.match(/^([ÄäÖöÜüß0-9a-zA-Z]{3,6})$/)) {
        setError(billingZipCode, 'Incorrect input')
    } else {
        setSuccess(billingZipCode);
    }

//billingCountry
    if (billingCountryValue === '') {
        setError(billingCountry, 'Required field');
    } else if (!billingCountryValue.match(/^([öÖäÄüÜßa-zA-Z -])*$/)) {
        setError(billingCountry, 'Incorrect input')
    } else {
        setSuccess(billingCountry);
    }

    return hasError;
};