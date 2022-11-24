package entity;

import main.GamePanel;
import main.UtilityTools;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class Entity {
    GamePanel gp;
    public int worldX, worldY;
    public int speed;
    public String name;
    public int behaviorLock = 0;
    public Player player;

    public BufferedImage up1, up2, down1, down2, right1, right2, left1, left2, standDown, standUp, standLeft, standRight;
    public String direction = "down";
    public int spriteCounter = 0;
    public int spriteAnimationFrame = 1;
    public boolean isMoving = false;
    public Rectangle collisionArea = new Rectangle(0,0,48,48);
    public int collisionAreaDefaultX, collisionAreaDefaultY;
    public boolean collision = false;

    public Entity(GamePanel gp, String name) {
        this.name = name;
        this.gp = gp;
        this.player = gp.getPlayer();
    }

    public int getWorldX() {
        return worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    public Rectangle getCollisionArea() {
        return collisionArea;
    }

    public int getCollisionAreaDefaultX() {
        return collisionAreaDefaultX;
    }

    public int getCollisionAreaDefaultY() {
        return collisionAreaDefaultY;
    }

    public abstract void update();
    public abstract void getEntityImage();
    public abstract void setBehavior();

    public void draw(Graphics2D g2){
        int screenX = worldX - gp.getPlayer().worldX + gp.getPlayer().getPositionOnScreenX();
        int screenY = worldY - gp.getPlayer().worldY + gp.getPlayer().getPositionOnScreenY();

        if (worldX + gp.getTileSize() > gp.getPlayer().worldX - gp.getPlayer().getPositionOnScreenX() &&
                worldX - gp.getTileSize() < gp.getPlayer().worldX + gp.getPlayer().getPositionOnScreenX() &&
                worldY + gp.getTileSize() > gp.getPlayer().worldY - gp.getPlayer().getPositionOnScreenY() &&
                worldY - gp.getTileSize() < gp.getPlayer().worldY + gp.getPlayer().getPositionOnScreenY()) {

            BufferedImage image = drawAnimationFrame();

            g2.drawImage(image, screenX, screenY, null);
        }
    }
    public BufferedImage imageSetup(String path, int x, int y) {
        BufferedImage image = null;
        try {
            BufferedImage i = ImageIO.read(getClass().getResourceAsStream("/sprites/characters/" + path)).getSubimage(x, y, 16, 16);
            image = UtilityTools.scaleImage(gp.getTileSize(), gp.getTileSize(), gp.getTileSize(), gp.getTileSize(), i);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public BufferedImage drawAnimationFrame(){
        BufferedImage image = null;
        if (isMoving) {
            switch (direction) {
                case "up":
                    if (spriteAnimationFrame == 1)
                        image = up1;
                    else
                        image = up2;
                    break;
                case "down":
                    if (spriteAnimationFrame == 1)
                        image = down1;
                    else
                        image = down2;
                    break;
                case "right":
                    if (spriteAnimationFrame == 1)
                        image = right1;
                    else
                        image = right2;
                    break;

                case "left":
                    if (spriteAnimationFrame == 1)
                        image = left1;
                    else
                        image = left2;
                    break;
                case "stand":
                    image = standDown;
            }
        } else
            image = standDown;

        return image;
    }
}
