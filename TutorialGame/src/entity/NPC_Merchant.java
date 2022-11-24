package entity;

import main.GamePanel;

import java.util.Random;

public class NPC_Merchant extends Entity {
    private Random random;

    public NPC_Merchant(String name, int col, int row, GamePanel gp) {
        super(gp, name);
        this.worldX = col * gp.getTileSize();
        this.worldY = row * gp.getTileSize();
        this.speed = 1;
        this.random = new Random();
        this.isMoving = true;

        this.collisionAreaDefaultX = collisionArea.x;
        this.collisionAreaDefaultY = collisionArea.y;
        getEntityImage();
    }

    @Override
    public void getEntityImage() {
        down1 = imageSetup("05-devout.png", 96, 0);
        down2 = imageSetup("05-devout.png", 128, 0);
        left1 = imageSetup("05-devout.png", 96, 16);
        left2 = imageSetup("05-devout.png", 128, 16);
        right1 = imageSetup("05-devout.png", 96, 32);
        right2 = imageSetup("05-devout.png", 128, 32);
        up1 = imageSetup("05-devout.png", 96, 48);
        up2 = imageSetup("05-devout.png", 128, 48);
        standDown = imageSetup("05-devout.png", 112, 0);
    }

    @Override
    public void setBehavior() {
        behaviorLock++;
        if (behaviorLock >= 60) {
            int r = random.nextInt(5) + 1;
            if (r == 1)
                this.direction = "up";
            if (r == 2)
                this.direction = "right";
            if (r == 3)
                this.direction = "down";
            if (r == 4)
                this.direction = "left";
            if(r == 5)
                this.direction = "stand";

            behaviorLock = 0;
        }

    }

    @Override
    public void update() {

        //SET BEHAVIOR
        setBehavior();

        //ANIMATION FRAME
        spriteCounter++;
        if (spriteCounter > 24) {
            if (spriteAnimationFrame == 1)
                spriteAnimationFrame = 0;
            else
                spriteAnimationFrame = 1;

            spriteCounter = 0;
        }

        //CHECK TILE COLLISION
        int squareMove = gp.getTileSize() / speed;

        boolean out = true;
        while (out) {
            collision = false;
            gp.getCollisionDetector().checkTile(this);
            if (collision == false) {
                if (direction == "up") {
                    worldY -= speed;
                    out = false;
                }
                if (direction == "down") {
                    worldY += speed;
                    out = false;
                }
                if (direction == "left") {
                    worldX -= speed;
                    out = false;
                }
                if (direction == "right") {
                    worldX += speed;
                    out = false;
                }
                if (direction == "stand"){
                    out = false;
                }
            } else {
                setBehavior();
            }
        }

    }
}
