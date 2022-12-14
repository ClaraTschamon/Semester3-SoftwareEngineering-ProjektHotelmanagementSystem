const form = document.getElementById('form');

const arrivalDate = document.getElementById('arrivalDate');
const departureDate = document.getElementById('departureDate');

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
const billingStreet= document.getElementById('billingstreet');
const billingHouseNumber = document.getElementById('billinghouseNumber');
const billingCity = document.getElementById('billingcity');
const billingZipCode = document.getElementById('billingzipCode');

const arrivalDate = document.getElementById('arrivalDate');
//const arrivalDate = document.getElementsByName('arrivalDate');

const departureDate = document.getElementById('departureDate');
const numberOfGuests = document.getElementById('people-input');
const singleRoom = document.getElementById('singleroom');
const doubleRoom = document.getElementById('doubleroom');
const familyRoom = document.getElementById('familyroom');
const suiteRoom = document.getElementById('suite');
const dateOfBirth = document.getElementById('birthdate');


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
    const billingcountry=document.getElementById('billingcountry');

    if (checkbox.checked) {
        billingStreet.value = homeStreet.value;
        billingHouseNumber.value = homeHouseNumber.value;
        billingCity.value = homeCity.value;
        billingZipCode.value = homeZipCode.value;
        billingcountry.value = homecountry.value;
    } else {
        billingStreet.value="";
        billingHouseNumber.value="";
        billingCity.value="";
        billingZipCode.value="";
        billingcountry.value="";
    }
}

function creditCardSelected(){
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
    const arrivalDateValue = arrivalDate.valueOf();


    // document.write(arrivalDateValue.valueOf());
    // document.write("abstand erste");
    // document.write(arrivalDate.valueOf());
    // document.write("Abstand hier")
    // //document.write(firstName);
    // document.write(firstNameValue);


    // var userdate = new Date(document.getElementById("arrivalDate").value).toJSON().slice(0,10);
    // var today = new Date().toJSON().slice(0,10);
    // if(arrivalDateValue < today){
    //     setError(arrivalDate, 'Required field');
    // } else {
    //     setSuccess(arrivalDate);
    // }

    // if(new Date(arrivalDate).getDate() < todayDate.getDate()) {
    //     setError(arrivalDate, 'Required field');
    // } else {
    //     setSuccess(arrivalDate);
    // }

    // if(departureDate < currentDate) {
    //     setError(departureDate, 'Required field');
    // } else {
    //     setSuccess(departureDate);
    // }
    var arrivalDateEntered = new Date(arrivalDate.value).getDate();
    var departureDateEntered = new Date(departureDate.value).getDate();
    var dateNow = new Date().getDate();

    if(arrivalDate.value === ""){
        setError(arrivalDate, 'Required field');
    } else if(!(arrivalDateEntered >= dateNow)){
        setError(arrivalDate, 'Arrival date can not be before today!')
    } else if(arrivalDateEntered > departureDateEntered) {
        setError(arrivalDate, 'Arrival date has to be before the departure date!')
    } else if(arrivalDateEntered === departureDateEntered) {
        setError(arrivalDate, 'Arrival date and departure date can not be the same!')
    }
    else{
        setSuccess(arrivalDate);
    }

    if(departureDate.value === ""){
        setError(departureDate, 'Required field');
    } else {
        setSuccess(departureDate);
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

    if (firstNameValue === '') {
        setError(firstName, 'Required field');
    } else if(!firstNameValue.match(/^([ÄäÖöÜüßa-zA-Z -])*$/)) {
        setError(firstName, 'Incorrect input')
    } else {
        setSuccess(firstName);
    }

    if (lastNameValue === '') {
        setError(lastName, 'Required field')
    } else if(!lastNameValue.match(/^([ÄäÖöÜüßa-zA-Z -])*$/)) {
        setError(lastName, 'Incorrect input')
    } else {
        setSuccess(lastName)
    }

    if(nationality.value === 'select'){
        setError(nationality, 'Please select your Nationality')
    }else{
        setSuccess(nationality)
    }

    if (streetValue === '') {
        setError(street, 'Required field');
    } else if(!streetValue.match(/^([ÄäÖöÜüß0-9a-zA-Z. /-])*$/)) {
        setError(street, 'Incorrect input')
    } else {
        setSuccess(street);
    }

    if (cityValue === '') {
        setError (city, 'Required field');
    } else if(!cityValue.match(/^([ÄäÖöÜüßa-zA-Z. /-])*$/)) {
        setError(city, 'Incorrect input')
    } else {
        setSuccess(city);
    }

    if (zipCodeValue === '') {
        setError (zipCode, 'Required field');
    } else if(!zipCodeValue.match(/^([ÄäÖöÜüß0-9a-zA-Z]{3,6})$/)) {
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

