package PaooGame.tile1;

import PaooGame.Monsters.*;
import PaooGame.Game;
import PaooGame.Objects.*;
import PaooGame.entity.Entity;

public class AssetSetter {

    public Game gp;

    public AssetSetter(Game gp){
        this.gp=gp;
    }

    public void setMonsters(){
        /*gp.monsters[0]=new Fatty(gp);
        gp.monsters[0].x=20*gp.tileSize;
        gp.monsters[0].y=9*gp.tileSize;

        gp.monsters[1]=new Fatty(gp);
        gp.monsters[1].x=13*gp.tileSize;
        gp.monsters[1].y=8*gp.tileSize;*/
    }

    public void setObjects(){
        for (int i = 0; i < gp.obj.length; i++) {
            gp.obj[i]=null;
        }
        for (int i = 0; i < gp.monsters.length; i++) {
            gp.monsters[i]=null;
        }
        switch (gp.nivel){
            case 0 -> setBasement();
            case 1 -> setCaves();
        }
    }
    public void setCaves(){
        switch (gp.camera){
            case 0 -> {

            }
        }
    }
    public void setBasement(){
        int kk=-1;
        switch (gp.camera){
            case 0 -> {



               // gp.obj[0]=setupObj("key",7*48,7*48);
                //gp.obj[1]=setupObj("heart",9*48,9*48);
                //gp.obj[2]=setupObj("grey_chest",10*48,6*48);
               // gp.obj[3]=setupObj("gold_chest",10*48+48,6*48);
                //gp.obj[4]=setupObj("coin",12*48,12*48);
                //gp.obj[5]=setupObj("spike",10*48,10*48);
                /*if(gp.nivelTerminat==0){
                    gp.monsters[0]=setupMonster("fatty",20*48,9*48);
                }*/

                //gp.monsters[1]=setupMonster("bonny",13*48,8*48);
                //gp.monsters[2]=setupMonster("monstro",10*48,10*48);

            }
            case 1 ->{
                int k=-1;
                gp.obj[++k]=setupObj("key",5*48+30,gp.actualScreenHeight/2-50);
                gp.obj[++k]=setupObj("grey_chest",25*48+10,gp.actualScreenHeight/2-27);
                for(int i=0;i<13-3;i++){
                    gp.obj[++k]=setupObj("spike",7*48,(i+3)*48+i*7);
                    gp.obj[++k]=setupObj("spike",24*48,(i+3)*48+i*7);
                }
                gp.obj[++k]=setupObj("fire",5*48+10,13*48-20);
                gp.obj[++k]=setupObj("fire",5*48+10,2*48);
                gp.obj[++k]=setupObj("fire",25*48,2*48);
                gp.obj[++k]=setupObj("fire",25*48,13*48-20);
                gp.obj[++k]=new Obj_urn(gp);
                gp.obj[k].x=gp.actualScreenWidth/2-gp.obj[k].entityWidth/2;
                gp.obj[k].y=gp.actualScreenHeight/2-gp.obj[k].entityHeight/2;
                if(gp.nivelTerminat==0 && gp.tileM.nivComplet[gp.camera]==0) {
                    gp.monsters[++k]=setupMonster("gaper",13*48,8*48);

                }
            }
            case 2 -> {
                int k=-1;
                gp.obj[++k]=setupObj("fire",5*48+10,13*48-20);
                gp.obj[++k]=setupObj("fire",5*48+10,2*48);
                gp.obj[++k]=setupObj("fire",25*48,2*48);
                gp.obj[++k]=setupObj("fire",25*48,13*48-20);

                gp.obj[++k]=new Obj_fire(gp);
                gp.obj[k].x=10*48+10;
                gp.obj[k].y=gp.actualScreenHeight/2-gp.obj[k].entityHeight;
                gp.obj[++k]=new Obj_fire(gp);
                gp.obj[k].x=11*48+20;
                gp.obj[k].y=gp.actualScreenHeight/2-gp.obj[k].entityHeight;

                gp.obj[++k]=new Obj_fire(gp);
                gp.obj[k].x=20*48+10;
                gp.obj[k].y=gp.actualScreenHeight/2-gp.obj[k].entityHeight;
                gp.obj[++k]=new Obj_fire(gp);
                gp.obj[k].x=19*48;
                gp.obj[k].y=gp.actualScreenHeight/2-gp.obj[k].entityHeight;

                if(gp.nivelTerminat==0 && gp.tileM.nivComplet[gp.camera]==0) {
                }
            }
            case 3 -> {
                int k=-1;
                gp.obj[++k]=setupObj("fire",6*48+10,12*48-20);
                gp.obj[++k]=setupObj("fire",6*48+10,3*48);
                gp.obj[++k]=setupObj("fire",24*48,3*48);
                gp.obj[++k]=setupObj("fire",24*48,12*48-20);
            }
            //shop room
            case 17 -> {
                int k=-1;
                gp.obj[++k]=setupObj("shop_key",gp.actualScreenWidth/2-150-18,gp.actualScreenHeight/2-100);
                gp.obj[++k]=setupObj("shop_heart",gp.actualScreenWidth/2+150-18,gp.actualScreenHeight/2-100);
            }
            case 4 -> {

            }
            case 5 -> {

            }
            //boss room
            case 19 -> {

            }
            case 6 -> {
                int k=-1;
                gp.obj[++kk]=setupObj("basementRockBig",gp.actualScreenWidth/2-100-60,gp.actualScreenHeight/4-100);
                gp.obj[++kk]=setupObj("basementRockBig",gp.actualScreenWidth/2+100-60,gp.actualScreenHeight/4-100);
                gp.obj[++kk]=setupObj("basementRockSmall",gp.actualScreenWidth/2-100-60-60*2+27*2+27/2,gp.actualScreenHeight/4-100);
                gp.obj[++kk]=setupObj("basementRockSmall",gp.actualScreenWidth/2+100-60+60*2,gp.actualScreenHeight/4-100);
                gp.obj[++kk]=setupObj("basementRockSmall",gp.actualScreenWidth/2-100-60-60*2+27*2+27/2-27*2,gp.actualScreenHeight/4-100);
                gp.obj[++kk]=setupObj("basementRockSmall",gp.actualScreenWidth/2+100-60+60*2+27*2,gp.actualScreenHeight/4-100);
            }
            case 7 -> {

            }
            //item room
            case 18 -> {
                int k=-1;
                gp.obj[++k]=setupObj("gold_chest",gp.actualScreenWidth/2,gp.actualScreenHeight/2);
            }
        }
    }

