package PaooGame.Objects;

import PaooGame.Game;
import PaooGame.entity.Entity;

import java.awt.*;

public class Shop_key extends Entity {
    public Game gp;

    public Shop_key(Game gp) {
        super(gp);
        this.gp = gp;
        name = "shop_key";
        direction="";
        solidArea.x=16;
        solidArea.y=13;
        collisionOn=false;
        solidArea.height=22;
        solidArea.width=15;
        solidAreaDefaultY=solidArea.y;
        solidAreaDefaultX=solidArea.x;
        getImage();
    }
    public void getImage() {
        entityHeight = 40*2;
        entityWidth = 18*2;
        image=setup("/textures/shop/key.png");
    }

    public void draw(Graphics g) {
        g.drawImage(image,x,y,entityWidth,entityHeight, null) ;
    }
}
