public class VehicleState {
    private final String modelName;
    private final int x;
    private final int y;
    private final int direction;

    public VehicleState(String modelName, int x, int y, int direction) {
        this.modelName = modelName;
        this.x = x;
        this.y = y;
        this.direction = direction;
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

    public int getDirection() {
        return direction;
    }
}
