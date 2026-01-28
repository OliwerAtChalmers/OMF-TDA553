import static org.junit.jupiter.api.Assertions.*;

class Saab95Test {

    private Saab95 saab95 = new Saab95();

    @org.junit.jupiter.api.Test
    void setTurbo(){
        saab95.setTurboOn();
        assertTrue(saab95.turboOn);

        saab95.setTurboOff();
        assertFalse(saab95.turboOn);
    }
}