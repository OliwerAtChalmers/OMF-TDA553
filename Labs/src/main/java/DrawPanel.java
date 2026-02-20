import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

// This panel represents the animated part of the view with the vehicle images.

public class DrawPanel extends JPanel{

    ArrayList<Point> vehiclePoints = new ArrayList<>();
    ArrayList<String> imageFileNames = new ArrayList<>();
    ArrayList<BufferedImage> imageList = new ArrayList<>();

    BufferedImage volvoWorkshopImage;
    Point volvoWorkshopPoint = new Point(300,300);

    void moveit(int vehiclePointIndex, int x, int y){
        Point vp = vehiclePoints.get(vehiclePointIndex);
        vp.x = x;
        vp.y = y;
    }

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
    }

    public void initImages(){
        try {
            for(String fileName: imageFileNames)
                imageList.add(ImageIO.read(DrawPanel.class.getResourceAsStream("/pics/" + fileName)));
            volvoWorkshopImage = ImageIO.read(DrawPanel.class.getResourceAsStream("/pics/VolvoBrand.jpg"));
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    // This method is called each time the panel updates/refreshes/repaints itself
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(int i = 0; i < vehiclePoints.size(); i++)
            g.drawImage(imageList.get(i), vehiclePoints.get(i).x, vehiclePoints.get(i).y, null); // see javadoc for more info on the parameters
        g.drawImage(volvoWorkshopImage, volvoWorkshopPoint.x, volvoWorkshopPoint.y, null);
    }
}
