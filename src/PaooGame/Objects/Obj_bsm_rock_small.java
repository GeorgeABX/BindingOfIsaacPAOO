package PaooGame.Objects;

import PaooGame.Game;
import PaooGame.entity.Entity;

import java.awt.*;

public class Obj_bsm_rock_small extends Entity {
    public Game gp;

    public Obj_bsm_rock_small(Game gp) {
        super(gp);
        this.gp = gp;
        name = "basementRockSmall";
        direction="";
        solidArea.x=0;
        solidArea.y=0;
        collisionOn=true;
        solidArea.height=26*2;
        solidArea.width=27*2;
        solidAreaDefaultY=solidArea.y;
        solidAreaDefaultX=solidArea.x;
        getImage();
    }
    public void getImage() {
        entityHeight = 27*2;
        entityWidth = 26*2;
        image=setup("/textures/objects/basement_rock.png");
    }
    public void draw(Graphics g) {
        g.drawImage(image,x,y,entityWidth,entityHeight, null) ;
    }
}
