package PaooGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public Game gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public boolean shotKeyPressed;

    public KeyHandler(Game gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        // Title State
        if (gp.gameState == gp.titleState) {

            if (gp.ui.titleScreenState == 0) {
                if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                    gp.ui.commandNum--;
                    if (gp.ui.commandNum < 0) {
                        gp.ui.commandNum = gp.ui.nrComenzi - 1;
                    }
                }
                if (code == KeyEvent.VK_ESCAPE) {
                    System.exit(0);
                }
                if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                    gp.ui.commandNum++;
                    if (gp.ui.commandNum > gp.ui.nrComenzi - 1) {
                        gp.ui.commandNum = 0;
                    }
                }
                if (code == KeyEvent.VK_ENTER) {
                    //continue
                    if (gp.ui.commandNum == 1) {
                        //gp.gameState = gp.playState;
                    }
                    //new game
                    if (gp.ui.commandNum == 0) {
                        gp.ui.titleScreenState = 1;
                    }
                    //options
                    if (gp.ui.commandNum == 2) {
                        //System.exit(0);
                    }
                }

            }
            if (gp.ui.titleScreenState == 0) {
                if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
                    gp.personaj--;
                    if (gp.personaj < 0) {
                        gp.personaj = gp.nrPersonaje - 1;
                    }
                }
                if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
                    gp.personaj++;
                    if (gp.personaj > gp.nrPersonaje - 1) {
                        gp.personaj = 0;
                    }
                }
                if (code == KeyEvent.VK_ESCAPE) {
                    gp.ui.titleScreenState = 0;
                }
                if (code == KeyEvent.VK_ENTER) {
                //new game

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
                    shotKeyPressed = true;
                }
                if (code == KeyEvent.VK_DOWN) {
                    shotKeyPressed = true;
                }
                if (code == KeyEvent.VK_LEFT) {
                    shotKeyPressed = true;
                }
                if (code == KeyEvent.VK_RIGHT) {
                    shotKeyPressed = true;
                }
                if (code == KeyEvent.VK_ESCAPE) {
                    gp.gameState = gp.titleState;
                }
                // testare
                if (code == KeyEvent.VK_NUMPAD7) {
                    gp.nivelTerminat=0;
                }
                if (code == KeyEvent.VK_NUMPAD8) {
                    gp.nivelTerminat=1;
                }
                if (code == KeyEvent.VK_NUMPAD9) {
                    for (int i = 0; i < gp.monsters.length; i++) {
                        gp.monsters[i]=null;
                    }
                }
                if (code == KeyEvent.VK_NUMPAD4) {
                    gp.isaac.coins=10;
                }
                if (code == KeyEvent.VK_NUMPAD5) {
                }
                if (code == KeyEvent.VK_NUMPAD6) {

                }
                if (code == KeyEvent.VK_1) {
                }
                if (code == KeyEvent.VK_2) {
                }
                if (code == KeyEvent.VK_3) {
                }
                if (code == KeyEvent.VK_4) {
                }
                if (code == KeyEvent.VK_5) {
                }
                if (code == KeyEvent.VK_6) {
                }
                if (code == KeyEvent.VK_7) {
                }
                if (code == KeyEvent.VK_8) {
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
                shotKeyPressed = false;
            }
            if (code == KeyEvent.VK_DOWN) {
                shotKeyPressed = false;
            }
            if (code == KeyEvent.VK_LEFT) {
                shotKeyPressed = false;
            }
            if (code == KeyEvent.VK_RIGHT) {
                shotKeyPressed = false;
            }
        }
    }




