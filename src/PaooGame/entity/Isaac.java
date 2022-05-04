package PaooGame.entity;

import PaooGame.Game;
import PaooGame.KeyHandler;
import PaooGame.Objects.*;
import PaooGame.Tiles.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;


public class Isaac extends Entity{
     KeyHandler keyH;
     int player;
     public int keys;
     public int coins;
     public int timer=0;
     boolean pressed;

     public Isaac(Game gp){
          super(gp);
          player=gp.personaj;
          keyH=gp.keyH;
          solidArea.x=16;
          solidArea.y=30;
          solidArea.height=18;
          solidArea.width=18;
          solidAreaDefaultX=solidArea.x;
          solidAreaDefaultY=solidArea.y;
          setDefaultValues();
          setPlayer(player);
          //scale(35);
     }
     public void setPozitie(int x, int y){
          this.x=x;
          this.y=y;
     }
     public void setDefaultValues(){
          x=(16/2)*48;
          y=(13/2)*48;
          speed=10;
          direction="down";
          health=6;
          maxLife=6;
          maxMaxLife=12;
          type=0;
          keys=0;
          coins=0;
          projectile=new Tears(gp);
     }
     public void scale(int i){
          solidArea.height+=i;
          solidArea.width+=i;
          entityHeight+=i;
          entityWidth+=i;
     }
     public void setTimer(){
          timer++;
          if(timer==60*30){
               timer=0;
          }
     }
     public void setPlayer(int player){
          player=gp.personaj;
          if(player==0) {
               setIsaac();
          }
          if(player==1){
               setAzazel();
          }
          scale(35);
     }
     public void setIsaac(){
          entityHeight=48;
          entityWidth=48;
          speed=10;
          health=6;
          maxLife=6;
          solidArea.x=16;
          solidArea.y=30;
          solidArea.height=18;
          solidArea.width=18;
          //damage=
          up1=setup("/textures/isaac/isaac_up1.png");
          up2=setup("/textures/isaac/isaac_up2.png");
          up3=setup("/textures/isaac/isaac_up3.png");
          down1=setup("/textures/isaac/isaac_down1.png");
          down2=setup("/textures/isaac/isaac_down2.png");
          down3=setup("/textures/isaac/isaac_down3.png");
          left1=setup("/textures/isaac/isaac_left1.png");
          left2=setup("/textures/isaac/isaac_left2.png");
          left3=setup("/textures/isaac/isaac_left3.png");
          right1=setup("/textures/isaac/isaac_right1.png");
          right2=setup("/textures/isaac/isaac_right2.png");
          right3=setup("/textures/isaac/isaac_right3.png");
     }
     public void setAzazel(){
          entityHeight=48;
          entityWidth=58;
          speed=10;
          health=6;
          maxLife=6;
          solidArea.x=20;
          solidArea.y=30;
          solidArea.height=18;
          solidArea.width=20;
          //damage=
          up1=setup("/textures/azazel/up1.png");
          up2=setup("/textures/azazel/up2.png");
          up3=setup("/textures/azazel/up3.png");
          down1=setup("/textures/azazel/down1.png");
          down2=setup("/textures/azazel/down2.png");
          down3=setup("/textures/azazel/down3.png");
          left1=setup("/textures/azazel/left1.png");
          left2=setup("/textures/azazel/left2.png");
          left3=setup("/textures/azazel/left3.png");
          right1=setup("/textures/azazel/right1.png");
          right2=setup("/textures/azazel/right2.png");
          right3=setup("/textures/azazel/right3.png");
     }
     public void updateAzazel(){
          switch (direction){

          }
     }
     public  void update(){
          //System.out.println("Health : "+health+"\tspeed : "+speed+"\tkeys : "+keys);
          if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
               if (keyH.upPressed) {

                    direction = "up";
               } else if (keyH.downPressed) {

                    direction = "down";
               } else if (keyH.leftPressed) {

                    direction = "left";
               } else if (keyH.rightPressed) {

                    direction = "right";
               }
               //System.out.println("x : "+x/48+"\ty : "+y/48);
               //System.out.println("Speed : "+speed);
               //coliziuni pt tileuri
               collisionOn = false;
               gp.cChecker.checkTile(this);
               //coliziuni pt obiecte
               int objIndex=gp.cChecker.checkObj(this,true);
               contactObject(objIndex);
               //if(objIndex!=999)
                     //System.out.println("obj index:"+objIndex);
               //coliziuni pt monstri
               int monsterIndex=gp.cChecker.checkEntity(this,gp.monsters);
               contactMonster(monsterIndex);

               if (!collisionOn) {
                    switch (direction) {
                         case "up" : y -= speed; break;
                         case "down" : y += speed; break;
                         case "left" : x -= speed; break;
                         case "right" : x += speed; break;
                    }
               }
               spriteCounter++;
               if (spriteCounter > 3) {
                    if (spriteNumber == 1)
                         spriteNumber = 2;
                    else if (spriteNumber == 2)
                         spriteNumber = 3;
                    else if(spriteNumber==3)
                         spriteNumber=1;
                    spriteCounter = 0;
               }
          }
          else{
               stopCounter++;
               if(stopCounter==20){
                    spriteNumber=1;
                    stopCounter=0;
               }
          }
          pressed=gp.keyH.shotUpPressed || gp.keyH.shotDownPressed || gp.keyH.shotLeftPressed || gp.keyH.shotRightPressed;
          if(pressed){
               //projectile.set(x,y,direction);
               gp.projectileList.add(projectile);
               //System.out.println("projectile");
               projectile=new Tears(gp);
          }

