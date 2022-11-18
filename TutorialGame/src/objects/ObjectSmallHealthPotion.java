package objects;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ObjectSmallHealthPotion extends SuperObject {

    public ObjectSmallHealthPotion(int column, int row, GamePanel gp) {
        this.name = "Small Health Potion";
        this.collision = true;
        this.worldX = column * gp.getTileSize();
        this.worldY = row * gp.getTileSize();
        this.gp = gp;
        try {
            BufferedImage i = ImageIO.read(getClass().getResourceAsStream("/sprites/items/potions.png")).getSubimage(48, 224, 16, 16);
            this.image = new BufferedImage(gp.getTileSize(), gp.getTileSize(),i.getType());
            Graphics2D g2 = image.createGraphics();
            g2.drawImage(i,0,0,gp.getTileSize(),gp.getTileSize(),null);
            g2.dispose();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
