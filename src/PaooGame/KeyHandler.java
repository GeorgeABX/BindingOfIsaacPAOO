package PaooGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    private static KeyHandler instance = null;

    private Game gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public boolean shotUpPressed,shotDownPressed,shotLeftPressed,shotRightPressed,shotReleased;

    private KeyHandler(Game gp) {
        this.gp = gp;
    }
    public static KeyHandler getInstance(Game gp){
        if(instance==null){
            instance=new KeyHandler(gp);
        }
        return instance;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        // Title State
        if (gp.gameState == gp.titleState) {
            switch (gp.ui.titleScreenState){
                //start menu
                case 0 : {
                    switch (code){
                        case KeyEvent.VK_ESCAPE -> System.exit(0);
                        case KeyEvent.VK_SPACE -> gp.ui.titleScreenState=1;
                    }
                    break;
                }
                //select menu
                case 1 : {
                    switch (code){
                        case KeyEvent.VK_ESCAPE -> gp.ui.titleScreenState=0;
                        case KeyEvent.VK_W, KeyEvent.VK_UP -> {
                            gp.ui.commandNum--;
                            if (gp.ui.commandNum < 0) {
                                gp.ui.commandNum = gp.ui.nrComenzi - 1;
                            }
                        }
                        case KeyEvent.VK_S, KeyEvent.VK_DOWN -> {
                            gp.ui.commandNum++;
                            if (gp.ui.commandNum > gp.ui.nrComenzi - 1) {
                                gp.ui.commandNum = 0;
                            }
                        }
                        case KeyEvent.VK_ENTER -> {
                            switch (gp.ui.commandNum){
                                case 0 -> gp.ui.titleScreenState = 2;
                                case 1 -> gp.gameState = gp.playState;
                               // case 2 -> ;
                            }

                        }
                    }
                    break;
                }
                //character select menu
                case 2 : {
                    switch (code){
                        case KeyEvent.VK_ESCAPE -> gp.ui.titleScreenState=1;
                        case KeyEvent.VK_A, KeyEvent.VK_LEFT -> {
                            gp.personaj--;
                            if (gp.personaj < 0) {
                                gp.personaj = gp.nrPersonaje - 1;
                            }
                            gp.isaac.setPlayer(gp.personaj);
                        }
                        case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> {
                            gp.personaj++;
                            if (gp.personaj > gp.nrPersonaje - 1) {
                                gp.personaj = 0;
                            }
                            gp.isaac.setPlayer(gp.personaj);
                        }
                        case KeyEvent.VK_ENTER -> {
                            gp.gameState = gp.playState;
                            gp.setNewGame();
                        }
                    }
                    break;
                }
            }
        }
        // Death State
        if(gp.gameState==gp.deathState){
            if (code == KeyEvent.VK_ESCAPE) {
                //gp.gameState = gp.titleState;
                //gp.ui.titleScreenState=1;
                //gp.ui.commandNum=1;
                gp.setNewGame();
                gp.gameState = gp.titleState;
                gp.ui.titleScreenState = 1;
                gp.ui.commandNum = 1;
            }
        }
        // Pause State
        if(gp.gameState == gp.pauseState){
            switch (code) {
                case KeyEvent.VK_W, KeyEvent.VK_UP -> {
                    gp.ui.pauseCom--;
                    if (gp.ui.pauseCom < 0) {
                        gp.ui.pauseCom = gp.ui.nrComenziPauza - 1;
                    }
                }
                case KeyEvent.VK_S, KeyEvent.VK_DOWN -> {
                    gp.ui.pauseCom++;
                    if (gp.ui.pauseCom > gp.ui.nrComenziPauza - 1) {
                        gp.ui.pauseCom = 0;
                    }
                }
                case KeyEvent.VK_ENTER -> {
                    switch (gp.ui.pauseCom){
                        case 0 : {break;}
                        case 1 : gp.gameState = gp.playState; break;
                        case 2 : gp.gameState=gp.titleState; break;
                    }

                }
            }
        }
        // Play State
        if (gp.gameState == gp.playState) {
            if (code == KeyEvent.VK_W) {
                upPressed = true;
            }
            if (code == KeyEvent.VK_S) {
                downPressed = true;
            }
            if (code == KeyEvent.VK_A) {
                leftPressed = true;
            }
            if (code == KeyEvent.VK_D) {
                rightPressed = true;
            }
            if (code == KeyEvent.VK_UP) {
                shotUpPressed = true;
            }
            if (code == KeyEvent.VK_DOWN) {
                shotDownPressed = true;
            }
            if (code == KeyEvent.VK_LEFT) {
                shotLeftPressed = true;
            }
            if (code == KeyEvent.VK_RIGHT) {
                shotRightPressed = true;
            }
            if (code == KeyEvent.VK_ESCAPE) {
                //gp.gameState = gp.titleState;
                //gp.ui.titleScreenState=1;
                //gp.ui.commandNum=1;
                gp.gameState = gp.pauseState;
                gp.ui.pauseCom = 1;
                gp.ui.titleScreenState=1;
                gp.ui.commandNum=1;
            }
            // testare
            switch (code) {
                case KeyEvent.VK_NUMPAD7 -> gp.nivelTerminat = 0;
                case KeyEvent.VK_NUMPAD8 -> gp.nivelTerminat = 1;
                case KeyEvent.VK_NUMPAD9 -> {
                    for (int i = 0; i < gp.monsters.length; i++) gp.monsters[i] = null;
                }
                case KeyEvent.VK_NUMPAD4 -> gp.isaac.coins = 10;
                /*
                case KeyEvent.VK_NUMPAD5 ->
                case KeyEvent.VK_1 ->
                 */
            }
        }
    }

        @Override
        public void keyReleased (KeyEvent e){
            int code = e.getKeyCode();
            if (code == KeyEvent.VK_W) {
                upPressed = false;
            }
            if (code == KeyEvent.VK_S) {
                downPressed = false;
            }
            if (code == KeyEvent.VK_A) {
                leftPressed = false;
            }
            if (code == KeyEvent.VK_D) {
                rightPressed = false;
            }
            if (code == KeyEvent.VK_UP) {
                shotUpPressed = false;
                shotReleased=true;
            }
            if (code == KeyEvent.VK_DOWN) {
                shotDownPressed = false;
                shotReleased=true;
            }
            if (code == KeyEvent.VK_LEFT) {
                shotLeftPressed = false;
                shotReleased=true;
            }
            if (code == KeyEvent.VK_RIGHT) {
                shotRightPressed = false;
                shotReleased=true;
            }
        }
    }
