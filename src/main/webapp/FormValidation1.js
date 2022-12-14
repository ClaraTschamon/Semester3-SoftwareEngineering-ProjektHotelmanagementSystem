const form = document.getElementById('index');

const arrivalDate = document.getElementById('arrivalDate');
const departureDate = document.getElementById('departureDate');
const numberOfGuest = document.getElementById('people-input');
// const arrivalDate = document.querySelector('input[type="date"]').getDate;
// const dateControl = document.querySelector('input[type="date"]');
// const numberOfGuests = document.getElementById('people-input');
// const arrivalDate1 = document.querySelector('.arrivaldatefield');

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

const validateInputs = () => {

    //const today = new Date();
    const inputDateArrival = arrivalDate.value;
    const inputDateDeparture = departureDate.value;
    const inputNumberofGuests = numberOfGuest.value;

    var arrivalDateValue = document.getElementById('arrivalDate').value;
    var departureDateValue = document.getElementById('departureDate').value;
    var inpDateArrival = new Date(arrivalDateValue);
    var inpDateDeparture = new Date(departureDateValue);
    var currDate = new Date();

    if(currDate.toDateString() > inpDateArrival.toDateString())
    {
        document.write("the date is correct");
        document.write(inpDateArrival);
        //setSuccess(arrivalDate);
    }
    else {
        document.write("date is incorrect");
        //setError(arrivalDate, 'Invalid date');
    }

    if(inpDateArrival.toDateString() > inpDateDeparture.toDateString())
    {
        document.write("ABREISE GUT");
        document.write(inpDateDeparture)
    }
    else {
        document.write("ABREISE SCHLECHT");
        document.write(inputDateArrival);
    }

    return hasError;
};
