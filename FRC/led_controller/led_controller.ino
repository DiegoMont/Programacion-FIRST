#include "controller.h"
#include "color.h"

const int RED_PIN = 9;
const int GREEN_PIN = 10;
const int BLUE_PIN =11;

LedController controller(RED_PIN, GREEN_PIN, BLUE_PIN);

void setup() {
    controller.initializePins();
}

void loop() {
    controller.blinkColor(Color::AMARILLO);
    controller.blinkColor(Color::AZUL);
    controller.blinkColor(Color::VERDE);
    controller.blinkColor(Color::NARANJA);
}
