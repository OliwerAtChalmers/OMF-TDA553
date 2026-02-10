import static org.junit.jupiter.api.Assertions.*;

class CarTransportTest {

    private CarTransport<Car> carTransport = new CarTransport<Car>(5);

    private void resetCarTransport(){
        carTransport.currentSpeed = 0;
        carTransport.dx = 0;
        carTransport.dy = 0;
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

        carTransport.currentSpeed = 1;
        carTransport.move();

        assertEquals(carTransport.dy, carTransport.getStoredCars().get(0).dy);
        assertEquals(carTransport.dx, carTransport.getStoredCars().get(0).dx);
        carTransport.unload();
    }

    @org.junit.jupiter.api.Test
    void load(){
        resetCarTransport();
        Saab95 s = new Saab95();
        carTransport.load(s);
        assertEquals(carTransport.getStoredCars().get(0), s);
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
