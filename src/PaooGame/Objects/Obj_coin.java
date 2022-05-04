package PaooGame.Objects;

import PaooGame.Game;
import PaooGame.entity.Entity;

import java.awt.*;

public class Obj_coin extends Entity {
    public Game gp;

    public Obj_coin(Game gp) {
        super(gp);
        this.gp = gp;
        name = "coin";
        direction="";
        solidArea.x=0;
        solidArea.y=0;
        collisionOn=true;
        solidArea.height=13;
        solidArea.width=18;
        solidAreaDefaultY=solidArea.y;
        solidAreaDefaultX=solidArea.x;
        getImage();
    }
    public void getImage() {
        entityHeight = 13;
        entityWidth = 18;
        image=setup("/textures/objects/coin.png");
    }
    public void draw(Graphics g) {
        g.drawImage(image,x,y,entityWidth+entityWidth/2,entityHeight+entityHeight/2, null) ;
    }
}
