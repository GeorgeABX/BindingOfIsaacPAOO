package PaooGame.Objects;

import PaooGame.Game;
import PaooGame.entity.Entity;

import java.awt.*;

public class Shop_heart extends Entity {
    public Game gp;

    public Shop_heart(Game gp) {
        super(gp);
        this.gp = gp;
        name = "shop_heart";
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
        entityHeight = 40*2;
        entityWidth = 18*2;
        image=setup("/textures/shop/heart.png");
    }
    public void draw(Graphics g) {
        g.drawImage(image,x,y,entityWidth,entityHeight, null) ;
    }
}
