package main;

import entity.Entity;

public class CollisionDetector {
    GamePanel gp;

    public CollisionDetector(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity){
        int entityLeftWorldX = entity.worldX + entity.collisionArea.x;
        int entityRightWorldX = entity.worldX + entity.collisionArea.x + entity.collisionArea.width;
        int entityTopWorldY = entity.worldY + entity.collisionArea.y;
        int entityBottomWorldY = entity.worldY + entity.collisionArea.y + entity.collisionArea.height;

        int entityLeftCol = entityLeftWorldX / gp.getTileSize();
        int entityRightCol = entityRightWorldX/gp.getTileSize();
        int entityTopRow = entityTopWorldY / gp.getTileSize();
        int entityBottomRow = entityBottomWorldY/gp.getTileSize();

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed)/gp.getTileSize();
                tileNum1 = gp.tileManager.getWorldMapIndex()[entityTopRow][entityLeftCol];
                tileNum2 = gp.tileManager.getWorldMapIndex()[entityTopRow][entityRightCol];
                if (gp.tileManager.getTiles()[tileNum1].collision == true || gp.tileManager.getTiles()[tileNum2].collision == true){
                    entity.collision = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.getTileSize();
                tileNum1 = gp.tileManager.getWorldMapIndex()[entityBottomRow][entityLeftCol];
                tileNum2 = gp.tileManager.getWorldMapIndex()[entityBottomRow][entityRightCol];
                if (gp.tileManager.getTiles()[tileNum1].collision == true || gp.tileManager.getTiles()[tileNum2].collision == true){
                    entity.collision = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.getTileSize();
                tileNum1 = gp.tileManager.getWorldMapIndex()[entityTopRow][entityLeftCol];
                tileNum2 = gp.tileManager.getWorldMapIndex()[entityBottomRow][entityLeftCol];
                if (gp.tileManager.getTiles()[tileNum1].collision == true || gp.tileManager.getTiles()[tileNum2].collision == true){
                    entity.collision = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gp.getTileSize();
                tileNum1 = gp.tileManager.getWorldMapIndex()[entityTopRow][entityRightCol];
                tileNum2 = gp.tileManager.getWorldMapIndex()[entityBottomRow][entityRightCol];
                if (gp.tileManager.getTiles()[tileNum1].collision == true || gp.tileManager.getTiles()[tileNum2].collision == true){
                    entity.collision = true;
                }
                break;
        }

    }

}
