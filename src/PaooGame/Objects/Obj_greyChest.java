package PaooGame.Objects;

import PaooGame.Game;
import PaooGame.entity.Entity;

import java.awt.*;

public class Obj_greyChest extends Entity {
    public Game gp;

    public Obj_greyChest(Game gp) {
        super(gp);
        this.gp = gp;
        name = "grey_chest";
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
        image=setup("/textures/objects/grey_chest.png");
    }
    public void draw(Graphics g) {
        g.drawImage(image,x,y,entityWidth*2,entityHeight*2, null) ;
    }
}
