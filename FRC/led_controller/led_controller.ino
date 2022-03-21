const int RED_PIN = 9;
const int GREEN_PIN = 10;
const int BLUE_PIN =11;

class LedController {
    private:
    int redPin;
    int greenPin;
    int bluePin;
    
    public:
    LedController(int redPin, int greenPin, int bluePin){
        this->redPin = redPin;
        this->greenPin = greenPin;
        this->bluePin = bluePin;
    }

    void initializePins(){
        pinMode (redPin, OUTPUT);
        pinMode (greenPin, OUTPUT);
        pinMode (bluePin, OUTPUT);
        turnOff();
        
    }

    void testLEDs() {
        // TODO: Implement
        const int DELAY_MS = 200;
        for(int i = 0; i < 3; i++) {
            int color = 0xff0000 >> (8*i);
            setColor(color);
            delay(DELAY_MS);
            turnOff();
            delay(DELAY_MS);
        }
    }

    void setColor(int hexColor) {
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

LedController controller(RED_PIN, GREEN_PIN, BLUE_PIN);

void setup() {
    controller.initializePins();
}

void loop() {
    controller.testLEDs();
}
