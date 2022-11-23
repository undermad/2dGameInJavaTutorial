package entity;

import main.GamePanel;

public class NPC_Merchant extends Entity{

    public NPC_Merchant(String name,int col, int row, GamePanel gp) {
        super(gp, name);
        this.worldX = col * gp.getTileSize();
        this.worldY = row * gp.getTileSize();
        this.speed = 0;

        this.collisionAreaDefaultX = collisionArea.x;
        this.collisionAreaDefaultY = collisionArea.y;
        getEntityImage();
    }

    @Override
    public void getEntityImage() {
        down1 = imageSetup("05-devout.png", 96,0);
        down2 = imageSetup("05-devout.png", 128,0);
        left1 = imageSetup("05-devout.png",96,16);
        left2 = imageSetup("05-devout.png",128,16);
        right1 = imageSetup("05-devout.png", 96,32);
        right2 = imageSetup("05-devout.png",128,32);
        up1 = imageSetup("05-devout.png",96,48);
        up2 = imageSetup("05-devout.png", 128,48);
        standDown = imageSetup("05-devout.png",112,0);
    }

    @Override
    public void update() {

    }
}
