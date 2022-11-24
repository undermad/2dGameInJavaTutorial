package entity;

import main.GamePanel;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class EntityManager {
    private GamePanel gp;
    private CopyOnWriteArrayList<Entity> entities;

    public EntityManager(GamePanel gp) {
        this.gp = gp;
        this.entities = new CopyOnWriteArrayList<>();
    }

    public CopyOnWriteArrayList<Entity> getEntities() {
        return entities;
    }

    public void createEntities () {
        entities.add(new NPC_Merchant( "George", 22,25, gp));
    }


}
