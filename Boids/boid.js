class Boid {

    constructor(x, y) {
        this.pos = createVector(x, y);
        this.heading = p5.Vector.random2D();
        this.vel = random(1, 2);
        this.heading.mult(this.vel);
    }

    render() {
        noFill();
        stroke(255);
        let alpha = PI / 8;
        let d1 = 12;
        let d2 = 10;

        let x1 = 0;
        let y1 = 0;
        let x2 = x1 - d1 * cos(alpha);
        let y2 = y1 - d1 * sin(alpha);
        let x3 = x1 - d2;
        let y3 = y1;
        let x4 = x1 - d1 * cos(alpha);
        let y4 = y1 + d1 * sin(alpha);
        push();
        translate(this.pos.x, this.pos.y);
        rotate(this.heading.heading());
        quad(x1, y1, x2, y2, x3, y3, x4, y4);
        pop();
    }

    update(){
        this.pos.add(this.heading);
        if (this.pos.x > width){
            this.pos.x -= width;
        }
        if (this.pos.x < 0){
            this.pos.x += width;
        }
        if (this.pos.y > height){
            this.pos.y -= height;
        }
        if (this.pos.y < 0){
            this.pos.y += height;
        }
        
    }

}