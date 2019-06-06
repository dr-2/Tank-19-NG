'use strict'

console.log("javascript caricato correttamente");


let loginForm = document.querySelector('loginForm')



loginForm.addEventListener(onsubmit, autenticate)


function onError(error) {
    alert('Could not connect to WebSocket server. Please refresh this page to try again!');
    //connectingElement.style.color = 'red';
}
