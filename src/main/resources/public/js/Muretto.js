class Muretto {
    constructor(posX, posY) {
        this.posX = posX;
        this.posY = posY;
    }

    draw() {
        noStroke();
        let img = gameConfig.muretto.muretto_standard;

        image(img, this.posX, this.posY, w, h);
    }

    moveToXY(x, y, dir) {
        console.warn("un muretto non può muoversi.... per ora")
    }
}

