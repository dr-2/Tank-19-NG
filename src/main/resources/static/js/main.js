const gameState = {
    tanks: {}
};
const command = {
    nord: false,
    sud: false,
    ovest: false,
    est: false,
    fuoco: false
};
const gameConfig = {
    tank: {
        velocita: null
    },
    canvas: {
        altezza: null,
        larghezza: null
    }
}

let username;
let stompClient;

const idPartita = 1;
let idOggettoControllato = 1;


function preload() {
    httpGet("/configurazioni/canvas/altezza", 'text', false, (response) => {
        gameConfig.canvas.larghezza = response;
        resizeCanvas(gameConfig.canvas.larghezza, gameConfig.canvas.altezza);
    }, () => {
        alert("Errore critico di configurazione. La pagina verrà ricaricata");
        location.reload();
    });
    httpGet("/configurazioni/canvas/larghezza", 'text', false, (response) => {
        gameConfig.canvas.altezza = response;
        resizeCanvas(gameConfig.canvas.larghezza, gameConfig.canvas.altezza);
    }, () => {
        alert("Errore critico di configurazione. La pagina verrà ricaricata");
        location.reload();
    });
    httpGet("/configurazioni/tank/velocita", 'text', false, (response) => {
        gameConfig.tank.velocita = response;
        resizeCanvas(gameConfig.canvas.larghezza, gameConfig.canvas.altezza);
    }, () => {
        alert("Errore critico di configurazione. La pagina verrà ricaricata");
        location.reload();
    });
}

function setup() {
    createCanvas(10, 10);
    background(51);
}

function draw() {
    background(51);
    for (const id of Object.keys(gameState.tanks)) {
        gameState.tanks[id].draw();
    }

}

const keyDownHandler = (e) => {
    if (e.keyCode === 38) {
        e.preventDefault();
        command.nord = true;
    }
    if (e.keyCode === 40) {
        e.preventDefault();
        command.sud = true;
    }
    if (e.keyCode === 39) {
        e.preventDefault();
        command.est = true;
    }
    if (e.keyCode === 37) {
        e.preventDefault();
        command.ovest = true;
    }
    if (e.keyCode === 70) {
        e.preventDefault();
        command.fuoco = true;
    }
}

const keyUpHandler = (e) => {
    if (e.keyCode === 38) {
        e.preventDefault();
        command.nord = false;
    }
    if (e.keyCode === 40) {
        e.preventDefault();
        command.sud = false;
    }
    if (e.keyCode === 39) {
        e.preventDefault();
        command.est = false;
    }
    if (e.keyCode === 37) {
        e.preventDefault();
        command.ovest = false;
    }
    if (e.keyCode === 70) {
        e.preventDefault();
        command.fuoco = false;
    }

    if (e.keyCode === 37 || e.keyCode === 39 || e.keyCode === 40 || e.keyCode === 38 || e.keyCode === 70) {

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

    setInterval(() => {
        JSON.stringify(command);
        stompClient.send("/app/partite/" + idPartita + "/.inviaComando",
            {},
            JSON.stringify({
                sender: username,
                tipoMessaggio: 'COMANDO',
                content: "prova di sto campo..",
                idPartita: idPartita,
                idOggetto: idOggettoControllato, //TODO: parametrizzare questo magic numb
                nord: command.nord, sud: command.sud, est: command.est, ovest: command.ovest, fuoco: command.fuoco
            })
        );
    }, 1000 / 60);

}

const onConnected = () => {
    stompClient.subscribe('/partite/' + idPartita + '/stato', onStateUpgradeReceived);

    // Tell your username to the server
    stompClient.send("/app/partite/" + idPartita + "/.connessioneGiocatore",
        {},
        JSON.stringify({sender: username, tipoMessaggio: 'CONNESSIONE'})
    );
};

const onError = () => {
    alert('Could not connect to WebSocket server. Please refresh this page to try again!');

};

const onStateUpgradeReceived = (message) => {
    parsedData = JSON.parse(message.body);
    gameState.tanks[parsedData.idOggetto] = new Tank(parsedData.posx, parsedData.posy, 250);

}

const cambiaGiocatoere = () => {
    if (idOggettoControllato === 1) {
        idOggettoControllato = 3;
    } else {
        idOggettoControllato = 1;
    }
}


document.addEventListener('keydown', keyDownHandler)
document.addEventListener('keyup', keyUpHandler)

document.getElementById("bottone-connessione").addEventListener("click", handleBottoneConnessione);
document.getElementById('bottone-diventa-Player2').addEventListener("click", cambiaGiocatoere)

