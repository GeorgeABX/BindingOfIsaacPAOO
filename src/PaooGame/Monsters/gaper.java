package PaooGame.Monsters;

import PaooGame.Game;
import PaooGame.KeyHandler;
import PaooGame.entity.Entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class gaper extends Entity {

    public KeyHandler keyH;

    public gaper(Game gp) {
        super(gp);

        direction="down";
        speed=3;
        health=6;
        type=1;
        name="gaper";
        entityHeight=34*2;
        entityWidth=28*2;
        solidArea.x=9;
        solidArea.y=13;
        solidArea.height=31;
        solidArea.width=24;
        solidAreaDefaultX=solidArea.x;
        solidAreaDefaultY=solidArea.y;
        collisionOn=true;
        getImage();
        //scale(35);
    }
    public void getImage(){
        up1=setup("/textures/monsters/gaper/down1.png");
        up2=setup("/textures/monsters/gaper/down2.png");
        up3=setup("/textures/monsters/gaper/down3.png");
        down1=setup("/textures/monsters/gaper/down1.png");
        down2=setup("/textures/monsters/gaper/down2.png");
        down3=setup("/textures/monsters/gaper/down3.png");
        left1=setup("/textures/monsters/gaper/left1.png");
        left2=setup("/textures/monsters/gaper/left2.png");
        left3=setup("/textures/monsters/gaper/left3.png");
        right1=setup("/textures/monsters/gaper/right1.png");
        right2=setup("/textures/monsters/gaper/right2.png");
        right3=setup("/textures/monsters/gaper/right3.png");
    }

    public void draw(Graphics g){
        BufferedImage image=null;
        switch (direction) {
            case "up":
                if (spriteNumber == 1) {
                    image = up1;
                }
                if (spriteNumber == 2) {
                    image = up2;
                }
                if (spriteNumber == 3) {
                    image = up3;
                }
                break;
            case "down":
                if (spriteNumber == 1) {
                    image = down1;
                }
                if (spriteNumber == 2) {
                    image = down2;
                }
                if (spriteNumber == 3) {
                    image = down3;
                }
                break;
            case "left":
                if (spriteNumber == 1) {
                    image = left1;
                }
                if (spriteNumber == 2) {
                    image = left2;
                }
                if (spriteNumber == 3) {
                    image = left3;
                }
                break;
            case "right":
                if (spriteNumber == 1) {
                    image = right1;
                }
                if (spriteNumber == 2) {
                    image = right2;
                }
                if (spriteNumber == 3) {
                    image = right3;
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
