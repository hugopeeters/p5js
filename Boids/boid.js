class Boid {

    constructor(x, y) {
        this.pos = createVector(x, y);
        this.vel = p5.Vector.random2D().mult(random(1, 2));
        this.acc = createVector(0, 0);
        this.alpha = PI / 8;
        this.d1 = 12;
        this.d2 = 0.8 * this.d1;
    }

    render() {
        noFill();
        stroke(255);
        let x1 = 0;
        let y1 = 0;
        let x2 = x1 - this.d1 * cos(this.alpha);
        let y2 = y1 - this.d1 * sin(this.alpha);
        let x3 = x1 - this.d2;
        let y3 = y1;
        let x4 = x1 - this.d1 * cos(this.alpha);
        let y4 = y1 + this.d1 * sin(this.alpha);
        push();
        translate(this.pos.x, this.pos.y);
        rotate(this.vel.heading());
        quad(x1, y1, x2, y2, x3, y3, x4, y4);
        pop();
    }

    applyForce(force) {
        this.acc.add(force);
    }

    update() {

        let steeringForce = createVector();
        //separation
        let separationForce = this.steerAwayFromCloseBoids(boids);
        steeringForce.add(separationForce);
        //alignment
        let alignmentForce = this.alignWithCloseBoids(boids);
        steeringForce.add(alignmentForce);
        //cohesion
        let cohesionForce = this.steerTowardsLocalCenterOfMass(boids);
        steeringForce.add(cohesionForce);
        //apply the combined forces
        this.applyForce(steeringForce);

        //calculate new velocity and position
        this.vel.add(this.acc);
        this.pos.add(this.vel);
        
        // loop around the edges
        if (this.pos.x > width + this.d1) {
            this.pos.x = 0 - this.d1;
        }
        if (this.pos.x < 0 - this.d1) {
            this.pos.x = width + this.d1;
        }
        if (this.pos.y > height + this.d1) {
            this.pos.y = 0 - this.d1;
        }
        if (this.pos.y < 0 - this.d1) {
            this.pos.y = height + this.d1;
        }
        
        //reset acceleration
        this.acc.setMag(0);
    }

    steerAwayFromCloseBoids(others) {
        let tooClose = 25;

        let force = createVector();
        return force;
    }

    alignWithCloseBoids(others){

        let force = createVector();
        return force;
    }

    steerTowardsLocalCenterOfMass(others){

        let force = createVector();
        return force;
    }

}