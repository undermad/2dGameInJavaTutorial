package objects;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ObjectSmallSpeedPotion extends SuperObject{
    public ObjectSmallSpeedPotion(int col, int row, GamePanel gp) {
        this.name = "Small Speed Potion";
        this.type = "Potion";
        this.gp = gp;
        this.worldX = col * gp.getTileSize()+2;
        this.worldY = row * gp.getTileSize()+3;
        this.collision = false;
        try {
            BufferedImage i = ImageIO.read(getClass().getResourceAsStream("/sprites/items/potions.png")).getSubimage(144, 224, 16, 16);
            this.image = gp.getTileManager().scaleImage(gp.getTileSize(),gp.getTileSize(),i);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPickUp() {
        gp.playSoundEffect(1);
        gp.getPlayer().speed += 1;
    }

}
