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


    public Player(GamePanel gp, KeyHandler keyHandler) {
        this.gp = gp;
        this.keyHandler = keyHandler;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = 0;
        y = 0;
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
            y -= speed;
        }
        if (keyHandler.sKeypad == true) {
            direction = "down";
            y += speed;
        }
        if (keyHandler.aKeypad == true) {
            direction = "left";
            x -= speed;
        }
        if (keyHandler.dKeypad == true) {
            direction = "right";
            x += speed;
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


        g2.drawImage(image, x, y, gp.getTileSize(), gp.getTileSize(), null);

    }
}
