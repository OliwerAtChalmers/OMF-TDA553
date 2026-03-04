import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class VehicleModel {
    private final int IMAGE_HEIGHT = 100;
    private final int IMAGE_WIDTH = 200;
    private final int SCREEN_OFFSET = 240;
    private final int GARAGE_RADIUS = 30;
    private final int MAX_CARS = 10;

    private final int worldWidth = 800;
    private final int worldHeight = 800;
    private final int xMax = worldWidth - IMAGE_WIDTH / 2;
    private final int yMax = worldHeight - IMAGE_HEIGHT - SCREEN_OFFSET;

    private static final String WORKSHOP_STATE_NAME = "VolvoBrand";

    private final ArrayList<Vehicle> vehicles = new ArrayList<>();
    private Point workshopPoint = new Point(300, 300);

    public void setWorkshopPoint(Point workshopPoint) {
        if (workshopPoint != null) {
            this.workshopPoint = workshopPoint;
        }
    }

    public void addVehicle(Vehicle vehicle)
    {
        if(vehicles.size() < MAX_CARS)
            vehicles.add(vehicle);
    }

    public int getWorldHeight() {
        return worldHeight;
    }

    public int getWorldWidth(){
        return worldWidth;
    }

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    public ArrayList<State> tick() {
        for (int i = vehicles.size() - 1; i >= 0; i--) {
            Vehicle vehicle = vehicles.get(i);
            vehicle.move();

            checkBorders(vehicle);

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

    private void checkBorders(Vehicle vehicle) {
        int x = (int) Math.round(vehicle.getPosition().getX());
        int y = (int) Math.round(vehicle.getPosition().getY());

        if (x < 0) {

            vehicle.setDirection(180 - vehicle.getDirection());
            vehicle.setPosition(0, (int) (vehicle.getPosition().getY()));
        } else if (x > xMax) {
            vehicle.setPosition(xMax, (int) (vehicle.getPosition().getY()));
            vehicle.setDirection(180 - vehicle.getDirection());
        }

        if (y < 0) {
            vehicle.setPosition((int) (vehicle.getPosition().getX()), 0);
            vehicle.setDirection(-vehicle.getDirection());
        } else if (y > yMax) {
            vehicle.setPosition((int) (vehicle.getPosition().getX()), yMax);
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
