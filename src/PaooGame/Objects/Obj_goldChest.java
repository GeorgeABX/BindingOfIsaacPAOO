package PaooGame.Objects;

import PaooGame.Game;
import PaooGame.entity.Entity;

import java.awt.*;

public class Obj_goldChest extends Entity {
    public Game gp;

    public Obj_goldChest(Game gp) {
        super(gp);
        this.gp = gp;
        name = "gold_chest";
        direction="";
        collisionOn=true;
        solidArea.x=0;
        solidArea.y=0;
        solidArea.height=23;
        solidArea.width=30;
        solidAreaDefaultY=solidArea.y;
        solidAreaDefaultX=solidArea.x;
        getImage();
    }
    public void getImage() {
        entityHeight = 23;
        entityWidth = 30;
        image=setup("/textures/objects/gold_chest.png");
    }
    public void draw(Graphics g) {
        g.drawImage(image,x,y,entityWidth+entityWidth/2,entityHeight+entityHeight/2, null) ;
    }
}
