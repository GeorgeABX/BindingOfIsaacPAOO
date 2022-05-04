package PaooGame.Monsters;

import PaooGame.Game;
import PaooGame.KeyHandler;
import PaooGame.entity.Entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Bonny extends Entity {

    public KeyHandler keyH;

    public Bonny(Game gp) {
        super(gp);

        direction="down";
        speed=3;
        health=2;
        type=1;

        name="bonny";
        entityHeight=48;
        entityWidth=48;
        solidArea.x=9;
        solidArea.y=13;
        solidArea.height=31;
        solidArea.width=24;
        solidAreaDefaultX=solidArea.x;
        solidAreaDefaultY=solidArea.y;
        collisionOn=true;
        getImage();
        scale(35);
    }
    public void getImage(){
        up1=setup("/textures/monsters/bonny/up1.png");
        up2=setup("/textures/monsters/bonny/up2.png");
        down1=setup("/textures/monsters/bonny/down1.png");
        down2=setup("/textures/monsters/bonny/down2.png");
        left1=setup("/textures/monsters/bonny/left1.png");
        left2=setup("/textures/monsters/bonny/left2.png");
        right1=setup("/textures/monsters/bonny/right1.png");
        right2=setup("/textures/monsters/bonny/right2.png");
    }

    public void draw(Graphics g){
        BufferedImage image=null;
        switch (direction){
            case "up":
                if(spriteNumber==1) {
                    image = up1;
                }
                if(spriteNumber==2){
                    image=up2;
                }
                break;
            case "down":
                if(spriteNumber==1) {
                    image = down1;
                }
                if(spriteNumber==2){
                    image = down2;
                }
                break;
            case "left":
                if(spriteNumber==1) {
                    image = left1;
                }
                if(spriteNumber==2){
                    image = left2;
                }
                break;
            case "right":
                if(spriteNumber==1) {
                    image = right1;
                }
                if(spriteNumber==2){
                    image = right2;
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
}
