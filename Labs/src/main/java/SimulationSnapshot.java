import java.util.List;

public class SimulationSnapshot {
    private final List<VehicleState> vehicleStates;

    public SimulationSnapshot(List<VehicleState> vehicleStates) {
        this.vehicleStates = vehicleStates;
    }

    public List<VehicleState> getVehicleStates() {
        return vehicleStates;
    }
}
