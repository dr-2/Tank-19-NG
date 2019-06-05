'use strict'

console.log("javascript caricato correttamente");

let username;
let stompClient;
let mainDiv = document.querySelector('#main-div');

function inviaMessaggio(e) {
    console.log("inviaMessaggio invocata correttamente");
    connect(e);
}


function connect(event) {
    username = "Carlo"; //document.querySelector('#name').value.trim();

    if (username) {
        //usernamePage.classList.add('hidden');
        //chatPage.classList.remove('hidden');

        var socket = new SockJS('/ws');
        stompClient = Stomp.over(socket);

        stompClient.connect({}, onConnected, onError);
    }
    event.preventDefault();
}

function onConnected() {
    // Subscribe to the Public Topic
    //stompClient.subscribe('/partita/public', onMessageReceived);
    //stompClient.subscribe('/topic/' + username, onMessageReceived);

    // Tell your username to the server
    stompClient.send("/app/partita.connessioneGiocatore",
        {},
        JSON.stringify({sender: username, type: 'CONNESSIONE'})
    );

    //connectingElement.classList.add('hidden');
}


function onError(error) {
    connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    connectingElement.style.color = 'red';
}

mainDiv.addEventListener('click', inviaMessaggio, true);