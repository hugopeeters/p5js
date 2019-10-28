let gravity;
let rain;
let wind;
let w;
let wOff;

function setup() {
  createCanvas(600, 400);
  gravity = createVector(0, 1);
  w = createVector(0, 0);
  wOff = 0;
  rain = new Array(0);
  for (let i = 0; i < 4500; i++) {
    let d = new Drop();
    d.randomize();
    rain.push(d);
  }
}

function draw() {
  background(5);
  for (let i = 0; i < rain.length; i++) {
    rain[i].update();
    rain[i].render();
  }
}