package PaooGame.entity;

import PaooGame.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Entity {
    public Game gp;

    public String name;

    public  int x;
    public  int y;

    //atribute
    public int speed;
    public int health;
    public int maxLife;
    public int maxMaxLife;
    public int type; // 0 - player, 1 - monster
    public int damage;
    public Projectile projectile;

    public boolean invincible=false;
    public boolean attacking=false;
    public boolean collisionOn=false;
    public boolean alive;

    public int entityWidth;
    public int entityHeight;

    public BufferedImage up1,up2,up3,down1,down2,down3,left1,left2,left3,right1,right2,right3;
    public BufferedImage attackUp, attackDown, attackLeft, attackRight;
    public BufferedImage image;
    public String direction;
    //Counter
    public int invincibleCounter=0;
    public int spriteCounter=0;
    public int spriteNumber=1;
    public int stopCounter=0;
    public int counterActiune=0;
    public int attackCounter=0;

    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public Rectangle attackArea = new Rectangle(0,0,0,0);


    public Entity(Game gp){
        this.gp=gp;
    }

    public BufferedImage setup(String path){
        BufferedImage rez=null;
        try {
            rez = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rez;
    }

    public void draw(Graphics g) {
        if(image!=null){
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
                case "":
                    break;
            }
            g.drawImage(image,x,y,entityWidth,entityHeight, null) ;
        }


    }
    public void scale(int i){
        solidArea.height+=i;
        solidArea.width+=i;
        entityHeight+=i;
        entityWidth+=i;
    }
    public void setAction(){}
    public void damageReact(){}
    public void update() {
        setAction();
        collisionOn = false;
        boolean contactPlayer = gp.cChecker.checkPlayer(this);

        if(name!="monstro"){

            gp.cChecker.checkTile(this);

            gp.cChecker.checkEntity(this,gp.monsters);
        }


        if(this.type==1 && contactPlayer){
            if(!gp.isaac.invincible){
                //putem da dmg
                gp.isaac.health-=1;
                gp.isaac.invincible=true;
            }
        }

        if (!collisionOn) {
            switch (direction) {
                case "up": y -= speed; break;
                case "down": y += speed; break;
                case "left": x -= speed; break;
                case "right": x += speed; break;
            }
        }
        spriteCounter++;
        if (spriteCounter > 10) {
            if (spriteNumber == 1)
                spriteNumber = 2;
            else if (spriteNumber == 2)
                spriteNumber = 1;
            spriteCounter = 0;
        }
        if(invincible){
            invincibleCounter++;
            if(invincibleCounter > 40){
                invincible=false;
                invincibleCounter=0;
            }
        }
    }
}
