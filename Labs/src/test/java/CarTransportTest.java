import static org.junit.jupiter.api.Assertions.*;

class CarTransportTest {

    private CarTransport<Car> carTransport = new CarTransport<Car>(5);

    private void resetCarTransport(){
        carTransport.setDirection(0);
        carTransport.setCurrentSpeed(0);
        carTransport.setPosition(0, 0);
    }

    @org.junit.jupiter.api.Test
    void lowerRamp(){
        carTransport.lowerRamp();
        assertTrue(carTransport.getRampStatus());
    }

    @org.junit.jupiter.api.Test
    void raiseRamp(){
        carTransport.raiseRamp();
        assertFalse(carTransport.getRampStatus());
    }

    @org.junit.jupiter.api.Test
    void move(){
        resetCarTransport();
        Saab95 s = new Saab95();
        carTransport.load(s);

        carTransport.setCurrentSpeed(1);
        carTransport.move();

        assertEquals(carTransport.getPosition().getY(), carTransport.getStoredCars().getFirst().getPosition().getY());
        assertEquals(carTransport.getPosition().getX(), carTransport.getStoredCars().getFirst().getPosition().getX());
        carTransport.unload();
    }

    @org.junit.jupiter.api.Test
    void load(){
        resetCarTransport();
        Saab95 s = new Saab95();
        carTransport.load(s);
        assertEquals(carTransport.getStoredCars().getFirst(), s);
        carTransport.unload();
    }

    @org.junit.jupiter.api.Test
    void unload(){
        resetCarTransport();
        Saab95 s = new Saab95();
        carTransport.load(s);
        assertEquals(carTransport.unload(), s);
    }


}
