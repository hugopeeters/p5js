let N = 50;
let numPatches;
let canvasSize = 1000;
let daf = 1.75; //defection award factor (0 - 3.0)  
let grid;

function setup() {
  createCanvas(canvasSize, canvasSize);
  numPatches = N * N;
  grid = new Array(numPatches);
  for (let i = 0; i < numPatches; i++) {
    grid[i] = new Patch(i);
  }
}

function draw() {
  background(0);
  for (let i = 0; i < grid.length; i++) {
    grid[i].update();
    grid[i].render();
  }
  for (let i = 0; i < grid.length; i++) {
    grid[i].next();
  }
}