package PaooGame.entity;

import PaooGame.Game;
import PaooGame.KeyHandler;
import PaooGame.Objects.*;
import PaooGame.States.PlayState;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Objects;
import java.util.Random;
import java.sql.*;


public class Isaac extends Entity{
     KeyHandler keyH;
     Graphics g;
     int player;
     public int keys;
     public int coins;
     public int timer=0;
     boolean pressed;

     public Isaac(Game gp){
          super(gp);
          player=gp.personaj;
          keyH=KeyHandler.getInstance(gp);
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
          //scale(35);
     }
     public void setIsaac(){
          entityHeight=48*2;
          entityWidth=48*2;
          speed=10;
          health=6;
          maxLife=6;
          solidArea.x=16;
          solidArea.y=30;
          solidArea.height=18*2;
          solidArea.width=18*2;

          attackArea.width=42;
          attackArea.height=28;

          damage=2;
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
          attackUp=setup("/textures/isaac/isaac_attack_up.png");
          attackDown=setup("/textures/isaac/isaac_attack_down.png");
          attackLeft=setup("/textures/isaac/isaac_attack_left.png");
          attackRight=setup("/textures/isaac/isaac_attack_right.png");
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
          if(keyH.spacePressed==true){
               attacking=true;

          }
          if(attacking==true){
               attackMeth();
          }
          if (keyH.upPressed || keyH.shotUpPressed) {
               direction = "up";
          } else if (keyH.downPressed || keyH.shotDownPressed) {

               direction = "down";
          } else if (keyH.leftPressed || keyH.shotLeftPressed) {

               direction = "left";
          } else if (keyH.rightPressed || keyH.shotRightPressed) {

               direction = "right";
          }
          if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {

               //System.out.println("x : "+x/48+"\ty : "+y/48);
               //System.out.println("Speed : "+speed);
               //coliziuni pt tileuri
               collisionOn = false;
               gp.cChecker.checkTile(this);
               //coliziuni pt obiecte
               int objIndex = gp.cChecker.checkObj(this, true);
               contactObject(objIndex);
               //if(objIndex!=999)
               //System.out.println("obj index:"+objIndex);
               //coliziuni pt monstri
               int monsterIndex = gp.cChecker.checkEntity(this, gp.monsters);
               contactMonster(monsterIndex);

               if (!collisionOn && !keyH.spacePressed && !keyH.shotUpPressed && !keyH.shotDownPressed && !keyH.shotLeftPressed && !keyH.shotRightPressed) {
                    switch (direction) {
                         case "up":
                              y -= speed;
                              break;
                         case "down":
                              y += speed;
                              break;
                         case "left":
                              x -= speed;
                              break;
                         case "right":
                              x += speed;
                              break;
                    }
               }
               spriteCounter++;
               if (spriteCounter > 3) {
                    if (spriteNumber == 1)
                         spriteNumber = 2;
                    else if (spriteNumber == 2)
                         spriteNumber = 3;
                    else if (spriteNumber == 3)
                         spriteNumber = 1;
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
          if(pressed && !projectile.alive){
               projectile.set(x,y,direction,true);
               gp.projectileList.add(projectile);
               //System.out.println("projectile");
              // projectile=new Tears(gp);
          }


          if(invincible){
               invincibleCounter++;
               if(invincibleCounter > 60){
                    invincible=false;
                    invincibleCounter=0;
               }
          }
          if(health<=0){
               gp.uiStates.setState(gp.deathState);
          }
     }
     public void attackMeth(){
          attackCounter++;
          if(attackCounter>5 && attackCounter <25){
//             Pastram datele initiale
               int tempX=x;
               int tempY=y;
               int solidAreaWidth=solidArea.width;
               int solidAreaHeight=solidArea.height;
//             Ajustam coordonatele pentru directie
               switch (direction){
                    case "up":
                         y-=attackArea.height;
                         break;
                    case "down":
                         y+=attackArea.height;
                         break;
                    case "left":
                         x-=attackArea.width;
                         break;
                    case "right":
                         x+=attackArea.width;
                         break;
               }
//               Modificam solid area
               solidArea.width=attackArea.width;
               solidArea.height=attackArea.height;
//               Verificam coliziuni
               int monsterIndex = gp.cChecker.checkEntity(this, gp.monsters);
               damageMonster(monsterIndex);
               x=tempX;
               y=tempY;
               solidArea.width=solidAreaWidth;
               solidArea.height=solidAreaHeight;

          }
          if(attackCounter>25){
               attackCounter=0;
               attacking=false;
          }
     }
     public void damageMonster(int i){
          if(i!=999){
               if(!gp.monsters[i].invincible){
                    gp.monsters[i].health-=damage;
                    gp.monsters[i].invincible=true;
                    gp.monsters[i].damageReact();

                    if(gp.monsters[i].health <=0)
                         gp.monsters[i]=null;
               }
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
                         gp.aSet.initialBsm[gp.nivel][gp.camera]=null;
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
          else{

          }
     }

     public  void draw(Graphics g){
          this.g=g;
          BufferedImage image=null;
          int tempX=x;
          int tempY=y;
          int tempHeight=entityHeight;
          int tempWidth=entityWidth;
          switch (direction) {
               case "up" : {
                    if(!attacking){
                         switch (spriteNumber){
                              case 1 : image = up1; break;
                              case 2 : image = up2; break;
                              case 3 : image = up3; break;
                         }
                    }
                    else{
                         image=attackUp;
                         tempY=tempY-gp.tileSize*2;
                         tempHeight=tempHeight*2;
                    }

                    break;
               }
               case "down" : {
                    if(!attacking){
                         switch (spriteNumber){
                              case 1 : image = down1; break;
                              case 2 : image = down2; break;
                              case 3 : image = down3; break;
                         }
                    }
                    else{
                         image=attackDown;
                         tempHeight=tempHeight*2;
                    }
                    break;
               }
               case "left" : {
                    if(!attacking){
                         switch (spriteNumber){
                              case 1 : image = left1; break;
                              case 2 : image = left2; break;
                              case 3 : image = left3; break;
                         }
                    }
                    else{
                         image=attackLeft;
                         tempX=tempX-gp.tileSize*2;
                         tempWidth=tempWidth*2;
                    }
                    break;
               }
               case "right" : {
                    if(!attacking){
                         switch (spriteNumber){
                              case 1 : image = right1; break;
                              case 2 : image = right2; break;
                              case 3 : image = right3; break;
                         }
                    }
                    else{
                         image=attackRight;
                         tempWidth=tempWidth*2;
                    }
                    break;
               }
          }
         // projectile.draw(g);
          g.drawImage(image,tempX,tempY,tempWidth,tempHeight, null) ;
     }
     public void save(){
          Statement s = null;
          Connection c = gp.c;
          // SALVAM INFORMATII DESPRE CARACTER
          try{
               s=c.createStatement();
               String sql="UPDATE player SET x = " + x + " WHERE ID = 1";
               System.out.println("UPDATE X " + s.executeUpdate(sql));
               sql="UPDATE player SET y = " + y + " WHERE ID = 1";
               System.out.println("UPDATE Y " + s.executeUpdate(sql));
               sql="UPDATE player SET speed = " + speed + " WHERE ID = 1";
               System.out.println("UPDATE SPEED" + s.executeUpdate(sql));
               sql="UPDATE player SET health = " + health + " WHERE ID = 1";
               System.out.println("UPDATE HEALTH " + s.executeUpdate(sql));
               sql="UPDATE player SET maxLife = " + maxLife + " WHERE ID = 1";
               System.out.println("UPDATE maxLife " + s.executeUpdate(sql));
               sql="UPDATE player SET keys = " + keys + " WHERE ID = 1";
               System.out.println("UPDATE HEALTH " + s.executeUpdate(sql));
               sql="UPDATE player SET coins = " + coins + " WHERE ID = 1";
               System.out.println("UPDATE maxLife " + s.executeUpdate(sql));
               sql="UPDATE player SET nivel = " + gp.nivel + " WHERE ID = 1";
               System.out.println("UPDATE Nivel " + s.executeUpdate(sql));
               sql="UPDATE player SET camera = " + gp.camera + " WHERE ID = 1";
               System.out.println("UPDATE Camera " + s.executeUpdate(sql));
               sql="UPDATE player SET nivelTerminat = " + gp.nivelTerminat + " WHERE ID = 1";
               System.out.println("UPDATE nivelTerminat " + s.executeUpdate(sql));

          } catch (Exception e) {
               e.printStackTrace();
          }
          try {
               BufferedWriter bw = new BufferedWriter(new FileWriter("nivelComplet.txt"));
               for(int i=0;i<gp.tileM.nivComplet.length;i++){
                    bw.write(String.valueOf(gp.tileM.nivComplet[i]));
                    bw.newLine();
               }
               System.out.println("Am salvat statusul levelului ");

               bw.close();

          } catch (IOException e) {
               e.printStackTrace();
          }
     }

     public void load(){
          try {
               BufferedReader br = new BufferedReader(new FileReader("nivelComplet.txt"));
               int rez;
               for(int i=0;i<gp.tileM.nivComplet.length;i++){
                    rez = Integer.parseInt(br.readLine());
                    gp.tileM.nivComplet[i]=rez;
               }

               br.close();
          } catch (Exception e) {
               e.printStackTrace();
          }


          Statement s = null;
          Connection c = gp.c;
          String sql;
          try{
               s=c.createStatement();
               sql = "SELECT * FROM player WHERE ID = 1;";
               ResultSet rs = s.executeQuery(sql);
               if (rs.getFloat("x") != 0) {
                    x=rs.getInt("x");
                    y=rs.getInt("y");
                    speed=rs.getInt("speed");
                    health=rs.getInt("health");
                    maxLife=rs.getInt("maxLife");
                    keys=rs.getInt("keys");
                    coins=rs.getInt("coins");
                    gp.nivel=rs.getInt("nivel");
                    gp.camera=rs.getInt("camera");
                    gp.nivelTerminat=rs.getInt("nivelTerminat");

               }
          } catch (Exception e) {
               e.printStackTrace();
          }
          gp.aSet.setObjects();
          gp.tileM.draw(g);
     }
}
