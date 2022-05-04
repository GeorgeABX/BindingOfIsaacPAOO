package PaooGame.Monsters;

import PaooGame.Game;
import PaooGame.entity.Entity;

import java.awt.*;

public class Monst_Monstro extends Entity {

    public Monst_Monstro(Game gp) {
        super(gp);
        name="monstro";
        entityHeight=52;
        entityWidth=67;
        //solidArea.x=7;
        //solidArea.y=14;
        //solidArea.height=36*2;
        //solidArea.width=52*2;
        solidArea.x=0;
        solidArea.y=0;
        solidArea.height=52*2;
        solidArea.width=67*2;
        solidAreaDefaultX=solidArea.x;
        solidAreaDefaultY=solidArea.y;
        collisionOn=true;
        getImage();
    }
    public void getImage(){
        up1=setup("/textures/monsters/monstro/monstro1.png");
    }
    public void draw(Graphics g){
        g.drawImage(up1,x,y,entityWidth*2,entityHeight*2, null) ;
    }
    public void update(){

    }
}
