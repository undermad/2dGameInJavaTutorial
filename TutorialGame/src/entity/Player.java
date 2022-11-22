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

    private final int positionOnScreenX;
    private final int positionOnScreenY;
    private int healthPotionsAmount = 0;


    public Player(GamePanel gp, KeyHandler keyHandler) {
        this.gp = gp;
        this.keyHandler = keyHandler;

        this.positionOnScreenX = gp.getScreenWidth() / 2 - (gp.getTileSize() / 2); //POSITION ON SCREEN
        this.positionOnScreenY = gp.getScreenHeight() / 2 - (gp.getTileSize() / 2);// POSITION ON SCREEN

        collisionArea = new Rectangle(10, 20, 30, 24); //
        this.collisionAreaDefaultX = collisionArea.x;
        this.collisionAreaDefaultY = collisionArea.y;

        setDefaultValues();
        getPlayerImage();
    }
    //GETTERS
    public int getHealthPotionsAmount() {
        return healthPotionsAmount;
    }
    public int getPositionOnScreenX() {
        return positionOnScreenX;
    }
    public int getPositionOnScreenY() {
        return positionOnScreenY;
    }
    public void getPlayerImage() {

        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/sprites/characters/06-conjurer.png")).getSubimage(0, 48, 16, 16);
            up1 = gp.getTileManager().scaleImage(gp.getTileSize(),gp.getTileSize(),up1);
            up2 = ImageIO.read(getClass().getResourceAsStream("/sprites/characters/06-conjurer.png")).getSubimage(32, 48, 16, 16);
            up2 = gp.getTileManager().scaleImage(gp.getTileSize(),gp.getTileSize(), up2);
            down1 = ImageIO.read(getClass().getResourceAsStream("/sprites/characters/06-conjurer.png")).getSubimage(0, 0, 16, 16);
            down1 = gp.getTileManager().scaleImage(gp.getTileSize(),gp.getTileSize(), down1);
            down2 = ImageIO.read(getClass().getResourceAsStream("/sprites/characters/06-conjurer.png")).getSubimage(32, 0, 16, 16);
            down2 = gp.getTileManager().scaleImage(gp.getTileSize(),gp.getTileSize(), down2);
            left1 = ImageIO.read(getClass().getResourceAsStream("/sprites/characters/06-conjurer.png")).getSubimage(0, 16, 16, 16);
            left1 = gp.getTileManager().scaleImage(gp.getTileSize(),gp.getTileSize(), left1);
            left2 = ImageIO.read(getClass().getResourceAsStream("/sprites/characters/06-conjurer.png")).getSubimage(32, 16, 16, 16);
            left2 = gp.getTileManager().scaleImage(gp.getTileSize(),gp.getTileSize(), left2);
            right1 = ImageIO.read(getClass().getResourceAsStream("/sprites/characters/06-conjurer.png")).getSubimage(0, 32, 16, 16);
            right1 = gp.getTileManager().scaleImage(gp.getTileSize(),gp.getTileSize(), right1);
            right2 = ImageIO.read(getClass().getResourceAsStream("/sprites/characters/06-conjurer.png")).getSubimage(32, 32, 16, 16);
            right2 = gp.getTileManager().scaleImage(gp.getTileSize(),gp.getTileSize(), right2);

            standDown = ImageIO.read(getClass().getResourceAsStream("/sprites/characters/06-conjurer.png")).getSubimage(16, 0, 16, 16);
            standDown = gp.getTileManager().scaleImage(gp.getTileSize(),gp.getTileSize(), standDown);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setDefaultValues() {
        worldX = (gp.getTileSize() * 25) - (gp.getTileSize() / 2); //POSITION IN WORLD
        worldY = (gp.getTileSize() * 25) - (gp.getTileSize() / 2); //POSITION IN WORLD
        speed = 4;
        direction = "down";
    }
    public void pickUpObject(int i) {
        if (i != 999) {
            String item = gp.getObjectManager().getObjects().get(i).getName();

            switch (item) {
                case "Small Health Potion":
                    healthPotionsAmount++;
                    gp.getObjectManager().getObjects().get(i).onPickUp();
                    gp.getObjectManager().getObjects().remove(i);
                    gp.getUi().showMessage("Small Health Potion added to your inventory.", 2);
                    break;
                case "Small Speed Potion":
                    gp.getObjectManager().getObjects().get(i).onPickUp();
                    gp.getObjectManager().getObjects().remove(i);
                    gp.getUi().showMessage("Drinking speed potion! Wrrrrrrrrrrrrrruuummmmmm!", 2);
                    break;
                case "Book":
                    gp.getObjectManager().getObjects().get(i).onPickUp();
                    gp.getObjectManager().getObjects().remove(i);
                    gp.getUi().showMessage("I feel incredible smart!", 2);
                    break;

            }
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

            //CHECK TILE COLLISION
            collision = false;
            gp.getCollisionDetector().checkTile(this);
            if (collision == false) {
                if (direction == "up") worldY -= speed;
                if (direction == "down") worldY += speed;
                if (direction == "left") worldX -= speed;
                if (direction == "right") worldX += speed;
            }

            //CHECK OBJECT COLLISION
            pickUpObject(gp.getCollisionDetector().checkObject(this, true));

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


        g2.drawImage(image, positionOnScreenX, positionOnScreenY, null);


    }
}
