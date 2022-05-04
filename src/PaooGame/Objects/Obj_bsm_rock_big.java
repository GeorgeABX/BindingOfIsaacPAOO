package PaooGame.Objects;

import PaooGame.Game;
import PaooGame.entity.Entity;

import java.awt.*;

public class Obj_bsm_rock_big extends Entity {
    public Game gp;

    public Obj_bsm_rock_big(Game gp) {
        super(gp);
        this.gp = gp;
        name = "basementRockBig";
        direction="";
        solidArea.x=0;
        solidArea.y=0;
        collisionOn=true;
        solidArea.height=63*2;
        solidArea.width=60*2;
        solidAreaDefaultY=solidArea.y;
        solidAreaDefaultX=solidArea.x;
        getImage();
    }
    public void getImage() {
        entityHeight = 63*2;
        entityWidth = 60*2;
        image=setup("/textures/objects/basement_rock_2x2.png");
    }
    public void draw(Graphics g) {
        g.drawImage(image,x,y,entityWidth,entityHeight, null) ;
    }
}
