package PaooGame.Monsters;

import PaooGame.Game;
import PaooGame.KeyHandler;
import PaooGame.entity.Entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class basicfly extends Entity {
    BufferedImage image1, image2;
    public KeyHandler keyH;

    public basicfly(Game gp) {
        super(gp);

        direction="down";
        speed=1;
        health=2;
        type=1;

        name="basic_fly";
        entityHeight=15*2;
        entityWidth=19*2;
        solidArea.x=0;
        solidArea.y=0;
        solidArea.height=15;
        solidArea.width=19;
        solidAreaDefaultX=solidArea.x;
        solidAreaDefaultY=solidArea.y;
        collisionOn=true;
        getImage();
        //scale(35);
    }
    public void getImage(){
        image1=setup("/textures/monsters/basicfly/image1.png");
        image2=setup("/textures/monsters/basicfly/image2.png");
    }

    public void draw(Graphics g){
        BufferedImage image=null;
        spriteCounter++;
        if(spriteCounter>=2){
            switch (spriteNumber){
                case 1 : spriteNumber=2; break;
                case 2 : spriteNumber=1; break;
            }
            spriteCounter=0;
        }
        switch (spriteNumber){
            case 1 : image=image1; break;
            case 2 : image=image2; break;
        }
        g.drawImage(image,x,y,entityWidth,entityHeight, null) ;
    }

    public void setAction(){
        counterActiune++;
        if(counterActiune==120){
            Random random = new Random();
            // 25% din cazuri merge in sus, in jos, stanga sau dreapta
            int i = random.nextInt(100)+1;
            if(i <= 25){
                direction="up";
            }
            if(i > 25 && i <= 50){
                direction = "down";
            }
            if(i > 50 && i <= 75){
                direction="left";
            }
            if(i > 75 && i <=100){
                direction="right";
            }
            counterActiune=0;
        }
    }
    public void damageReact(){
        counterActiune=0;
        direction=gp.isaac.direction;
    }
}
