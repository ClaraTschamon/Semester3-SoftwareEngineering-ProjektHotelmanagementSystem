const form = document.getElementById('form');

const board = document.getElementById('board');
const fullBoard = document.getElementById('fullBoard');
const halfBoard = document.getElementById('halfBoard');
const justBreakfast = document.getElementById('justBreakfast');
const noPackage = document.getElementById('noPackage');


const firstName = document.getElementById('firstName');
const lastName = document.getElementById('lastName');
/*let nationality = document.querySelectorAll('input[name="Nationality"]');*/
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

let hasError = false;

form.addEventListener('submit', e => {
    e.preventDefault();
    validateInputs();

    if (!hasError) {
        form.submit();
    }
    hasError = false
});

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
    const nationalityValue = nationality.value;

    // const arrivalDateValue = arrivalDate.value.trim();
    const singleRoomValue = singleRoom.value.trim();
    const doubleRoomValue = doubleRoom.valueOf();
    const familyRoomValue = familyRoom.valueOf();
    const suiteRoomValue = suiteRoom.valueOf();
    const creditCardValue = creditCard.value.trim();
    const securityNumberValue = securityNumber.value.trim();
    const expirationDateValue = expirationDate.value.trim();
    const billingStreetValue = billingStreet.value.trim();
    const billingCityValue = billingCity.value.trim();
    const billingHouseNumberValue = billingHouseNumber.value.trim();
    const billingPostalCodeValue = billingZipCode.value.trim();
    const billingCountryValue = billingCountry.value.trim();


    // var arrivalDateEntered = new Date(arrivalDate.value).getDate();
    // var departureDateEntered = new Date(departureDate.value).getDate();
    var dateNow = new Date().getDate();

    // if(arrivalDate.value === ""){
    //     setError(arrivalDate, 'Required field');
    // } else if(!(arrivalDateEntered >= dateNow)){
    //     setError(arrivalDate, 'Arrival date can not be before today!')
    // } else if(arrivalDateEntered > departureDateEntered) {
    //     setError(arrivalDate, 'Arrival date has to be before the departure date!')
    // } else if(arrivalDateEntered === departureDateEntered) {
    //     setError(arrivalDate, 'Arrival date and departure date can not be the same!')
    // }
    // else{
    //     setSuccess(arrivalDate);
    // }

    // if(departureDate.value === ""){
    //     setError(departureDate, 'Required field');
    // } else {
    //     setSuccess(departureDate);
    // }

    const singleRoomInput = document.querySelector('#singleroom');
    const doubleRoomInput = document.querySelector('#doubleroom');
    const familyRoomInput = document.querySelector('#familyroom');
    const suiteInput = document.querySelector('#suite');

    if (singleRoomInput.value === "0" || doubleRoomInput.value === "0" || familyRoomInput.value === "0" || suiteInput.value === "0") {
        setError(singleRoom, 'Choose at least one room!');
        setError(doubleRoom, 'Choose at least one room!');
        setError(familyRoom, 'Choose at least one room!');
        setError(suiteRoom, 'Choose at least one room!');
        event.preventDefault();
    } else {
        setSuccess(singleRoom);
        setSuccess(doubleRoom);
        setSuccess(familyRoom);
        setSuccess(suiteRoom);
    }

    const guestsInput = document.querySelector('#guests');

    const singleRooms = Number(document.querySelector('#singleroom').value);
    const doubleRooms = Number(document.querySelector('#doubleroom').value);
    const familyRooms = Number(document.querySelector('#familyroom').value);
    const suites = Number(document.querySelector('#suite').value);

    const maxCapacity = singleRooms * 1 + doubleRooms * 2 + familyRooms * 4 + suites * 4;

    if (Number(guestsInput.value) > maxCapacity) {
        guestsInput.classList.add('invalid');
        document.querySelector('.error').innerHTML = 'Number of guests exceeds maximum capacity of selected rooms';
        event.preventDefault();
    } else {
        guestsInput.classList.remove('invalid');
    }


    //validierung für ablaufdatum
    const today = new Date();
    const expirationDateString = new Date(Date.parse(expirationDateValue));

    var expire = document.getElementById('expirationdate').value;
    if (expire === '') {
        setError(expirationDate, 'Requiered field');
    } else if (!expire.match(/^(0[1-9]|1[0-2])\/[0-9]{2}$/)) {
        setError(expirationDate, 'wrong format');
    } else {
        // get current year and month
        var d = new Date();
        var currentYear = d.getFullYear();
        var currentMonth = d.getMonth() + 1;
        // get parts of the expiration date
        var parts = expire.split('/');
        var year = parseInt(parts[1], 10) + 2000;
        var month = parseInt(parts[0], 10);
        // compare the dates
        if (expirationDateString < today) {
            setError(expirationDate, 'expire date has passed');
        } else {
            setSuccess(expirationDate);
        }
    }

