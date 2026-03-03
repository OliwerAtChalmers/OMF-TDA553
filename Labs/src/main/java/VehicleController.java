import javax.swing.*;
import java.awt.*;
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
    VehicleModel simulator = new VehicleModel();

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        VehicleController cc = new VehicleController();

        // Start a new view and send a reference of self
        cc.frame = new VehicleView("VehicleSim 1.0", cc);
        cc.simulator.setWorkshopPoint(cc.frame.drawPanel.volvoWorkshopPoint);

        // Start the timer
        cc.timer.start();
    }

    /* Each step the TimerListener moves all the vehicles in the list and tells the
     * view to update its images. Change this method to your needs.
     * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ArrayList<State> states = new ArrayList<>(simulator.tick());
            for (int i = 0; i < states.size(); i++) {
                // Gets a vehicle
                State state = states.get(i);
                moveIt(i, state.getX(), state.getY());
            }

            frame.drawPanel.repaint();
        }
    }

    // Helper function to remove vehicles across parallel lists
    public void removeVehicle(int index) {
        if (index < 0)
            return;
        getVehicles().remove(index);
        frame.drawPanel.vehiclePoints.remove(index);
        frame.drawPanel.imageList.remove(index);
        frame.drawPanel.imageFileNames.remove(index);
    }

    public void addVehicle(Vehicle vehicle) {
        if(getVehicles().size() == 10) {return;}
        getVehicles().add(vehicle);
        frame.drawPanel.vehiclePoints.add(new Point());
        frame.drawPanel.imageFileNames.add(getVehicles().getLast().getModelName() + ".jpg");
        frame.drawPanel.initImages();
    }

    // Calls the gas method for each vehicle once
    public void gas(int amount) {
        simulator.gasAll(amount);
    }

    // Brake all vehicles
    public void brake(int amount) {
        simulator.brakeAll(amount);
    }

    // Start all vehicles
    public void startAllVehicles() {
        simulator.startAll();
    }

    // Stop all vehicles
    public void stopAllVehicles() {
        simulator.stopAll();
    }

    // Turn turbo on (only Saab)
    public void turboOn() {
        simulator.turboOnAllSaab();
    }

    // Turn turbo off (only Saab)
    public void turboOff() {
        simulator.turboOffAllSaab();
    }

    // Lift bed (only Scania)
    public void liftBed() {
        simulator.liftBedsAllScania();
    }

    // Lower bed (only Scania)
    public void lowerBed() {
        simulator.lowerBedsAllScania();
    }

    // Turn all vehicles left
    public void turnLeft() {
        simulator.turnLeftAll();
    }

    // Turn all vehicles right
    public void turnRight() {
        simulator.turnRightAll();
    }

    // returns all vehicles
    public ArrayList<Vehicle> getVehicles() {
        return simulator.getVehicles();
    }

    void moveIt(int vehiclePointIndex, int x, int y){
        Point vp = frame.drawPanel.vehiclePoints.get(vehiclePointIndex);
        vp.x = x;
        vp.y = y;
    }

}
