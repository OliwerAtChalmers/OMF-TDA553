import java.awt.*;

public class Scania extends Car{

    private final int MAX_BED_ANGLE = 70;
    private final int MIN_BED_ANGLE = 0;

    private int _bedAngle = 0;

    public Scania(){
        nrDoors = 2;
        color = Color.blue;
        enginePower = 500;
        modelName = "Scania";
        stopEngine();
    }

    public void raiseBed(){
        if (getCurrentSpeed() == 0 && getBedAngle() < MAX_BED_ANGLE){
            _bedAngle++;
        }
    }

    public void lowerBed(){
        if (getCurrentSpeed() == 0 && getBedAngle() > MIN_BED_ANGLE) {
            _bedAngle--;
        }
    }

    public int getBedAngle(){ return _bedAngle; }

    @Override
    public void move() {
        if (getBedAngle() == MIN_BED_ANGLE) {
            super.move();
        }
    }
}
