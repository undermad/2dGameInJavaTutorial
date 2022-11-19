package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public class TileManager {

    private final GamePanel gp;
    private final Tile[] tiles;
    private final int[][] worldMapIndex;

    public TileManager(GamePanel gp) {

        this.gp = gp;
        this.tiles = new Tile[10];
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

    public void getTileImage() {

        try {
            // GROUND
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/sprites/dungeon/tileset.png")).getSubimage(0, 16, 16, 16);


            //
            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/sprites/dungeon/tileset.png")).getSubimage(16, 16, 16, 16);
            tiles[1].collision = true;


            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/sprites/dungeon/tileset.png")).getSubimage(32, 16, 16, 16);
            tiles[2].collision = true;


            // WALL
            tiles[3] = new Tile();
            tiles[3].image = ImageIO.read(getClass().getResourceAsStream("/sprites/dungeon/tileset.png")).getSubimage(16, 0, 16, 16);
            tiles[3].collision = true;


        } catch (IOException e) {
            e.printStackTrace();
        }

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

                if (worldX + gp.getTileSize()> gp.getPlayer().worldX - gp.getPlayer().getPositionOnScreenX() &&
                        worldX -gp.getTileSize() < gp.getPlayer().worldX + gp.getPlayer().getPositionOnScreenX() &&
                        worldY + gp.getTileSize() > gp.getPlayer().worldY - gp.getPlayer().getPositionOnScreenY() &&
                        worldY - gp.getTileSize() < gp.getPlayer().worldY + gp.getPlayer().getPositionOnScreenY()) {

                    g2.drawImage(tiles[worldMapIndex[i][j]].image, screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);

                }
            }
        }

    }

}
