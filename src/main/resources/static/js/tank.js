class Tank {
    constructor(posX, posY, direzione) {
        this.posX = posX;
        this.posY = posY;
        this.direzione = direzione;
        // this.width = 20;
        // this.height = 20;
    }

    draw() {
        noStroke();
        //fill(this.color);
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
        image(img, this.posX, this.posY, this.width, this.height);
    }

    moveToXY(x, y, dir) {
        this.posX = x;
        this.posY = y;
        this.direzione = dir;
    }
}

