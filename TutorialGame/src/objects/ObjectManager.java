package objects;

import main.GamePanel;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class ObjectManager {

    private GamePanel gp;
    private CopyOnWriteArrayList<SuperObject> objects;

    public ObjectManager(GamePanel gp) {
        this.gp = gp;
        this.objects = new CopyOnWriteArrayList<>();
    }

    public CopyOnWriteArrayList<SuperObject> getObjects() {
        return objects;
    }

    public void createObjects() {

        objects.add(new ObjectSmallHealthPotion(23,22,gp));
        objects.add(new ObjectBook(23,23,gp));
        objects.add(new ObjectSmallSpeedPotion(23,24,gp));
    }

}
