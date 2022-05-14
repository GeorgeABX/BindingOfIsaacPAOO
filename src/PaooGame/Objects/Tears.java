package PaooGame.Objects;

import PaooGame.Game;
import PaooGame.entity.Projectile;

import java.awt.*;

public class Tears extends Projectile {
    public Game gp;
    int bx,by;
    public boolean readyToFire, shot=false;
    public boolean upPressed, downPressed, leftPressed, rightPressed, pressed,keyReleased;
    public Rectangle bullet;

    public Tears(Game gp) {
        super(gp);
        this.gp=gp;
        setDefault();
        getImage();
    }
    public void setDefault() {
        speed = 10;
        damage=2;
        maxLife=50;
        solidArea.x=0;
        solidArea.y=0;
        solidArea.height=15;
        solidArea.width=15;
        solidAreaDefaultX=solidArea.x;
        solidAreaDefaultY=solidArea.y;
    }

    public void getImage(){
        image=setup("/textures/isaac/tear.png");
    }
   /* public void update(){
        upPressed=gp.keyH.shotUpPressed;
        downPressed=gp.keyH.shotDownPressed;
        leftPressed=gp.keyH.shotLeftPressed;
        rightPressed=gp.keyH.shotRightPressed;
        keyReleased=gp.keyH.shotReleased;



        pressed=upPressed || downPressed || leftPressed || rightPressed;
        if(pressed){
            if(bullet==null){
                readyToFire=true;
            }
            if(readyToFire){
                by=gp.isaac.y;
                bx=gp.isaac.x;
                bullet=new Rectangle(bx,by,15,15);
                if(upPressed){
                    direction="up";
                }
                else if(downPressed){
                    direction="down";
                }
                else if(leftPressed){
                    direction="left";
                }
                else if(rightPressed){
                    direction="right";
                }
                shot=true;
            }
        }
        if(keyReleased && !pressed){
            readyToFire=false;
            //System.out.println("x : "+bullet.x +"\t y : "+bullet.y);
            //if((bullet.y<=gp.isaac.y-200 || bullet.y>=gp.isaac.y+200) && (bullet.x<=gp.isaac.x-200 || bullet.x<=gp.isaac.x+200)){
            //if(!((bullet.y<=1300 || bullet.y>=250) && (bullet.x<=700 || bullet.x>=110))){
            if((bullet.x>=1300 || bullet.x<=250) || (bullet.y>=700 || bullet.y<=110)){
//if(bullet.y<=-5){
                bullet=new Rectangle(0,0,0,0);
                shot=false;

                readyToFire=true;
            }
        }
        shoot();
    }
    public void shoot(){
        if(shot){
            switch (direction){
                case "up" :
                    bullet.y-=speed;
                    break;
                case "down" :
                    bullet.y+=speed;
                    break;
                case "left" :
                    bullet.x-=speed;
                    break;
                case "right" :
                    bullet.x+=speed;
                    break;
            }
        }
    }*/
    public void draw(Graphics g){
        if((x<=1300 && x>=250) && (y<=700 && y>=110)) {
            if (alive) {
                g.drawImage(image, x, y, image.getWidth() * 2, image.getHeight() * 2, null);
            }
        }
    }

}
