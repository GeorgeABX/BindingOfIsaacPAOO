package PaooGame.States;

import PaooGame.Game;

import java.awt.*;

public class PlayState extends State{
    public PlayState(Game gp) {
        super(gp);
    }

    @Override
    public void update() {
        /*if(keyH.escPressed){
            gp.uiStates.setState(gp.pauseState);
            gp.ui.pauseCom = 1;
            gp.ui.titleScreenState = 1;
            gp.ui.commandNum = 1;
        }*/

        gp.isaac.update();
        gp.monstersNull=true;
        for (int i =0;i<gp.monsters.length; i++){
            if(gp.monsters[i]!=null){
                gp.monsters[i].update();
            }
        }
        for (int i =0;i<gp.monsters.length; i++){
            if(gp.monsters[i]!=null){
                gp.monstersNull=false;
                break;
            }
        }
        if(gp.monstersNull==true)
            gp.nivelTerminat=1;
        //isaac.projectile.update();
        for (int i =0;i<gp.projectileList.size(); i++){
            if(gp.projectileList.get(i).alive){
                gp.projectileList.get(i).update();
            }
            if(!gp.projectileList.get(i).alive){
                gp.projectileList.remove(i);
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        this.g=g;
        drawGame(g);
        drawGameUI();
    }
}
