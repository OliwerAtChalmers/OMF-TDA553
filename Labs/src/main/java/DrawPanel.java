import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel{

    // Just a single image, TODO: Generalize
    BufferedImage volvoImage;
    // To keep track of a single car's position
    Point volvoPoint = new Point();

    ArrayList<Point> vehiclePoints = new ArrayList<>();
    ArrayList<String> vehicleNames = new ArrayList<>();
    ArrayList<BufferedImage> vehicleImages = new ArrayList<>();

    BufferedImage volvoWorkshopImage;
    Point volvoWorkshopPoint = new Point(300,300);


    // TODO: Make this general for all cars
    void moveit(int i, int x, int y){
        vehiclePoints.get(i).x = x;
        vehiclePoints.get(i).y = y;

    }

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y, ArrayList<Vehicle> cars) {
        for(int i = 0; i < cars.size(); i++){
            Vehicle c = cars.get(i);
            c.dx = i*30;
            c.dy = i*30;
            vehiclePoints.add(new Point());
            vehicleNames.add(c.getModelName());
        }

        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        // Print an error message in case file is not found with a try/catch block
        try {
            for (String s: vehicleNames)
                vehicleImages.add(ImageIO.read(new File("C:\\Users\\oliwe\\Documents\\GitHub\\OMF-TDA553\\Labs\\src\\main\\java\\pics\\" + s + ".jpg")));
            // You can remove the "pics" part if running outside of IntelliJ and
            // everything is in the same main folder.
            // volvoImage = ImageIO.read(new File("Volvo240.jpg"));

            // Rememember to rightclick src New -> Package -> name: pics -> MOVE *.jpg to pics.
            // if you are starting in IntelliJ.
            volvoWorkshopImage = ImageIO.read(new File("C:\\Users\\oliwe\\Documents\\GitHub\\OMF-TDA553\\Labs\\src\\main\\java\\pics\\VolvoBrand.jpg"));
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }

    }

    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < vehiclePoints.size(); i++){
            Point vp = vehiclePoints.get(i);
            BufferedImage img = vehicleImages.get(i);
            g.drawImage(img, vp.x, vp.y, null); // see javadoc for more info on the parameters
        }
        g.drawImage(volvoWorkshopImage, volvoWorkshopPoint.x, volvoWorkshopPoint.y, null);
    }
}
