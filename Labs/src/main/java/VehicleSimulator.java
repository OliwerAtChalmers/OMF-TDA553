import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class VehicleSimulator {
    private final int IMAGE_HEIGHT = 100;
    private final int IMAGE_WIDTH = 200;
    private final int SCREEN_OFFSET = 220;
    private final int GARAGE_RADIUS = 30;

    private final ArrayList<Vehicle> vehicles = new ArrayList<>();
    private final ArrayList<Integer> stashedVehicles = new ArrayList<>();

    private int worldWidth;
    private int worldHeight;
    private Point workshopPoint = new Point(0, 0);

    public void setWorldSize(int width, int height) {
        this.worldWidth = width;
        this.worldHeight = height;
    }

    public void setWorkshopPoint(Point workshopPoint) {
        if (workshopPoint != null) {
            this.workshopPoint = workshopPoint;
        }
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    public List<State> tick() {
        stashedVehicles.clear();

        for (int i = vehicles.size() - 1; i >= 0; i--) {
            Vehicle vehicle = vehicles.get(i);
            vehicle.move();

            int x = (int) Math.round(vehicle.getPosition().getX());
            int y = (int) Math.round(vehicle.getPosition().getY());

            int xMax = worldWidth - IMAGE_WIDTH / 2;
            int yMax = worldHeight - IMAGE_HEIGHT - SCREEN_OFFSET;
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

            double dx = x - workshopPoint.x;
            double dy = y - workshopPoint.y;
            if (Math.sqrt(dx * dx + dy * dy) <= GARAGE_RADIUS && Objects.equals(vehicle.getModelName(), "Volvo240")) {
                vehicles.remove(i);
                stashedVehicles.add(i);
            }
        }

        ArrayList<State> states = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            states.add(new State(
                    vehicle.getModelName(),
                    (int) Math.round(vehicle.getPosition().getX()),
                    (int) Math.round(vehicle.getPosition().getY()),
                    vehicle.getDirection()
            ));
        }

        return states;
    }

    public ArrayList<Integer> consumeRemovedVehicles() {
        ArrayList<Integer> indices = new ArrayList<>(stashedVehicles);
        stashedVehicles.clear();
        return indices;
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
}
