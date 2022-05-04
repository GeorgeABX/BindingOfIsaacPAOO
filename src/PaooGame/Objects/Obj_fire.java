package PaooGame.Objects;

import PaooGame.Game;
import PaooGame.entity.Entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Obj_fire extends Entity {
    public Game gp;
    BufferedImage image1,image2,image3,image4,image5,image6;
    public int spriteCounter;
    public int spriteNumber;

    public Obj_fire(Game gp) {
        super(gp);
        this.gp = gp;
        name = "fire";
        direction="";
        solidArea.x=50;
        solidArea.y=50;
//        solidArea.x=0;
//        solidArea.y=0;
        collisionOn=true;
        solidArea.height=24;
        solidArea.width=20;
        solidAreaDefaultY=solidArea.y;
        solidAreaDefaultX=solidArea.x;
        spriteNumber=1;
        spriteCounter=0;
        getImage();
    }
    public void getImage() {
        entityHeight = 48*2;
        entityWidth = 48*2;
        image1=setup("/textures/objects/fire/fire1.png");
        image2=setup("/textures/objects/fire/fire2.png");
        image3=setup("/textures/objects/fire/fire3.png");
        image4=setup("/textures/objects/fire/fire4.png");
        image5=setup("/textures/objects/fire/fire5.png");
        image6=setup("/textures/objects/fire/fire6.png");
    }
    public void draw(Graphics g) {
        spriteCounter++;
        if(spriteCounter>=2){
            switch (spriteNumber){
                case 1 : spriteNumber=2; break;
                case 2 : spriteNumber=3; break;
                case 3 : spriteNumber=4; break;
                case 4 : spriteNumber=5; break;
                case 5 : spriteNumber=6; break;
                case 6 : spriteNumber=1; break;
            }
            spriteCounter=0;
        }

        BufferedImage image=null;
        switch (spriteNumber){
            case 1 : image=image1; break;
            case 2 : image=image2; break;
            case 3 : image=image3; break;
            case 4 : image=image4; break;
            case 5 : image=image5; break;
            case 6 : image=image6; break;
        }
        g.drawImage(image,x,y,entityWidth,entityHeight, null) ;

    }
}
