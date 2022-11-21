package objects;

import main.GamePanel;

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
            this.image = new BufferedImage(gp.getTileSize(), gp.getTileSize(),i.getType());
            Graphics2D g2 = image.createGraphics();
            g2.drawImage(i,0,0,gp.getTileSize(),gp.getTileSize(),null);
            g2.dispose();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}