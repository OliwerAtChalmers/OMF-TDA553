import static org.junit.jupiter.api.Assertions.*;

class Volvo240Test {

    private final Volvo240 volvo240 = new Volvo240();

    void turnLeft90Degrees(){
        for (int i = 0; i < 90; i++){
            volvo240.turnLeft();
        }
    }

    @org.junit.jupiter.api.Test
    void move() {
        volvo240.setCurrentSpeed(1);
        volvo240.setPosition(0, 0);
        volvo240.move();

        assertEquals(1, Math.round(volvo240.getPosition().getX() * 1000.0) / 1000.0);
        turnLeft90Degrees();
        volvo240.move();
        assertEquals(1, Math.round(volvo240.getPosition().getY() * 1000.0) / 1000.0);
        turnLeft90Degrees();
        volvo240.move();
        assertEquals(0, Math.round(volvo240.getPosition().getX() * 1000.0) / 1000.0);
        turnLeft90Degrees();
        volvo240.move();
        assertEquals(0, Math.round(volvo240.getPosition().getY() * 1000.0) / 1000.0);
    }

    @org.junit.jupiter.api.Test
    void turnLeft() {
        volvo240.setDirection(0);
        int sum = 0;
        int r = (int)(Math.random() * 1080);
        for (int j = 0; j < r; j++)
            volvo240.turnLeft();

        sum += r;
        assertEquals(sum % 360, volvo240.getDirection());
    }

    @org.junit.jupiter.api.Test
    void turnRight() {
        volvo240.setDirection(0);
        int sum = 0;
        int r = (int)(Math.random() * 1080);
        for (int j = 0; j < r; j++)
            volvo240.turnRight();

        sum -= r;
        assertEquals(((sum % 360) + 360) % 360, volvo240.getDirection());

    }

    @org.junit.jupiter.api.Test
    void gas() {
        volvo240.setCurrentSpeed(0);
        volvo240.gas(-100);
        assertEquals(0, volvo240.getCurrentSpeed());
        volvo240.gas(100);
        assertEquals(1.25, volvo240.getCurrentSpeed());
    }

    @org.junit.jupiter.api.Test
    void brake() {
        volvo240.setCurrentSpeed(1);
        volvo240.brake(-100);
        assertEquals(1, volvo240.getCurrentSpeed());
        volvo240.brake(100);
        assertEquals(0, volvo240.getCurrentSpeed());
    }
}