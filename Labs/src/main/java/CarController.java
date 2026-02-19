import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {
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
    CarView frame;
    // A list of cars, modify if needed
    ArrayList<Vehicle> cars = new ArrayList<>();

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        cc.cars.add(new Volvo240());
        cc.cars.add(new Saab95());
        cc.cars.add(new Scania());

        for(int i = 0; i < cc.cars.size(); i++) {
            cc.cars.get(i).setPosition(0, i*100);
        }
        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        // Start the timer
        cc.timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (int i = cars.size() - 1; i >= 0; i--) {
                Vehicle car = cars.get(i);
                car.move();

                int x = (int) Math.round(car.getPosition().getX());
                int y = (int) Math.round(car.getPosition().getY());
                frame.drawPanel.moveit(i, x, y);
                frame.drawPanel.repaint();

                // Checks if car is out of bounds or in garage
                if (x < 0 || x > (frame.getScreenWidth() - IMAGE_WIDTH) || y < 0 || y > (frame.getScreenHeight() - IMAGE_HEIGHT - SCREEN_OFFSET)){
                    car.setDirection(car.getDirection() + 180);
                }

                double dx = x - frame.drawPanel.volvoWorkshopPoint.x;
                double dy = y - frame.drawPanel.volvoWorkshopPoint.y;
                if (Math.sqrt(dx * dx + dy * dy) <= GARAGE_RADIUS && Objects.equals(car.getModelName(), "Volvo240")) {
                    removeCar(i);
                }
            }
        }
    }

    // Helper function to remove cars accross parallel lists
    void removeCar(int index) {
        cars.remove(index);
        frame.drawPanel.vehiclePoints.remove(index);
        frame.drawPanel.imageList.remove(index);
        frame.drawPanel.imageFileNames.remove(index);
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
       for (Vehicle car : cars) {
            car.gas(gas);
       }
    }

    // Brake all cars
    void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (Vehicle car : cars) {
            car.brake(brake);
        }
    }

    // Start all cars
    void startAllCars() {
        for (Vehicle car : cars) {
            car.startEngine();
        }
    }

    // Stop all cars
    void stopAllCars() {
        for (Vehicle car : cars) {
            car.stopEngine();
        }
    }

    // Turn turbo on (only Saab)
    void turboOn() {
        for (Vehicle car : cars) {
            if (Objects.equals(car.getModelName(), "Saab95")) {
                ((Saab95) car).setTurboOn();
            }
        }
    }

    // Turn turbo off (only Saab)
    void turboOff() {
        for (Vehicle car : cars) {
            if (Objects.equals(car.getModelName(), "Saab95")) {
                ((Saab95) car).setTurboOff();
            }
        }
    }

    // Lift bed (only Scania)
    void liftBed() {
        for (Vehicle car : cars) {
            if (Objects.equals(car.getModelName(), "Scania")) {
                ((Scania) car).liftBed();
            }
        }
    }

    // Lower bed (only Scania)
    void lowerBed() {
        for (Vehicle car : cars) {
            if (Objects.equals(car.getModelName(), "Scania")) {
                ((Scania) car).lowerBed();
            }
        }
    }

    // Turn all cars left
    void turnLeft() {
        for (Vehicle car : cars) {
            car.turnLeft();
        }
    }

    // Turn all cars right
    void turnRight() {
        for (Vehicle car : cars) {
            car.turnRight();
        }
    }
}
