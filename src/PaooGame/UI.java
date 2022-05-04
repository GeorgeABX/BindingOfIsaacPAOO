package PaooGame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class UI {

    Game gp;
    Graphics g;
    Font isaacFont;
    Font font_40;
    Font pixelFont;
    BufferedImage image,start1,start2,fly1,fly2;
    int spriteCounter=0,spriteCounter2=0;
    int spriteNumber=1,spriteNumber2=1;
    public int commandNum=1,pauseCom=1;
    public int nrComenzi=3,nrComenziPauza=3;
    public int titleScreenState=0; // 0 = meniu principal, 1 = meniu selectie caracter, 2 = setari

    public int health=0;
    public int maxLife=0;
    public int keys=0;
    public int coins=0;
    public int linii=1;

    public void getImage(){
        start1=setup("/textures/menu_textures/start1.png");
        start2=setup("/textures/menu_textures/start2.png");
        fly1=setup("/textures/menu_textures/fly1.png");
        fly2=setup("/textures/menu_textures/fly2.png");
    }

    public  UI(Game gp){
        this.gp=gp;
        getImage();
        getFonts();
    }

    public void draw(Graphics g){
        this.g=g;
        this.health=gp.isaac.health;
        this.maxLife=gp.isaac.maxLife;
        this.keys=gp.isaac.keys;
        this.coins=gp.isaac.coins;
        g.setFont(isaacFont);
        if(gp.gameState==gp.titleState){
            drawTitleScreen();
        }
        if(gp.gameState==gp.playState){
            drawGameUI();
        }
        if(gp.gameState==gp.pauseState){
            drawPause();
        }
        if(gp.gameState==gp.deathState){
            drawDeathScreen();
        }
    }
    public void drawDeathScreen(){
        drawGameUI();
        image=setup("/textures/menu_textures/deathScreen.png");
        int x=0;
        int y=0;
        g.drawImage(image,x,y-45,480*3+100,272*3+100,null);
    }
    public void drawPause(){
        drawGameUI();
        image=setup("/textures/menu_textures/pause/pauseMenu.png");
        int x=0;
        int y=0;
        g.drawImage(image,x,y-45,480*3+100,272*3+100,null);
        switch (pauseCom){
            case 0 : {
                image=setup("/textures/menu_textures/pause/sageataOption.png");
                g.drawImage(image,x,y-45,480*3+100,272*3+100,null);
            } break;
            case 1 : {
                image=setup("/textures/menu_textures/pause/sageataResume.png");
                g.drawImage(image,x,y-45,480*3+100,272*3+100,null);
            } break;
            case 2 : {
                image=setup("/textures/menu_textures/pause/sageataExit.png");
                g.drawImage(image,x,y-45,480*3+100,272*3+100,null);
            } break;
        }
    }
    public void drawGameUI(){
        drawHearts();
        drawKeys();
        drawCoins();
        drawMinimap();
    }
    public void drawTitleScreenState0(){
        if(titleScreenState==0){
            image=setup("/textures/menu_textures/titleScreenBackground.png");
            int x=0;
            int y=0;
            g.drawImage(image,x,y-45,480*3+100,272*3+100,null);
            spriteCounter++;
            if(spriteCounter>=3){
                switch (spriteNumber){
                    case 1 : spriteNumber=2; break;
                    case 2 : spriteNumber=1; break;
                }
                spriteCounter=0;
            }
            switch (spriteNumber){
                case 1 : image=start1; break;
                case 2 : image=start2; break;
            }
            g.drawImage(image,x,y-45,480*3+100,272*3+100,null);
            spriteCounter2++;
            if(spriteCounter2>=1){
                switch (spriteNumber2){
                    case 1 : spriteNumber2=2;
                    case 2 : spriteNumber2=1;
                }
                spriteCounter2=0;
            }
            switch (spriteNumber2){
                case 1 : image=fly1; break;
                case 2 : image=fly2; break;
            }
            g.drawImage(image,x,y-45,480*3+100,272*3+100,null);
            image=setup("/textures/menu_textures/isaacLogo.png");
            g.drawImage(image,x,y-45,480*3+100,272*3+100,null);
        }
    }
    public void drawTitleScreenState1(){
        image=setup("/textures/menu_textures/titleScreenMenu.png");
        int x=0;
        int y=0;
        g.drawImage(image,x,y-45,480*3+100,272*3+100,null);
        switch (commandNum){
            case 0 : {
                image=setup("/textures/menu_textures/sageataNewRun.png");
                g.drawImage(image,x,y-45,480*3+100,272*3+100,null);
            } break;
            case 1 : {
                image=setup("/textures/menu_textures/sageataContinue.png");
                g.drawImage(image,x,y-45,480*3+100,272*3+100,null);
            } break;
            case 2 : {
                image=setup("/textures/menu_textures/sageataOptions.png");
                g.drawImage(image,x,y-45,480*3+100,272*3+100,null);
            } break;
        }
    }
    public void drawTitleScreenState2(){
        image=setup("/textures/menu_textures/characterMenuBackground.png");
        int x=0;
        int y=0;
        g.drawImage(image,x,y-45,480*3+100,272*3+100,null);
        switch (gp.personaj){
            case 0 : image=setup("/textures/menu_textures/characterIsaac.png"); break;

            case 1 : image=setup("/textures/menu_textures/characterAzazel.png"); break;
        }
        g.drawImage(image,x,y-45,480*3+100,272*3+100,null);
    }
    public void drawTitleScreen(){
        if(titleScreenState==0){
            drawTitleScreenState0();
        }
        else if(titleScreenState==1){
            drawTitleScreenState1();
        }
        else if(titleScreenState==2){
            drawTitleScreenState2();
        }
    }
    /*public void drawTitleScreen(){
        //Fundal
        if(titleScreenState==0){
            drawTitleScreenState0();
            g.setColor(new Color(238,216,203));
            g.fillRect(0,0,gp.screenWidth,gp.screenHeight);
            //Logo
            String text="";
            //titleLogo();
            image=setup("/textures/menu_textures/titleScreen1.png");
            int x=gp.actualScreenWidth/2-image.getWidth()/2;
            int y=gp.tileSize*2;
            g.drawImage(image,x,y,image.getWidth(), image.getHeight(),null);
            //Meniu
            g.setFont(g.getFont().deriveFont(Font.PLAIN, 30f));
            g.setColor(new Color(69,57,59));

            text="CONTINUE";
            x=getXCenterText(text);
            y+=gp.tileSize*5;
            g.drawString(text,x,y);
            if(commandNum==0){
                g.drawString(">",x-gp.tileSize/2,y);
            }

            text="NEW GAME";
            x=getXCenterText(text);
            y+=gp.tileSize;
            g.drawString(text,x,y);
            if(commandNum==1){
                g.drawString(">",x-gp.tileSize/2,y);
            }

            text="LOAD GAME";
            x=getXCenterText(text);
            y+=gp.tileSize;
            g.drawString(text,x,y);
            if(commandNum==2){
                g.drawString(">",x-gp.tileSize/2,y);
            }

            text="SETTINGS";
            x=getXCenterText(text);
            y+=gp.tileSize;
            g.drawString(text,x,y);
            if(commandNum==3){
                g.drawString(">",x-gp.tileSize/2,y);
            }

            text="QUIT";
            x=getXCenterText(text);
            y+=gp.tileSize;
            g.drawString(text,x,y);
            if(commandNum==4){
                g.drawString(">",x-gp.tileSize/2,y);
            }

        }
        else if(titleScreenState==1){
            g.setColor(new Color(238,216,203));
            g.fillRect(0,0,gp.screenWidth,gp.screenHeight);

            g.setFont(g.getFont().deriveFont(Font.PLAIN, 30f));
            g.setColor(new Color(69,57,59));

            String text="Who am I ?";
            int x = getXCenterText(text);
            int y = gp.tileSize*2;
            g.drawString(text,x,y);

            switch (gp.personaj){
                case 0:
                    x=gp.actualScreenWidth/2-image.getWidth();
                    y=gp.tileSize*6;
                    //isaacCharacter();
                    image=setup("/textures/isaac/isaac_down1.png");
                    g.drawImage(image,x,y,image.getWidth()*2,image.getHeight()*2,null);
                    text="< Isaac >";
                    x=getXCenterText(text);
                    y+=gp.tileSize*(2.5);
                    g.drawString(text,x,y);
                    break;
                case 1:
                    x=gp.actualScreenWidth/2-image.getWidth();
                    y=gp.tileSize*6;
                    int yy=y-12;
                    //azazelCharacter();
                    image=setup("/textures/azazel/down1.png");
                    g.drawImage(image,x,yy,image.getWidth()*2,image.getHeight()*2,null);
                    text="< Azazel >";
                    x=getXCenterText(text);
                    y+=gp.tileSize*(2.5);
                    g.drawString(text,x,y);
                    break;
            }


        }


    }*/

    public void drawHearts(){
        int x=20;
        int y=20;
        int i;
        for(i=0;i<health/2;i++){
            image=setup("/textures/objects/hearts/full_heart.png");
            g.drawImage(image,x,y,image.getWidth()*3,image.getHeight()*3,null);
            x=x+image.getWidth()+25;
        }
        double dHealth=health;
        if(dHealth/2>health/2){
            image=setup("/textures/objects/hearts/half_heart.png");
            g.drawImage(image,x,y,image.getWidth()*3,image.getHeight()*3,null);
            x=x+image.getWidth()+25;
        }
        if(dHealth/2==health/2 && health!=maxLife){
            image=setup("/textures/objects/hearts/empty_heart.png");
            g.drawImage(image,x,y,image.getWidth()*3,image.getHeight()*3,null);
            x=x+image.getWidth()+25;
        }
        for(i=health/2+1;i<maxLife/2;i++){
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
        g.drawString(Integer.toString(keys),x,y);
        x-=2;
        y-=1;
        g.setColor(Color.WHITE);
        g.setFont(g.getFont().deriveFont(Font.PLAIN, 20f));
        g.drawString(Integer.toString(keys),x,y);
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
        g.drawString(Integer.toString(coins),x,y);
        x-=2;
        y-=1;
        g.setColor(Color.WHITE);
        g.setFont(g.getFont().deriveFont(Font.PLAIN, 20f));
        g.drawString(Integer.toString(coins),x,y);
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
    public int getXCenterText(String text){
        int length=(int)g.getFontMetrics().getStringBounds(text,g).getWidth();
        return gp.actualScreenWidth/2-length/2;
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
