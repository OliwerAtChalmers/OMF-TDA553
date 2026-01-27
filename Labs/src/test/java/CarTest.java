import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    private Car car = new Car();

    void turnLeft90Degrees(){
        for (int i = 0; i < 90; i++){
            car.turnLeft();
        }
    }

    @org.junit.jupiter.api.Test
    void move() {
        car.currentSpeed = 1;
        car.dx = 0;
        car.dy = 0;
        car.move();

        assertEquals(1, Math.round(car.dx * 1000.0) / 1000.0);
        turnLeft90Degrees();
        car.move();
        assertEquals(1, Math.round(car.dy * 1000.0) / 1000.0);
        turnLeft90Degrees();
        car.move();
        assertEquals(0, Math.round(car.dx * 1000.0) / 1000.0);
        turnLeft90Degrees();
        car.move();
        assertEquals(0, Math.round(car.dy * 1000.0) / 1000.0);
    }

    @org.junit.jupiter.api.Test
    void turnLeft() {
        car.dir = 0;
        int sum = 0;
        for (int i = 0; i < 100; i++){
            int r = (int)(Math.random() * 1080);
            for (int j = 0; j < r; j++)
                car.turnLeft();

            sum += r;
            assertEquals(sum % 360, car.dir);
        }
    }

    @org.junit.jupiter.api.Test
    void turnRight() {
        car.dir = 0;
        int sum = 0;
        for (int i = 0; i < 100; i++){
            int r = (int)(Math.random() * 1080);
            for (int j = 0; j < r; j++)
                car.turnRight();

            sum -= r;
            assertEquals(((sum % 360) + 360) % 360, car.dir);
        }
    }

    @org.junit.jupiter.api.Test
    void gas() {
    }

    @org.junit.jupiter.api.Test
    void brake() {
    }
}