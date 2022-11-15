package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyHandler;
    private final int screenX;
    private final int screenY;


    //GETTERS
    public int getScreenX() {
        return screenX;
    }

    public int getScreenY() {
        return screenY;
    }

    public Player(GamePanel gp, KeyHandler keyHandler) {
        this.gp = gp;
        this.keyHandler = keyHandler;

        this.screenX = gp.getScreenWidth() / 2 - (gp.getTileSize() / 2); //POSITION ON SCREEN
        this.screenY = gp.getScreenHeight() / 2 - (gp.getTileSize() / 2);// POSITION ON SCREEN

        collisionArea = new Rectangle(8, 16, 32, 32);

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = (gp.getTileSize() * 25) - (gp.getTileSize() / 2); //POSITION IN WORLD
        worldY = (gp.getTileSize() * 25) - (gp.getTileSize() / 2); //POSITION IN WORLD
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {

        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/sprites/characters/06-conjurer.png")).getSubimage(0, 48, 16, 16);
            up2 = ImageIO.read(getClass().getResourceAsStream("/sprites/characters/06-conjurer.png")).getSubimage(32, 48, 16, 16);
            down1 = ImageIO.read(getClass().getResourceAsStream("/sprites/characters/06-conjurer.png")).getSubimage(0, 0, 16, 16);
            down2 = ImageIO.read(getClass().getResourceAsStream("/sprites/characters/06-conjurer.png")).getSubimage(32, 0, 16, 16);
            left1 = ImageIO.read(getClass().getResourceAsStream("/sprites/characters/06-conjurer.png")).getSubimage(0, 16, 16, 16);
            left2 = ImageIO.read(getClass().getResourceAsStream("/sprites/characters/06-conjurer.png")).getSubimage(32, 16, 16, 16);
            right1 = ImageIO.read(getClass().getResourceAsStream("/sprites/characters/06-conjurer.png")).getSubimage(0, 32, 16, 16);
            right2 = ImageIO.read(getClass().getResourceAsStream("/sprites/characters/06-conjurer.png")).getSubimage(32, 32, 16, 16);
            standDown = ImageIO.read(getClass().getResourceAsStream("/sprites/characters/06-conjurer.png")).getSubimage(16, 0, 16, 16);
            standUp = ImageIO.read(getClass().getResourceAsStream("/sprites/characters/06-conjurer.png")).getSubimage(16, 48, 16, 16);
            standLeft = ImageIO.read(getClass().getResourceAsStream("/sprites/characters/06-conjurer.png")).getSubimage(16, 16, 16, 16);
            standRight = ImageIO.read(getClass().getResourceAsStream("/sprites/characters/06-conjurer.png")).getSubimage(16, 32, 16, 16);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {

        if (keyHandler.wKeypad == true || keyHandler.dKeypad == true ||
                keyHandler.aKeypad == true || keyHandler.sKeypad == true) {
            spriteCounter++;
            if (spriteCounter > 6) {
                if (spriteNum == 1)
                    spriteNum = 0;
                else
                    spriteNum = 1;

                spriteCounter = 0;
            }
            isMoving = true;
        } else
            isMoving = false;

        if (keyHandler.wKeypad == true) {
            direction = "up";
        }
        if (keyHandler.sKeypad == true) {
            direction = "down";
        }
        if (keyHandler.aKeypad == true) {
            direction = "left";
        }
        if (keyHandler.dKeypad == true) {
            direction = "right";
        }


        //CHECK TILE COLLISION
        collision = false;
        gp.getCollisionDetector().checkTile(this);
        if (collision == false) {
            if (direction == "up")
                worldY -= speed;
            if (direction == "down")
                worldY += speed;
            if (direction == "left")
                worldX -= speed;
            if (direction == "right")
                worldX += speed;
        }
        System.out.println(this.direction);
    }




    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        if (isMoving) {
            switch (direction) {
                case "up":
                    if (spriteNum == 1)
                        image = up1;
                    else
                        image = up2;
                    break;
                case "down":
                    if (spriteNum == 1)
                        image = down1;
                    else
                        image = down2;
                    break;
                case "right":
                    if (spriteNum == 1)
                        image = right1;
                    else
                        image = right2;
                    break;

                case "left":
                    if (spriteNum == 1)
                        image = left1;
                    else
                        image = left2;
                    break;
            }
        } else
            image = standDown;


        g2.drawImage(image, screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);


    }
}
