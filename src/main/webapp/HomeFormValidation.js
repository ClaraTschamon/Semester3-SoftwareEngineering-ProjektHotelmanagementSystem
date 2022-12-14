const form = document.getElementById('form');

const arrivalDate = document.getElementById('arrivalDate');
const departureDate = document.getElementById('departureDate');

let hasError = false;

form.addEventListener('submit', e => {
    e.preventDefault();
    validateInputs();

    if (!hasError) {
        form.submit();
    }
    hasError = false
});

const validateInputs = () => {
    var arrivalDateEntered = new Date(arrivalDate.value).getDate();
    var departureDateEntered = new Date(departureDate.value).getDate();
    var dateNow = new Date().getDate();
    if(!(arrivalDateEntered >= dateNow)) {
        hasError = true;
        alert("Arrival date can not be before today!")
    } else if (arrivalDateEntered > departureDateEntered) {
        hasError = true;
        alert("Arrival date has to be before the departure date!");
    } else if(arrivalDateEntered === departureDateEntered) {
        hasError = true;
        alert("Arrival date and departure date can not be the same!");
    }
    else {
        hasError = false;
    }
}