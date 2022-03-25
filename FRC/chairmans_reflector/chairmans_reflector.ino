const int BUTTON_PIN = 2;
const int LED_PIN = 13;

bool isLedOn;

void setup() {
    pinMode(BUTTON_PIN, INPUT);
    pinMode(LED_PIN, OUTPUT);
    turnLedOff();
}


void loop() {
    static bool pressedBefore = false;
    const bool isButtonPressed = digitalRead(BUTTON_PIN) == HIGH;
    if(isButtonPressed && !pressedBefore)
        pressedBefore = true;
    else if(!isButtonPressed && pressedBefore) {
        pressedBefore = false;
        processClick();
    }
}

void processClick() {
    static unsigned long lastClickTime = millis();
    unsigned long currentTime = millis();
    unsigned long timeBetweenClicks = currentTime - lastClickTime;
    if(timeBetweenClicks > 100) {
        if(timeBetweenClicks > 1000)
            toggleLedState();
        else
            simulateBrokenLight();
    }
    lastClickTime = currentTime;
}

void toggleLedState() {
    if(isLedOn)
        turnLedOff();
    else
        turnLedOn();
}

void simulateBrokenLight() {
    blinkLed(4, 500);
    delay(1000);
    blinkLed(2, 300);
    delay(1500);
    turnLedOn();
}

void blinkLed(int blinksNum, int blinkDelay) {
    while(0 < blinksNum) {
        turnLedOn();
        delay(blinkDelay);
        turnLedOff();
        delay(blinkDelay);
        blinksNum--;
    }
}

void turnLedOff() {
    digitalWrite(LED_PIN, LOW);
    isLedOn = false;
}

void turnLedOn() {
    digitalWrite(LED_PIN, HIGH);
    isLedOn = true;
}
