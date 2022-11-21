package objects;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class SuperObject {
    protected String type;
    protected BufferedImage image;
    protected GamePanel gp;
    protected String name;
    protected int worldX, worldY;
    protected boolean collision = false;
    protected Rectangle collisionArea = new Rectangle(0,0,48,48);
    protected int collisionAreaDefaultX = 0;
    protected int collisionAreaDefaultY = 0;

    public String getType() {
        return type;
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

    public String getName() {
        return name;
    }

    public int getWorldX() {
        return worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    public boolean isCollision() {
        return collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }
    public abstract void onPickUp ();

    public void draw(Graphics2D g2, GamePanel gp) {

        int screenX = worldX - gp.getPlayer().worldX + gp.getPlayer().getPositionOnScreenX();
        int screenY = worldY - gp.getPlayer().worldY + gp.getPlayer().getPositionOnScreenY();

        if (worldX + gp.getTileSize() > gp.getPlayer().worldX - gp.getPlayer().getPositionOnScreenX() &&
                worldX - gp.getTileSize() < gp.getPlayer().worldX + gp.getPlayer().getPositionOnScreenX() &&
                worldY + gp.getTileSize() > gp.getPlayer().worldY - gp.getPlayer().getPositionOnScreenY() &&
                worldY - gp.getTileSize() < gp.getPlayer().worldY + gp.getPlayer().getPositionOnScreenY()) {

            g2.drawImage(image, screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);
        }

    }
}
