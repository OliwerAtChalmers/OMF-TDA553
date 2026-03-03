import java.awt.*;

public final class State {
    private final String name;
    private final Point position;
    private final int direction;

    public State(String name, int x, int y, int direction) {
        this.name = name;
        position = new Point(x, y);
        this.direction = direction;
    }

    public String getName() {
        return name;
    }

    public Point getPosition() {
        return new Point(position);
    }

    public int getX() {
        return position.x;
    }

    public int getY() {
        return position.y;
    }

    public int getDirection() {
        return direction;
    }
}
