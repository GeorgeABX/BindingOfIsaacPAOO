package PaooGame.States;

import PaooGame.Game;
import PaooGame.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public abstract class State {
    public Game gp;
    Graphics g;
    Font isaacFont;
    Font font_40;
    Font pixelFont;
    BufferedImage image;
    KeyHandler keyH;

    public int titleScreenState=0;
    public int commandNum=1,pauseCom=1;
    public int nrComenzi=3,nrComenziPauza=3;

    public State(Game gp){
        this.gp=gp;
        getFonts();
        keyH=KeyHandler.getInstance(gp);
    }
    public abstract void update();
    public abstract void draw(Graphics g);

    public void drawGame(Graphics g){
        gp.tileM.draw(g);
        // Objects
        for(int i = 0; i < gp.obj.length;i++){
            if(gp.obj[i]!=null){
                gp.obj[i].draw(g);
            }
        }

        //monstri
        for(int i = 0; i < gp.monsters.length;i++){
            if(gp.monsters[i]!=null){
                gp.monsters[i].draw(g);
            }
        }
        for (int i =0;i<gp.projectileList.size(); i++) {
            gp.projectileList.get(i).draw(g);
        }

        // Personajul
        gp.isaac.draw(g);
    }
    public void getFonts(){
        InputStream is = getClass().getResourceAsStream("/font/upheavtt.ttf");
        try {
            isaacFont = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        font_40=isaacFont.deriveFont(30f);
        is = getClass().getResourceAsStream("/font/pixelfontbold.ttf");
        try {
            pixelFont = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }
    public void drawGameUI(){
        drawHearts();
        drawKeys();
        drawCoins();
        drawMinimap();
    }
    public void drawHearts(){
        int x=20;
        int y=20;
        int i;
        for(i=0;i<gp.isaac.health/2;i++){
            image=setup("/textures/objects/hearts/full_heart.png");
            g.drawImage(image,x,y,image.getWidth()*3,image.getHeight()*3,null);
            x=x+image.getWidth()+25;
        }
        double dHealth=gp.isaac.health;
        if(dHealth/2>gp.isaac.health/2){
            image=setup("/textures/objects/hearts/half_heart.png");
            g.drawImage(image,x,y,image.getWidth()*3,image.getHeight()*3,null);
            x=x+image.getWidth()+25;
        }
        if(dHealth/2==gp.isaac.health/2 && gp.isaac.health!=gp.isaac.maxLife){
            image=setup("/textures/objects/hearts/empty_heart.png");
            g.drawImage(image,x,y,image.getWidth()*3,image.getHeight()*3,null);
            x=x+image.getWidth()+25;
        }
        for(i=gp.isaac.health/2+1;i<gp.isaac.maxLife/2;i++){
            image=setup("/textures/objects/hearts/empty_heart.png");
            g.drawImage(image,x,y,image.getWidth()*3,image.getHeight()*3,null);
            x=x+image.getWidth()+25;
        }
    }
    public void drawKeys(){
        int x=25;
        int y=55;
        image=setup("/textures/hud/keys.png");
        g.drawImage(image,x,y,image.getWidth()*3,image.getHeight()*3,null);
        x=60;
        y=82;
        g.setFont(pixelFont);
        g.setColor(Color.BLACK);
        g.setFont(g.getFont().deriveFont(Font.PLAIN, 20f));
        g.drawString(Integer.toString(gp.isaac.keys),x,y);
        x-=2;
        y-=1;
        g.setColor(Color.WHITE);
        g.setFont(g.getFont().deriveFont(Font.PLAIN, 20f));
        g.drawString(Integer.toString(gp.isaac.keys),x,y);
        g.setFont(isaacFont);
    }
    public void drawCoins(){
        int x=23;
        int y=90;
        image=setup("/textures/hud/coins.png");
        g.drawImage(image,x,y,image.getWidth()*3,image.getHeight()*3,null);
        x=60;
        y=116;
        g.setFont(pixelFont);
        g.setColor(Color.BLACK);
        g.setFont(g.getFont().deriveFont(Font.PLAIN, 20f));
        g.drawString(Integer.toString(gp.isaac.coins),x,y);
        x-=2;
        y-=1;
        g.setColor(Color.WHITE);
        g.setFont(g.getFont().deriveFont(Font.PLAIN, 20f));
        g.drawString(Integer.toString(gp.isaac.coins),x,y);
        g.setFont(isaacFont);
    }
    public void drawMinimap(){
        int x=27*48;
        int y=20;
        switch (gp.nivel){
            case 0 : {
                switch(gp.camera){
                    case 0 :
                        image=setup("/textures/hud/minimap/0.png");
                        break;
                    case 1 :
                        image=setup("/textures/hud/minimap/1.png");
                        break;
                    case 2 :
                        image=setup("/textures/hud/minimap/2.png");
                        break;
                    case 3 :
                        image=setup("/textures/hud/minimap/3.png");
                        break;
                    case 4 :
                        image=setup("/textures/hud/minimap/4.png");
                        break;
                    case 5 :
                        image=setup("/textures/hud/minimap/5.png");
                        break;
                    case 6 :
                        image=setup("/textures/hud/minimap/6.png");
                        break;
                    case 7 :
                        image=setup("/textures/hud/minimap/7.png");
                        break;
                    case 19,17 :
                        image=setup("/textures/hud/minimap/fara.png");
                        break;
                    case 18 :
                        image=setup("/textures/hud/minimap/itm.png");
                        break;
                }
                break;
            }
        }
        //image=setup("/textures/hud/coins.png");
        g.drawImage(image,x,y,image.getWidth()*3,image.getHeight()*3,null);
    }
    public BufferedImage setup(String path) {
        BufferedImage rez=null;
        try {
            rez = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rez;
    }
}
