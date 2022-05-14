package PaooGame.entity;

import PaooGame.Game;

public class Projectile extends Entity{

    public Projectile(Game gp) {
        super(gp);
    }

    public void set(int x, int y, String direction, boolean alive){
        this.x=x+80/2;
        this.y=y+96/2;
        this.direction=direction;
        this.alive=alive;
        this.health=this.maxLife;
    }
    public void update(){
        int monsterIndex=gp.cChecker.checkEntity(this,gp.monsters);
        if(monsterIndex!=999){
            gp.isaac.damageMonster(monsterIndex);
            alive=false;
        }

        switch (direction) {
            case "up" : y -= speed; break;
            case "down" : y += speed; break;
            case "left" : x -= speed; break;
            case "right" : x += speed; break;
        }

        health--;
        if(health<=0){
            alive=false;
        }
    }
}
