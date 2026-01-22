import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    private Car car = new Car();

    @org.junit.jupiter.api.Test
    void move() {

    }

    @org.junit.jupiter.api.Test
    void turnLeft() {
    }

    @org.junit.jupiter.api.Test
    void turnRight() {
        int sum = 0;
        for (int i = 0; i < 100; i++){
            int r = (int)(Math.random() * 1080);
            for (int j = 0; j < r; j++)
                car.turnRight();

            sum += r;
            assertEquals(sum % 360, car.getDirection());
        }
    }

    @org.junit.jupiter.api.Test
    void gas() {
    }

    @org.junit.jupiter.api.Test
    void brake() {
    }
}