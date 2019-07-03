class Muretto {
    constructor(posX, posY) {
        this.posX = posX;
        this.posY = posY;
    }

    draw() {
        noStroke();
        let img = gameConfig.muretto.muretto_standard;

        image(img, this.posX, this.posY);
    }

    moveToXY(x, y, dir) {
        this.posX = x;
        this.posY = y;
        this.direzione = dir;
    }
}

