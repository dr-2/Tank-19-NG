class Tank {
    constructor(posX, posY, direzione) {
        this.posX = posX;
        this.posY = posY;
        this.direzione = direzione;
    }

    draw() {
        noStroke();
        let img = imgTank_n;
        if (this.direzione === "NORD") {
            img = imgTank_n
        } else if (this.direzione === "SUD") {
            img = imgTank_s
        } else if (this.direzione === "EST") {
            img = imgTank_e
        } else if (this.direzione === "OVEST") {
            img = imgTank_o
        }
        image(img, this.posX, this.posY, 30, 30);
    }

    moveToXY(x, y, dir) {
        this.posX = x;
        this.posY = y;
        this.direzione = dir;
    }
}

