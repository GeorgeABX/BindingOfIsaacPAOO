package PaooGame.Objects;

import PaooGame.Game;
import PaooGame.entity.Entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Obj_spike extends Entity {
    public Game gp;

    public Obj_spike(Game gp) {
        super(gp);
        this.gp = gp;
        name = "spike";
        direction="";
        solidArea.x=0;
        solidArea.y=0;
        collisionOn=false;
        solidArea.height=28*2;
        solidArea.width=23*2;
        solidAreaDefaultY=solidArea.y;
        solidAreaDefaultX=solidArea.x;
        getImage();
    }
    public void getImage() {
        entityHeight = 28*2;
        entityWidth = 23*2;
        image=setup("/textures/objects/spike.png");
    }
    public void draw(Graphics g) {
        g.drawImage(image,x,y,entityWidth,entityHeight, null) ;
    }
}
