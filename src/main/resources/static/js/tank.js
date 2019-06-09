class Tank {
    constructor(posX, posY, color) {
        this.posX = posX;
        this.posY = posY;
        this.color = color;
        this.width = 40;
        this.height = 40;
    }

    draw() {
        noStroke();
        fill(this.color);
        rect(this.posX, this.posY, this.width, this.height);
    }

    moveToXY(x, y) {
        this.posX = x;
        this.posY = y;
    }
}

