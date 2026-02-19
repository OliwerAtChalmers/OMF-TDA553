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
                Point pos = this.getPosition();
                car.setPosition(pos.x, pos.y);
            }
        }
    }

    public boolean load(C car){
        Point pos = this.getPosition();
        if (Math.abs(Point2D.distance(car.getPosition().getX(), car.getPosition().getY(), pos.getX(), pos.getY())) > LOAD_DISTANCE
                || getCurrentSpeed() > 0)
            return false;

        car.setPosition(pos.x, pos.y);
        return _carStorage.loadCar(car);
    }

    public C unload(){
        if (getCurrentSpeed()!= 0)
            return null;

        C car = _carStorage.unloadCar();
        if (car == null)
            return null;


        Point carPos = car.getPosition();
        car.setPosition(carPos.x + 10, carPos.y + 10);
        return car;
    }

    public boolean getRampStatus(){
        return _rampIsLowered;
    }

    public ArrayList<C> getStoredCars(){
        return _carStorage.getStoredCars();
    }
}
