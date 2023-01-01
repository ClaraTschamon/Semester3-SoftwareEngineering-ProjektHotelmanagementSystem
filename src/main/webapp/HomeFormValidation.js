const form = document.getElementById('form');

const arrivalDate = document.getElementById('arrivalDate');
const departureDate = document.getElementById('departureDate');

let hasError = false;

form.addEventListener('submit', e => {
    e.preventDefault();
    validateInputs(e);

    if (!hasError) {
        form.submit();
    }
    hasError = false;
});

const validateInputs = () => {

    const arrivalDateEntered = new Date(arrivalDate.value).getTime();
    const departureDateEntered = new Date(departureDate.value).getTime();
    const dateNow = new Date().getTime();

    if(!(arrivalDateEntered < dateNow)) {
        hasError = true;
        alert("Arrival date can not be before today!")
    } else if  (arrivalDateEntered >= departureDateEntered) {
        hasError = true;
        alert("Arrival date has to be before the departure date!");
    } else {
        hasError = false;
    }
}