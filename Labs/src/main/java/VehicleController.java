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

    // The frame that represents this instance View of the MVC pattern
    VehicleView frame;
    VehicleSimulation simulation = new VehicleSimulation();

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        VehicleController cc = new VehicleController();


        cc.simulation.addVehicle(new Volvo240());
        cc.simulation.addVehicle(new Saab95());
        cc.simulation.addVehicle(new Scania());
        cc.simulation.addVehicle(new Saab95());

        for(int i = 0; i < cc.simulation.getVehicles().size(); i++) {
            cc.simulation.getVehicles().get(i).setPosition(0, i*100);
        }

        // Start a new view and send a reference of self
        cc.frame = new VehicleView(
                "VehicleSim 1.0",
                cc,
                cc.simulation.getWorldWidth(),
                cc.simulation.getWorldHeight()
        );

        // Start the timer
        cc.timer.start();
    }

    /* Each step the TimerListener moves all the vehicles in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            frame.render(simulation.tick());
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

    public void addVehicle(Vehicle vehicle) {
        simulation.addVehicle(vehicle);
    }

    public void removeVehicleAt(int index) {
        simulation.removeVehicleAt(index);
    }

    public void removeVehicle(Vehicle vehicle) {
        simulation.removeVehicle(vehicle);
    }

    // returns all vehicles
    public ArrayList<Vehicle> getVehicles() {
        return simulation.getVehicles();
    }
}
