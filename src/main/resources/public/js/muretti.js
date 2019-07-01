class Muretto {
    constructor(posX, posY, direzione) {
        this.posX = posX;
        this.posY = posY;
        this.direzione = direzione;
    }

    draw() {
        noStroke();
        let img = gameConfig.muretto.muretto_n;
        let w = 30;
        let h = 30;
        image(img, this.posX, this.posY, w, h);
    }

}

