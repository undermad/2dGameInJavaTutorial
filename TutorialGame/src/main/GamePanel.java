package main;

import entity.Player;
import objects.ObjectManager;
import objects.SuperObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {

    //SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile
    final int scaleFactor = 3;
    final int tileSize = originalTileSize * scaleFactor; // 48x48 tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768px
    final int screenHeight = tileSize * maxScreenRow; // 576px

    // WORLD SETTINGS
    final int maxWorldCol = 50;
    final int maxWorldRow = 50;
    final int worldWidth = tileSize * maxWorldCol;
    final int worldHeight = tileSize * maxWorldRow;

    // CORE GAME SETTINGS
    int FPS = 60;
    Thread gameThread;
    CollisionDetector collisionDetector = new CollisionDetector(this);
    KeyHandler keyH = new KeyHandler();
    Player player = new Player(this, keyH);
    TileManager tileManager = new TileManager(this);
    ObjectManager objectManager = new ObjectManager(this);

    // GETTERS

    public ObjectManager getObjectManager() {
        return objectManager;
    }
    public int getTileSize() {
        return tileSize;
    }
    public int getMaxWorldCol() {
        return maxWorldCol;
    }
    public int getMaxWorldRow() {
        return maxWorldRow;
    }
    public int getWorldWidth() {
        return worldWidth;
    }
    public int getWorldHeight() {
        return worldHeight;
    }
    public Player getPlayer() {
        return player;
    }
    public int getScreenWidth() {
        return screenWidth;
    }
    public int getScreenHeight() {
        return screenHeight;
    }
    public CollisionDetector getCollisionDetector() {
        return collisionDetector;
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
    public void setObjects(){
        objectManager.setObjects();
    }
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

            if (timer >= 1000000000) {
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


        //TILES
        tileManager.draw(g2);
        
        //OBJECTS
        for (SuperObject o :
                objectManager.getObjects()) {
            o.draw(g2, this);
        }
        
        //PLAYER
        player.draw(g2);
        g2.dispose();

    }

}
