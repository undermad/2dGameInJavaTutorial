package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class UI {
    private GamePanel gp;
    private Graphics2D g2;
    private final Font SANS_SERIF;
    private BufferedImage smallHealthPotionImage;
    private String message;
    private boolean showMessage = false;
    private int messageDuration = 0;
    private int messageDurationCounter = 0;
    private int textLength;
    private int messageX;
    private int messageY;

    public UI(GamePanel gp) {
        this.gp = gp;
        this.SANS_SERIF = new Font("SANS_SERIF", Font.PLAIN, 25);
        try {
            smallHealthPotionImage = ImageIO.read(getClass().getResourceAsStream("/sprites/items/potions.png")).getSubimage(48, 224, 16, 16);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showMessage(String text, int seconds) {
        message = text;
        showMessage = true;
        messageDuration = seconds * gp.getFPS();
    }

    public void draw(Graphics2D g2) {

        this.g2 = g2;
        g2.setFont(SANS_SERIF);
        g2.setColor(Color.WHITE);
        g2.drawImage(smallHealthPotionImage, 0, 10, gp.getTileSize(), gp.getTileSize(), null);
        g2.drawString("" + gp.getPlayer().getHealthPotionsAmount(), 50, 45);

        if (showMessage) {
            textLength = g2.getFontMetrics().stringWidth(message);
            messageX = gp.screenWidth/2 - (textLength/5);
            messageY = gp.screenHeight - gp.getTileSize()/2;


            g2.setFont(SANS_SERIF.deriveFont(Font.ITALIC, 10F));
            g2.drawString(message, messageX, messageY);
            messageDurationCounter++;
            if (messageDurationCounter >= messageDuration) {
                showMessage = false;
                messageDuration = 0;
                messageDurationCounter = 0;
            }
        }
        if(gp.getGameState() == gp.getPlayState()){

        } else if (gp.getGameState() == gp.getPauseState()) {
            g2.setFont(SANS_SERIF.deriveFont(Font.CENTER_BASELINE, 50));
            drawPauseScreen();
        }

    }

    private void drawPauseScreen() {
        String text = "PAUSE";

        int x = drawOnScreenCenterX(text);
        int y = gp.getScreenHeight()/2;
        g2.drawString(text,x,y);

    }

    private int drawOnScreenCenterX(String text) {
        return (gp.getScreenWidth() / 2) - g2.getFontMetrics().stringWidth(text)/2;
    }

}

















