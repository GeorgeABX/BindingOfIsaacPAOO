package PaooGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    private static KeyHandler instance = null;
    public boolean []keys;
    private Game gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed,escPressed,spacePressed,enterPressed;
    public boolean shotUpPressed, shotDownPressed, shotLeftPressed, shotRightPressed, shotReleased;

    private KeyHandler(Game gp) {
        this.gp = gp;
        keys=new boolean[255];
    }

    public static KeyHandler getInstance(Game gp) {
        if (instance == null) {
            instance = new KeyHandler(gp);
        }
        return instance;
    }
    public void update() {
        upPressed = keys[KeyEvent.VK_W];
        downPressed = keys[KeyEvent.VK_S];
        leftPressed = keys[KeyEvent.VK_A];
        rightPressed = keys[KeyEvent.VK_D];
        spacePressed = keys[KeyEvent.VK_SPACE];
        escPressed = keys[KeyEvent.VK_ESCAPE];
        enterPressed = keys[KeyEvent.VK_ENTER];
        shotUpPressed = keys[KeyEvent.VK_UP];
        shotDownPressed = keys[KeyEvent.VK_DOWN];
        shotLeftPressed = keys[KeyEvent.VK_LEFT];
        shotRightPressed = keys[KeyEvent.VK_RIGHT];
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        keys[code] = true;
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

        if (gp.uiStates.getState() == gp.playState) {
            if (code == KeyEvent.VK_ESCAPE) {
                gp.uiStates.setState(gp.pauseState);
                gp.uiStates.state.pauseCom = 1;
                gp.uiStates.state.titleScreenState = 1;
                gp.uiStates.state.commandNum = 1;
            }
        }
        if (gp.uiStates.getState() == gp.pauseState) {
            if (code == KeyEvent.VK_ENTER) {
                switch (gp.uiStates.getState().pauseCom) {
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
            if (code == KeyEvent.VK_ESCAPE) {
                gp.uiStates.setState(gp.playState);
            }
            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                gp.uiStates.getState().pauseCom--;
                if (gp.uiStates.getState().pauseCom < 0) {
                    gp.uiStates.getState().pauseCom = gp.uiStates.getState().nrComenziPauza - 1;
                }
            }
            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                gp.uiStates.getState().pauseCom++;
                if (gp.uiStates.getState().pauseCom > gp.uiStates.getState().nrComenziPauza - 1) {
                    gp.uiStates.getState().pauseCom = 0;
                }
            }
        }
        if (gp.uiStates.getState() == gp.titleState){
            switch (gp.uiStates.state.titleScreenState){
                case 0 : {
                    if(code == KeyEvent.VK_ESCAPE){
                        System.exit(0);
                    }
                    if(code == KeyEvent.VK_SPACE){
                        gp.uiStates.state.titleScreenState=1;
                    }
                    break;
                }
                case 1 : {
                    if(code == KeyEvent.VK_ESCAPE){
                        gp.uiStates.state.titleScreenState = 0;
                    }
                    if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
                        gp.uiStates.state.commandNum--;
                        if (gp.uiStates.state.commandNum < 0) {
                            gp.uiStates.state.commandNum = gp.uiStates.state.nrComenzi - 1;
                        }
                    }
                    if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
                        gp.uiStates.state.commandNum++;
                        if (gp.uiStates.state.commandNum > gp.uiStates.state.nrComenzi - 1) {
                            gp.uiStates.state.commandNum = 0;
                        }
                    }
                    if(code == KeyEvent.VK_ENTER){
                        switch (gp.uiStates.state.commandNum){
                            case 0 : gp.uiStates.state.titleScreenState = 2; break;
                            case 1 : gp.uiStates.setState(gp.playState); break;
                            case 2 : break;
                        }
                    }
                    break;
                }
                case 2 : {
                    if(code == KeyEvent.VK_ESCAPE){
                        gp.uiStates.state.titleScreenState = 1;
                    }
                    if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT){
                        gp.personaj--;
                        if (gp.personaj < 0) {
                            gp.personaj = gp.nrPersonaje - 1;
                        }
                        gp.isaac.setPlayer(gp.personaj);
                    }
                    if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT){
                        gp.personaj++;
                        if (gp.personaj > gp.nrPersonaje - 1) {
                            gp.personaj = 0;
                        }
                        gp.isaac.setPlayer(gp.personaj);
                    }
                    if(code == KeyEvent.VK_ENTER){
                        gp.uiStates.setState(gp.playState);
                        gp.setNewGame();
                    }
                    break;
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        keys[code]=false;
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
            shotReleased = true;
        }
        if (code == KeyEvent.VK_DOWN) {
            shotDownPressed = false;
            shotReleased = true;
        }
        if (code == KeyEvent.VK_LEFT) {
            shotLeftPressed = false;
            shotReleased = true;
        }
        if (code == KeyEvent.VK_RIGHT) {
            shotRightPressed = false;
            shotReleased = true;
        }
    }
}
