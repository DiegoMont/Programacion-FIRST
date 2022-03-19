#include "controller.cpp"

const int RED_PIN = 5;
const int GREEN_PIN = 6;
const int BLUE_PIN = 9;

LedController controller(RED_PIN, GREEN_PIN, BLUE_PIN);

void setup() {
    controller.initializePins();
    controller.testLEDs();
}

void loop() {
}
