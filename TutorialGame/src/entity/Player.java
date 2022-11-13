package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyHandler;
    ArrayList<BufferedImage> animationFrames = new ArrayList<>();


    public Player(GamePanel gp, KeyHandler keyHandler) {
        this.gp = gp;
        this.keyHandler = keyHandler;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = 0;
        y = 0;
        speed = 5;
        direction = "down";
    }

    public void getPlayerImage() {

        try {
            animationFrames.add(ImageIO.read(getClass().getResourceAsStream("../sprites/player/player1.png")));
            animationFrames.add(ImageIO.read(getClass().getResourceAsStream("../sprites/player/player2.png")));
            animationFrames.add(ImageIO.read(getClass().getResourceAsStream("../sprites/player/player3.png")));
            animationFrames.add(ImageIO.read(getClass().getResourceAsStream("../sprites/player/player4.png")));
            animationFrames.add(ImageIO.read(getClass().getResourceAsStream("../sprites/player/player5.png")));
            animationFrames.add(ImageIO.read(getClass().getResourceAsStream("../sprites/player/player6.png")));
            animationFrames.add(ImageIO.read(getClass().getResourceAsStream("../sprites/player/player7.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {

        if(keyHandler.wKeypad == true || keyHandler.dKeypad == true ||
                keyHandler.aKeypad == true || keyHandler.dKeypad == true){
            spriteCounter++;
            if (spriteCounter > 10){
                if (spriteNum == 6)
                    spriteNum = 0;
                else
                    spriteNum++;

                spriteCounter = 0;
            }
        }

        if (keyHandler.wKeypad == true) {
            direction = "up";
            this.y -= speed;
        }
        if (keyHandler.sKeypad == true) {
            direction = "down";
            this.y += speed;
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

        BufferedImage image = animationFrames.get(spriteNum);

        g2.drawImage(image, x, y, gp.getTileSize(), gp.getTileSize(), null);

    }
}
