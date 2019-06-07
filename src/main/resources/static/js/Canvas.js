'use strict'

console.log("javascript caricato correttamente");

let username;
let stompClient;
let mainDiv = document.querySelector('#main-div');
let bottoneConnessione = document.querySelector('#bottone-connessione');
let bottoneInvio = document.querySelector('#bottone-invio');


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

function onStateUpgradeReceived(payload) {
    let message = JSON.parse(payload.body);
    let content = JSON.parse(message.content);
    console.log("AAA -> " + payload + " <-- " + message);
    console.log(message)
    console.log(content)

    let data = content;
    drawPoint(data);
}

function onConnected() {
    // Subscribe to the Public Topic
    //stompClient.subscribe('/partita/public', onMessageReceived);
    //stompClient.subscribe('/topic/' + username, onMessageReceived);
    stompClient.subscribe('/partita/public', onStateUpgradeReceived);


    // Tell your username to the server
    stompClient.send("/app/partita.connessioneGiocatore",
        {},
        JSON.stringify({sender: username, tipoMessaggio: 'CONNESSIONE'})
    );


    //connectingElement.classList.add('hidden');
}

function inviaMessaggio(m) {
    stompClient.send("/app/partita.inviaComando",
        {},
        JSON.stringify({sender: username, tipoMessaggio: 'COMANDO', content: m})
    );
}


function onError(error) {
    alert('Could not connect to WebSocket server. Please refresh this page to try again!');
    //connectingElement.style.color = 'red';
}

bottoneConnessione.addEventListener('click', connect, true);
bottoneInvio.addEventListener('click', inviaMessaggio, true);


let myColor = {
    r: 200,
    g: 50,
    b: 22
};


function setup() {
    createCanvas(800, 600);
    background(51);
}

function draw() {

}

function mouseDragged() {
    //console.log("Sending: " + mouseX + "," + mouseY);

    let data = {
        x: mouseX,
        y: mouseY,
        color: myColor
    };

    console.log(data);

    drawPoint(data);
    sendPoint(data);
}

function drawPoint(data) {
    noStroke();
    fill(data.color.r, data.color.g, data.color.b);
    ellipse(data.x, data.y, 36, 36);
}

function sendPoint(data) {
    let a = JSON.stringify(data);
    inviaMessaggio(a);
}