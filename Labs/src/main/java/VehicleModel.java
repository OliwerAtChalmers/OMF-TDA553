import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class VehicleModel {
    private static final int IMAGE_HEIGHT = 100;
    private static final int IMAGE_WIDTH = 200;
    private static final int SCREEN_OFFSET = 240;
    private static final int GARAGE_RADIUS = 30;
    private static final int MAX_CARS = 10;
    private static final int WORLD_WIDTH = 800;
    private static final int WORLD_HEIGHT = 800;
    private static final int X_MAX = WORLD_WIDTH - IMAGE_WIDTH / 2;
    private static final int Y_MAX = WORLD_HEIGHT - IMAGE_HEIGHT - SCREEN_OFFSET;
    private static final String WORKSHOP_STATE_NAME = "VolvoBrand";

    private ArrayList<Vehicle> vehicles = new ArrayList<>();
    private Point workshopPoint = new Point(300, 300);

    public void addVehicle(Vehicle vehicle)
    {
        if(vehicles.size() < MAX_CARS)
            vehicles.add(vehicle);
    }

    public int getWorldHeight() {
        return WORLD_HEIGHT;
    }

    public int getWorldWidth(){
        return WORLD_WIDTH;
    }

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    public ArrayList<State> tick() {
        for (int i = vehicles.size() - 1; i >= 0; i--) {
            Vehicle vehicle = vehicles.get(i);
            vehicle.move();

            isWithinBorders(vehicle);

            double dx = (int) Math.round(vehicle.getPosition().getX()) - workshopPoint.x;
            double dy = (int) Math.round(vehicle.getPosition().getY()) - workshopPoint.y;
            if (Math.sqrt(dx * dx + dy * dy) <= GARAGE_RADIUS && Objects.equals(vehicle.getModelName(), "Volvo240")) {
                vehicles.remove(i);
            }
        }

        ArrayList<State> states = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            states.add(new State(
                    vehicle.getModelName(),
                    (int) Math.round(vehicle.getPosition().getX()),
                    (int) Math.round(vehicle.getPosition().getY())
            ));
        }

        states.add(new State(WORKSHOP_STATE_NAME, workshopPoint.x, workshopPoint.y));

        return states;
    }

    private void isWithinBorders(Vehicle vehicle) {
        int x = (int) Math.round(vehicle.getPosition().getX());
        int y = (int) Math.round(vehicle.getPosition().getY());

        if (x < 0) {
            vehicle.setDirection(180 - vehicle.getDirection());
            vehicle.setPosition(0, (int) (vehicle.getPosition().getY()));
        } else if (x > X_MAX) {
            vehicle.setPosition(X_MAX, (int) (vehicle.getPosition().getY()));
            vehicle.setDirection(180 - vehicle.getDirection());
        }

        if (y < 0) {
            vehicle.setPosition((int) (vehicle.getPosition().getX()), 0);
            vehicle.setDirection(-vehicle.getDirection());
        } else if (y > Y_MAX) {
            vehicle.setPosition((int) (vehicle.getPosition().getX()), Y_MAX);
            vehicle.setDirection(-vehicle.getDirection());
        }
    }

    public void gasAll(int amount) {
        double gas = ((double) amount) / 100;
        for (Vehicle vehicle : vehicles) {
            vehicle.gas(gas);
        }
    }

    public void brakeAll(int amount) {
        double brake = ((double) amount) / 100;
        for (Vehicle vehicle : vehicles) {
            vehicle.brake(brake);
        }
    }

    public void startAll() {
        for (Vehicle vehicle : vehicles) {
            vehicle.startEngine();
        }
    }

    public void stopAll() {
        for (Vehicle vehicle : vehicles) {
            vehicle.stopEngine();
        }
    }

    public void turboOnAllSaab() {
        for (Vehicle vehicle : vehicles) {
            if (Objects.equals(vehicle.getModelName(), "Saab95")) {
                ((Saab95) vehicle).setTurboOn();
            }
        }
    }

    public void turboOffAllSaab() {
        for (Vehicle vehicle : vehicles) {
            if (Objects.equals(vehicle.getModelName(), "Saab95")) {
                ((Saab95) vehicle).setTurboOff();
            }
        }
    }

    public void liftBedsAllScania() {
        for (Vehicle vehicle : vehicles) {
            if (Objects.equals(vehicle.getModelName(), "Scania")) {
                ((Scania) vehicle).liftBed();
            }
        }
    }

    public void lowerBedsAllScania() {
        for (Vehicle vehicle : vehicles) {
            if (Objects.equals(vehicle.getModelName(), "Scania")) {
                ((Scania) vehicle).lowerBed();
            }
        }
    }

    public void turnLeftAll() {
        for (Vehicle vehicle : vehicles) {
            vehicle.turnLeft();
        }
    }

    public void turnRightAll() {
        for (Vehicle vehicle : vehicles) {
            vehicle.turnRight();
        }
    }

    public void removeVehicleAt(int index) {
        if (index >= 0 && index < vehicles.size()) {
            vehicles.remove(index);
        }
    }
}
