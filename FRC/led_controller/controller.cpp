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
        analogWrite(redPin, LOW);
        analogWrite(greenPin, LOW);
        analogWrite(bluePin, LOW);
    }

    void testLEDs() {
        // TODO: Implement
        const int delay = 200;
        for(int i = 0)
        setColor(0xff0000);
        delay(delay);
        turnOff();
        delay(delay);
        setColor(0xff00);
        delay(delay);
        turnOff();
        delay(delay);
        setColor(0xff);
        delay(delay);
        turnOff();
}

    void setColor(int hexColor) {
        // TODO: Fix implementation
        int red = 0;
        int green = 0;
        int blue = 0
        analogWrite(redPin, red);
        analogWrite(greenPin, green);
        analogWrite(bluePin, blue); 
    }

    void turnOff() {
        setColor(0x0);
    }
};
