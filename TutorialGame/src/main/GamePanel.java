package main;

import entity.Player;
import objects.ObjectManager;
import tile.TileManager;

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

    // WORLD SETTINGS
    final int maxWorldCol = 50;
    final int maxWorldRow = 50;

    // FPS
    int FPS = 60;
    //DEBUG
    long lowestInterval = Long.MAX_VALUE;

    // SYSTEM
    Thread gameThread;
    KeyHandler keyH = new KeyHandler();
    CollisionDetector collisionDetector = new CollisionDetector(this);
    TileManager tileManager = new TileManager(this);
    ObjectManager objectManager = new ObjectManager(this);
    Sound soundMusic = new Sound();
    Sound soundEffects = new Sound();
    UI ui = new UI(this);

    // PLAYER AND OBJECTS
    Player player = new Player(this, keyH);

    // GETTERS
    public UI getUi() {
        return ui;
    }
    public int getFPS() {
        return FPS;
    }
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
    public TileManager getTileManager() {
        return tileManager;
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
    public void setupGame(){
        objectManager.setObjects();
        playMusic(0);
    }
    public void playMusic(int i){
        soundMusic.setFile(i);
        soundMusic.play();
        soundMusic.loop();
    }
    public void stopMusic(){
        soundMusic.stop();
    }
    public void playSoundEffect(int i){
        soundEffects.setFile(i);
        soundEffects.play();
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
            repaint(); // calling paintComponent method

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
                System.out.println("Speed: " + player.speed);

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

        //DEBUG
//        long drawStart = System.nanoTime();

        //TILES
        tileManager.draw(g2);

        //OBJECTS
        for (int i = 0; i<objectManager.getObjects().size(); i++){
            objectManager.getObjects().get(i).draw(g2, this);
        }
        //PLAYER
        player.draw(g2);

        //UI
        ui.draw(g2);

        g2.dispose();


        // DEBUG
//        long drawInterval = System.nanoTime() - drawStart;
//        if (drawInterval< lowestInterval)
//            lowestInterval = drawInterval;
//        System.out.println("lowest interval : "+lowestInterval);
//        System.out.println(drawInterval);

    }

}
