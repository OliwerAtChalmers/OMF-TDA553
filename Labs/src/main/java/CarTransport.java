import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class CarTransport<C extends Car> extends Truck{

    private boolean _rampIsLowered = false;
    private VehicleStorage<C> _carStorage;
    private final double LOAD_DISTANCE = 2;

    public CarTransport(int maxNrOfCars){
        nrDoors = 2;
        color = Color.red;
        enginePower = 500;
        modelName = "CarTransport";
        _carStorage = new VehicleStorage<C>(maxNrOfCars);
        stopEngine();
    }

    public void lowerRamp(){
        if (getCurrentSpeed() == 0)
            _rampIsLowered = true;
    }

    public void raiseRamp(){
        if (getCurrentSpeed() == 0)
            _rampIsLowered = false;
    }

    @Override
    public void move() {
        if (!_rampIsLowered) {
            super.move();
            ArrayList<C> storedCars = _carStorage.getStoredCars();
            for (Car car : storedCars) {
                car.dx = dx;
                car.dy = dy;
            }
        }
    }

    public boolean load(C car){
        if (Point2D.distance(car.dx, car.dy, this.dx, this.dy) > LOAD_DISTANCE || currentSpeed > 0)
            return false;

        return _carStorage.loadCar(car);
    }

    public Car unload(){
        if (currentSpeed != 0)
            return null;

        Car car = _carStorage.unloadCar();
        if (car == null)
            return null;

        car.dx += 1;
        car.dy += 1;
        return car;
    }
}
