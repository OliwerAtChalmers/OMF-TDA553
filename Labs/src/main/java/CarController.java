import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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

    private final int X = CarView.getXSize();
    private final int Y = CarView.getYSize();
    private final int IMG_SIZE = 100; // size of img

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
            for (int i = 0; i < cars.size(); i++) {
                Vehicle car = cars.get(i);
                car.move();
                int x = (int) Math.round(car.getPosition()[0]);
                int y = (int) Math.round(car.getPosition()[1]);

                if (x > (X - IMG_SIZE) || y > Y || x < 0 || y < 0)
                    car.dir = car.dir + 180;
                frame.drawPanel.moveit(i, x, y);
                // repaint() calls the paintComponent method of the panel
                frame.drawPanel.repaint();
            }
        }
    }

    // Calls the gas method for each car once
    public void gas(int amount) {
        double gas = amount / 100.0;
        for (Vehicle car : cars)
            car.gas(gas);
    }

    public void brake(int amount) {
        double brakeAmount = amount / 100.0;
        for (Vehicle car : cars)
            car.brake(brakeAmount);
    }

    public void start() {
        for (Vehicle car : cars)
            car.startEngine();
    }

    public void stop() {
        for (Vehicle car : cars)
            car.stopEngine();
    }

    public void turboOn() {
        for (Vehicle car : cars)
            if (car instanceof Saab95 saab)
                saab.setTurboOn();
    }

    public void turboOff() {
        for (Vehicle car : cars)
            if (car instanceof Saab95 saab)
                saab.setTurboOff();
    }

    public void liftBed() {
        for (Vehicle car : cars)
            if (car instanceof Scania scania)
                scania.raiseBed();
    }

    public void lowerBed() {
        for (Vehicle car : cars)
            if (car instanceof Scania scania)
                scania.lowerBed();
    }

}
