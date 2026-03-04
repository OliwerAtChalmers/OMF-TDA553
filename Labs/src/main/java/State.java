public final class State {
    private final String name;
    private final int x;
    private final int y;

    public State(String modelName, int x, int y) {
        this.name = modelName;
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
