package PaooGame;

import PaooGame.entity.Entity;
import PaooGame.entity.Isaac;
import com.sun.jdi.connect.Connector;

public class CheckCollision {
    Game gp;

    public CheckCollision(Game gp){
        this.gp=gp;
    }

    public void checkTile(Entity entity){
        int entityLeftX= entity.x+entity.solidArea.x;
        int entityRightX=entity.x+entity.solidArea.x+entity.solidArea.width;
        int entityTopY= entity.y+entity.solidArea.y;
        int entityBottomY=entity.y+entity.solidArea.y+entity.solidArea.height;
        //System.out.print("\nLeftX : "+entityLeftX/48+"; RightX : "+entityRightX/48+"; TopY : "+entityTopY/48+"; BottomY : "+entityBottomY/48);

        int entityLeftCol = entityLeftX/48;
        int entityRightCol = entityRightX/48;
        int entityTopRow = entityTopY/48;
        int entityBottomRow = entityBottomY/48;

        int tileNum1,tileNum2;

        switch(entity.direction){
            case "up":
                entityTopRow=(entityTopY-entity.speed)/48;
                tileNum1=gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2=gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                if(gp.tileM.tile[tileNum1].collision==true || gp.tileM.tile[tileNum2].collision==true){
                    entity.collisionOn=true;
                }
                break;
            case "down":
                entityBottomRow=(entityBottomY+entity.speed)/48;
                tileNum1=gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2=gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision==true || gp.tileM.tile[tileNum2].collision==true){
                    entity.collisionOn=true;
                }
                break;
            case "left":
                entityLeftCol=(entityLeftX-entity.speed)/48;
                tileNum1=gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2=gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision==true || gp.tileM.tile[tileNum2].collision==true){
                    entity.collisionOn=true;
                }
                break;
            case "right":
                entityRightCol=(entityRightX+entity.speed)/48;
                tileNum1=gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2=gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision==true || gp.tileM.tile[tileNum2].collision==true){
                    entity.collisionOn=true;
                }
                break;
        }
    }

    public int checkObj(Entity entity, boolean player){
        int index=999;
        for (int i=0;i<gp.obj.length;i++){
            if(gp.obj[i]!=null){
                //luam dreptunghiul pentru coliziune a entitatii
                entity.solidArea.x=entity.x+entity.solidArea.x;
                entity.solidArea.y=entity.y+entity.solidArea.y;
                //luam dreptunghiul pt coliziune a obiectului
                gp.obj[i].solidArea.x=gp.obj[i].x+gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y=gp.obj[i].y+gp.obj[i].solidArea.y;
                switch (entity.direction){
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        break;
                }
                if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                   if(gp.obj[i].collisionOn == true){
                       entity.collisionOn=true;
                   }
                   if(player==true){
                       index=i;
                   }
                }
                entity.solidArea.x=entity.solidAreaDefaultX;
                entity.solidArea.y=entity.solidAreaDefaultY;
                gp.obj[i].solidArea.x=gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y=gp.obj[i].solidAreaDefaultY;
            }
        }
        return index;
    }

    public int checkEntity(Entity entity, Entity[] target){
        int index=999;
        for (int i=0;i<target.length;i++){
            if(target[i]!=null){
                //luam dreptunghiul pentru coliziune a playerlui
                entity.solidArea.x=entity.x+entity.solidArea.x;
                entity.solidArea.y=entity.y+entity.solidArea.y;
                //luam dreptunghiul pt coliziune a obiectului
                target[i].solidArea.x=target[i].x+target[i].solidArea.x;
                target[i].solidArea.y=target[i].y+target[i].solidArea.y;
                switch (entity.direction){
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        break;
                }

                if(entity.solidArea.intersects(target[i].solidArea)){
                    //excludem entity din vectorul de target
                    if(target[i]!=entity){
                        entity.collisionOn=true;
                        index=i;
                    }
                }
                entity.solidArea.x=entity.solidAreaDefaultX;
                entity.solidArea.y=entity.solidAreaDefaultY;
                target[i].solidArea.x=target[i].solidAreaDefaultX;
                target[i].solidArea.y=target[i].solidAreaDefaultY;
            }
        }
        return index;
    }

    public boolean checkPlayer(Entity entity){
        boolean contactPlayer = false;
        //luam dreptunghiul pentru coliziune a playerlui
        entity.solidArea.x=entity.x+entity.solidArea.x;
        entity.solidArea.y=entity.y+entity.solidArea.y;
        //luam dreptunghiul pt coliziune a obiectului
        gp.isaac.solidArea.x=gp.isaac.x+gp.isaac.solidArea.x;
        gp.isaac.solidArea.y=gp.isaac.y+gp.isaac.solidArea.y;
        switch (entity.direction){
            case "up":
                entity.solidArea.y -= entity.speed;
                break;
            case "down":
                entity.solidArea.y += entity.speed;
                break;
            case "left":
                entity.solidArea.x -= entity.speed;
                break;
            case "right":
                entity.solidArea.x += entity.speed;
                break;
        }

        if(entity.solidArea.intersects(gp.isaac.solidArea)){
            //excludem entity din vectorul de target
                entity.collisionOn=true;
                contactPlayer=true;
        }
        entity.solidArea.x=entity.solidAreaDefaultX;
        entity.solidArea.y=entity.solidAreaDefaultY;
        gp.isaac.solidArea.x=gp.isaac.solidAreaDefaultX;
        gp.isaac.solidArea.y=gp.isaac.solidAreaDefaultY;
        return contactPlayer ;
    }
}
