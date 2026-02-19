import java.awt.*;

public abstract class Vehicle implements Movable{

    protected int nrDoors; // Number of doors on the car
    protected double enginePower; // Engine power of the car
    protected Color color; // Color of the car
    protected String modelName; // The car model name

    private Point position = new Point();
    private int direction = 0;
    private double currentSpeed; // The current speed of the car

    private final int TURN_RADIUS = 30;

    public void move(){
        double rad = Math.toRadians(direction);
        position.x += (int) (Math.cos(rad) * getCurrentSpeed());
        position.y += (int) (Math.sin(rad) * getCurrentSpeed());
    }

    public void turnLeft(){
        direction = (((direction - TURN_RADIUS) % 360) + 360) % 360;
    }

    public void turnRight(){
        direction = (direction + TURN_RADIUS) % 360;
    }

    public void gas(double amount){
        incrementSpeed(Math.clamp(amount, 0, 1));
    }

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
    };

    private void incrementSpeed(double amount){
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, enginePower);
    }

    private void decrementSpeed(double amount){
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount,0);
    }

    private double getEnginePower(){
        return enginePower;
    }

    public double getCurrentSpeed(){
        return currentSpeed;
    }

    public void setCurrentSpeed(double speed){
        currentSpeed = speed;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(int x, int y){
        position = new Point(x, y);
    }

    public int getDirection(){
        return direction;
    }

    public void setDirection(int direction){
        this.direction = direction;
    }

    public String getModelName(){
        return modelName;
    };
}
