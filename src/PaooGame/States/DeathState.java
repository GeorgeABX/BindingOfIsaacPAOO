package PaooGame.States;

import PaooGame.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class DeathState extends State{

    public DeathState(Game gp) {
        super(gp);
    }

    @Override
    public void update() {
        if(keyH.escPressed){
            gp.setNewGame();
            gp.uiStates.setState(gp.titleState);
            titleScreenState = 1;
            commandNum = 0;
        }
    }

    @Override
    public void draw(Graphics g) {
        this.g=g;
        drawGame(g);
        drawGameUI();
        image=setup("/textures/menu_textures/deathScreen.png");
        int x=0;
        int y=0;
        g.drawImage(image,x,y-45,480*3+100,272*3+100,null);
    }
}
