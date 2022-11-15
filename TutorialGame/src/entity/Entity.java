package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int worldX, worldY;
    public int speed;

    public BufferedImage up1, up2, down1, down2, right1, right2, left1, left2, standDown, standUp, standLeft, standRight;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public boolean isMoving = false;
    public Rectangle collisionArea;
    public boolean collision = false;

}