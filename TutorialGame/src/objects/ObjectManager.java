package objects;

import main.GamePanel;

import java.util.ArrayList;

public class ObjectManager {
    GamePanel gp;
    ArrayList<SuperObject> objects;

    public ObjectManager(GamePanel gp) {
        this.gp = gp;
        this.objects = new ArrayList<>();
    }

    public ArrayList<SuperObject> getObjects() {
        return objects;
    }

    public void setObjects() {

        objects.add(new ObjectSmallHealthPotion(23,22,gp));
    }
}
