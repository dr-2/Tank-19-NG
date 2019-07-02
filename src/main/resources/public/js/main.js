const gameState = {
    tanks: {},
    proiettili: {},
    muretti: {}
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
        myTank_n: null,
        myTank_s: null,
        myTank_e: null,
        myTank_o: null
    },
    proiettile: {
        proiettile_n: null
    },
    muretto: {
        muretto_n: null
    },
    canvas: {
        altezza: null,
        larghezza: null
    },
    userInfo: {
        username: null
    },
    partita: {
        idPartita: null,
        idOggettoControllato: null
    },
    communication: {
        stompClient: null
    }
}

const PROIETTILE = "PROIETTILE"
const CARRO_ARMATO = "CARRO_ARMATO"
const MURETTO = "MURETTO"


let haveEvents = 'ongamepadconnected' in window;
let controllers = {};


function preload() {
    httpGet("/configurazioni/getPartitaId", 'text', false, (response) => {
        gameConfig.partita.idPartita = parseInt(response);
    }, () => {
        console.error("A questo giocatore non è stata ancora assegnata una partita")
    });
    httpGet("/configurazioni/getMioTankId", 'text', false, (response) => {
        gameConfig.partita.idOggettoControllato = parseInt(response);
    }, () => {
        console.error("questo Giocatore non ha un tank")
    });

    httpGet("/configurazioni/canvas/altezza", 'text', false, (response) => {
        gameConfig.canvas.altezza = parseInt(response);
        resizeCanvas(gameConfig.canvas.larghezza, gameConfig.canvas.altezza);
    }, () => {
        alert("Errore critico di configurazione. La pagina verrà ricaricata");
        location.reload();
    });
    httpGet("/configurazioni/canvas/larghezza", 'text', false, (response) => {
        gameConfig.canvas.larghezza = parseInt(response);
        resizeCanvas(gameConfig.canvas.larghezza, gameConfig.canvas.altezza);
    }, () => {
        alert("Errore critico di configurazione. La pagina verrà ricaricata");
        location.reload();
    });
    httpGet("/configurazioni/userinfo/username", 'text', false, (response) => {
        gameConfig.userInfo.username = response;
    }, () => {
        alert("Errore critico di configurazione. La pagina verrà ricaricata");
        location.reload();
    });


    gameConfig.tank.myTank_n = loadImage('/pictures/game/tank/giallo_n.png');
    gameConfig.tank.myTank_s = loadImage('/pictures/game/tank/giallo_s.png');
    gameConfig.tank.myTank_e = loadImage('/pictures/game/tank/giallo_e.png');
    gameConfig.tank.myTank_o = loadImage('/pictures/game/tank/giallo_o.png');
    gameConfig.proiettile.proiettile_n = loadImage('/pictures/game/tank/proiettile.png');
    gameConfig.muretto.muretto_n = loadImage('/pictures/game/brick.png');

}

function setup() {
    if (!(gameConfig.tank.velocita && gameConfig.canvas.larghezza && gameConfig.canvas.altezza)) {
        createCanvas(10, 10);
    } else {
        resizeCanvas(gameConfig.canvas.larghezza, gameConfig.canvas.altezza);
    }
    background(51);
}

function draw() {
    background(51);
    for (const id of Object.keys(gameState.tanks)) {
        gameState.tanks[id].draw();
    }
    for (const id of Object.keys(gameState.proiettili)) {
        gameState.proiettili[id].draw();
    }
    for (const id of Object.keys(gameState.muretti)) {
        gameState.muretti[id].draw();
    }

    //check gamepad inputs
    gamepadLoop()
}

function connecthandler(e) {
    addgamepad(e.gamepad);
}

function addgamepad(gamepad) {
    controllers[gamepad.index] = gamepad;
    document.removeEventListener('keydown', keyDownHandler);
    document.removeEventListener('keyup', keyUpHandler)
}

function disconnecthandler(e) {
    removegamepad(e.gamepad);
}

function removegamepad(gamepad) {
    delete controllers[gamepad.index];
    document.addEventListener('keydown', keyDownHandler)
    document.addEventListener('keyup', keyUpHandler)
}

function gamepadLoop() {
    if (!haveEvents) {
        scangamepads();
    }
    var i = 0;
    var j;

    for (j in controllers) {
        var controller = controllers[j];

        //Buttons
        for (i = 0; i < controller.buttons.length; i++) {
            var val = controller.buttons[i];
            var pressed = val === 1.0;
            if (typeof (val) == "object") {
                pressed = val.pressed;
                val = val.value;
            }

            if (pressed) {
                console.log("button pressed: " + i);
            } else {
                //console.log("button NON premuto: " + i);
            }
        }

        //Axes
        if (controller.axes[0] === 0) {
            command.nord = false;
            command.sud = false;
        } else if (controller.axes[0] === 1) {
            command.nord = true;
            command.sud = false;
        } else if (controller.axes[0] === -1) {
            command.nord = false;
            command.sud = true;
        }

        if (controller.axes[1] === 0) {
            command.est = false;
            command.ovest = false;
        } else if (controller.axes[1] === 1) {
            command.est = true;
            command.ovest = false;
        } else if (controller.axes[1] === -1) {
            command.est = false;
            command.ovest = true;
        }

    }
}

