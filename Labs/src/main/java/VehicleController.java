import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

/*
* This class represents the Controller part in the MVC pattern.
* Its responsibilities are to listen to the View and responds in an appropriate manner by
* modifying the model state and the updating the view.
 */

public class VehicleController {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    private final int IMAGE_HEIGHT = 100;
    private final int IMAGE_WIDTH = 200;
    private final int SCREEN_OFFSET = 220;
    private final int GARAGE_RADIUS = 30;


    // The frame that represents this instance View of the MVC pattern
    VehicleView frame;
    // A list of vehicles, modify if needed
    ArrayList<Vehicle> vehicles = new ArrayList<>();

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        VehicleController cc = new VehicleController();

        cc.vehicles.add(new Volvo240());
        cc.vehicles.add(new Saab95());
        cc.vehicles.add(new Scania());

        for(int i = 0; i < cc.vehicles.size(); i++) {
            cc.vehicles.get(i).setPosition(0, i*100);
        }
        // Start a new view and send a reference of self
        cc.frame = new VehicleView("VehicleSim 1.0", cc);

        // Start the timer
        cc.timer.start();
    }

    /* Each step the TimerListener moves all the vehicles in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (int i = vehicles.size() - 1; i >= 0; i--) {
                Vehicle vehicle = vehicles.get(i);
                vehicle.move();

                int x = (int) Math.round(vehicle.getPosition().getX());
                int y = (int) Math.round(vehicle.getPosition().getY());
                frame.drawPanel.moveit(i, x, y);
                frame.drawPanel.repaint();

                int xMax = frame.getScreenWidth() - IMAGE_WIDTH / 2;
                int yMax = frame.getScreenHeight() - IMAGE_HEIGHT - SCREEN_OFFSET;
                // Checks if vehicle is out of bounds or in garage
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

                double dx = x - frame.drawPanel.volvoWorkshopPoint.x;
                double dy = y - frame.drawPanel.volvoWorkshopPoint.y;
                if (Math.sqrt(dx * dx + dy * dy) <= GARAGE_RADIUS && Objects.equals(vehicle.getModelName(), "Volvo240")) {
                    removeVehicle(i);
                }
            }
        }
    }

    // Helper function to remove vehicles across parallel lists
    void removeVehicle(int index) {
        vehicles.remove(index);
        frame.drawPanel.vehiclePoints.remove(index);
        frame.drawPanel.imageList.remove(index);
        frame.drawPanel.imageFileNames.remove(index);
    }

    // Calls the gas method for each vehicle once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
       for (Vehicle vehicle : vehicles) {
            vehicle.gas(gas);
       }
    }

    // Brake all vehicles
    void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (Vehicle vehicle : vehicles) {
            vehicle.brake(brake);
        }
    }

    // Start all vehicles
    void startAllVehicles() {
        for (Vehicle vehicle : vehicles) {
            vehicle.startEngine();
        }
    }

    // Stop all vehicles
    void stopAllVehicles() {
        for (Vehicle vehicle : vehicles) {
            vehicle.stopEngine();
        }
    }

    // Turn turbo on (only Saab)
    void turboOn() {
        for (Vehicle vehicle : vehicles) {
            if (Objects.equals(vehicle.getModelName(), "Saab95")) {
                ((Saab95) vehicle).setTurboOn();
            }
        }
    }

    // Turn turbo off (only Saab)
    void turboOff() {
        for (Vehicle vehicle : vehicles) {
            if (Objects.equals(vehicle.getModelName(), "Saab95")) {
                ((Saab95) vehicle).setTurboOff();
            }
        }
    }

    // Lift bed (only Scania)
    void liftBed() {
        for (Vehicle vehicle : vehicles) {
            if (Objects.equals(vehicle.getModelName(), "Scania")) {
                ((Scania) vehicle).liftBed();
            }
        }
    }

    // Lower bed (only Scania)
    void lowerBed() {
        for (Vehicle vehicle : vehicles) {
            if (Objects.equals(vehicle.getModelName(), "Scania")) {
                ((Scania) vehicle).lowerBed();
            }
        }
    }

    // Turn all vehicles left
    void turnLeft() {
        for (Vehicle vehicle : vehicles) {
            vehicle.turnLeft();
        }
    }

    // Turn all vehicles right
    void turnRight() {
        for (Vehicle vehicle : vehicles) {
            vehicle.turnRight();
        }
    }
}
