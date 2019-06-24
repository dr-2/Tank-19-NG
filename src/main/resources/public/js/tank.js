class Tank {
    constructor(posX, posY, direzione) {
        this.posX = posX;
        this.posY = posY;
        this.direzione = direzione;
    }

    draw() {
        noStroke();
        let img = gameConfig.tank.myTank_n;
        if (this.direzione === "NORD") {
            img = gameConfig.tank.myTank_n
        } else if (this.direzione === "SUD") {
            img = gameConfig.tank.myTank_s
        } else if (this.direzione === "EST") {
            img = gameConfig.tank.myTank_e
        } else if (this.direzione === "OVEST") {
            img = gameConfig.tank.myTank_o
        }
        image(img, this.posX, this.posY, 30, 30);
    }

    moveToXY(x, y, dir) {
        this.posX = x;
        this.posY = y;
        this.direzione = dir;
    }
}

