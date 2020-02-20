let myList = [4, 2, 3, 5, 1, 6, 9, 7, 8];
let i = 0;

function setup() {
    createCanvas(800, 400);
    frameRate(1);
    visualizeArray(myList, null);
}

function draw() {

    //find smallest
    let smallest = myList[i];
    let index = i;
    for (let j = i; j < myList.length; j++) {
        if (myList[j] < smallest) {
            smallest = myList[j];
            index = j;
        }
    }

    //swap
    let temp = myList[i];
    myList[i] = myList[index];
    myList[index] = temp;
    visualizeArray(myList, i);

    i++;
    if (i >= myList.length) {
        noLoop();
    }
}

function visualizeArray(arr, highlight) {
    background(200);
    let margin = 10;
    let barWidth = (width - 2 * margin) / arr.length;
    let barHeightUnit = (height - 2 * margin) / arr.length;
    stroke(255);
    for (let i = 0; i < arr.length; i++) {
        if (highlight != null && highlight == i) {
            fill(255, 0, 0);
        } else {
            fill(52);
        }
        rect(margin + i * barWidth, height - margin - arr[i] * barHeightUnit, barWidth, arr[i] * barHeightUnit);
    }
}