package PaooGame.Objects;

import PaooGame.Game;
import PaooGame.entity.Entity;

import java.awt.*;

public class Obj_heart extends Entity {
    public Game gp;

    public Obj_heart(Game gp) {
        super(gp);
        this.gp = gp;
        name = "pickup_heart";
        direction="";
        solidArea.x=0;
        solidArea.y=0;
        collisionOn=false;
        solidArea.height=13;
        solidArea.width=16;
        solidAreaDefaultY=solidArea.y;
        solidAreaDefaultX=solidArea.x;
        getImage();
    }
    public void getImage() {
        entityHeight = 13;
        entityWidth = 16;
        image=setup("/textures/objects/heart.png");
    }
    public void draw(Graphics g) {
        g.drawImage(image,x,y,entityWidth+entityWidth/2,entityHeight+entityHeight/2, null) ;
    }
}
