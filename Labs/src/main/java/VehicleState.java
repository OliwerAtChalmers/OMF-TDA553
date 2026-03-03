public class VehicleState {
    private final String modelName;
    private final int x;
    private final int y;

    public VehicleState(String modelName, int x, int y) {
        this.modelName = modelName;
        this.x = x;
        this.y = y;
    }

    public String getModelName() {
        return modelName;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
