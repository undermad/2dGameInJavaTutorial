package main;

import entity.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    //SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile
    final int scaleFactor = 3;
    final int tileSize = originalTileSize * scaleFactor; // 48x48 tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768px
    final int screenHeight = tileSize * maxScreenRow; // 576px

    // CORE GAME SETTINGS
    int FPS = 60;
    Thread gameThread;
    KeyHandler keyH = new KeyHandler();
    Player player = new Player(this, keyH);

    // GETTERS
    public int getTileSize() {
        return tileSize;
    }

    // CONSTRUCTORS
    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    // METHODS
    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.run();
    }

    @Override
    public void run() {

        // 1 000 000 000 nanoseconds = 1second
        double drawInterval = 1000000000 / FPS; // 16 666 666 = 0,01666 second
        double nextDrawTime = System.nanoTime() + drawInterval;
        long currentTime;
        long lastTime = System.nanoTime();

        long timer = 0;
        int fpsCounter = 0;

        while (gameThread != null) {

            update();

            repaint();

            currentTime = System.nanoTime();
            timer += currentTime - lastTime;
            lastTime = currentTime;

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime /= 1000000;

                if (remainingTime < 0)
                    remainingTime = 0;

                Thread.sleep((long) remainingTime);

                nextDrawTime = System.nanoTime() + drawInterval;
                fpsCounter++;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if(timer >= 1000000000){
                System.out.println("FPS: " + fpsCounter);
                fpsCounter = 0;
                timer = 0;
            }
        }

    }

    public void update() {

        player.update();

    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        player.draw(g2);

        g2.dispose();

    }

}
