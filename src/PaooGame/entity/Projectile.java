package PaooGame.entity;

import PaooGame.Game;

public class Projectile extends Entity{

    public Projectile(Game gp) {
        super(gp);
    }

    public void set(int x, int y, String direction){
        this.x=x;
        this.y=y;
        this.direction=direction;
    }
    public void update(){
        switch (direction) {
            case "up" -> y -= speed;
            case "down" -> y += speed;
            case "left" -> x -= speed;
            case "right" -> x += speed;
        }
    }
}
