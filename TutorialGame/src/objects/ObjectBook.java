package objects;

import main.GamePanel;
import main.UtilityTools;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ObjectBook extends SuperObject{

    public ObjectBook(int col, int row, GamePanel gp) {
        this.name = "Book";
        this.collision = false;
        this.gp = gp;
        this.worldX = col * gp.getTileSize();
        this.worldY = row * gp.getTileSize();
        try {
            BufferedImage i = ImageIO.read(getClass().getResourceAsStream("/sprites/items/books.png")).getSubimage(48, 0, 16, 16);
            this.image = UtilityTools.scaleImage(gp.getTileSize(),gp.getTileSize(),gp.getTileSize(),gp.getTileSize(),i);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onPickUp() {
        gp.playSoundEffect(2);
    }
}
