public class Garage<V extends Vehicle>{

    private VehicleStorage<V> _vehicleStorage;

    public Garage(int maxNrOfCars){
        _vehicleStorage = new VehicleStorage<V>(maxNrOfCars);
        Garage<Saab95> g = new Garage<Saab95>(5);
    }

    public Boolean load(V vehicle){
        return _vehicleStorage.loadCar(vehicle);
    }

    public V unload(){
        return _vehicleStorage.unloadCar();
    }
}
