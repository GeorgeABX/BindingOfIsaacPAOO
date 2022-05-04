package PaooGame.Objects;

import PaooGame.Game;
import PaooGame.entity.Entity;

import java.awt.*;

public class Obj_urn extends Entity {
    public Game gp;

    public Obj_urn(Game gp) {
        super(gp);
        this.gp = gp;
        name = "urn";
        direction="";
        solidArea.x=0;
        solidArea.y=0;
        collisionOn=true;
        solidArea.height=27*2;
        solidArea.width=26*2;
        solidAreaDefaultY=solidArea.y;
        solidAreaDefaultX=solidArea.x;
        getImage();
    }
    public void getImage() {
        entityHeight = 27*2;
        entityWidth = 26*2;
        image=setup("/textures/objects/urn.png");
    }
    public void draw(Graphics g) {
        g.drawImage(image,x,y,entityWidth,entityHeight, null) ;
    }
}
