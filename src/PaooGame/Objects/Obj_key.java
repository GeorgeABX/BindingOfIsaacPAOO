package PaooGame.Objects;

import PaooGame.Game;
import PaooGame.entity.Entity;

import java.awt.*;

public class Obj_key extends Entity {
    public Game gp;

    public Obj_key(Game gp) {
        super(gp);
        this.gp = gp;
        name = "key";
        direction="";
        solidArea.x=16;
        solidArea.y=13;
        collisionOn=true;
        solidArea.height=22;
        solidArea.width=15;
        solidAreaDefaultY=solidArea.y;
        solidAreaDefaultX=solidArea.x;
        getImage();
    }
    public void getImage() {
        entityHeight = 48;
        entityWidth = 48;
        image=setup("/textures/objects/key.png");
    }
    public void draw(Graphics g) {
        g.drawImage(image,x,y,entityWidth+entityWidth/2,entityHeight+entityHeight/2, null) ;
    }
}
