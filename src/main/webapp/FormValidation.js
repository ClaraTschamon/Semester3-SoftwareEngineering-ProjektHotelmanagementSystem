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

let hasError = false;

form.addEventListener('submit', e => {
    e.preventDefault();
    validateInputs();

    if(!hasError){
        form.submit();
    }
    hasError=false
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

const isValidEmail = emailValue => {
    const regex = new RegExp('^[a-zA-Z]([a-zA-Z_-][.]{0,1})*@[a-z0-9]([a-z0-9_-][.]{0,1}){2,62}[^.-]$');
    return regex.test(emailValue);
}

const isValidStreet = streetValue => {
    const regex = new RegExp('/^([a-zA-Z0-9 _/-]*$)/');
    return regex.test(streetValue);
    //cronst regex = ...
    //return regex.test(streetValue);
}

const isValidHouseNr = houseNumberValue => {
    const regex = new RegExp('/^([a-zA-Z0-9_]){1,5}/');
    return regex.test(houseNumberValue);
    //cronst regex = ...
    //return regex.test(houseNumberValue);
}

const isValidFirstName = firstName => {
    const regex = new RegExp('/^[a-zA-Z -]*$/');
    return regex.test(firstName);
}

const isValidLastName = lastName => {
    const regex = new RegExp('/^[a-zA-Z -]*$/');
    return regex.test(lastName);
}

const isValidCity = city => {
    const regex = new RegExp('^[A-Za-z- ]*');
    return regex.test(city);
}

const isValidZipCode = zipCode => {
    const regex = new RegExp('^[0-9a-zA-Z ]{3,6}');
    return regex.test(zipCode);
}

const isValidPhoneNumber = phoneNumber => {
    const regex = new RegExp('/^([+])?([0-9 ]){7,12}/');
    return regex.test(phoneNumber);
}

const isValidCountry = country => {
    const regex = new RegExp('^[A-Za-z]*$');
    return regex.test(country);
}

const validateInputs = () =>{
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

    //umändern in board radiobutton überprüfung
    /*
    if (!(genderMale.checked === true || genderFemale.checked === true || genderDivers.checked === true)) {
        setError(gender, 'Pflichtfeld')
    }else{
        setSuccess(gender)
    }*/

   if(!(fullBoard.checked === true ||
        halfBoard.checked === true ||
        justBreakfast.checked === true ||
        noPackage.checked === true)) {
       setError(board, 'Required field');
   } else {
       setSuccess(board);
   }

    if(nationality.value === 'select') {
        setError(nationality, 'Required field');
    } else {
        setSuccess(nationality);
    }

    if(firstNameValue === ''){
        setError(firstName, 'Required field');
    } else if(!firstNameValue.match(/^([a-zA-Z -])*$/)) {
        setError(firstName, 'Incorrect input')
    } else {
        setSuccess(firstName);
    }

    if(lastNameValue === ''){
        setError(lastName, 'Required field')
    } else if(!lastNameValue.match(/^([a-zA-Z -])*$/)) {
        setError(lastName, 'Incorrect input')
    } else{
        setSuccess(lastName)
    }

    if(nationality.value === 'select'){
        setError(country, 'Please select your Nationality')
    }else{
        setSuccess(country)
    }

    if(streetValue === ''){
        setError(street, 'Required field');
    } else if(!streetValue.match(/^([0-9a-zA-Z /-])*$/)) {
        setError(street, 'Incorrect input')
    } else{
        setSuccess(street);
    }

    if (cityValue === '') {
        setError (city, 'Required field');
    } else if(!cityValue.match(/^([a-zA-Z /-])*$/)) {
        setError(city, 'Incorrect input')
    } else {
        setSuccess (city);
    }

    if (zipCodeValue === '') {
        setError (zipCode, 'Required field');
    } else if(!zipCodeValue.match(/^([0-9a-zA-Z]{3,6})$/)) {
        setError(zipCode, 'Incorrect input')
    } else {
        setSuccess (zipCode);
    }

    if (countryValue === '') {
        setError (country, 'Required field');
    } else if(!countryValue.match(/^([a-zA-ZöÖäÄüÜß -])*$/)) {
        setError(country, 'Incorrect input')
    } else {
        setSuccess(country);
    }

    if (phoneNumberValue === '') {
        setError (phoneNumber, 'Required field');
    } else if(!phoneNumberValue.match(/^[+]?[0-9 /]{7,15}/)) {
        setError(phoneNumber, 'Incorrect input')
    } else {
        setSuccess (phoneNumber);
    }

    if (houseNumberValue === '') {
        setError(houseNumber, 'Required field');
    } else if(!phoneNumberValue.match(/^([a-zA-Z0-9_]){1,5}/)) {
        setError(phoneNumber, 'Incorrect input')
    } else {
        setSuccess (houseNumber);
    }

    if(emailValue === ''){
        setError(email, 'Required field');
    } else if(!emailValue.match(/^(?=.{1,64}@)[A-Za-z0-9_-]+(\.[A-Za-z0-9_-]+)*@[^-][ÄäÖöÜüA-Za-z0-9-]+(\.[ÄäÖöÜüA-Za-z0-9-]+)*(\.[A-Za-z ]{2,})$/)) {
        setError(email, 'Incorrect input')
    } else{
        setSuccess(email);
    }


    /*
    if(password1Value === ''){
        setError(password1, 'Pflichtfeld')
    }
    if(password2Value === ''){
        setError(password2, 'Pflichtfeld')
    }else if(password1Value !== password2Value){
        setError(password1,'Passwörter stimmen nicht überein')
        setError(password2, 'Passwörter stimmen nicht überein')
    }else if(!isValidPassword(password1Value)){
        setError(password2, 'Muss zwischen 6 und 11 Zeichen lang sein und mit\n' +
            'einem Buchstaben anfangen und darf nur Buchstaben, Ziffern und “_“, keine\n' +
            'Leer- oder Sonderzeichen enthalten')
    }else{
        setSuccess(password1)
        setSuccess(password2)
    }*/

    /*
    //ich lasse code hier. das war mehrere medientypen auswählen.
    //brauchen wir vielleicht bei zimmerauswahl
    let values = [];
    mediatype.forEach((checkbox) => {
        if(checkbox.checked === true){
            values.push(checkbox.value);
        }
    });

    if(values.length < 1){
        setError(mediatype, 'Bitte Medientyp(en) auswählen')
    }else{
        setSuccess(mediatype)
    }*/

    /*
    if (!(categoryCustomer.checked === true || categoryEmployee.checked === true || categoryAdmin.checked === true)) {
        setError(userCategory, 'Pflichtfeld')
    }else{
        setSuccess(userCategory)
    }*/



    return hasError;
};