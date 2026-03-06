import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

// This panel represents the animated part of the view with the vehicle images.

public class DrawPanel extends JPanel{

    private Map<String, BufferedImage> sprites = new HashMap<>();
    private ArrayList<State> states = new ArrayList<>();


    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
    }

    public void setSprites(Map<String, BufferedImage> sprites) {
        this.sprites = sprites;
    }

    public void setStates(ArrayList<State> states) {
        this.states = states;
    }

    // This method is called each time the panel updates/refreshes/repaints itself
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (State state : states) {
            BufferedImage sprite = sprites.get(state.getName() + ".jpg");
            if (sprite != null) {
                g.drawImage(sprite, state.getX(), state.getY(), null);
            }
        }
    }
}
