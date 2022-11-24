package entity;

import main.GamePanel;
import main.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {

    KeyHandler keyHandler;

    private final int positionOnScreenX;
    private final int positionOnScreenY;
    private int healthPotionsAmount = 0;


    public Player(String name, GamePanel gp, KeyHandler keyHandler) {
        super(gp, name);
        this.keyHandler = keyHandler;

        this.positionOnScreenX = gp.getScreenWidth() / 2 - (gp.getTileSize() / 2); //POSITION ON SCREEN
        this.positionOnScreenY = gp.getScreenHeight() / 2 - (gp.getTileSize() / 2);// POSITION ON SCREEN

        collisionArea = new Rectangle(10, 20, 30, 24); //
        this.collisionAreaDefaultX = collisionArea.x;
        this.collisionAreaDefaultY = collisionArea.y;

        setDefaultValues();
        getEntityImage();
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

    @Override
    public void getEntityImage() {
        up1 = imageSetup("06-conjurer.png", 0, 48);
        up2 = imageSetup("06-conjurer.png", 32, 48);
        down1 = imageSetup("06-conjurer.png", 0, 0);
        down2 = imageSetup("06-conjurer.png", 32, 0);
        left1 = imageSetup("06-conjurer.png", 0, 16);
        left2 = imageSetup("06-conjurer.png", 32, 16);
        right1 = imageSetup("06-conjurer.png", 0, 32);
        right2 = imageSetup("06-conjurer.png", 32, 32);
        standDown = imageSetup("06-conjurer.png", 16, 0);
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

    @Override
    public void setBehavior() {
    }
    @Override
    public void update() {


        if (keyHandler.wKeypad == true || keyHandler.dKeypad == true ||
                keyHandler.aKeypad == true || keyHandler.sKeypad == true) {
            spriteCounter++;
            if (spriteCounter > 6) {
                if (spriteAnimationFrame == 1)
                    spriteAnimationFrame = 0;
                else
                    spriteAnimationFrame = 1;

                spriteCounter = 0;
            }
            this.isMoving = true;

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

    @Override
    public void draw(Graphics2D g2) {

        BufferedImage image = drawAnimationFrame();
        g2.drawImage(image, positionOnScreenX, positionOnScreenY, null);


    }
}
