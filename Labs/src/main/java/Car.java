import java.awt.*;

public class Car implements Movable{
    public double dx;
    public double dy;
    public int dir;

    protected int nrDoors; // Number of doors on the car
    protected double enginePower; // Engine power of the car
    protected Color color; // Color of the car
    protected String modelName; // The car model name

    private double currentSpeed; // The current speed of the car

    public Car(){
        dx = 0;
        dy = 0;
        dir = 0;
    }

    public void move(){
        double rad = Math.toRadians(dir);
        dx += Math.sin(rad) * getCurrentSpeed();
        dy += Math.cos(rad) * getCurrentSpeed();
    }

    public void turnLeft(){
        dir = (dir - 1) % 360;
    }

    public void turnRight(){
        dir = (dir + 1) % 360;
    }

    // TODO: IS FIXED?
    public void gas(double amount){
        incrementSpeed(Math.clamp(amount, 0, 1));
    }

    // TODO: IS FIXED?
    public void brake(double amount){
        decrementSpeed(Math.clamp(amount, 0, 1));
    }

    public int getNrDoors(){
        return nrDoors;
    }

    public Color getColor(){
        return color;
    }

    public void setColor(Color clr){
        color = clr;
    }

    public void startEngine(){
        currentSpeed = 0.1;
    }

    public void stopEngine(){
        currentSpeed = 0;
    }

    protected double speedFactor(){
        return enginePower * 0.01;
    }

    private void incrementSpeed(double amount){
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, enginePower);
    }

    private void decrementSpeed(double amount){
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount,0);
    }

    private double getEnginePower(){
        return enginePower;
    }

    private double getCurrentSpeed(){
        return currentSpeed;
    }
}
