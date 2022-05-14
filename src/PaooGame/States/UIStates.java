package PaooGame.States;

import PaooGame.Game;

public class UIStates {
    public State state;
    public Game gp;

    public UIStates(Game gp){
        this.gp=gp;
        this.state=gp.titleState;
    }
    public void setState(State state){
        this.state=state;
    }
    public State getState(){
        return this.state;
    }
}