//kreditkarte
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
    } else if (!securityNumberValue.match(/^[ ]{0,}[0-9]{3,5}[ ]{0,}$/)) {
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


    // if (creditCardValue === '') {
    //     setError(creditCard, 'Required field');
    // } else if (!creditCardValue.match(/^([0-9]{4}[ ][0-9]{4}[ ][0-9]{4}[ ][0-9]{4}[ ]{0,})/) ||
    //     !creditCardValue.match(/^([0-9]{4}[ ][0-9]{6}[ ][0-9]{4}[ ]{0,})/) ||
    //     !creditCardValue.match(/^([0-9]{14,16}[ ]{0,})/) ||
    //     !creditCardValue.match(/^([0-9]{4}[ ][0-9]{6}[ ][0-9]{5}[ ]{0,})/)) {
    //     setError(creditCard, 'Incorrect Input');
    // } else {
    //     setSuccess(creditCard);
    // }
    //
    // if (securityNumberValue === '') {
    //     setError(securityNumber, 'Required field');
    // } else if (!securityNumberValue.match(/^([ ]{0,}[0-9 ]{3,5}[ ]{0,})/)) {
    //     setError(securityNumber, 'Incorrect input');
    // } else {
    //     setSuccess(securityNumber);
    // }

    // if (expirationDateValue === '') {
    //     setError(expirationDate, 'Required field')
    // } else if (!expirationDateValue.match(/^([ ]{0,}[0-9 ]{3,5}[ ]{0,})/)) {
    //     setError(expirationDate, 'Incorrect input');
    //} else if ()
// continue hereeeeeeeeeeeeeeeeeeeeee
    if (firstNameValue === '') {
        setError(firstName, 'Required field');
    } else if (!firstNameValue.match(/^([ÄäÖöÜüßa-zA-Z -])*$/)) {
        setError(firstName, 'Incorrect input')
    } else {
        setSuccess(firstName);
    }

    if (lastNameValue === '') {
        setError(lastName, 'Required field')
    } else if (!lastNameValue.match(/^([ÄäÖöÜüßa-zA-Z -])*$/)) {
        setError(lastName, 'Incorrect input')
    } else {
        setSuccess(lastName)
    }

    if (nationality.value === 'select') {
        setError(nationality, 'Please select your Nationality')
    } else {
        setSuccess(nationality)
    }

    if (streetValue === '') {
        setError(street, 'Required field');
    } else if (!streetValue.match(/^([ÄäÖöÜüß0-9a-zA-Z. /-])*$/)) {
        setError(street, 'Incorrect input')
    } else {
        setSuccess(street);
    }

    if (cityValue === '') {
        setError(city, 'Required field');
    } else if (!cityValue.match(/^([ÄäÖöÜüßa-zA-Z. /-])*$/)) {
        setError(city, 'Incorrect input')
    } else {
        setSuccess(city);
    }

    if (zipCodeValue === '') {
        setError(zipCode, 'Required field');
    } else if (!zipCodeValue.match(/^([ÄäÖöÜüß0-9a-zA-Z]{3,6})$/)) {
        setError(zipCode, 'Incorrect input')
    } else {
        setSuccess(zipCode);
    }

    if (countryValue === '') {
        setError(country, 'Required field');
    } else if (!countryValue.match(/^([öÖäÄüÜßa-zA-Z -])*$/)) {
        setError(country, 'Incorrect input')
    } else {
        setSuccess(country);
    }

    if (billingStreetValue === '') {
        setError(billingStreet, 'Required field');
    } else if (!billingStreetValue.match(/^([ÄäÖöÜüß0-9a-zA-Z. /-])*$/)) {
        setError(billingStreet, 'Incorrect input')
    } else {
        setSuccess(billingStreet);
    }

    if (billingCityValue === '') {
        setError(billingCity, 'Required field');
    } else if (!billingCityValue.match(/^([ÄäÖöÜüßa-zA-Z. /-])*$/)) {
        setError(billingCity, 'Incorrect input')
    } else {
        setSuccess(billingCity);
    }

    if (billingPostalCodeValue === '') {
        setError(billingZipCode, 'Required field');
    } else if (!billingPostalCodeValue.match(/^([ÄäÖöÜüß0-9a-zA-Z]{3,6})$/)) {
        setError(billingZipCode, 'Incorrect input')
    } else {
        setSuccess(billingZipCode);
    }

    if (billingCountryValue === '') {
        setError(billingCountry, 'Required field');
    } else if (!billingCountryValue.match(/^([öÖäÄüÜßa-zA-Z -])*$/)) {
        setError(billingCountry, 'Incorrect input')
    } else {
        setSuccess(billingCountry);
    }


    if (phoneNumberValue === '') {
        setError(phoneNumber, 'Required field');
    } else if (!phoneNumberValue.match(/^[+]?[0-9 /]{7,16}/)) {
        setError(phoneNumber, 'Incorrect input')
    } else {
        setSuccess(phoneNumber);
    }

    if (houseNumberValue === '') {
        setError(houseNumber, 'Required field');
    } else if (!houseNumberValue.match(/^([a-zA-Z0-9_]){1,5}/)) {
        setError(houseNumber, 'Incorrect input')
    } else {
        setSuccess(houseNumber);
    }

    if (emailValue === '') {
        setError(email, 'Required field');
    } else if (!emailValue.match(/^(?=.{1,64}@)[A-Za-z0-9_-]+(\.[A-Za-z0-9_-]+)*@[^-][ÄäÖöÜüA-Za-z0-9-]+(\.[ÄäÖöÜüA-Za-z0-9-]+)*(\.[A-Za-z ]{2,})$/)) {
        setError(email, 'Incorrect input')
    } else {
        setSuccess(email);
    }

    return hasError;
};