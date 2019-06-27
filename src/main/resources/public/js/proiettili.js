class Proiettile {
    constructor(posX, posY, direzione) {
        this.posX = posX;
        this.posY = posY;
        this.direzione = direzione;
    }

    draw() {
        noStroke();
        let img = gameConfig.proiettile.proiettile_n;
        let w, h;
        if (this.direzione === "NORD" || this.direzione === "SUD") {
            w = 5;
            h = 8;
        } else if (this.direzione === "EST" || this.direzione === "OVEST") {
            w = 8;
            h = 5
        }
        image(img, this.posX, this.posY, w, h);
    }

    moveToXY(x, y, dir) {
        this.posX = x;
        this.posY = y;
        this.direzione = dir;
    }
}

