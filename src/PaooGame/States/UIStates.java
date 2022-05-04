package PaooGame.States;

import PaooGame.Game;

public class UIStates {
    public State state;
    public Game gp;

    public UIStates(Game gp){
        this.gp=gp;
        this.state=new HomeState(gp);
    }
}
