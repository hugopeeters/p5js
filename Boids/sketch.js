let boids = [];
let numBoids = 100

function setup() {
    createCanvas(800, 800);
    for(let i = 0; i < numBoids; i++){
        boids.push(new Boid(random(width), random(height)));
    }
    console.log(boids);
}

function draw() {
    background(52);
    for(let i = 0; i < numBoids; i++){
        boids[i].render();
        boids[i].update();
    }
}