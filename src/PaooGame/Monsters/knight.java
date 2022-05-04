package PaooGame.Monsters;

import PaooGame.Game;
import PaooGame.KeyHandler;
import PaooGame.entity.Entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class knight extends Entity {

    public KeyHandler keyH;

    public knight(Game gp) {
        super(gp);

        direction="down";
        speed=3;
        health=2;
        type=1;

        name="knight";
        entityHeight=25;
        entityWidth=28;
        solidArea.x=0;
        solidArea.y=0;
        solidArea.height=25;
        solidArea.width=28;
        solidAreaDefaultX=solidArea.x;
        solidAreaDefaultY=solidArea.y;
        collisionOn=true;
        getImage();
        scale(20);
    }
    public void getImage(){
        up1=setup("/textures/monsters/knight/up.png");
        down1=setup("/textures/monsters/knight/down.png");
        left1=setup("/textures/monsters/knight/left.png");
        right1=setup("/textures/monsters/knight/right.png");
    }

    public void draw(Graphics g){
        BufferedImage image=null;
        switch (direction){
            case "up":
                image = up1;
                break;
            case "down":
                image = down1;
                break;
            case "left":
                image = left1;
                break;
            case "right":
                image = right1;
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
