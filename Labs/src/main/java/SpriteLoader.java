import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SpriteLoader {
    private final String basePath;

    public SpriteLoader(String basePath) {
        this.basePath = basePath;
    }

    public Map<String, BufferedImage> loadSprites(ArrayList<String> fileNames) {
        Map<String, BufferedImage> sprites = new HashMap<>();
        for (String fileName : fileNames) {
            BufferedImage image = loadImage(fileName);
            if (image != null) {
                sprites.put(fileName, image);
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

    public ArrayList<String> loadImageNames(){
        String folderPath = "src/main/resources" + basePath;
        File folder = new File(folderPath);
        ArrayList<String> fileNames = new ArrayList<>();

        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();

            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        fileNames.add((file.getName()));
                    }
                }
            }
        }
        return fileNames;
    }
}
