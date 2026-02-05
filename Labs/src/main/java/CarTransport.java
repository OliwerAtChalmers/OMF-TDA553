import java.awt.*;

public class CarTransport extends Car{

    private boolean _rampIsLowered = false;
    private CarStorage _carStorage;

    public CarTransport(int maxNrOfCars){
        nrDoors = 2;
        color = Color.red;
        enginePower = 500;
        modelName = "CarTransport";
        _carStorage = new CarStorage(maxNrOfCars);
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
        }
    }
}
