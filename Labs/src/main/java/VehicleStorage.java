import java.util.ArrayList;

public class VehicleStorage<V extends Vehicle> {

    private ArrayList<V> _storedCars = new ArrayList<V>();
    private int _maxNrOfCars;

    public VehicleStorage(int maxNrOfCars){
        this._maxNrOfCars = maxNrOfCars;
    }

    public boolean loadCar(V e) {
        if (_storedCars.size() >= _maxNrOfCars)
            return false;
        _storedCars.add(e);
        return true;
    }

    public V unloadCar() {
        if (_storedCars.isEmpty())
            return null;
        return _storedCars.removeLast();
    }

    public ArrayList<V> getStoredCars(){
        return _storedCars;
    }

}
