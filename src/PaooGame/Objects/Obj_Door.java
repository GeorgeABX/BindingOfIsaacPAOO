package PaooGame.Objects;

import PaooGame.Game;
import PaooGame.entity.Entity;

public class Obj_Door extends Entity {
    public Game gp;

    public Obj_Door(Game gp) {
        super(gp);
        this.gp = gp;
        name = "item_door";
        direction="";
        getImage();

    }

    public void getImage() {
        entityHeight = 48;
        entityWidth = 48;
        image=setup("/textures/objects/itemDoor1.png");
        /*up1 = setup("/textures/isaac/isaac_up1.png");
        up2 = setup("/textures/isaac/isaac_up2.png");
        down1 = setup("/textures/isaac/isaac_down1.png");
        down2 = setup("/textures/isaac/isaac_down2.png");
        left1 = setup("/textures/isaac/isaac_left1.png");
        left2 = setup("/textures/isaac/isaac_left2.png");
        right1 = setup("/textures/isaac/isaac_right1.png");
        right2 = setup("/textures/isaac/isaac_right2.png");
         */
    }
}