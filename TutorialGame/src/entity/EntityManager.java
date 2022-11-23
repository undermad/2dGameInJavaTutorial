package entity;

import main.GamePanel;

import java.util.ArrayList;

public class EntityManager {
    private GamePanel gp;
    private ArrayList<Entity> entities;

    public EntityManager(GamePanel gp) {
        this.gp = gp;
        this.entities = new ArrayList<>();
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void createEntities () {
        entities.add(new NPC_Merchant( "George", 22,25, gp));
    }


}
