package PaooGame.Objects;

import PaooGame.Game;
import PaooGame.entity.Entity;

import java.awt.*;

public class Item_magicmush extends Entity {
    public Game gp;

    public Item_magicmush(Game gp) {
        super(gp);
        this.gp = gp;

        setDefault();
        getImage();
    }
    public void setDefault(){
        name = "magic_mush";
        direction="";
        solidArea.x=0;
        solidArea.y=0;
        collisionOn=true;
        solidArea.height=32;
        solidArea.width=32;
        solidAreaDefaultY=solidArea.y;
        solidAreaDefaultX=solidArea.x;
    }
    public void getImage() {
        entityHeight = 32;
        entityWidth = 32;
        image=setup("/textures/objects/items/magic_mush.png");
    }
    public void draw(Graphics g) {
        g.drawImage(image,x,y,entityWidth+entityWidth/2,entityHeight+entityHeight/2, null) ;
    }
}
