package it.polimi.isw2019.View;

import it.polimi.isw2019.Utilities.Observable;
import it.polimi.isw2019.Utilities.Observer;
import it.polimi.isw2019.Message.MoveMessage.*;
public abstract class View extends Observable implements Observer<MoveMessage> {

    protected String nickname;
    protected String phrase;
    protected String color;
    protected int idPlayer;
    public abstract void welcome();
    
    public abstract void insertDate();

    public void setNickname(String nickname){
        this.nickname = nickname;
    }

    public void setPhrase(String phrase){
        this.phrase = phrase;
    }

    public abstract void updateView(MoveMessage moveMessage);

    public void chooseGameMode(){

    }

    public void gameView (){

    }

    public void chooseAction(){

    }

    //must be invocated only one and must not change during the execution
     public void setColor(String color){
        this.color = color;
     }


     public abstract void displayStatus();

    @Override
    public void update(MoveMessage moveMessage) {
            updateView(moveMessage);
    }


}
