import static org.junit.jupiter.api.Assertions.*;

class ScaniaTest {

    private Scania scania = new Scania();

    @org.junit.jupiter.api.Test
    void bed(){
        scania.raiseBed();
        assertEquals(1, scania.getBedAngle());
        scania.lowerBed();
        assertEquals(0, scania.getBedAngle());
    }

    @org.junit.jupiter.api.Test
    void move(){
        scania.currentSpeed = 0;
        scania.raiseBed();
        scania.currentSpeed = 1;
        scania.move();
        assertEquals(0, scania.dx);
        assertEquals(0, scania.dy);
    }

}
