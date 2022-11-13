package entity;

import main.GamePanel;
import main.KeyHandler;

import java.awt.*;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyHandler;

    public Player(GamePanel gp, KeyHandler keyHandler) {
        this.gp = gp;
        this.keyHandler = keyHandler;
        setDefaultValues();
    }

    public void setDefaultValues (){
        x = 0;
        y = 0;
        speed = 5;
    }

    public void update() {

        if (keyHandler.wKeypad == true) {
            y -= speed;
        }
        if (keyHandler.sKeypad == true) {
            y += speed;
        }
        if (keyHandler.aKeypad == true) {
            x -= speed;
        }
        if (keyHandler.dKeypad == true) {
            x += speed;
        }

    }
    public void draw (Graphics2D g2) {
        g2.setColor(Color.WHITE);
        g2.drawRect(x, y, gp.getTileSize(), gp.getTileSize());
    }
}
