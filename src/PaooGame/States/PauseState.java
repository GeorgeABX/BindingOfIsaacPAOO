package PaooGame.States;

import PaooGame.Game;

import java.awt.*;

public class PauseState extends State{
    public PauseState(Game gp) {
        super(gp);
    }

    @Override
    public void update() {
        /*if(keyH.enterPressed){
            switch (pauseCom) {
                case 0: {
                    break;
                }
                case 1:
                    gp.uiStates.setState(gp.playState);
                    break;
                case 2:
                    gp.uiStates.setState(gp.titleState);
                    break;
            }
        }
        if(keyH.escPressed){
            gp.uiStates.setState(gp.playState);
        }
        if(keyH.upPressed || keyH.shotUpPressed){
            pauseCom--;
            if (pauseCom < 0) {
                pauseCom = nrComenziPauza - 1;
            }
        }
        if(keyH.downPressed || keyH.shotDownPressed){
            pauseCom++;
            if (pauseCom > nrComenziPauza - 1) {
                pauseCom = 0;
            }
        }*/
    }

    @Override
    public void draw(Graphics g) {
        this.g=g;
        drawGame(g);
        drawGameUI();
        image=setup("/textures/menu_textures/pause/pauseMenu.png");
        int x=0;
        int y=0;
        g.drawImage(image, x, y, gp.width, gp.height, null);
        switch (pauseCom){
            case 0 : {
                image=setup("/textures/menu_textures/pause/sageataOption.png");
                g.drawImage(image, x, y, gp.width, gp.height, null);
            } break;
            case 1 : {
                image=setup("/textures/menu_textures/pause/sageataResume.png");
                g.drawImage(image, x, y, gp.width, gp.height, null);
            } break;
            case 2 : {
                image=setup("/textures/menu_textures/pause/sageataExit.png");
                g.drawImage(image, x, y, gp.width, gp.height, null);
            } break;
        }
    }
}