          if(invincible){
               invincibleCounter++;
               if(invincibleCounter > 60){
                    invincible=false;
                    invincibleCounter=0;
               }
          }
          if(health<=0){
               gp.gameState=gp.deathState;
          }
     }
     public void contactObject(int i){
          setTimer();
          if(i!=999){
               int x,y;
               String obj_name=gp.obj[i].name;
               switch (obj_name){
                    case "key" ->{
                         keys++;
                         gp.obj[i]=null;
                    }
                    case "coin" -> {
                         coins++;
                         gp.obj[i]=null;
                    }
                    case "item_door" -> {
                         if(keys>0){
                              gp.obj[i]=null;
                              keys--;
                         }
                    }
                    case "grey_chest" -> {
                         x=gp.obj[i].x;
                         y=gp.obj[i].y;
                         gp.obj[i]=generateRandom();
                         gp.obj[i].x=x;
                         gp.obj[i].y=y;
                    }
                    case "gold_chest" -> {
                         if(keys>0){
                              x=gp.obj[i].x;
                              y=gp.obj[i].y;
                              gp.obj[i]=generateRandomItem();
                              gp.obj[i].x=x;
                              gp.obj[i].y=y;
                              keys--;
                         }
                    }
                    case "heart" -> {
                         if(maxLife/2<maxMaxLife/2)
                              maxLife+=2;
                         health=maxLife;
                         gp.obj[i]=null;
                    }
                    case "pickup_heart" -> {
                         if(health<maxLife){
                              if(health%2==0)
                                   health+=2;
                              else
                                   health+=1;
                              gp.obj[i]=null;
                         }
                    }
                    case "magic_mush" -> {
                         if(maxLife/2<maxMaxLife/2)
                              maxLife+=2;
                         health=maxLife;
                         speed+=1;
                         //dmg ++
                         gp.obj[i]=null;
                    }
                    case "speed" -> {
                         speed+=0.5;
                         gp.obj[i]=null;
                    }
                    case "stigmata" -> {
                         //dmg++
                         gp.obj[i]=null;
                    }
                    case "fire", "spike" ->{
                         if(!invincible){
                              health--;
                              invincible=true;
                         }
                    }
                    case "shop_key" -> {
                         if(coins >=5){
                              keys++;
                              coins-=5;
                              gp.obj[i]=null;
                         }
                    }
                    case "shop_heart" -> {
                         if(health<maxLife){
                              if(coins >=3){
                                   if(health%2==0)
                                        health+=2;
                                   else
                                        health+=1;
                                   coins-=3;
                                   gp.obj[i]=null;
                              }
                         }
                    }
               }

               if(obj_name=="grey_chest"){
                    setTimer();
               }
          }
     }
     public Entity generateRandom(){
          Random random=new Random();
          int limita=2;
          int gen=random.nextInt(limita);
          Entity rez = switch (gen) {
               case 0 -> new Obj_heart(gp);
               case 1 -> new Obj_key(gp);
               default -> null;
          };
          return rez;
     }
     public Entity generateRandomItem(){
          Random random=new Random();
          int limita=2;
          int gen=random.nextInt(limita);
          Entity rez = switch (gen) {
               case 0 -> new Item_stigmata(gp);
               case 1 -> new Item_heart(gp);
               case 2 -> new Item_magicmush(gp);
               case 3 -> new Item_speed(gp);
               default -> null;
               //0 - stigmata, 1 - health, 2-magic_mush, 3-speed
          };
          return rez;
     }
     public void contactMonster(int i){
          if(i!=999){
               if(!invincible){
                    health--;
                    invincible=true;
               }
          }
     }

     public  void draw(Graphics g){
          BufferedImage image=null;
          switch (direction) {
               case "up" : {
                    switch (spriteNumber){
                         case 1 -> image = up1;
                         case 2 -> image = up2;
                         case 3 -> image = up3;
                    }
                    break;
               }
               case "down" : {
                    switch (spriteNumber){
                         case 1 -> image = down1;
                         case 2 -> image = down2;
                         case 3 -> image = down3;
                    }
                    break;
               }
               case "left" : {
                    switch (spriteNumber){
                         case 1 -> image = left1;
                         case 2 -> image = left2;
                         case 3 -> image = left3;
                    }
                    break;
               }
               case "right" : {
                    switch (spriteNumber){
                         case 1 -> image = right1;
                         case 2 -> image = right2;
                         case 3 -> image = right3;
                    }
                    break;
               }
          }
         // projectile.draw(g);
          g.drawImage(image,x,y,entityWidth,entityHeight, null) ;
     }
}
