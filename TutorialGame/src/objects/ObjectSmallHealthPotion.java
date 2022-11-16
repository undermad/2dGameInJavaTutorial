package objects;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ObjectSmallHealthPotion extends SuperObject {

    public ObjectSmallHealthPotion(int column, int row, GamePanel gp) {
        this.name = "Small Health Potion";
        this.collision = false;
        this.worldX = column * gp.getTileSize();
        this.worldY = row * gp.getTileSize();
        this.gp = gp;
        try {
            this.image = ImageIO.read(getClass().getResourceAsStream("/sprites/items/potions.png")).getSubimage(48, 224, 16, 16);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
