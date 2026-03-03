import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpriteLoader {
    private static final String WORKSHOP_IMAGE = "VolvoBrand.jpg";
    private final String basePath;

    public SpriteLoader(String basePath) {
        this.basePath = basePath;
    }

    public Map<String, BufferedImage> loadSprites(List<String> modelNames) {
        Map<String, BufferedImage> sprites = new HashMap<>();
        for (String modelName : modelNames) {
            BufferedImage image = loadImage(modelName + ".jpg");
            if (image != null) {
                sprites.put(modelName, image);
            }
        }
        return sprites;
    }

    public BufferedImage loadImage(String fileName) {
        try {
            return ImageIO.read(SpriteLoader.class.getResourceAsStream(basePath + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public BufferedImage loadWorkshopImage() {
        return loadImage(WORKSHOP_IMAGE);
    }
}
