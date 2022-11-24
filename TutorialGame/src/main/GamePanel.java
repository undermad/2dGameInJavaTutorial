package main;

import entity.Entity;
import entity.EntityManager;
import entity.Player;
import objects.ObjectManager;
import objects.SuperObject;
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
//    long lowestInterval = Long.MAX_VALUE;

    // SYSTEM
    Thread gameThread;
    KeyHandler keyH = new KeyHandler(this);
    CollisionDetector collisionDetector = new CollisionDetector(this);
    TileManager tileManager = new TileManager(this);
    Sound soundMusic = new Sound();
    Sound soundEffects = new Sound();
    UI ui = new UI(this);

    // PLAYER AND OBJECTS
    Player player = new Player("Ectimel", this, keyH);
    ObjectManager objectManager = new ObjectManager(this);
    EntityManager entityManager = new EntityManager(this);

    //STATE
    private int gameState;
    private final int playState = 1;
    private final int pauseState = 2;

    // SETTERS

    public void setGameState(int gameState) {
        this.gameState = gameState;
    }


    // GETTERS

    public int getGameState() {
        return gameState;
    }
    public int getPlayState() {
        return playState;
    }
    public int getPauseState() {
        return pauseState;
    }
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
    public EntityManager getEntityManager() {
        return entityManager;
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
    public void setupGame() {
        objectManager.createObjects();
        entityManager.createEntities();
        playMusic(0);
        gameState = playState;
    }

    public void playMusic(int i) {
        soundMusic.setFile(i);
        soundMusic.play();
        soundMusic.loop();
    }

    public void stopMusic() {
        soundMusic.stop();
    }

    public void playSoundEffect(int i) {
        soundEffects.setFile(i);
        soundEffects.play();
    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        // 1 000 000 000 nanoseconds = 1second
        double drawInterval = 1000000000f / FPS; // 16 666 666 = 0,01666 second
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

        if (gameState == playState) {
            //PLAYER
            player.update();

            //ENTITIES
            for (Entity e :
                    entityManager.getEntities()) {
                e.update();
            }

        } else if (gameState == pauseState) {
            System.out.println("Game paused");
        }

    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        //DEBUG
//        long drawStart = System.nanoTime();

        //TILES
        tileManager.draw(g2);

        //OBJECTS
        for (SuperObject o :
                objectManager.getObjects()) {
            o.draw(g2, this);
        }
//        for (int i = 0; i < objectManager.getObjects().size(); i++) {
//            objectManager.getObjects().get(i).draw(g2, this);
//        }
        //ENTITIES
        for (Entity e :
                entityManager.getEntities()) {
            e.draw(g2);
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
