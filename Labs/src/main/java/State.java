import java.awt.*;

public class State {
    final private String name;
    final private Point position;

    State(String name, int x, int y){
        this.name = name;
        this.position = new Point(x, y);
    }

    public String getName(){
        return name;
    }

    public Point getPosition() {
        return new Point(position);
    }
}
