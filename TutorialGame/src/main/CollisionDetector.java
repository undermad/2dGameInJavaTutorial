package main;

import entity.Entity;

public class CollisionDetector {
    GamePanel gp;

    public CollisionDetector(GamePanel gp) {
        this.gp = gp;
    }

    public int checkObject(Entity entity, boolean player) {

        int index = 999;

        for (int i = 0; i < gp.objectManager.getObjects().size(); i++) {
            if (gp.objectManager.getObjects() != null) {

                // GET PLAYER COLLISION AREA
                entity.collisionArea.x = (int) (entity.worldX + entity.collisionArea.getX());
                entity.collisionArea.y = (int) (entity.worldY + entity.collisionArea.getY());

                // GET OBJECT COLLISION AREA
                gp.objectManager.getObjects().get(i).getCollisionArea().x = (int) (gp.objectManager.getObjects().get(i).getWorldX() + gp.objectManager.getObjects().get(i).getCollisionArea().getX());
                gp.objectManager.getObjects().get(i).getCollisionArea().y = (int) (gp.objectManager.getObjects().get(i).getWorldY() + gp.objectManager.getObjects().get(i).getCollisionArea().getY());

                switch (entity.direction) {
                    case "up":
                        entity.collisionArea.y -= entity.speed;
                        if (gp.objectManager.getObjects().get(i).getCollisionArea().intersects(entity.collisionArea)) {
                            if (gp.objectManager.getObjects().get(i).isCollision()) {
                                entity.collision = true;
                            }
                            if (player)
                                return i;
                        }
                        break;
                    case "down":
                        entity.collisionArea.y += entity.speed;
                        if (gp.objectManager.getObjects().get(i).getCollisionArea().intersects(entity.collisionArea)) {
                            if (gp.objectManager.getObjects().get(i).isCollision()) {
                                entity.collision = true;
                            }
                            if (player)
                                return i;
                        }
                        break;
                    case "left":
                        entity.collisionArea.x -= entity.speed;
                        if (gp.objectManager.getObjects().get(i).getCollisionArea().intersects(entity.collisionArea)) {
                            if (gp.objectManager.getObjects().get(i).isCollision()) {
                                entity.collision = true;
                            }
                            if (player)
                                return i;
                        }
                        break;
                    case "right":
                        entity.collisionArea.x += entity.speed;
                        if (gp.objectManager.getObjects().get(i).getCollisionArea().intersects(entity.collisionArea)) {
                            if (gp.objectManager.getObjects().get(i).isCollision()) {
                                entity.collision = true;
                            }
                            if (player)
                                return i;
                        }
                        break;
                }
                entity.collisionArea.x = entity.collisionAreaDefaultX;
                entity.collisionArea.y = entity.collisionAreaDefaultY;
                gp.objectManager.getObjects().get(i).getCollisionArea().x = gp.objectManager.getObjects().get(i).getCollisionAreaDefaultX();
                gp.objectManager.getObjects().get(i).getCollisionArea().y = gp.objectManager.getObjects().get(i).getCollisionAreaDefaultY();
            }
        }
        return index;
    }

    public void checkTile(Entity entity) {
        int entityLeftWorldX = entity.worldX + entity.collisionArea.x;
        int entityRightWorldX = entity.worldX + entity.collisionArea.x + entity.collisionArea.width;
        int entityTopWorldY = entity.worldY + entity.collisionArea.y;
        int entityBottomWorldY = entity.worldY + entity.collisionArea.y + entity.collisionArea.height;

        int entityLeftCol = entityLeftWorldX / gp.getTileSize();
        int entityRightCol = entityRightWorldX / gp.getTileSize();
        int entityTopRow = entityTopWorldY / gp.getTileSize();
        int entityBottomRow = entityBottomWorldY / gp.getTileSize();

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.getTileSize();
                tileNum1 = gp.tileManager.getWorldMapIndex()[entityTopRow][entityLeftCol];
                tileNum2 = gp.tileManager.getWorldMapIndex()[entityTopRow][entityRightCol];
                if (gp.tileManager.getTiles()[tileNum1].collision == true || gp.tileManager.getTiles()[tileNum2].collision == true) {
                    entity.collision = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.getTileSize();
                tileNum1 = gp.tileManager.getWorldMapIndex()[entityBottomRow][entityLeftCol];
                tileNum2 = gp.tileManager.getWorldMapIndex()[entityBottomRow][entityRightCol];
                if (gp.tileManager.getTiles()[tileNum1].collision == true || gp.tileManager.getTiles()[tileNum2].collision == true) {
                    entity.collision = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.getTileSize();
                tileNum1 = gp.tileManager.getWorldMapIndex()[entityTopRow][entityLeftCol];
                tileNum2 = gp.tileManager.getWorldMapIndex()[entityBottomRow][entityLeftCol];
                if (gp.tileManager.getTiles()[tileNum1].collision == true || gp.tileManager.getTiles()[tileNum2].collision == true) {
                    entity.collision = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gp.getTileSize();
                tileNum1 = gp.tileManager.getWorldMapIndex()[entityTopRow][entityRightCol];
                tileNum2 = gp.tileManager.getWorldMapIndex()[entityBottomRow][entityRightCol];
                if (gp.tileManager.getTiles()[tileNum1].collision == true || gp.tileManager.getTiles()[tileNum2].collision == true) {
                    entity.collision = true;
                }
                break;
        }

    }

}
