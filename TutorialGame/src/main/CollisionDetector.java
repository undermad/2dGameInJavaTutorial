package main;

import entity.Entity;
import entity.Player;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class CollisionDetector {
    GamePanel gp;

    public CollisionDetector(GamePanel gp) {
        this.gp = gp;
    }
    public void checkPlayer (Entity entity, Player player){
        // GET ENTITY COLLISION AREA
        entity.collisionArea.x = entity.worldX + entity.collisionArea.x;
        entity.collisionArea.y = entity.worldY + entity.collisionArea.y;

        // GET NPC COLLISION AREA
        player.getCollisionArea().x =  player.getWorldX() +  player.getCollisionArea().x;
        player.getCollisionArea().y =  player.getWorldY() +  player.getCollisionArea().y;

        switch (entity.direction) {
            case "up":
                entity.collisionArea.y -= entity.speed;
                if (entity.collisionArea.intersects( player.getCollisionArea())) {
                    entity.collision = true;
                }
                break;

            case "down":
                entity.collisionArea.y += entity.speed;
                if (entity.collisionArea.intersects( player.getCollisionArea())) {
                    entity.collision = true;
                }
                break;

            case "left":
                entity.collisionArea.x -= entity.speed;
                if (entity.collisionArea.intersects( player.getCollisionArea())) {
                    entity.collision = true;
                }
                break;

            case "right":
                entity.collisionArea.x += entity.speed;
                if (entity.collisionArea.intersects( player.getCollisionArea())) {
                    entity.collision = true;
                }
                break;
        }

        entity.collisionArea.x = entity.collisionAreaDefaultX;
        entity.collisionArea.y = entity.collisionAreaDefaultY;
        player.getCollisionArea().x = player.getCollisionAreaDefaultX();
        player.getCollisionArea().y = player.getCollisionAreaDefaultY();

    }

    public int checkNPC(Entity entity, CopyOnWriteArrayList<Entity> entities) {

        int index = 999;

        for (Entity npc : entities) {
            if (entities != null) {

                // GET PLAYER COLLISION AREA
                entity.collisionArea.x = entity.worldX + entity.collisionArea.x;
                entity.collisionArea.y = entity.worldY + entity.collisionArea.y;

                // GET NPC COLLISION AREA
                npc.getCollisionArea().x =  npc.getWorldX() +  npc.getCollisionArea().x;
                npc.getCollisionArea().y =  npc.getWorldY() +  npc.getCollisionArea().y;

                switch (entity.direction) {
                    case "up":
                        entity.collisionArea.y -= entity.speed;
                        if (entity.collisionArea.intersects( npc.getCollisionArea())) {
                            entity.collision = true;
                            index = entities.indexOf(npc);
                        }
                        break;

                    case "down":
                        entity.collisionArea.y += entity.speed;
                        if (entity.collisionArea.intersects( npc.getCollisionArea())) {
                            entity.collision = true;
                            index = entities.indexOf(npc);
                        }
                        break;

                    case "left":
                        entity.collisionArea.x -= entity.speed;
                        if (entity.collisionArea.intersects( npc.getCollisionArea())) {
                            entity.collision = true;
                            index = entities.indexOf(npc);
                        }
                        break;

                    case "right":
                        entity.collisionArea.x += entity.speed;
                        if (entity.collisionArea.intersects( npc.getCollisionArea())) {
                            entity.collision = true;
                            index = entities.indexOf(npc);
                        }
                        break;
                }

                entity.collisionArea.x = entity.collisionAreaDefaultX;
                entity.collisionArea.y = entity.collisionAreaDefaultY;
                npc.getCollisionArea().x = npc.getCollisionAreaDefaultX();
                npc.getCollisionArea().y = npc.getCollisionAreaDefaultY();
            }
        }
        return index;
    }

    public int checkObject(Entity entity, boolean player) {

        int index = 999;

        for (int i = 0; i < gp.objectManager.getObjects().size(); i++) {
            if (gp.objectManager.getObjects() != null) {

                // GET PLAYER COLLISION AREA
                entity.collisionArea.x = entity.worldX + entity.collisionArea.x;
                entity.collisionArea.y = entity.worldY + entity.collisionArea.y;

                // GET OBJECT COLLISION AREA
                gp.objectManager.getObjects().get(i).getCollisionArea().x = gp.objectManager.getObjects().get(i).getWorldX() + gp.objectManager.getObjects().get(i).getCollisionArea().x;
                gp.objectManager.getObjects().get(i).getCollisionArea().y = gp.objectManager.getObjects().get(i).getWorldY() + gp.objectManager.getObjects().get(i).getCollisionArea().y;

                switch (entity.direction) {
                    case "up":
                        entity.collisionArea.y -= entity.speed;
                        if (entity.collisionArea.intersects(gp.objectManager.getObjects().get(i).getCollisionArea())) {

                            if (player)
                                index = i;
                        }
                        break;
                    case "down":
                        entity.collisionArea.y += entity.speed;
                        if (entity.collisionArea.intersects(gp.objectManager.getObjects().get(i).getCollisionArea())) {

                            if (player)
                                index = i;
                        }
                        break;
                    case "left":
                        entity.collisionArea.x -= entity.speed;
                        if (entity.collisionArea.intersects(gp.objectManager.getObjects().get(i).getCollisionArea())) {
                            if (player)
                                index = i;
                        }

                        break;
                    case "right":
                        entity.collisionArea.x += entity.speed;
                        if (entity.collisionArea.intersects(gp.objectManager.getObjects().get(i).getCollisionArea())) {
                            if (player)
                                index = i;
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
