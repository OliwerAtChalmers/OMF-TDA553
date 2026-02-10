import static org.junit.jupiter.api.Assertions.*;

class VehicleStorageTest {

    private VehicleStorage<Vehicle> storage = new VehicleStorage<Vehicle>(5);

    @org.junit.jupiter.api.Test
    void loadCar(){
        Saab95 s = new Saab95();
        assertTrue(storage.loadCar(s));
        storage.loadCar(s);
        storage.loadCar(s);
        storage.loadCar(s);
        storage.loadCar(s);
        assertFalse(storage.loadCar(s));

    }

    @org.junit.jupiter.api.Test
    void unloadCar(){
        assertNull(storage.unloadCar());

        Saab95 s = new Saab95();
        storage.loadCar(s);
        assertEquals(storage.unloadCar(), s);

    }

}
