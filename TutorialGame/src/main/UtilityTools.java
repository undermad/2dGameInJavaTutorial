package main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UtilityTools {
    public static BufferedImage scaleImage(int scaleToX, int scaleToY, int originalImageSizeX, int originalImageSizeY, BufferedImage image) {
        BufferedImage scaledImage = new BufferedImage(originalImageSizeX, originalImageSizeY, image.getType());
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(image, 0, 0, scaleToX, scaleToY, null);
        g2.dispose();

        return scaledImage;
    }
}
