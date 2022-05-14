package PaooGame.Monsters;

import PaooGame.Game;
import PaooGame.KeyHandler;
import PaooGame.entity.Entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class charger extends Entity {

    public KeyHandler keyH;

    public charger(Game gp) {
        super(gp);

        direction="down";
        speed=3;
        health=4;
        type=1;
        name="charger";
        solidArea.x=9;
        solidArea.y=13;
        solidArea.height=31;
        solidArea.width=24;
        solidAreaDefaultX=solidArea.x;
        solidAreaDefaultY=solidArea.y;
        collisionOn=true;
        getImage();
        //scale(15);
    }
    public void getImage(){
        up1=setup("/textures/monsters/charger/up1.png");
        up2=setup("/textures/monsters/charger/up2.png");
        up3=setup("/textures/monsters/charger/up3.png");
        down1=setup("/textures/monsters/charger/down1.png");
        down2=setup("/textures/monsters/charger/down2.png");
        down3=setup("/textures/monsters/charger/down3.png");
        left1=setup("/textures/monsters/charger/left1.png");
        left2=setup("/textures/monsters/charger/left2.png");
        left3=setup("/textures/monsters/charger/left3.png");
        right1=setup("/textures/monsters/charger/right1.png");
        right2=setup("/textures/monsters/charger/right2.png");
        right3=setup("/textures/monsters/charger/right3.png");
    }

    public void draw(Graphics g){
        if(direction=="down" || direction=="up"){
            entityHeight=25*2;
            entityWidth=16*2;
        }
        else if(direction=="left" || direction=="right")
        {
            entityHeight=12*2;
            entityWidth=27*2;
        }
        BufferedImage image=null;
        switch (direction){
            case "up":
                if(spriteNumber==1) {
                    image = up1;
                }
                if(spriteNumber==2){
                    image=up2;
                }
                if(spriteNumber==3){
                    image=up3;
                }
                break;
            case "down":
                if(spriteNumber==1) {
                    image = down1;
                }
                if(spriteNumber==2){
                    image = down2;
                }
                if(spriteNumber==3){
                    image=down3;
                }
                break;
            case "left":
                if(spriteNumber==1) {
                    image = left1;
                }
                if(spriteNumber==2){
                    image = left2;
                }
                if(spriteNumber==3){
                    image=left3;
                }
                break;
            case "right":
                if(spriteNumber==1) {
                    image = right1;
                }
                if(spriteNumber==2){
                    image = right2;
                }
                if(spriteNumber==3){
                    image=right3;
                }
                break;
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
