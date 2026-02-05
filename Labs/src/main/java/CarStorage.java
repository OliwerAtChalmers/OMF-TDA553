import java.awt.*;
import java.util.ArrayList;

public class CarStorage {

    ArrayList<Car> _storedCars = new ArrayList<Car>();

    private int _maxNrOfCars;

    public CarStorage(int maxNrOfCars){
        this._maxNrOfCars = maxNrOfCars;
    }

    public boolean loadCar(Car e) {
        if (_storedCars.size() >= _maxNrOfCars)
            return false;
        _storedCars.add(e);
        return true;
    }

    public Car unloadCar() {
        if (_storedCars.isEmpty())
            return null;
        return _storedCars.removeLast();
    }

}
