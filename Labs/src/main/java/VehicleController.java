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
    VehicleSimulator simulator = new VehicleSimulator();

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        VehicleController cc = new VehicleController();

        cc.simulator.addVehicle(new Volvo240());
        cc.simulator.addVehicle(new Saab95());
        cc.simulator.addVehicle(new Scania());

        for(int i = 0; i < cc.simulator.getVehicles().size(); i++) {
            cc.simulator.getVehicles().get(i).setPosition(0, i*100);
        }
        // Start a new view and send a reference of self
        cc.frame = new VehicleView("VehicleSim 1.0", cc);
        cc.simulator.setWorldSize(cc.frame.getScreenWidth(), cc.frame.getScreenHeight());
        cc.simulator.setWorkshopPoint(cc.frame.drawPanel.volvoWorkshopPoint);

        // Start the timer
        cc.timer.start();
    }

    /* Each step the TimerListener moves all the vehicles in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ArrayList<VehicleState> states = new ArrayList<>(simulator.step());
            for (int i = 0; i < states.size(); i++) {
                VehicleState state = states.get(i);
                frame.drawPanel.moveit(i, state.getX(), state.getY());
            }

            frame.drawPanel.repaint();

            ArrayList<Integer> indicesToRemove = new ArrayList<>(simulator.consumeRemovedIndices());
            for (int index : indicesToRemove) {
                removeVehicle(index);
            }
        }
    }

    // Helper function to remove vehicles across parallel lists
    void removeVehicle(int index) {
        frame.drawPanel.vehiclePoints.remove(index);
        frame.drawPanel.imageList.remove(index);
        frame.drawPanel.imageFileNames.remove(index);
    }

    // Calls the gas method for each vehicle once
    void gas(int amount) {
        simulator.gasAll(amount);
    }

    // Brake all vehicles
    void brake(int amount) {
        simulator.brakeAll(amount);
    }

    // Start all vehicles
    void startAllVehicles() {
        simulator.startAll();
    }

    // Stop all vehicles
    void stopAllVehicles() {
        simulator.stopAll();
    }

    // Turn turbo on (only Saab)
    void turboOn() {
        simulator.turboOnAllSaab();
    }

    // Turn turbo off (only Saab)
    void turboOff() {
        simulator.turboOffAllSaab();
    }

    // Lift bed (only Scania)
    void liftBed() {
        simulator.liftBedsAllScania();
    }

    // Lower bed (only Scania)
    void lowerBed() {
        simulator.lowerBedsAllScania();
    }

    // Turn all vehicles left
    void turnLeft() {
        simulator.turnLeftAll();
    }

    // Turn all vehicles right
    void turnRight() {
        simulator.turnRightAll();
    }

    ArrayList<Vehicle> getVehicles() {
        return simulator.getVehicles();
    }
}
