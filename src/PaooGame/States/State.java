package PaooGame.States;

import PaooGame.Game;

public abstract class State {
    public Game gp;

    public State(Game gp){
        this.gp=gp;
    }
}
