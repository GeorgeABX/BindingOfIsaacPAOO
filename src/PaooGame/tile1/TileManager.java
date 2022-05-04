package PaooGame.tile1;

import PaooGame.Game;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {
    Game gp;
    public Graphics g;
    public BufferedImage background,image;
    public tile1 doorUp, doorDown, doorLeft, doorRight, trapDoor;
    public boolean doorUpTrue, doorDownTrue, doorLeftTrue, doorRightTrue;
    public tile1[] tile;
    public int k=-1;
    public int cols,rows;
    public int [][]mapTileNum;

    public int []nivComplet=new int[20];

    int x,y;

    //coordonate pentru usi
    int xMijloc, yMijloc, ySus, yJos, xStanga, xDreapta;

    public TileManager(Game gp){
        this.gp=gp;
        tile=new tile1[100];
        mapTileNum=new int[32][18];
        getTileImage();
        loadMap("/maps/basement1.txt");

    }
    public BufferedImage setup(String path){
        BufferedImage rez=null;
        try {//textures/tiles/itemDoorDown.png
            rez = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/textures/tiles/" + path + ".png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rez;
    }
    public void setupTile(int i,String path, boolean colis){
        tile[i]=new tile1();
        tile[i].image=setup(path);
        tile[i].collision=colis;
    }
    public void setDimensions(int i,int width, int heigh){
        tile[i].width=width;
        tile[i].height=heigh;
    }
    public void getTileImage(){
        setupTile(++k,"perete1",true);
        setupTile(++k,"floor1",false);

        setupTile(++k,"basementDoorDownOpen",false);
        setDimensions(k,49*2,33*2);
        setupTile(++k,"basementDoorUpOpen",false);
        setDimensions(k,49*2,33*2);
        setupTile(++k,"basementDoorLeftOpen",false);
        setDimensions(k,33*2,49*2);
        setupTile(++k,"basementDoorRightOpen",false);
        setDimensions(k,33*2,49*2);

        setupTile(++k,"basementDoorDownClose",false);
        setDimensions(k,49*2,33*2);
        setupTile(++k,"basementDoorUpClose",false);
        setDimensions(k,49*2,33*2);
        setupTile(++k,"basementDoorLeftClose",false);
        setDimensions(k,33*2,49*2);
        setupTile(++k,"basementDoorRightClose",false);
        setDimensions(k,33*2,49*2);

        setupTile(++k,"shopDoorDownOpen",false);
        setDimensions(k,49*2,34*2);
        setupTile(++k,"shopDoorUpOpen",false);
        setDimensions(k,49*2,34*2);
        setupTile(++k,"shopDoorLeftOpen",false);
        setDimensions(k,34*2,49*2);
        setupTile(++k,"shopDoorRightOpen",false);
        setDimensions(k,34*2,49*2);

        setupTile(++k,"shopDoorDownClose",false);
        setDimensions(k,49*2,34*2);
        setupTile(++k,"shopDoorUpClose",false);
        setDimensions(k,49*2,34*2);
        setupTile(++k,"shopDoorLeftClose",false);
        setDimensions(k,34*2,49*2);
        setupTile(++k,"shopDoorRightClose",false);
        setDimensions(k,34*2,49*2);

        setupTile(++k,"itemDoorDownOpen",false);
        setDimensions(k,49*2,38*2);
        setupTile(++k,"itemDoorUpOpen",false);
        setDimensions(k,49*2,38*2);
        setupTile(++k,"itemDoorLeftOpen",false);
        setDimensions(k,38*2,49*2);
        setupTile(++k,"itemDoorRightOpen",false);
        setDimensions(k,38*2,49*2);

        setupTile(++k,"itemDoorDownClose",false);
        setDimensions(k,49*2,38*2);
        setupTile(++k,"itemDoorUpClose",false);
        setDimensions(k,49*2,38*2);
        setupTile(++k,"itemDoorLeftClose",false);
        setDimensions(k,38*2,49*2);
        setupTile(++k,"itemDoorRightClose",false);
        setDimensions(k,38*2,49*2);

        setupTile(++k,"depthsDoorDownOpen",false);
        setDimensions(k,49*2,33*2);
        setupTile(++k,"depthsDoorUpOpen",false);
        setDimensions(k,49*2,33*2);
        setupTile(++k,"depthsDoorLeftOpen",false);
        setDimensions(k,33*2,49*2);
        setupTile(++k,"depthsDoorRightOpen",false);
        setDimensions(k,33*2,49*2);

        setupTile(++k,"depthsDoorDownClose",false);
        setDimensions(k,49*2,33*2);
        setupTile(++k,"depthsDoorUpClose",false);
        setDimensions(k,49*2,33*2);
        setupTile(++k,"depthsDoorLeftClose",false);
        setDimensions(k,33*2,49*2);
        setupTile(++k,"depthsDoorRightClose",false);
        setDimensions(k,33*2,49*2);

        setupTile(++k,"bossDoorDownOpen",false);
        setDimensions(k,63*2,77*2);

        setupTile(++k,"bossDoorDownClose",false);
        setDimensions(k,63*2,77*2);

        setupTile(++k,"trapdoorOpen",false);
        setDimensions(k,32*2,32*2);
        setupTile(++k,"trapdoorClosed",false);
        setDimensions(k,32*2,32*2);

    }

    public void drawBackground(String name){
        background=setup(name+"_final");
        g.drawImage(background,0,-20,1551,873,null);
        //g.drawImage(background,0,0,1920,1080,null);
    }
    public boolean verificaSus(int camera){
        //System.out.println("x = "+x+"\ty = "+y);
        if(gp.nivelTerminat==1)
            if(x>=708 && x<=762 && y>=66 && y <=90){
                System.out.println("teleport sus");
                gp.camera=camera;
                gp.isaac.setPozitie(gp.actualScreenWidth/2-gp.isaac.entityWidth/2,620);
                return true;
            }
        return false;
    }
    public boolean verificaJos(int camera){
        //System.out.println("x = "+x+"\ty = "+y);
        if(gp.nivelTerminat==1)
            if(x>=726 && x<=762 && y>=628){
                System.out.println("teleport jos");
                gp.camera=camera;
                gp.isaac.setPozitie(gp.actualScreenWidth/2-gp.isaac.entityWidth/2,115);
                return true;
            }
        return false;
    }
    public boolean verificaStanga(int camera){
        //System.out.println("x = "+x+"\ty = "+y);
        if(gp.nivelTerminat==1)
            if(x<=235 && y>=340 && y <=400){
                System.out.println("teleport stanga");
                gp.camera=camera;
                gp.isaac.setPozitie(1200,gp.actualScreenHeight/2-gp.isaac.entityHeight/2-40);
                return true;
            }
        return false;
    }
    public boolean verificaDreapta(int camera){
        //System.out.println("x = "+x+"\ty = "+y);
        if(gp.nivelTerminat==1)
            if(x>=1215 && y>=340 && y <=400){
                System.out.println("teleport dreapta");
                gp.camera=camera;
                gp.isaac.setPozitie(250,gp.actualScreenHeight/2-gp.isaac.entityHeight/2-40);
                return true;
            }
        return false;
    }
    public boolean verificaTrapDoor(int nivel) {
        if (gp.nivelTerminat == 1)
            if (x >= 704 && x <= 764 && y >= 178 && y <= 228) {
                gp.nivel = nivel;
                gp.isaac.setPozitie(gp.actualScreenWidth / 2 - gp.isaac.entityWidth / 2, gp.actualScreenHeight / 2 - gp.isaac.entityHeight / 2 - 40);
                return true;
            }
        return false;
    }
    public boolean verSus, verJos, verStg, verDrt, verTrapdoor;
    public void drawBasement(){
        drawBackground("basement");
        switch (gp.camera){
            case 0 : {
                image=gp.ui.setup("/textures/hud/tutorial.png");
                int x=gp.actualScreenWidth/2-image.getWidth();
                int y=gp.actualScreenHeight/2-image.getHeight();
                g.drawImage(image,x,y,image.getWidth()*2,image.getHeight()*2,null);

                gp.nivelTerminat=1;
                nivComplet[gp.camera]=1;
                drawUsi(0,false,true,false,true);
                //drawUsi(0,true,true,true,true);
                //drawUsi(1,false,false,true,false);
                verSus=verificaSus(1);
                if(verSus){
                    gp.nivelTerminat=0;
                    gp.aSet.setObjects();
                }
                verDrt=verificaDreapta(6);
                if(verDrt){
                    gp.nivelTerminat=0;
                    gp.aSet.setObjects();
                }
                break;
            }
            case 1 : {
                nivComplet[gp.camera]=gp.nivelTerminat;
                drawUsi(0,true,true,false,false);
                //drawUsi(1,false,false,true,false);
                verSus=verificaSus(2);
                if(verSus){
                    gp.nivelTerminat=0;
                    gp.aSet.setObjects();
                }
                verJos=verificaJos(0);
                if(verJos){
                    gp.nivelTerminat=0;
                    gp.aSet.setObjects();
                }
                break;
            }
            case 2 : {
                nivComplet[gp.camera]=gp.nivelTerminat;
                drawUsi(0,true,false,false,true);
                verJos=verificaJos(1);
                if(verJos){
                    gp.nivelTerminat=0;
                    gp.aSet.setObjects();
                }
                verDrt=verificaDreapta(3);
                if(verDrt){
                    gp.nivelTerminat=0;
                    gp.aSet.setObjects();
                }
                break;
            }
            case 3 : {
                nivComplet[gp.camera]=gp.nivelTerminat;
                drawUsi(0,false,false,true,true);
                drawUsi(3,false,true,false,false);
                verificaStanga(2);
                if(verStg){
                    gp.nivelTerminat=0;
                    gp.aSet.setObjects();
                }
                verSus=verificaSus(17);
                if(verSus){
                    gp.nivelTerminat=0;
                    gp.aSet.setObjects();
                }
                verDrt=verificaDreapta(4);
                if(verDrt){
                    gp.nivelTerminat=0;
                    gp.aSet.setObjects();
                }
                break;
            }
            //shop room
            case 17 : {
                nivComplet[gp.camera]=gp.nivelTerminat;
                drawBackground("shop");
                drawUsi(0,true,false,false,false);
                verJos=verificaJos(3);
                if(verJos){
                    gp.nivelTerminat=0;
                    gp.aSet.setObjects();
                }
                break;
            }
            case 4 : {
                nivComplet[gp.camera]=gp.nivelTerminat;
                drawUsi(0,false,false,true,true);
                //drawUsi(3,false,true,false,false);
                verStg=verificaStanga(3);
                if(verStg){
                    gp.nivelTerminat=0;
                    gp.aSet.setObjects();
                }
                verDrt=verificaDreapta(5);
                if(verDrt){
                    gp.nivelTerminat=0;
                    gp.aSet.setObjects();
                }
                break;
            }
            case 5 : {
                nivComplet[gp.camera]=gp.nivelTerminat;
                drawUsi(0,false,false,true,false);
                drawBossDoor();
                verStg=verificaStanga(4);
                if(verStg){
                    gp.nivelTerminat=0;
                    gp.aSet.setObjects();
                }
                    //gp.aSet.setObjects();
                verSus=verificaSus(19);
                if(verSus){
                    gp.nivelTerminat=0;
                    gp.aSet.setObjects();
                }
                break;
            }
            //boss room
            case 19 : {
                nivComplet[gp.camera]=gp.nivelTerminat;
                drawUsi(0,true,false,false,false);
                drawTrapDoor();
                verJos=verificaJos(5);
                if(verJos){
                    gp.nivelTerminat=0;
                    gp.aSet.setObjects();
                }
                verTrapdoor=verificaTrapDoor(1);
                if(verTrapdoor)
                    gp.aSet.setObjects();
                break;
            }
            case 6 : {
                nivComplet[gp.camera]=gp.nivelTerminat;
                drawUsi(0,true,false,true,false);
                //drawUsi(3,false,true,false,false);
                verStg=verificaStanga(0);
                if(verStg){
                    gp.nivelTerminat=0;
                    gp.aSet.setObjects();
                }
                verJos=verificaJos(7);
                if(verJos){
                    gp.nivelTerminat=0;
                    gp.aSet.setObjects();
                }
                break;
            }
            case 7 : {
                nivComplet[gp.camera]=gp.nivelTerminat;
                drawUsi(0,false,true,false,false);
                drawUsi(1,false,false,false,true);
                verSus=verificaSus(6);
                if(verSus){
                    gp.nivelTerminat=0;
                    gp.aSet.setObjects();

                }
                verDrt=verificaDreapta(18);
                if(verDrt){
                    gp.nivelTerminat=0;
                    gp.aSet.setObjects();
                }
                break;
            }
            //item room
            case 18 : {
                gp.nivelTerminat=1;
                nivComplet[gp.camera]=gp.nivelTerminat;
                drawUsi(1,false,false,true,false);
                verStg=verificaStanga(7);
                if(verStg){
                    gp.nivelTerminat=0;
                    gp.aSet.setObjects();
                }
                break;
            }
        }
    }
    public void drawCaves(){
        drawBackground("caves");
        switch (gp.camera){
            case 0 : {
                //drawUsi(0,true,false,true,true);
                //drawUsi(1,false,false,false,false);
                drawBossDoor();
                break;
            }
        }
    }

    public void drawDepths(){
        drawBackground("depths");
        switch (gp.camera) {
            case 0 : {
                drawUsi(2, false, false, true, true);
                //drawUsi(1, false, false, false, false);
                drawBossDoor();
                break;
            }
        }
    }

    public void draw(Graphics g){
        this.g=g;
        this.x=gp.isaac.x;
        this.y=gp.isaac.y;

        //System.out.println("Nivel: "+gp.nivel+",   Camera: "+gp.camera);
//        System.out.println("nivel terminat : "+gp.nivelTerminat);
        /*System.out.print("\n");
        for(int i=0;i<nivComplet.length;i++){
            System.out.print(i+":"+nivComplet[i]+", ");
        }*/
        switch (gp.nivel) {
            case 0 : drawBasement(); break;
            case 1 : drawCaves(); break;
            case 2 : drawDepths(); break;
        }
        //drawTiles();
    }

    public void drawUsi(int type, boolean doorDownB, boolean doorUpB, boolean doorLeftB, boolean doorRightB){
        switch (type) {
            //basement,caves doors
            case 0 : {
                switch (gp.nivelTerminat){
                    case 1 : {
                        doorUp = tile[2];
                        doorDown = tile[3];
                        doorLeft = tile[4];
                        doorRight = tile[5];
                        break;
                    }
                    case 0 : {
                        doorUp = tile[6];
                        doorDown = tile[7];
                        doorLeft = tile[8];
                        doorRight = tile[9];
                        break;
                    }
                }
                break;
            }
            //item room doors
            case 1 : {
                switch (gp.nivelTerminat){
                    case 1 : {
                        doorUp = tile[18];
                        doorDown = tile[19];
                        doorLeft = tile[20];
                        doorRight = tile[21];
                        break;
                    }
                    case 0 : {
                        doorUp = tile[22];
                        doorDown = tile[23];
                        doorLeft = tile[24];
                        doorRight = tile[25];
                        break;
                    }
                }
                break;
            }
            //depths doors
            case 2 : {
                switch (gp.nivelTerminat){
                    case 1 : {
                        doorUp = tile[26];
                        doorDown = tile[27];
                        doorLeft = tile[28];
                        doorRight = tile[29];
                        break;
                    }
                    case 0 : {
                        doorUp = tile[30];
                        doorDown = tile[31];
                        doorLeft = tile[32];
                        doorRight = tile[33];
                        break;
                    }
                }
                break;
            }
            //shop doors
            case 3 : {
                switch (gp.nivelTerminat){
                    case 1 : {
                        doorUp = tile[10];
                        doorDown = tile[11];
                        doorLeft = tile[12];
                        doorRight = tile[13];
                        break;
                    }
                    case 0 : {
                        doorUp = tile[14];
                        doorDown = tile[15];
                        doorLeft = tile[16];
                        doorRight = tile[17];
                        break;
                    }
                }
                break;
            }
            //boss door
            case 4 : {
                switch (gp.nivelTerminat){
                    case 1 : {
                        doorUp = tile[34];
                        break;
                    }
                    case 0 : {
                        doorUp = tile[35];
                        break;
                    }
                }
                break;
            }
        }
        if(doorDownB){
            setCoordonateUsi(doorDown);
            g.drawImage(doorDown.image,xMijloc,yJos,doorDown.width,doorDown.height,null);
        }
        if(doorUpB){
            setCoordonateUsi(doorUp);
            g.drawImage(doorUp.image,xMijloc,ySus,doorUp.width,doorUp.height,null);
        }
        if(doorLeftB){
            setCoordonateUsi(doorLeft);
            g.drawImage(doorLeft.image,xStanga,yMijloc,doorLeft.width,doorLeft.height,null);
        }
        if(doorRightB){
            setCoordonateUsi(doorRight);
            g.drawImage(doorRight.image,xDreapta,yMijloc,doorRight.width,doorRight.height,null);
        }
    }
    public void drawBossDoor(){
        drawUsi(4,false,true,false,false);
    }
    public void drawTrapDoor(){
        switch (gp.nivelTerminat){
            case 1 : {
                trapDoor=tile[36];
                break;
            }
            case 0 : {
                trapDoor=tile[37];
                break;
            }
        }
        int x=gp.actualScreenWidth/2-trapDoor.width/2;
        int y=5*48;
        g.drawImage(trapDoor.image,x,y,trapDoor.width,trapDoor.height,null);
    }
    public void loadMap(String path){
        int linii=18;
        int coloane=32;
        try{
            InputStream is=getClass().getResourceAsStream(path);
            BufferedReader br=new BufferedReader(new InputStreamReader(is));
            int col=0;
            int row=0;
            while(col<coloane && row <linii) {
                String line=br.readLine();
                while(col< coloane){
                    String []numbers=line.split(" ");
                    int num=Integer.parseInt(numbers[col]);
                    mapTileNum[col][row]=num;
                    col++;
                }
                if(col==coloane){
                    col=0;
                    row++;
                }
            }
            br.close();
        }catch (Exception e){
        }
    }
    public void drawTiles(){
        int col=0;
        int row=0;
        int x=0;
        int y=0;
        while(col<15 &&row<13){
            int tileNum=mapTileNum[col][row];
            //g.drawImage(tile[tileNum].image,x,y,tile[tileNum].image.getWidth(),tile[tileNum].image.getHeight(),null);
            col++;
            x+=48;
            if(col==15){
                col=0;
                x=0;
                row++;
                y+=48;
            }
        }
    }
    public int getMiddleOrizontala(int i){
        return gp.actualScreenWidth/2-tile[i].width/2;
    }
    public int getMiddleVerticala(int i){
        return gp.actualScreenHeight/2-tile[i].height/2-20;
    }
    public int getMiddleOrizontala(tile1 tile){
        return gp.actualScreenWidth/2-tile.width/2;
    }
    public int getMiddleVerticala(tile1 tile){
        return gp.actualScreenHeight/2-tile.height/2-20;
    }
    public void setCoordonateUsi(int i){
        xMijloc=getMiddleOrizontala(i);
        yMijloc=getMiddleVerticala(i);
        ySus = gp.tileSize+10;
        yJos=15*48-12;
        xStanga=48*4;
        xDreapta=26*48+38;
    }
    public void setCoordonateUsi(tile1 i){
        xMijloc=getMiddleOrizontala(i);
        yMijloc=getMiddleVerticala(i);
        ySus = gp.tileSize+10;
        yJos=15*48-12;
        xStanga=48*4;
        xDreapta=26*48+38;
    }
}
