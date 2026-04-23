const display = document.querySelector(".display");
let currentExpression = '0';

function updateDisplay() {
    display.textContent = currentExpression || '0';
}

function appendToDisplay(value){
    if(currentExpression === '0' && value !== '.' ){
        currentExpression = '';
    }

    if (value === '.' && currentExpression.includes('.')) return;

    currentExpression += value;
    updateDisplay();
}

function clearDisplay(){
    currentExpression = '0';
    updateDisplay();
}
function calculate() {
    try {
        currentExpression = eval(currentExpression.replace(/x/g, '*')).toString();
        updateDisplay();
    } catch (error) {
        currentExpression = 'Error'
        updateDisplay();
        setTimeout(clearDisplay, 1000);
    }
}