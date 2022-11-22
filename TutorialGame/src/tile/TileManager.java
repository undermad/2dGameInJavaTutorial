package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class TileManager {

    private final GamePanel gp;
    private final Tile[] tiles;
    private final int[][] worldMapIndex;
    private final int numberOfTiles = 4;

    public TileManager(GamePanel gp) {

        this.gp = gp;
        this.tiles = new Tile[numberOfTiles];
        this.worldMapIndex = new int[gp.getMaxWorldCol()][gp.getMaxWorldRow()];
        getTileImage();
        loadMap("/maps/map01.txt");

    }

    public int[][] getWorldMapIndex() {
        return worldMapIndex;
    }

    public Tile[] getTiles() {
        return tiles;
    }


    public BufferedImage scaleImage(int x, int y, BufferedImage image) {
        BufferedImage scaledImage = new BufferedImage(gp.getTileSize(), gp.getTileSize(), image.getType());
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(image, 0, 0, x, y, null);
        g2.dispose();

        return scaledImage;
    }
    public void setup (int index, String imagePath, int drawFromX, int drawFromY, boolean collision){
        tiles[index] = new Tile();
        tiles[index].collision = collision;
        try {
            tiles[index].image = ImageIO.read(getClass().getResourceAsStream("/sprites/tiles/" + imagePath)).getSubimage(drawFromX, drawFromY, 16, 16);
            tiles[index].image = scaleImage(gp.getTileSize(),gp.getTileSize(), tiles[index].image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void getTileImage() {

        setup(0, "tileset.png",0,16,false);
        setup(1, "tileset.png",16,16,true);
        setup(2, "tileset.png",32,16,true);
        setup(3, "tileset.png",16,0,true);

    }

    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            for (int i = 0; i < gp.getMaxWorldRow(); i++) {
                String[] line = br.readLine().split("\\s+");

                for (int j = 0; j < gp.getMaxWorldCol(); j++) {
                    int t = Integer.parseInt(line[j]);

                    worldMapIndex[i][j] = t;
                }
            }

            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {

        for (int i = 0; i < gp.getMaxWorldRow(); i++) {
            for (int j = 0; j < gp.getMaxWorldCol(); j++) {
                int worldX = j * gp.getTileSize(); // WHERE TILE IS IN THE WORLD
                int worldY = i * gp.getTileSize(); // WHERE TILE IS IN THE WORLD
                int screenX = worldX - gp.getPlayer().worldX + gp.getPlayer().getPositionOnScreenX(); // WHERE IT SHOULD RENDER ON SCREEN
                int screenY = worldY - gp.getPlayer().worldY + gp.getPlayer().getPositionOnScreenY(); //  WHERE IT SHOULD BE RENDERED ON SCREEN

                if (worldX + gp.getTileSize() > gp.getPlayer().worldX - gp.getPlayer().getPositionOnScreenX() &&
                        worldX - gp.getTileSize() < gp.getPlayer().worldX + gp.getPlayer().getPositionOnScreenX() &&
                        worldY + gp.getTileSize() > gp.getPlayer().worldY - gp.getPlayer().getPositionOnScreenY() &&
                        worldY - gp.getTileSize() < gp.getPlayer().worldY + gp.getPlayer().getPositionOnScreenY()) {

                    g2.drawImage(tiles[worldMapIndex[i][j]].image, screenX, screenY, null);

                }
            }
        }

    }

}
