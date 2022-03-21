#ifndef LED_CONTROLLER
#define LED_CONTROLLER
#include <Arduino.h>

class LedController {
    private:
    int redPin;
    int greenPin;
    int bluePin;
    
    public:
    int DELAY_MS;
    
    LedController(int redPin, int greenPin, int bluePin){
        this->redPin = redPin;
        this->greenPin = greenPin;
        this->bluePin = bluePin;
        DELAY_MS = 1000;
    }

    void initializePins(){
        pinMode(redPin, OUTPUT);
        pinMode(greenPin, OUTPUT);
        pinMode(bluePin, OUTPUT);
        turnOff();
        
    }

    void testLEDs() {
        for(int i = 0; i < 3; i++) {
            long int color = 0xff0000 >> (8*i);
            blinkColor(color);
        }
    }

    void blinkColor(long int hexColor) {
        setColor(hexColor);
        delay(DELAY_MS);
        turnOff();
        delay(DELAY_MS);
    }

    void setColor(long int hexColor) {
        int red = (hexColor >> 16) & 255;
        int green = (hexColor >> 8) & 255;
        int blue = hexColor & 255;
        setColor(red, green, blue);
    }

    void setColor(int red, int green, int blue) {
        analogWrite(redPin, red);
        analogWrite(greenPin, green);
        analogWrite(bluePin, blue);
    }

    void turnOff() {
        setColor(0x0);
    }
};
#endif