    public Entity setupObj(String name, int x, int y){
        Entity rez=new Entity(gp);
        switch (name){
            case "pickup_heart" -> rez=new Obj_heart(gp);
            case "key" -> rez=new Obj_key(gp);
            case "coin" -> rez=new Obj_coin(gp);
            case "grey_chest" -> rez=new Obj_greyChest(gp);
            case "gold_chest" -> rez=new Obj_goldChest(gp);
            case "item_door" -> rez=new Obj_Door(gp);
            case "heart" -> rez=new Item_heart(gp);
            case "magic_mush" -> rez=new Item_magicmush(gp);
            case "speed" -> rez=new Item_speed(gp);
            case "stigmata" -> rez=new Item_stigmata(gp);
            case "fire" -> rez=new Obj_fire(gp);
            case "spike" -> rez=new Obj_spike(gp);
            case "shop_key" -> rez=new Shop_key(gp);
            case "shop_heart" -> rez=new Shop_heart(gp);
            case "basementRockBig" -> rez=new Obj_bsm_rock_big(gp);
            case "basementRockSmall" -> rez=new Obj_bsm_rock_small(gp);
            case "urn" -> rez=new Obj_urn(gp);
        }
        rez.x=x;
        rez.y=y;
        return rez;
    }
    public Entity setupObj(String name){
        Entity rez=new Entity(gp);
        switch (name){
            case "pickup_heart" -> rez=new Obj_heart(gp);
            case "key" -> rez=new Obj_key(gp);
            case "coin" -> rez=new Obj_coin(gp);
            case "grey_chest" -> rez=new Obj_greyChest(gp);
            case "gold_chest" -> rez=new Obj_goldChest(gp);
            case "item_door" -> rez=new Obj_Door(gp);
            case "heart" -> rez=new Item_heart(gp);
            case "magic_mush" -> rez=new Item_magicmush(gp);
            case "speed" -> rez=new Item_speed(gp);
            case "stigmata" -> rez=new Item_stigmata(gp);
            case "fire" -> rez=new Obj_fire(gp);
            case "spike" -> rez=new Obj_spike(gp);
        }
        return rez;
    }
    public Entity setupMonster(String name, int x, int y){
        Entity rez=new Entity(gp);
        switch (name){
            case "fatty" -> rez=new Fatty(gp);
            case "bonny" -> rez=new Bonny(gp);
            case "monstro" -> rez=new Monst_Monstro(gp);
            case "basic_fly" -> rez=new basicfly(gp);
            case "red_fly" -> rez=new redfly(gp);
            case "pooter" -> rez=new pooter(gp);
            case "sucker" -> rez=new sucker(gp);
            case "knight" -> rez=new knight(gp);
            case "full_fly" -> rez=new fullfly(gp);
            case "charger" -> rez=new charger(gp);
            case "gaper" -> rez=new gaper(gp);
        }
        rez.x=x;
        rez.y=y;
        return rez;
    }

}
