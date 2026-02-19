import static org.junit.jupiter.api.Assertions.*;

class ScaniaTest {

    private Scania scania = new Scania();

    @org.junit.jupiter.api.Test
    void bed(){
        scania.liftBed();
        assertEquals(1, scania.getBedAngle());
        scania.lowerBed();
        assertEquals(0, scania.getBedAngle());
    }

    @org.junit.jupiter.api.Test
    void move(){
        scania.setCurrentSpeed(0);
        scania.liftBed();
        scania.setCurrentSpeed(1);
        scania.move();
        assertEquals(0, scania.getPosition().getX());
        assertEquals(0, scania.getPosition().getY());
    }

}
