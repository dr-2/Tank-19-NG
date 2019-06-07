const gameState = {
    tanks: {}
};
const command = {
    up: false,
    down: false,
    left: false,
    right: false
};

let username;
let stompClient;

function setup() {
    createCanvas(800, 600);
    background(51);
}

function draw() {
    //background(51)
}

const keyDownHandler = (e) => {
    if (e.keyCode === 38) {
        e.preventDefault();
        command.up = false;
    }
    if (e.keyCode === 40) {
        e.preventDefault();
        command.down = false;
    }
    if (e.keyCode === 39) {
        e.preventDefault();
        command.right = false;
    }
    if (e.keyCode === 37) {
        e.preventDefault();
        command.left = false;
    }

    if (e.keyCode === 37 || e.keyCode === 39 || e.keyCode === 40 || e.keyCode === 38) {
        console.log(command);
    }
}

const handleBottoneConnessione = () => {
    username = "Carlo";

    if (username) {
        let socket = new SockJS("/ws");
        stompClient = Stomp.over(socket);
        stompClient.connect({}, onConnected, onError);
        //stompClient.debug = null; TODO: disable debug on STOMP client
    }

}

const onConnected = () => {
    stompClient.subscribe('/partita/public', onStateUpgradeReceived);

    // Tell your username to the server
    stompClient.send("/app/partita.connessioneGiocatore",
        {},
        JSON.stringify({sender: username, tipoMessaggio: 'CONNESSIONE'})
    );
};

const onError = () => {
    alert('Could not connect to WebSocket server. Please refresh this page to try again!');

};

const onStateUpgradeReceived = (message) => {
    gameState.tanks[0] = new Tank(100, 100, 250);
    gameState.tanks[0].draw();
    console.log(message);
}


document.addEventListener('keydown', keyDownHandler)
//document.addEventListener('keyup', keyUpHandler)

document.getElementById("bottone-connessione").addEventListener("click", handleBottoneConnessione);

