package PaooGame.States;

import PaooGame.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class TitleState extends State{
    public BufferedImage image,start1,start2,fly1,fly2;
    public int spriteCounter=0,spriteNumber=1,spriteCounter2=0,spriteNumber2=1;
    // 0 - home screen
    // 1 - meniu principal
    // 2 - selectie caracter

    public int code;
    public void getTextures(){
        start1=setup("/textures/menu_textures/start1.png");
        start2=setup("/textures/menu_textures/start2.png");
        fly1=setup("/textures/menu_textures/fly1.png");
        fly2=setup("/textures/menu_textures/fly2.png");
    }
    public TitleState(Game gp) {
        super(gp);
        getTextures();
    }

    @Override
    public void update() {
    }
    @Override
    public void draw(Graphics g) {
        this.g=g;
        switch (titleScreenState){
            case 0 : drawTitleScreenState0(); break;
            case 1 : drawTitleScreenState1(); break;
            case 2 : drawTitleScreenState2(); break;
        }
    }
    public void drawTitleScreenState0() {
        image = setup("/textures/menu_textures/titleScreenBackground.png");
        int x = 0;
        int y = 0;
        g.drawImage(image, x, y, gp.width, gp.height, null);
        spriteCounter++;
        if (spriteCounter >= 3) {
            switch (spriteNumber) {
                case 1:
                    spriteNumber = 2;
                    break;
                case 2:
                    spriteNumber = 1;
                    break;
            }
            spriteCounter = 0;
        }
        switch (spriteNumber) {
            case 1:
                image = start1;
                break;
            case 2:
                image = start2;
                break;
        }
        g.drawImage(image, x, y, gp.width, gp.height, null);
        spriteCounter2++;
        if (spriteCounter2 >= 1) {
            switch (spriteNumber2) {
                case 1:
                    spriteNumber2 = 2;
                    break;
                case 2:
                    spriteNumber2 = 1;
                    break;
            }
            spriteCounter2 = 0;
        }
        switch (spriteNumber2) {
            case 1:
                image = fly1;
                break;
            case 2:
                image = fly2;
                break;
        }
        g.drawImage(image, x, y, gp.width, gp.height, null);
        image = setup("/textures/menu_textures/isaacLogo.png");
        g.drawImage(image, x, y, gp.width, gp.height, null);
    }
    public void drawTitleScreenState1(){
        image=setup("/textures/menu_textures/titleScreenMenu.png");
        int x=0;
        int y=0;
        g.drawImage(image, x, y, gp.width, gp.height, null);
        switch (commandNum){
            case 0 : {
                image=setup("/textures/menu_textures/sageataNewRun.png");
                g.drawImage(image, x, y, gp.width, gp.height, null);
            } break;
            case 1 : {
                image=setup("/textures/menu_textures/sageataContinue.png");
                g.drawImage(image, x, y, gp.width, gp.height, null);
            } break;
            case 2 : {
                image=setup("/textures/menu_textures/sageataOptions.png");
                g.drawImage(image, x, y, gp.width, gp.height, null);
            } break;
        }
    }
    public void drawTitleScreenState2(){
        image=setup("/textures/menu_textures/characterMenuBackground.png");
        int x=0;
        int y=0;
        g.drawImage(image, x, y, gp.width, gp.height, null);
        switch (gp.personaj){
            case 0 : image=setup("/textures/menu_textures/characterIsaac.png"); break;

            case 1 : image=setup("/textures/menu_textures/characterAzazel.png"); break;
        }
        g.drawImage(image, x, y, gp.width, gp.height, null);
    }
}