function scangamepads() {
    var gamepads = navigator.getGamepads ? navigator.getGamepads() : (navigator.webkitGetGamepads ? navigator.webkitGetGamepads() : []);
    for (var i = 0; i < gamepads.length; i++) {
        if (gamepads[i]) {
            if (gamepads[i].index in controllers) {
                controllers[gamepads[i].index] = gamepads[i];
            } else {
                addgamepad(gamepads[i]);
            }
        }
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


const handleBottoneConnessione = (e) => {
    e["srcElement"].disabled = true;
    console.log("ciao, " + gameConfig.userInfo.username)

    if (gameConfig.userInfo.username) {
        let socket = new SockJS("/ws");
        gameConfig.communication.stompClient = Stomp.over(socket);
        gameConfig.communication.stompClient.connect({}, onConnected, onError);
        gameConfig.communication.stompClient.debug = null; //TODO: disable debug on STOMP client
    }

    setInterval(() => {
        JSON.stringify(command);
        gameConfig.communication.stompClient.send("/app/partite/" + gameConfig.partita.idPartita + "/.inviaComando",
            {},
            JSON.stringify({
                sender: gameConfig.userInfo.username,
                tipoMessaggio: 'COMANDO',
                idPartita: gameConfig.partita.idPartita,
                idOggetto: gameConfig.partita.idOggettoControllato,
                nord: command.nord, sud: command.sud, est: command.est, ovest: command.ovest, fuoco: command.fuoco
            })
        );
    }, 1000 / 60);

}

const onConnected = () => {
    gameConfig.communication.stompClient.subscribe('/partite/' + gameConfig.partita.idPartita + '/stato', onStateUpgradeReceived);

    // Tell your username to the server
    gameConfig.communication.stompClient.send("/app/partite/" + gameConfig.partita.idPartita + "/.connessioneGiocatore",
        {},
        JSON.stringify({sender: gameConfig.userInfo.username, tipoMessaggio: 'CONNESSIONE'})
    );
};

const onError = () => {
    alert('Could not connect to WebSocket server. Please refresh this page to try again!');

};

const onStateUpgradeReceived = (message) => {
    parsedData = JSON.parse(message.body);
    let tipoOggetto = parsedData.tipoOggetto;

    if (tipoOggetto === CARRO_ARMATO) {
        if (gameState.tanks[parsedData.idOggetto]) {
            gameState.tanks[parsedData.idOggetto].moveToXY(parsedData.posx, parsedData.posy, parsedData.direzione)
        } else {
            gameState.tanks[parsedData.idOggetto] = new Tank(parsedData.posx, parsedData.posy, parsedData.direzione)
        }
    }

    if (tipoOggetto === PROIETTILE) {

        if (gameState.proiettili[parsedData.idOggetto]) {
            gameState.proiettili[parsedData.idOggetto].moveToXY(parsedData.posx, parsedData.posy, parsedData.direzione)
        } else {
            gameState.proiettili[parsedData.idOggetto] = new Proiettile(parsedData.posx, parsedData.posy, parsedData.direzione)
        }
    }

    if (tipoOggetto === MURETTO) {
        gameState.proiettili[parsedData.idOggetto] = new Muretto(parsedData.posx, parsedData.posy, parsedData.direzione)
    }


}

const cambiaGiocatoere = () => {
    if (gameConfig.partita.idOggettoControllato === 1) {
        gameConfig.partita.idOggettoControllato = 2;
    } else if (gameConfig.partita.idOggettoControllato === 2) {
        gameConfig.partita.idOggettoControllato = 3;
    } else if (gameConfig.partita.idOggettoControllato === 3) {
        gameConfig.partita.idOggettoControllato = 4;
    } else if (gameConfig.partita.idOggettoControllato === 4) {
        gameConfig.partita.idOggettoControllato = 1;
    }
    console.log(gameConfig.partita.idOggettoControllato)
}

const pulisciProiettili = () => {
    gameState.proiettili = {};
}

const cambiaPartita = () => {
    if (gameConfig.partita.idPartita === 1) {
        gameConfig.partita.idPartita = 2;
    } else {
        gameConfig.partita.idPartita = 1;
    }
}

setTimeout(() => {
    resizeCanvas(gameConfig.canvas.larghezza, gameConfig.canvas.altezza);
}, 2000)

document.addEventListener('keydown', keyDownHandler)
document.addEventListener('keyup', keyUpHandler)
window.addEventListener("gamepadconnected", connecthandler);
window.addEventListener("gamepaddisconnected", disconnecthandler);

document.getElementById("bottone-connessione").addEventListener("click", handleBottoneConnessione);
document.getElementById('bottone-diventa-Player2').addEventListener("click", cambiaGiocatoere);
document.getElementById('bottone-cambia-partita').addEventListener("click", cambiaPartita);


if (!haveEvents) {
    setInterval(scangamepads, 500);
}

setInterval(pulisciProiettili, 500);
