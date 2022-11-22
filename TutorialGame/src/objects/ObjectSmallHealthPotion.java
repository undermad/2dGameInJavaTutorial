package objects;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ObjectSmallHealthPotion extends SuperObject {

    public ObjectSmallHealthPotion(int column, int row, GamePanel gp) {
        this.name = "Small Health Potion";
        this.type = "Potion";
        this.collision = true;
        this.gp = gp;
        this.worldX = column * gp.getTileSize()+3;
        this.worldY = row * gp.getTileSize()+3;
        try {
            BufferedImage i = ImageIO.read(getClass().getResourceAsStream("/sprites/items/potions.png")).getSubimage(48, 224, 16, 16);
            this.image = gp.getTileManager().scaleImage(gp.getTileSize(),gp.getTileSize(),i);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onPickUp() {
        gp.playSoundEffect(1);
    }
}
