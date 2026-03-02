import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class SpriteLoader {
    public BufferedImage load(String fileName){
        try {
            return ImageIO.read(DrawPanel.class.getResourceAsStream("/pics/" + fileName));
        } catch (IOException ex)
        {
            ex.printStackTrace();
            return null;
        }
    }

    public ArrayList<BufferedImage> loadSprites(ArrayList<String> fileNames){
        ArrayList<BufferedImage> sprites = new ArrayList<>();
        for (String fileName : fileNames)
            sprites.add(load(fileName));

        return sprites;
    }
}
