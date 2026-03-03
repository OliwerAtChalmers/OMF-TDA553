import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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

    VehicleView vehicleView;
    VehicleSimulation simulation = new VehicleSimulation();

    public static void main(String[] args) {
        // Instance of this class
        VehicleController cc = new VehicleController();

        // Start a new view and send a reference of self
        cc.vehicleView = new VehicleView(
                "VehicleSim 1.0",
                cc,
                cc.simulation.getWorldWidth(),
                cc.simulation.getWorldHeight()
        );

        // Start the timer
        cc.timer.start();
    }

    /*  Each step the TimerListener moves all the vehicles in the list and tells the
        view to update its images. Change this method to your needs.
    */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ArrayList<State> states = simulation.tick();
            vehicleView.render(states);
        }
    }

    // Calls the gas method for each vehicle once
    public void gas(int amount) {
        simulation.gasAll(amount);
    }

    // Brake all vehicles
    public void brake(int amount) {
        simulation.brakeAll(amount);
    }

    // Start all vehicles
    public void startAllVehicles() {
        simulation.startAll();
    }

    // Stop all vehicles
    public void stopAllVehicles() {
        simulation.stopAll();
    }

    // Turn turbo on (only Saab)
    public void turboOn() {
        simulation.turboOnAllSaab();
    }

    // Turn turbo off (only Saab)
    public void turboOff() {
        simulation.turboOffAllSaab();
    }

    // Lift bed (only Scania)
    public void liftBed() {
        simulation.liftBedsAllScania();
    }

    // Lower bed (only Scania)
    public void lowerBed() {
        simulation.lowerBedsAllScania();
    }

    // Turn all vehicles left
    public void turnLeft() {
        simulation.turnLeftAll();
    }

    // Turn all vehicles right
    public void turnRight() {
        simulation.turnRightAll();
    }

    public void addVehicle() {
        int r = (int) (Math.random() * (3 - 1)) + 1;
        Vehicle v;
        if (r == 1)
            v = new Volvo240();
        else if (r == 2)
            v = new Saab95();
        else
            v = new Scania();
        simulation.addVehicle(v);
    }

    public void removeVehicle() {
        simulation.removeVehicleAt(simulation.getVehicles().size() - 1);
    }
}
